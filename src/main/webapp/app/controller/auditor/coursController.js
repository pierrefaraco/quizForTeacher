quizApp.controller('coursController', ["$scope", "$rootScope", "coursRestService", "$cookies","webSocketService",
    function ($scope, $rootScope, coursRestService, $cookies,webSocketService) {
             
       refresh();
        
       function refresh (){
             var coursResource = coursRestService.getListCoursWithAuditorStatusResource();
             $scope.listOfCours = coursResource.query({userId: $cookies.get("userId")},function(){
       
             });    
        };
      
        $scope.subscribe = function (cours){    
           var subscribeResource = coursRestService.getSubscribeResource();
           subscribeResource.get({userId:  $cookies.get("userId"),coursId:cours.id}, function () {
               refresh ();
           }, function (response) {
                alert("Error : " + response.status);
           });
        };
        
        $scope.unSubscribe = function (cours){    
           var unSubscribeResource = coursRestService.getUnSubscribeResource();
           unSubscribeResource.get({userId:  $cookies.get("userId"),coursId:cours.id}, function () {
               refresh ();
           }, function (response) {
                alert("Error : " + response.status);
           });
        };
        

}]);

