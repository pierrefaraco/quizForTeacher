quizApp.factory("AuthSharedService", ["$rootScope", "$http",  "$cookies", "SERVER_URL",function($rootScope, $http, $cookies, SERVER_URL) {
 return {
  login: function(userName, password, rememberMe) {
   var config = {
   params: {
     username: userName,
     password: password,
     rememberme: rememberMe
    },
    ignoreAuthModule: 'ignoreAuthModule'
   };
   $http.post(SERVER_URL + "authenticate",'', config)
            .success(function(data, status, headers, config) {
                $cookies.put("userId",data.id);    
                $rootScope.user = data;
                $rootScope.user.connected = true ;

            }).error(function(data, status, headers, config) {
                $rootScope.authenticationError = true;
            });
  },
  
  logout: function() {
        $http.post(SERVER_URL + "logout",'')
                .success(function(data) {
                    $cookies.put("userId",'');    
                    $rootScope.user = '';
                    $rootScope.user.connected = false ;
        
                });
        
    }
    
 };
}]);