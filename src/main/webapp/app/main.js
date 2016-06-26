'use strict';

var quizApp = angular.module('quizApp',['ngRoute','ngResource','ngCookies','ui.bootstrap']);
        
        
 quizApp.run(["$rootScope","$cookies","restService",function($rootScope,$cookies,restService){
         var coursRestService = restService.getCoursRestObject();
         var userRestObject = restService.getUserRestObject();
         userRestObject.get({userId:$cookies.get("userId")},function(data){  
             $rootScope.user  = data ; 
         if ($rootScope.user !== null)
            $rootScope.user.connected = true;
         else 
            $rootScope.user.connected = false;  
         });
         
         
        if ($cookies.get("selectedCours") !== null)
            $rootScope.selectedCours = coursRestService.get({ userId: $cookies.get("userId") , coursId:$cookies.get("selectedCours")});
 }]);
   
    
    






quizApp.config(['$httpProvider',function($httpProvider){    
          $httpProvider.defaults.withCredentials = true;  
}]);

