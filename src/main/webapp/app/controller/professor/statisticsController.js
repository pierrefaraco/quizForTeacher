quizApp.controller('statisticsController', ["$scope",  "$cookies", "quizRestService", "statisticRestService",
    function ($scope, $cookies, quizRestService, statisticRestService) {
        
        refresh();
        function refresh() {
            var getListSessionCours = quizRestService.getListSessionCoursResource();
            if($cookies.get("selectedCours") != null && $cookies.get("selectedCours") != "null")
                $scope.sessionsQuizList = getListSessionCours.query({coursId: $cookies.get("selectedCours")});
        };
        
         $scope.showSessionResult =  function (sessionQuiz){
             var getResultsBySession = statisticRestService.getResultsBySessionResource ();
             $scope.resultList =getResultsBySession .get({sessionId: sessionQuiz.id});
        };
        
         $scope.showCoursResult =  function (){
             var getResultsByCours= statisticRestService.getResultsByCoursResource ();
             $scope.resultList =getResultsByCours .get({coursId: $cookies.get("selectedCours")});
        };
    }]);

