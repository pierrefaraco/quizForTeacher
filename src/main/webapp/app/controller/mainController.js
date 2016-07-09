quizApp.controller('mainController',["$scope" , "$cookies", "$location", "AuthSharedService",
    function ($scope,$cookies, $location, AuthSharedService) {

   $scope.logout = function logout(){
        AuthSharedService.logout();
        $location.path('');
    };
}]);
 