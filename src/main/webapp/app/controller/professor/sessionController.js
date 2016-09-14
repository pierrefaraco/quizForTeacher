quizApp.controller('sessionController', ["$scope", "$rootScope","quizRestService", "$cookies", "$uibModal", "webSocketService", "statisticRestService",
    function ($scope,$rootScope,  quizRestService, $cookies, $uibModal, webSocketService, statisticRestService) {

        refresh();

        function  refresh() {
                   
            var sessionService = quizRestService.getListSessionCoursResource();
            if ($cookies.get("selectedCours") != null  && $cookies.get("selectedCours") != "null")
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
            
            });
        
        }
        ;

        $scope.startSessionQuiz = function (sessionQuiz) {
            var sessionService = quizRestService.getSessionResource();
            sessionService.save(sessionQuiz, function (session) {
                $scope.runningSessionQuiz = session;
                $scope.chargeSequence();
            }, function (response) {
         
            });
        };

        $scope.stopSessionQuiz = function () {
            var sessionService = quizRestService.getSessionResource();
            sessionService.update($scope.runningSessionQuiz, function () {
                $scope.runningSessionQuiz = null;
                $rootScope.selectedSequence = null;
            }, function (error) {
                alert("Error : " + error.status);
            });
        };


        $scope.chargeSequence = function () {
           
                var sequenceRestService = quizRestService.getSequenceResource();
                sequenceRestService.get({sequenceId: $scope.runningSessionQuiz.sequenceId}, function (sequence) {
                        setAlreadyAsked(sequence);                  
                        $rootScope.selectedSequence = sequence;
                    }, function (response) {

                    });
        };
           
        function setAlreadyAsked(sequence){
              if($rootScope.selectedSequence != null)        
                        for (var i in sequence.questions) 
                            for (var j in $rootScope.selectedSequence.questions) {
                            if ( 
                                    sequence.questions[i].id == $rootScope.selectedSequence.questions[j].id  
                                    && 
                                    $rootScope.selectedSequence.questions[j].asked
                                )
                                sequence.questions[i].asked = true;  
                               
                            } 
        }

        $scope.selectQuestion = function (question) {
            $scope.selectedQuestion = question;
        }

        $scope.openSessionModal = function (action, size) {
            var params = {'action': action,
                'quizRestService': quizRestService
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
            question.asked = true ;
            $cookies.put ("lastQuestion" , question.id);
            webSocketService.sendQuestion(question.id);
            $rootScope.lastAskedQuestion = question;
        };


        $scope.getLastResultList = function () {

            var getResultsByQuestionAndSession = statisticRestService.getResultsByQuestionAndSessionResource();
         //   alert($rootScope.lastQuestion.id +" "+$scope.runningSessionQuiz.id);
            $scope.resultList = getResultsByQuestionAndSession.get(
                    {
                        questionId: $cookies.get("lastQuestion"),
                        sessionId: $scope.runningSessionQuiz.id
                    });

        };
        
        $scope.openQuestionPreviewModal = function (question) {
            var params = {'action': 'Preview'};      
            params.question = question ;             
            $uibModal.open({
                animation: false,
                templateUrl: "questionPrewiewModal.html",
                controller: "questionPreviewCtrl",   
                scope: $scope,     
                resolve: {
                    params: function () {
                        return params;
                    }
                }
            });
        };

    }]);

quizApp.controller('sessionFormCtrl', ["$scope", "$cookies", "$uibModalInstance", "params", function ($scope, $cookies, $uibModalInstance, params) {
        $scope.action = params.action;
        refreshSequences();

        function refreshSequences() {
            var sequenceRestService = params.quizRestService.getListSequenceResource();
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