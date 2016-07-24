
quizApp.controller('quizCtrl', ["$scope", "$rootScope", "quizRestClient", "$cookies", "$uibModal",
    function ($scope, $rootScope, quizRestClient, $cookies, $uibModal) {
     
       refresh();
       function refresh(){
            refreshTopic () ;     
            refreshSequence () ;
        }
    
        function refreshTopic (){
            var topicRestService = quizRestClient.getListTopicResource();
            $scope.listOfTopics = topicRestService .query({userId: $cookies.get("userId")});      
        };
                   
        $scope.createTopic = function (topic){
            var topicRestService = quizRestClient.getTopicResource();
            topicRestService.save({},topic, function(createdTopic){
                    refreshTopic();
                    $scope.selectedTopic = createdTopic;
                    $scope.listOfQuestions = null;
            },function(response){
                    alert("Error : "+ response.status );
            });      
        };
        
        $scope.editTopic= function (topic){ 
            var topicRestService = quizRestClient.getTopicResource();
            topicRestService.update({} ,topic , function(editedTopic){
                   refreshTopic();
                   $scope.selectedTopic = editedTopic;
            },function(response){
                    alert("Error : "+ response.status );
            });         
        };
            
       $scope.deleteTopic= function (){
            var topicRestService = quizRestClient.getTopicResource();
            topicRestService.remove({topicId:$scope.selectedTopic.id}, function(){
                    refreshTopic();
                    $scope.selectedTopic = null;
            },function(response){
                    alert("Error : "+ response.status );
            });         
       };
       
       $scope.openTopicModal = function (action, size) {
            var params = {'action': action};      
            if (params.action == "Edit topic")
                 params.topic = $scope.selectedTopic;             
            $uibModal.open({
                animation: false,
                templateUrl: "topicModal.html",
                controller: "topicCtrl",
                size: size,
                scope:$scope,
                resolve: {
                    params: function () {
                        return params;
                    }
                }
            }); 
        }; 
        
       function refreshSequence (){
            var sequenceRestService = quizRestClient.getListSequenceResource();
            $scope.listOfSequences = sequenceRestService.query({userId: $cookies.get("userId")});      
        };
        
       $scope.addToSequence =  function (question){
           var sequenceRestService =   quizRestClient.addQuestionToSequenceResource();
           sequenceRestService.get({
               sequenceId: $scope.selectedSequence.id,
               questionId:question.id,
               position:0
           },
           function(){        
                  $scope.chargeSelectedSequence ();
            },function(response){
                    alert("Error : "+ response.status );
            });         
       };
       
       $scope.removeFromSequence =  function (question){
           var sequenceRestService =   quizRestClient.removeQuestionFromSequenceResource();
           sequenceRestService.get({
               sequenceId: $scope.selectedSequence.id,
               position:question.positionInSequence
           },
           function(){        
                  $scope.chargeSelectedSequence ();
            },function(response){
                    alert("Error : "+ response.status );
            });         
       };
       
       
       
       
       $scope.chargeSelectedSequence = function (){
            var sequenceRestService = quizRestClient.getSequenceResource();
            sequenceRestService.get({sequenceId: $scope.selectedSequence.id }, function(sequence){
                    $scope.selectedSequence = sequence;
            },function(response){
                    alert("Error : "+ response.status );
            });         
       };
                   
        $scope.createSequence = function (sequence){
            var sequenceRestService = quizRestClient.getSequenceResource();
            sequenceRestService.save({},sequence, function(createdSequence){
                    refreshSequence();
                    $scope.selectedSequence = createdSequence;
            },function(response){
                    alert("Error : "+ response.status );
            });      
        };
        
        $scope.editSequence= function (sequence){ 
            var sequenceRestService = quizRestClient.getSequenceResource();
            sequenceRestService.update({},sequence, function(editedSequence){
                    refreshSequence();
                    $scope.selectedSequence = editedSequence;
            },function(response){
                    alert("Error : "+ response.status );
            });          
        };
            
       $scope.deleteSequence= function (){
             var sequenceRestService = quizRestClient.getSequenceResource();
            sequenceRestService.remove({sequenceId:$scope.selectedSequence.id}, function(){
                    refreshSequence();
                    $scope.selectedSequence = null;
            },function(response){
                    alert("Error : "+ response.status );
            });         
       };
       
       $scope.openSequenceModal = function (action, size) {
            var params = {'action': action};      
            if (params.action == "Edit sequence")
                 params.sequence = $scope.selectedSequence;             
            $uibModal.open({
                animation: false,
                templateUrl: "sequenceModal.html",
                controller: "sequenceCtrl",
                size: size,
                scope:$scope,
                resolve: {
                    params: function () {
                        return params;
                    }
                }
            }); 
        }; 
             
        $scope.refreshQuestions = function (){
            var questioncRestService = quizRestClient.getListQuestionResource();
            $scope.listOfQuestions = questioncRestService .query({topicId:$scope.selectedTopic.id});   
            $scope.chargeSelectedSequence();
           
        }; 
        
        $scope.selectQuestion = function(question){
               $scope.selectedQuestion = question; 
        };
        
        
        $scope.createQuestion = function (question){
            var questionRestService = quizRestClient.getQuestionResource();
            questionRestService.save({},question, function(createdQuestion){
                     $scope.refreshQuestions();
                     $scope.selectedQuestion = createdQuestion;
            },function(response){
                    alert("Error : "+ response.status );
            });   
        };
        
        $scope.editQuestion= function (question){ 
            var questionRestService = quizRestClient.getQuestionResource();
            questionRestService.update({},question, function(editedQuestion){
                   $scope.refreshQuestions();
                   $scope.selectedQuestion = editedQuestion ;
            },function(response){
                    alert("Error : "+ response.status );
            });  
        };
            
       $scope.deleteQuestion = function (){
            var questionRestService = quizRestClient.getQuestionResource();
            questionRestService.remove({questionId : $scope.selectedQuestion.id},function(res){
                    $scope.refreshQuestions();
                    $scope.selectedQuestion = null ;
            },function(response){
                    alert("Error : "+ response.status );
            });  
       };
          
        $scope.openQuestionModal = function (action, size) {
            var params = {
                'action': action
            };      
            if (params.action == "Edit question")
                 params.question = $scope.selectedQuestion;             
            $uibModal.open({
                animation: false,
                templateUrl: "questionModal.html",
                controller: "questionCtrl",
                size: size,
                scope:$scope,
                resolve: {
                    params: function () {
                        return params;
                    }
                }
            }); 
        }; 
  
    }]);

