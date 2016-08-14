quizApp.controller('coursAuditorCtrl', ["$scope", "$rootScope", "coursRestClient", "$cookies","webSocketService",
    function ($scope, $rootScope, coursRestClient, $cookies,webSocketService) {
             
       refresh();
        
       function refresh (){
             var coursRestService = coursRestClient.getListCoursWithAuditorStatusResource();
             $scope.listOfCours = coursRestService.query({userId: $cookies.get("userId")},function(){
       
             });    
        };
      
        $scope.subscribe = function (cours){    
           var subscribeResource = coursRestClient.getSubscribeResource();
           subscribeResource.get({userId:  $cookies.get("userId"),coursId:cours.id}, function () {
               refresh ();
           }, function (response) {
                alert("Error : " + response.status);
           });
        };
        
        $scope.unSubscribe = function (cours){    
           var unSubscribeResource = coursRestClient.getUnSubscribeResource();
           unSubscribeResource.get({userId:  $cookies.get("userId"),coursId:cours.id}, function () {
               refresh ();
           }, function (response) {
                alert("Error : " + response.status);
           });
        };
        

}]);

