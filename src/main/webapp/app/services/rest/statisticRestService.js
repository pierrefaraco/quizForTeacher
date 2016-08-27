quizApp.factory("statisticRestClient", ["$resource", "SERVER_URL",
    function ($resource, SERVER_URL) {

        return {
            getResultResource: function () {
                return $resource(SERVER_URL + 'all/result/ ', {
                }, {
                    'save': {method: 'POST'}
                });
            }
           ,
           
            getResultsByQuestionAndSessionResource: function () {
                return $resource(SERVER_URL + 'professor/question/:questionId/session/:sessionId/result/ ', {
                }, {
                    'get': {method: 'GET', isArray: true}
                });
            }
           ,
            getResultsBySessionResource: function () {
                return $resource(SERVER_URL + 'professor/session/:sessionId/result/ ', {
                }, {
                    'get': {method: 'GET', isArray: true}
                });
            }
           ,
             getResultsByCoursResource: function () {
                return $resource(SERVER_URL + 'professor/cours/:coursId/result/ ', {
                }, {
                    'get': {method: 'GET', isArray: true}
                });
            }
           ,
           getResultsByUserAndSessionResource: function () {
                return $resource(SERVER_URL + 'all/user/:userId/session/:sessionId/result/ ', {
                }, {
                    'get': {method: 'GET', isArray: true}
                });
            }
           ,
             getResultsByUserAndCoursResource: function () {
                return $resource(SERVER_URL + 'all/user/:userId/cours/:coursId/result/ ', {
                }, {
                    'get': {method: 'GET', isArray: true}
                });
            }
        };


    }])