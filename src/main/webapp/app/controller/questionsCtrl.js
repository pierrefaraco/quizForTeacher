

//var questionsMngmnt = angular.module('questionsMngmnt',[]);

quizApp.controller('questionCtrl',["$scope" , function ($scope) {
    var paramsTopic=$scope.paramsTopic={
        topics : [],
        selectedTopic :{}    
    };
    
    $scope.selectTopic = function (topic) {        
         paramsTopic.selectedTopic = paramsTopic.topics[ findIndexItem(topic,paramsTopic.topics)];
    };
}]);
 
 
 
quizApp.controller('topicCtrl',["$scope", "$uibModal", function ($scope, $uibModal) {
    
    var paramsTopic = $scope.$parent.paramsTopic;
    $scope.animationsEnabled = false;       
    $scope.count = 0;  
    $scope.open = function (action,size) {

        var modalInstance = $uibModal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'myModalContent.html',
            controller: 'topicCtrlForm',
            size: size, 
            resolve: {
                param: function () {
                    return {'paramsTopic':paramsTopic ,'action':action  };
                }
            }
        });
        

    };
    
    $scope.remove = function () 
    {
        var index= findIndexItem (paramsTopic.selectedTopic,paramsTopic.topics);
        paramsTopic.topics[index] = null;
        paramsTopic.selectedTopic = null;
    };
    
    
    
    
    $scope.toggleAnimation = function () {
        $scope.animationsEnabled = !$scope.animationsEnabled;
     
    }; 
}]);

// Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

quizApp.controller('topicCtrlForm',["$scope", "$uibModalInstance", "param",function ($scope, $uibModalInstance, param) {
  
  var action = param.action;
  var topics  = param.paramsTopic.topics;
  var index;
  if(action==='edit')
  {
      $scope.name = param.paramsTopic.selectedTopic.name;
      $scope.description = param.paramsTopic.selectedTopic.description ; 
      index=findIndexItem (param.paramsTopic.selectedTopic,param.paramsTopic.topics);
  }
	
  var ok = $scope.ok = function () {
    $uibModalInstance.close();
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };
  

       
  $scope.validate = function () {
          
      if ( !$scope.name .length  ) {
          return;
      }
      
    param.paramsTopic.selectedTopic = {
          name :  $scope.name  ,
          description : $scope.description
      };
      if(action==='edit')
        topics[index] = param.paramsTopic.selectedTopic;
      else
        topics.push( param.paramsTopic.selectedTopic );    
  
      ok();
     
  };
}]);


var findIndexItem = function(item,items)
{
    for (var iter = 0; iter < items.length ; iter++) {
            if (item=== items[iter]) {
                return iter;
            }
            
        }
}
;