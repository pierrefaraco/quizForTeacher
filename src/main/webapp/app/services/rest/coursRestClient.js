
quizApp.factory("coursRestClient", ["$resource",  "SERVER_URL",
    function ($resource, SERVER_URL) {

        return {
            getListCoursResource: function () {
                return $resource(SERVER_URL + 'professor/user/:userId/cours/ ', {
                }, {'get': {method: 'GET'},
                    'query': {method: 'GET', isArray: true}
                });
            }
            ,
            getCoursResource: function () {
                return $resource(SERVER_URL + 'professor/cours/:coursId ', {
                }, {'get': {method: 'GET'},
                    'save': {method: 'POST'},
                    'update': {method: 'PUT'},
                    'remove': {method: 'DELETE'},
                    'delete': {method: 'DELETE'}
                });
            }                   
          ,
          getSubscribersResource: function () {
                return $resource(SERVER_URL + 'professor/coursAndSubscribers/:coursId ', {
                }, {
                    'get': {method: 'GET'},
                    'update': {method: 'PUT'}
                });
          }
          ,
          getSubscriberStatusResource: function () {
                return $resource(SERVER_URL + 'professor/user/:userId/cours/:coursId/updatesubscriberstatus/:status', {
                }, {
                    'update': {method: 'PUT'}
                });
          }
          ,
          getListCoursWithAuditorStatusResource: function () {
                return $resource(SERVER_URL + 'all/user/:userId/coursWithAuditorStatus/ ', {
                }, {'get': {method: 'GET'},
                    'query': {method: 'GET', isArray: true}
                });
            }
          , 
          getSubscribeResource: function () {           
                return $resource(SERVER_URL + 'all/user/:userId/cours/:coursId/subscribe/ ', {
                }, {
                    'get': {method: 'GET'},
                });
            }
          ,
          getUnSubscribeResource : function () {           
                return $resource(SERVER_URL + 'all/user/:userId/cours/:coursId/unsubscribe/ ', {
                }, {
                    'get': {method: 'GET'},
                });
            }    
        };


    }]);
