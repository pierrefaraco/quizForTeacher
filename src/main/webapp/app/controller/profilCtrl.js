'use strict';
quizApp.controller('profilCtrl', ["$scope", "$rootScope", "userRestClient", "$cookies","$q","$location", 
    
    function ($scope, $rootScope, userRestClient, $cookies,$q,$location) {

        $scope.updateUser = function () {
            var userRestObject = userRestClient.getUserRestObject();
            userRestObject.update({ } , $scope.user , function(res){
               
            },function(response){
                    alert("Error : "+ response.status );
            });
        };

        $scope.createUser = function () {
            var unsecuredRestObject = userRestClient.getUnsecuredUserRestObject();
            unsecuredRestObject.save({},$scope.user,function(res){
                 $location.path('');
            });
            $scope.user = null;
        };
    }]);

