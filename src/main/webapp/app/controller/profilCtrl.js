'use strict';
quizApp.controller('profilCtrl', ["$scope", "$rootScope", "restService", "$cookies","$q","$location", 
    function ($scope, $rootScope, restService, $cookies,$q,$location) {

        $scope.updateUser = function () {
            var userRestObject = restService.getUserRestObject();
            var user = userRestObject.get({userId: $scope.user.id});
            user.id = $scope.user.id;
            user.firstName = $scope.user.firstName;
            user.lastName = $scope.user.lastName;
            user.password = $scope.user.password;
            user.email = $scope.user.email;
            user.presentation = $scope.user.presentation;
            user.birthDay = $scope.user.birthDay;      
            user.$update({userId: $scope.user.id});
        };


        $scope.createUser = function () {
            var unsecuredRestObject = restService.getUnsecuredUserRestObject();
            var user = unsecuredRestObject.get({userId: "-1"});
            user.firstName = $scope.user.firstName;
            user.lastName = $scope.user.lastName;
            user.password = $scope.user.password;
            user.email = $scope.user.email;
            user.presentation = $scope.user.presentation;
            user.birthDay = $scope.user.birthDay;
            user.$save({userId: '-1'}).then(function (res) {
                if (res === null)
                    return $q.reject("Error, server returned null");
                return res;
            }).then(function (team) {
                console.log('team: ', team);
                $location.path('');
            }, function (err) {
                console.log('err: ', err);
            });
            $scope.user = null;
        };
    }]);

