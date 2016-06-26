quizApp.controller('mainController',["$scope" , "restService","$cookies", "$location", "AuthSharedService",
    function ($scope,restService,$cookies, $location, AuthSharedService) {

   $scope.logout = function logout(){
        AuthSharedService.logout();
        $location.path('');
    };
}]);
 