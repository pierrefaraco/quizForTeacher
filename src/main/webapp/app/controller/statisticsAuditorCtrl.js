quizApp.controller('statisticsAuditorCtrl', ["$scope", "$rootScope", "$cookies", "quizRestClient", "statisticRestClient",
    function ($scope, $rootScope, $cookies, quizRestClient, statisticRestClient) {
       refresh();
        function refresh() {
            var getListSessionCours = quizRestClient.getListSessionCoursResource();
            if($cookies.get("selectedCours") != null && $cookies.get("selectedCours") !="")
                $scope.sessionsQuizList = getListSessionCours.query({coursId: $cookies.get("selectedCours")});
        };
        
         $scope.showSessionResult =  function (sessionQuiz){
             var getResultsBySession = statisticRestClient.getResultsByUserAndSessionResource ();
             $scope.resultList =getResultsBySession .get({userId : $cookies.get("userId"),sessionId: sessionQuiz.id});
        };
        
         $scope.showCoursResult =  function (){
             var getResultsByCours= statisticRestClient.getResultsByUserAndCoursResource ();
             $scope.resultList =getResultsByCours .get({userId : $cookies.get("userId"),coursId: $cookies.get("selectedCours")});
        };
      
        
}]);

