
quizApp.factory("restService", ["$resource", "$http", "SERVER_URL",
    function ($resource, $http, SERVER_URL) {

        return {
            getUserRestObject: function () {
                return $resource(SERVER_URL + 'user/:userId', {
                    userId: 'id'
                }, {
                    'update': { method: 'PUT'}           
                });
            }
          ,
          getUnsecuredUserRestObject: function () {
                return $resource(SERVER_URL + 'unsecured/user/:userId', {
                    userId: 'id'
                }, {
                    'update': { method: 'PUT'}           
                });
            }
          ,
    
         getCoursRestObject: function () {

                return $resource(SERVER_URL + 'user/:userId/cours/:coursId ', {
                    userId: 'id'
                }, {'get': {method: 'GET'},
                    'save': {method: 'POST'},
                    'query': {method: 'GET', isArray: true},
                    'remove': {method: 'DELETE'},
                    'delete': {method: 'DELETE'}});
            }
        };

    }]);