quizApp.controller('topicCtrl', ["$scope","$cookies", "$uibModalInstance", "params", function ($scope, $cookies,$uibModalInstance, params) {
        $scope.action = params.action;
        $scope.topic = {
                name : null,
                description : null,
                userId : $cookies.get("userId")
        };
        if ($scope.action === "Edit topic" ) 
            $scope.topic = params.topic;
 
        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.validate = function () {
            if ( $scope.action === "Edit topic")
                $scope.editTopic($scope.topic);
            if ( $scope.action === "Create topic")   
                $scope.createTopic($scope.topic);
            $uibModalInstance.close();
        };

    }]);

quizApp.controller('sequenceCtrl', ["$scope","$cookies", "$uibModalInstance", "params", function ($scope, $cookies,$uibModalInstance, params) {
        $scope.action = params.action;
        $scope.sequence = {
                name : null,
                description : null,
                userId : $cookies.get("userId")
        };
        if ($scope.action === "Edit sequence" ) 
            $scope.sequence = params.sequence;
        
        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.validate = function () {
            if ( $scope.action === "Edit sequence")
                $scope.editSequence($scope.sequence);
            if ( $scope.action === "Create sequence")   
                $scope.createSequence($scope.sequence);
            $uibModalInstance.close();
        };

    }]);


quizApp.controller('questionCtrl', ["$scope","$cookies", "$uibModalInstance","$uibModal","params", function ($scope, $cookies,$uibModalInstance,$uibModal, params) {
        
        
        
        $scope.action = params.action;
        if ($scope.action === "Create question" ) {
                $scope.question = {
                        title : $scope.selectedTopic.name,
                        question: null,
                        points :2,
                        timeToAnswer :15,
                        position :0,
                        questionType :"QUIZ",
                        topicId: $scope.selectedTopic.id, 
                        answers : new Array()
                };
            }
     
        if ($scope.action === "Edit question" ) 
             $scope.question = params.question;

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };


        $scope.apply = function () { refresh();
            
           if ( $scope.action === "Edit question")
                $scope.editQuestion($scope.question);
            if ( $scope.action === "Create question")   
                $scope.createQuestion($scope.question);  
           
        };
        
        $scope.validate = function () {
            if ( $scope.action === "Edit question")
                $scope.editQuestion($scope.question);
            if ( $scope.action === "Create question")   
                $scope.createQuestion($scope.question);
            $uibModalInstance.close();
        };
        
        $scope.addProposition = function (isItTrue) {
             var prop = {
                       text : $scope.proposition,
                       isItTrue : isItTrue
             };
             $scope.question.answers.push(prop);
         };
     
        $scope.removeProposition = function (proposition){          
            var temp = new Array();
            for  (var i  in $scope.question.answers ){
                if ($scope.question.answers[i] !== proposition)
                    temp.push($scope.question.answers[i]);              
            }             
            $scope.question.answers= temp ;
        };
        
        $scope.openQuestionPresentationModal = function (action, size) {
            var params = {'action': action};      
            params.question = $scope.question;             
            $uibModal.open({
                animation: false,
                templateUrl: "questionPresentationModal.html",
                controller: "questionPresentationCtrl",
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


quizApp.controller('questionPresentationCtrl', ["$scope", "$uibModalInstance", "params", function ($scope, $uibModalInstance, params) {     
        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    }]);