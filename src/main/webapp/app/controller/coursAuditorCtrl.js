quizApp.controller('coursAuditorCtrl', ["$scope", "$rootScope", "coursRestClient", "$cookies","webSocketService",
    function ($scope, $rootScope, coursRestClient, $cookies,webSocketService) {
             
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
        
        $scope.selectCours = function (cours) {
            if ($rootScope.selectedCours === cours)
                 unSelectCours ();
            else{
                $rootScope.selectedCours = cours;
                $cookies.put("selectedCours",  $rootScope.selectedCours.id);
                $cookies.put("courStatus",  $rootScope.selectedCours.status);
                var coursRestService = coursRestClient.getWebsocketPoolNumberResource();
                coursRestService.get({coursId: $rootScope.selectedCours.id, userId: $cookies.get("userId")},function(params){
                     $rootScope.poolNumber =  params.poolNumber;  
                     $cookies.put("poolNumber", params.poolNumber);
                     webSocketService.init();
                });    
               
            }
        };
            
        function unSelectCours () {
            $rootScope.selectedCours = null;
            $cookies.put("selectedCours", null);
            $cookies.put("courStatus",  null);
            $cookies.put("poolNumber", null);
        };
}]);

