'use strict';

var quizApp = angular.module('quizApp',['ngRoute','ngResource','ngCookies','ui.bootstrap']);
        
        
 quizApp.run(["$rootScope","$cookies","coursRestClient","userRestClient",function($rootScope,$cookies,coursRestClient,userRestClient){
         var coursRestService = coursRestClient.getCoursResource();
         var userRestObject = userRestClient.getUserRestObject();
         userRestObject.get({userId:$cookies.get("userId")},function(data){  
             $rootScope.user  = data ; 
         if ($rootScope.user !== null)
            $rootScope.user.connected = true;
         else 
            $rootScope.user.connected = false;  
         });
         
         
        if ($cookies.get("selectedCours") !== null)
            $rootScope.selectedCours = coursRestService.get({coursId:$cookies.get("selectedCours")});
 }]);
   
    
    



quizApp.config(['$httpProvider',function($httpProvider){    
          $httpProvider.defaults.withCredentials = true;  
}]);

