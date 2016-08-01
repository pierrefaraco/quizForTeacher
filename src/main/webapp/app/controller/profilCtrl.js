'use strict';
quizApp.controller('profilCtrl', ["$scope", "$rootScope", "userRestClient", "$cookies","$q","$location", 
    
    function ($scope, $rootScope, userRestClient, $cookies,$q,$location) {
       refresh();
        
       function refresh (){
      
           if ($scope.user != null)
                $scope.user.birthDay = new Date( $scope.user.birthDay );
            
        };

        $scope.updateUser = function () {
            var userRestObject = userRestClient.getUserRestObject();
            userRestObject.update({ } , $scope.user , function(res){
               
            },function(response){
                    alert("Error : "+ response.status );
            });
        };

        $scope.createUser = function () {
            var unsecuredRestObject = userRestClient.getUnsecuredUserRestObject();
            unsecuredRestObject.save({},$scope.user,function(){
                 $location.path('');
            });
            $scope.user = null;
        };
    }]);

