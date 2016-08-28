quizApp.controller('statisticsController', ["$scope", "$rootScope", "$cookies", "quizRestService", "statisticRestService",
    function ($scope, $rootScope, $cookies, quizRestService, statisticRestService) {
       refresh();
        function refresh() {
            var getListSessionCours = quizRestService.getListSessionCoursResource();
            if($cookies.get("selectedCours") != null && $cookies.get("selectedCours") != "null")
                $scope.sessionsQuizList = getListSessionCours.query({coursId: $cookies.get("selectedCours")});
        };
        
         $scope.showSessionResult =  function (sessionQuiz){
             var getResultsBySession = statisticRestService.getResultsByUserAndSessionResource ();
             $scope.resultList =getResultsBySession .get({userId : $cookies.get("userId"),sessionId: sessionQuiz.id});
        };
        
         $scope.showCoursResult =  function (){
             var getResultsByCours= statisticRestService.getResultsByUserAndCoursResource ();
             $scope.resultList =getResultsByCours .get({userId : $cookies.get("userId"),coursId: $cookies.get("selectedCours")});
        };
      
        
}]);

