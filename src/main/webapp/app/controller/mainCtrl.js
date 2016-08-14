quizApp.controller('mainCtrl', ["$scope", "$rootScope", "$q", "$window", "$location", "$uibModal", "userRestClient", "coursRestClient", "AuthSharedService", "webSocketService", "statisticRestClient", "coursRestClient", "$cookies", "quizRestClient",
    function ($scope, $rootScope, $q, $window, $location, $uibModal, userRestClient, coursRestClient, AuthSharedService, webSocketService, statisticRestClient, coursRestClient, $cookies, quizRestClient) {





        $rootScope.init = function () {
          
            if ($cookies.get("userId") != null && $cookies.get("userId") != "") {
                var userRestObject = userRestClient.getUserRestObject();
                userRestObject.get({userId: $cookies.get("userId")}, function (data) {
                    $rootScope.user = data;
                    if ($rootScope.user !== null)
                        $rootScope.user.connected = true;
                    else
                        $rootScope.user.connected = false;
                });
              
                if ($cookies.get("selectedCours") !== "null") {
                    var freeCoursResource = coursRestClient.getFreeCoursResource();
                    freeCoursResource.get({coursId: $cookies.get("selectedCours")}, function (cours) {
                        cours.status = $cookies.get("courStatus");
                        $rootScope.selectedCours = cours;
                        $cookies.put("selectedCours", $rootScope.selectedCours.id);
                        $cookies.put("courStatus", $rootScope.selectedCours.status);
                        takeSlotOnPool();

                    });
                }
            }
        };

        $rootScope.selectCours = function (cours) {     
            if ($rootScope.selectedCours === cours) { 
                unSelectCours();
            } 
            else
            {            
                webSocketService.unsubscribe();
                $rootScope.selectedCours = cours;
                takeSlotOnPool()
                $cookies.put("selectedCours", $rootScope.selectedCours.id);
                $cookies.put("courStatus", $rootScope.selectedCours.status);
            }
        };

        function unSelectCours() {
            freeSlotOnPool().then(function () {
             
                $rootScope.selectedCours = null;
                $cookies.put("selectedCours", null);
                $cookies.put("courStatus", null);
     
            });
        }
        ;

        function takeSlotOnPool() {
            var websocketPoolNumberResource = coursRestClient.getWebsocketPoolNumberResource();
            websocketPoolNumberResource.get({coursId: $rootScope.selectedCours.id, userId: $cookies.get("userId")}, function (params) {
                $rootScope.poolNumber = params.poolNumber;
                $cookies.put("poolNumber", params.poolNumber);
                webSocketService.init();
            });
        };


        function freeSlotOnPool() {
            var deferred = $q.defer();
            var websocketPoolNumberResource = coursRestClient.getWebsocketPoolNumberResource();
            // -1 est considéré comme un unsubscribe 
            websocketPoolNumberResource.get({coursId: -1, userId: $cookies.get("userId")}, function () {
                $cookies.put("poolNumber", null);
                webSocketService.unsubscribe();
                deferred.resolve();
            }, function () {
                deferred.reject();
            });
            return deferred.promise;
        };

        webSocketService.receive().then(null, null, function (question) {
            $scope.questionToAnswer = question;
            $scope.displayQuestion('Preview');
            $scope.result = {
                state: "Unknow"
            };
        });


        $scope.logout = function logout() {
            AuthSharedService.logout();
            freeSlotOnPool().then(function () {
                $cookies.put("userId", null);
                $cookies.put("JSESSIONID", null);
                var host = $location.host();
                var port = $location.port();
                $window.location.href = "http://" + host + ":" + port + "/quizForTeacher/index.html";
            });
        };

        $scope.displayQuestion = function (action, size) {
            var params = {'action': action};
            params.question = $scope.questionToAnswer;
            $uibModal.open({
                animation: true,
                templateUrl: "questionPresentationModal.html",
                controller: "questionPopUpCtrl",
                size: size,
                scope: $scope,
                resolve: {
                    params: function () {
                        return params;
                    }
                }
            });
        };

        $scope.computeResult = function () {
            $scope.result.state = "Right";
            for (var i in $scope.questionToAnswer.answers) {
                var proposition = $scope.questionToAnswer.answers[i];
                if (proposition.isItTrue !== proposition.checked)
                    $scope.result.state = "Wrong";
            }
            var sessionService = quizRestClient.getListSessionCoursResource();

            var result = {
                questionId: $scope.questionToAnswer.id,
                userId: $cookies.get("userId"),
                sessionQuizId: 0,
                answerTime: $scope.questionToAnswer.time,
                title: $scope.questionToAnswer.title,
                question: $scope.questionToAnswer.question,
                answers: $scope.questionToAnswer.answers,
                points: $scope.questionToAnswer.points,
                coursId: $cookies.get("selectedCours")
            };


            var statisticRestService = statisticRestClient.getResultResource(result);
            statisticRestService.save(result);
            $scope.displayResult();
        };



        $scope.displayResult = function (action, size) {
            var params = {'action': action};
            params.question = $scope.questionToAnswer;
            $uibModal.open({
                animation: true,
                templateUrl: "ResultModal.html",
                controller: "resultCtrl",
                size: size,
                scope: $scope,
                resolve: {
                    params: function () {
                        return params;
                    }
                }
            });
        };

        $rootScope.init();

    }]);


quizApp.controller('questionPopUpCtrl', ["$scope", "$timeout", "$interval", "$uibModalInstance", "params", function ($scope, $timeout, $interval, $uibModalInstance, params) {

        var t1 = new Date();
        var t2 = -1;

        $scope.questionToAnswer.time = $scope.questionToAnswer.timeToAnswer;
        for (var i in $scope.questionToAnswer.answers) {
            var proposition = $scope.questionToAnswer.answers[i];
            proposition.checked = false;
        }
        var myVar = $interval(myTimer, 1000);
        $timeout(checkAnswer, ($scope.questionToAnswer.timeToAnswer) * 1000);

        function myTimer() {
            $scope.questionToAnswer.time -= 1;
        }
        ;

        function checkAnswer() {
            $uibModalInstance.dismiss('cancel');
            $interval.cancel(myVar);
            if (t2 == -1)
                t2 = $scope.questionToAnswer.timeToAnswer * 1000;
            $scope.questionToAnswer.time = t2;
            $scope.computeResult();
        }
        ;

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
            $interval.cancel(myVar);
            t2 = new Date() - t1;
        };

        $scope.checkProposition = function (proposition) {
            if (proposition.checked)
                proposition.checked = false;
            else
                proposition.checked = true;
        };



    }]);

quizApp.controller('resultCtrl', ["$scope", "$timeout", "$uibModalInstance", "params", function ($scope, $timeout, $uibModalInstance, params) {

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

    }]);