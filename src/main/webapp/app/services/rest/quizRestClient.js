
quizApp.factory("quizRestClient", ["$resource", "SERVER_URL",
    function ($resource,  SERVER_URL) {

        return {
     
            getListTopicResource: function () {
                return $resource(SERVER_URL + 'professor/user/:userId/topic/ ', {
                }, 
                {'query': {method: 'GET', isArray: true}            
                });
            }                            
            ,
            
            getTopicResource: function () {
                return $resource(SERVER_URL + 'professor/topic/:topicId ', {
               }, {'get': {method: 'GET'},
                    'save': {method: 'POST'},
                    'update': {method: 'PUT'},
                    'remove': {method: 'DELETE'},
                    'delete': {method: 'DELETE'}
                });
            }   
            ,         
            getListSequenceResource: function () {
                return $resource(SERVER_URL + 'professor/user/:userId/sequence/ ', {
                }, 
                {'query': {method: 'GET', isArray: true}            
                });
            }                            
            ,
            
            getSequenceResource: function () {
                return $resource(SERVER_URL + 'professor/sequence/:sequenceId ', {
               }, {'get': {method: 'GET'},
                    'save': {method: 'POST'},
                    'update': {method: 'PUT'},
                    'remove': {method: 'DELETE'},
                    'delete': {method: 'DELETE'}
                });
            } 
            , 
            getListQuestionResource: function () {
                return $resource(SERVER_URL + 'professor/topic/:topicId/question/ ', {
                }, 
                {'query': {method: 'GET', isArray: true}            
                });
            }                            
            ,
            
            getQuestionResource: function () {
                return $resource(SERVER_URL + 'professor/question/:questionId ', {
               }, {'get': {method: 'GET'},
                    'save': {method: 'POST'},
                    'update': {method: 'PUT'},
                    'remove': {method: 'DELETE'},
                    'delete': {method: 'DELETE'}
                });
            } 
            ,            
            addQuestionToSequenceResource: function () {
                 return $resource(SERVER_URL + 'professor/sequence/:sequenceId/question/:questionId/position/:position/add/ ', {
               }, {'get': {method: 'GET'}
                });
            } 
            ,
            removeQuestionFromSequenceResource: function () {
                 return $resource(SERVER_URL + 'professor/sequence/:sequenceId/position/:position/remove/ ', {
               }, {'get': {method: 'GET'}
                });
            }     
            ,
            getSessionResource: function () {
                return $resource(SERVER_URL + 'professor/sessionquiz/:sessionId ', {
               }, { 'get': {method: 'GET'},
                    'save': {method: 'POST'},
                    'update': {method: 'PUT'},
                    'remove': {method: 'DELETE'},
                    'delete': {method: 'DELETE'}
                });
            } 
            ,    
            getListSessionCoursResource: function () {
                return $resource(SERVER_URL + 'all/cours/:coursId/sessionquiz/ ', {
                }, 
                {'query': {method: 'GET', isArray: true}            
                });
            } 
      //      /all/cours/{coursid}/sessionquiz/
            
        };


    }]);
