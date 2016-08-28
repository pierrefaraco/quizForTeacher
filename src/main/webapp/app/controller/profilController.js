'use strict';
quizApp.controller('profilController', ["$scope", "$rootScope", "userRestService", "$cookies","$q","$location", 
    
    function ($scope, $rootScope, userRestService, $cookies,$q,$location) {
       refresh();
        
       function refresh (){
      
           if ($scope.user != null)
                $scope.user.birthDay = new Date( $scope.user.birthDay );
            
        };

        $scope.updateUser = function () {
            var userRestObject = userRestService.getUserRestObject();
            userRestObject.update({ } , $scope.user , function(res){
               
            },function(response){
                  
            });
        };

        $scope.createUser = function () {
            var unsecuredRestObject = userRestService.getUnsecuredUserRestObject();
            unsecuredRestObject.save({},$scope.user,function(){
                 $location.path('');
            },function(response){
              
            });
            $scope.user = null;
        };
    }]);

