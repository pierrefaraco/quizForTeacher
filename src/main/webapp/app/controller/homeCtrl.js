'use strict';
quizApp.controller('homeCtrl', ["$scope", "userService", "$cookies","AuthSharedService", function ($scope, userService, $cookies,AuthSharedService) {
        var loginRestService = userService.getLoginRest();

        $scope. connected =   $cookies.get("testCookies");
    
        $scope.login = function () {
            AuthSharedService.login($scope.Email, $scope.Password,'false');
            
            
            /*
            loginRestService.get({email: $scope.Email, password: $scope.Password},
                function(user , getResponseHeader){
                     
                      console.log(getResponseHeader);
            
                     $cookies.put("testCookies", "connected");
                     
                     $scope. connected =   $cookies.get("testCookies");
                },
                function(){
                    $cookies.put("testCookies", "not connected");    
                    $scope. connected =   $cookies.get("testCookies");
                });
            
            */
           
           
        };

        $scope.display = function () {
   
                  
        };

           // loginRestService.get({email: $scope.Email, password: $scope.Password});
           // $scope.user.connected = $cookies.get("connected");
           // $('#connected').val($scope.user.connected);
        
   
    }]);



     