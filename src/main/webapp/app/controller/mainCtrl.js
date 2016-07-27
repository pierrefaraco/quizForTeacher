quizApp.controller('mainCtrl', ["$scope", "$window", "$location", "$uibModal", "AuthSharedService", "webSocketService","statisticRestClient","$cookies",
    function ($scope, $window, $location, $uibModal, AuthSharedService, webSocketService,statisticRestClient,$cookies) {

        $scope.logout = function logout() {
            AuthSharedService.logout();
            var host = $location.host();
            var port = $location.port();
            $window.location.href = "http://" + host + ":" + port + "/quizForTeacher/index.html";
        };

        webSocketService.receive().then(null, null, function (question) {
            $scope.questionToAnswer = question;
            $scope.displayQuestion('Preview');
            $scope.result = { 
                   state:"Unknow"
               };
               
        });

        $scope.computeResult = function () {              
            $scope.result.state = "Right";
            for (var i in $scope.questionToAnswer.answers) {
                var proposition = $scope.questionToAnswer.answers[i];
                if (proposition.isItTrue !== proposition.checked)
                     $scope.result.state = "Wrong";
            }
            
           var result = {
                questionId :$scope.questionToAnswer.id,
                userId  :$cookies.get("userId"),
                sessionQuizId : 1,
                answerTime : 0,
                title : $scope.questionToAnswer.title,
                question : $scope.questionToAnswer.question,
                answers : $scope.questionToAnswer.answers,
                points : $scope.questionToAnswer.points
           }; 

 
           var statisticRestService = statisticRestClient.getResultResource(result);
           statisticRestService.save( result );
           $scope.displayResult();
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
    }]);


quizApp.controller('questionPopUpCtrl', ["$scope", "$timeout", "$interval", "$uibModalInstance", "params", function ($scope, $timeout, $interval, $uibModalInstance, params) {
       
        $scope.questionToAnswer.time = $scope.questionToAnswer.timeToAnswer;
        for (var i in $scope.questionToAnswer.answers) {
            var proposition = $scope.questionToAnswer.answers[i];
            proposition.checked = false;
        }
        var myVar = $interval(myTimer, 1000);
        $timeout(checkAnswer, ($scope.questionToAnswer.timeToAnswer + 1) * 1000);

        function myTimer() {
            var d = new Date();
            $scope.questionToAnswer.time -= 1;
        }
        ;

        function checkAnswer() {
            $uibModalInstance.dismiss('cancel');
            $interval.cancel(myVar);
            $scope.computeResult();
        }
        ;

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
            $interval.cancel(myVar);
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