
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
            
        };


    }]);
