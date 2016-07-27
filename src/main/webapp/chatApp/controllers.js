//angular.module("chatApp.controllers",["ui.bootstrap"]);
        
        
chatApp.controller("ChatCtrl", function($scope, ChatService,$uibModal) {
  $scope.messages = [];
  $scope.message = "";
  $scope.max = 140;

  $scope.addMessage = function() {
    ChatService.sendQuestion($scope.message)
    $scope.message = "";
  };

  ChatService.receive().then(null, null, function(question) {    
      $scope.question = question ;
      $scope.openQuestionPresentationModal ('Preview');
  });
  
     $scope.openQuestionPresentationModal = function (action, size) {
            var params = {'action': action};      
            params.question = $scope.question;             
            $uibModal.open({
                animation: true,
                templateUrl: "questionPresentationModal.html",
                controller: "questionPresentationCtrl",
                size: size,    
                scope: $scope,     
                resolve: {
                    params: function () {
                        return params;
                    }
                }
            });
         };
});

chatApp.controller('questionPresentationCtrl', ["$scope", "$uibModalInstance", "params", function ($scope, $uibModalInstance, params) {     
        $scope.cancel = function () {           
            $uibModalInstance.dismiss('cancel');
        };
 }]);