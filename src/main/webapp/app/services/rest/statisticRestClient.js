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
        };


    }])