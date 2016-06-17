quizApp .factory('Session',[ function () {
     return {
   create : function (data) {
        this.id = data.id;
        this.login = data.login;
        this.firstName = data.firstName;
        this.lastName = data.familyName;
        this.email = data.email;
        this.userRoles = [];
        angular.forEach(data.authorities, function (value, key) {
            this.push(value.name);
        }, this.userRoles);
    },
    invalidate : function () {
        this.id = null;
        this.login = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.userRoles = null;
    }
};
}]);
 
quizApp.factory("AuthSharedService", ["$rootScope", "$http",  "Session","SERVER_URL",function($rootScope, $http, Session,SERVER_URL) {
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
        alert(data);
    }).error(function(data, status, headers, config) {
     $rootScope.authenticationError = true;
     Session.invalidate();
    });
  }
 };
}]);