// register the interceptor as a service
quizApp.factory('httpRequestInterceptor', ["$injector", "$q", function ($injector, $q) {
     
        openMessage = function (response) {
            var params = {'response': response};
            //  On utilise  $injector sinon 
            //  erreur d 'injection de $uibModal si fournis dans les parametres
            //  Pourquoi ? 
            var modalInstance = $injector.get('$uibModal').open({
                animation: false,
                templateUrl: 'messageModal.html',
                controller: 'messageCtrl',
                size: 'md',
                resolve: {
                    params: function () {
                        return params;
                    }
                }
            });
        };//*/
        return {
            //    alert("coucou");
            // optional method
            'request': function (config) {

                // do something on success
                return config;
            },
            // optional method
            'requestError': function (rejection) {
                // do something on error
                if (canRecover(rejection)) {
                    return responseOrNewPromise
                }
                return $q.reject(rejection);
            },
            // optional method
            'response': function (response) {
                // do something on success
                return response;
            },
            // optional method
            'responseError': function (rejection) {
             // alert(rejection.status + " "+rejection.statusText )
                openMessage(rejection);

                return $q.reject(rejection);
            },
        };
    }]);


quizApp.controller('messageCtrl', [  "$scope","params", "$uibModalInstance", function ($scope,params ,$uibModalInstance) {
        $scope.response = params.response;             
        $scope.acknowledge = function () {
            $uibModalInstance.dismiss('cancel');
        };
    }]);



