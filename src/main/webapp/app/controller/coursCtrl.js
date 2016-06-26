quizApp.controller('CoursController',["$scope" ,  "$rootScope","restService","$cookies", function ($scope,$rootScope,restService,$cookies) {
       
        var coursRestService = restService.getCoursRestObject();
	$scope.listOfCours  = coursRestService.query({userId : $cookies.get("userId")});
        
         
        $scope.selectCours = function (cours) {        
                $rootScope.selectedCours = cours;
                $cookies.put("selectedCours", $scope.selectedCours.id);
        };
  
}]);
 