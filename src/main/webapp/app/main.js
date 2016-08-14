'use strict';

var quizApp = angular.module('quizApp',['ngRoute','ngResource','ngCookies','ui.bootstrap','hljs','ui.select','ngSanitize']);

quizApp.config(['$httpProvider',function($httpProvider){    
   
       $httpProvider.defaults.withCredentials = true;  
       $httpProvider.interceptors.push('httpRequestInterceptor');
    
}]);
      
quizApp.run(["$rootScope","$cookies","coursRestClient","userRestClient",function($rootScope,$cookies,coursRestClient,userRestClient){
        
    
 }]);
   


