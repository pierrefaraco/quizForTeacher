quizApp.controller('statisticsCtrl', ["$scope", "$rootScope", "$cookies", "quizRestClient", "statisticRestClient",
    function ($scope, $rootScope, $cookies, quizRestClient, statisticRestClient) {
        
        refresh();
        function refresh() {
            var getListSessionCours = quizRestClient.getListSessionCoursResource();
            if($cookies.get("selectedCours") != null && $cookies.get("selectedCours") !="")
                $scope.sessionsQuizList = getListSessionCours.query({coursId: $cookies.get("selectedCours")});
        };
        
         $scope.showSessionResult =  function (sessionQuiz){
             var getResultsBySession = statisticRestClient.getResultsBySessionResource ();
             $scope.resultList =getResultsBySession .get({sessionId: sessionQuiz.id});
        };
        
         $scope.showCoursResult =  function (){
             var getResultsByCours= statisticRestClient.getResultsByCoursResource ();
             $scope.resultList =getResultsByCours .get({coursId: $cookies.get("selectedCours")});
        };
    }]);

