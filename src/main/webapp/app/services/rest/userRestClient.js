
quizApp.factory("userRestClient", ["$resource", "$http", "SERVER_URL",
    function ($resource, $http, SERVER_URL) {

        return {
            getUserRestObject: function () {
                return $resource(SERVER_URL + 'all/user/:userId ', {
                }, {
                    'update': { method: 'PUT'}           
                });
            }
          ,
          getUnsecuredUserRestObject: function () {
                return $resource(SERVER_URL + 'unsecured/user/ ', {
                }, {
                     'save': { method: 'POST'}        
                });
            }
        
        };

    }]);
