quizApp.controller('mainCtrl',["$scope" ,"$window", "$location", "AuthSharedService",
    function ($scope,$window, $location, AuthSharedService) {

   $scope.logout = function logout(){
        AuthSharedService.logout();
        var host = $location.host();
        var port = $location.port();
        $window.location.href = "http://" + host +":"+port + "/quizForTeacher/index.html";
    };
}]);
 