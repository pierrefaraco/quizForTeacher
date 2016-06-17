'use strict';
quizApp.controller('profilCtrl', ["$scope", "userService", function ($scope, userService) {

        var userRest = userService.getUserRest();
        var user = $scope.user = userRest.get({});

        $scope.updateUser = function () {   
                user .$update({userId:$scope.user.id});
        };
        
        
        $scope.createUser = function () {     
                user.accountType = null;
                user.$save({userId:'-1'});             
        };
        
    

    }]);

