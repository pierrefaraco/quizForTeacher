quizApp.controller('coursController', ["$scope", "$rootScope", "coursRestService", "$cookies","webSocketService",
    function ($scope, $rootScope, coursRestService, $cookies,webSocketService) {
             
       refresh(); 
        
       function refresh (){
             var coursResource = coursRestService.getListCoursWithAuditorStatusResource();
             $scope.listOfCours = coursResource.query({userId: $cookies.get("userId")},function(){              
              //le cours chargé dans mainController n'a pas l'attribust status alors on le remplace ici
             for (var i in $scope.listOfCours){
                    if ($scope.listOfCours[i].id == $rootScope.selectedCours.id )
                        $rootScope.selectedCours = $scope.listOfCours[i];
                     
              }
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
        
        $scope.bigRefresh = function (){
            $rootScope.unSelectCours ();
            $rootScope.init();
            refresh();         
        };

}]);

