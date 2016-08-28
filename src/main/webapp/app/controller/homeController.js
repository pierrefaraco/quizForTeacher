'use strict';
quizApp.controller('homeController',
        [
            "$scope",
            "$rootScope",
            "$location",
            "AuthSharedService",
            function ($scope, $rootScope, $location, AuthSharedService) {

                $scope.login = function () {
                    AuthSharedService.login($scope.Email, $scope.Password,
                            'false');
                };

                $scope.signup = function () {
                    $location.path("/ProfilUI");
                };
            }]);
