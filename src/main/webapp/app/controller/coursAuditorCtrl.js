quizApp.controller('coursAuditorCtrl', ["$scope", "$rootScope", "coursRestClient", "$cookies",
    function ($scope, $rootScope, coursRestClient, $cookies) {
             
       refresh();
        
       function refresh (){
             var coursRestService = coursRestClient.getListCoursWithAuditorStatusResource();
             $scope.listOfCours = coursRestService.query({userId: $cookies.get("userId")});    
        };
      
       $scope.subscribe = function (cours){    
           var coursRestService = coursRestClient.getSubscribeResource();
           coursRestService.get({userId:  $cookies.get("userId"),coursId:cours.id}, function () {
               refresh ();
           }, function (response) {
                alert("Error : " + response.status);
           });
        };
        
         $scope.unSubscribe = function (cours){    
           var coursRestService = coursRestClient.getUnSubscribeResource();
           coursRestService.get({userId:  $cookies.get("userId"),coursId:cours.id}, function () {
               refresh ();
           }, function (response) {
                alert("Error : " + response.status);
           });
        };
}]);

