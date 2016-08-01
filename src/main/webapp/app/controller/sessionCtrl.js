quizApp.controller('sessionCtrl', ["$scope", "$rootScope", "quizRestClient", "$cookies", "$uibModal", "webSocketService", "statisticRestClient",
    function ($scope, $rootScope, quizRestClient, $cookies, $uibModal, webSocketService, statisticRestClient) {

        refresh();

        function  refresh() {
            var sessionService = quizRestClient.getListSessionCoursResource();
            sessionService.query({coursId: $cookies.get("selectedCours")}, function (sessionsQuiz) {
                $scope.runningSessionQuiz = null;
                for (var i in sessionsQuiz) {
                    if (sessionsQuiz [i].status === "RUNNING") {
                        $scope.runningSessionQuiz = sessionsQuiz[i];
                    }
                }
                if($scope.runningSessionQuiz != null)
                     $scope.chargeSequence();
            }, function (response) {
                alert("Error : " + response.status);
            });
        }
        ;

        $scope.startSessionQuiz = function (sessionQuiz) {
            var sessionService = quizRestClient.getSessionResource();
            sessionService.save(sessionQuiz, function (session) {
                $scope.runningSessionQuiz = session;
                $scope.chargeSequence();
            }, function (response) {
                alert("Error : " + response.status);
            });
        };

        $scope.stopSessionQuiz = function () {
            var sessionService = quizRestClient.getSessionResource();
            sessionService.update($scope.runningSessionQuiz, function () {
                $scope.runningSessionQuiz = null;
                $scope.selectedSequence = null;
            }, function (error) {
                alert("Error : " + error.status);
            });
        };


        $scope.chargeSequence = function () {
            var sequenceRestService = quizRestClient.getSequenceResource();
            $scope.selectedSequence = sequenceRestService.get({sequenceId: $scope.runningSessionQuiz.sequenceId}, function (sequence) {
            }, function (response) {
                alert("Error : " + response.status);
            });
        };

        $scope.selectQuestion = function (question) {
            $scope.selectedQuestion = question;
        }

        $scope.openSessionModal = function (action, size) {
            var params = {'action': action,
                'quizRestClient': quizRestClient
            };
            var modalInstance = $uibModal.open({
                animation: false,
                templateUrl: 'sessionModal.html',
                controller: 'sessionFormCtrl',
                size: size,
                scope: $scope,
                resolve: {
                    params: function () {
                        return params;
                    }
                }
            });
        };


        $scope.runQuestion = function (question) {
            $cookies.put ("lastQuestion" , question.id);
            webSocketService.sendQuestion(question.id);
        };


        $scope.getLastResultList = function () {

            var getResultsByQuestionAndSession = statisticRestClient.getResultsByQuestionAndSessionResource();
         //   alert($rootScope.lastQuestion.id +" "+$scope.runningSessionQuiz.id);
            $scope.resultList = getResultsByQuestionAndSession.get(
                    {
                        questionId: $cookies.get("lastQuestion"),
                        sessionId: $scope.runningSessionQuiz.id
                    });

        };

    }]);

quizApp.controller('sessionFormCtrl', ["$scope", "$cookies", "$uibModalInstance", "params", function ($scope, $cookies, $uibModalInstance, params) {
        $scope.action = params.action;
        refreshSequences();

        function refreshSequences() {
            var sequenceRestService = params.quizRestClient.getListSequenceResource();
            $scope.listOfSequences = sequenceRestService.query({userId: $cookies.get("userId")});
        }
        ;

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.start = function () {
            var sessionQuiz = {
                coursId: $cookies.get("selectedCours"),
                sequenceId: $scope.selectedSequence.id
            };
            $scope.startSessionQuiz(sessionQuiz);
            $uibModalInstance.close();
        };
    }]);