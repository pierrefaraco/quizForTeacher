
quizApp.factory("userRestService", ["$resource",  "SERVER_URL",
    function ($resource,  SERVER_URL) {

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
