quizApp.factory("AuthSharedService", ["$rootScope", "$http", "$cookies", "SERVER_URL", function ($rootScope, $http, $cookies, SERVER_URL) {
        return {
            login: function (userName, password, rememberMe) {
                var config = {
                    params: {
                        username: userName,
                        password: password,
                        rememberme: rememberMe
                    },
                    ignoreAuthModule: 'ignoreAuthModule'
                };
                $http.post(SERVER_URL + "authenticate", '', config)
                        .then(function (response) {
                        //    alert(response.status +" " +response.statusText +" " + response.body);
                            $cookies.put("userId", response.data.id);
                            $rootScope.user = response.data;
                            $rootScope.user.connected = true;
                            $rootScope.init();
                        },function (response) {
                            $rootScope.authenticationError = true;
                  
                });
            },
            logout: function () {
                $http.post(SERVER_URL + "logout", '')
                        .then(function (data) {
                            $cookies.put("userId", '');
                            $rootScope.user = '';
                            $rootScope.user.connected = false;
                        });

            }

        };
    }]);