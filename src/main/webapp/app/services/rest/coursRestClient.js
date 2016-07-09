
quizApp.factory("coursRestClient", ["$resource", "$http", "SERVER_URL",
    function ($resource, $http, SERVER_URL) {

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
          getSubscribersRestObject: function () {
                return $resource(SERVER_URL + 'professor/cours/:coursId/sucribers/ ', {
                }, {
                    'get': {method: 'GET'}
                });
          }

        };


    }]);
