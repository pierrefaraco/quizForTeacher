'use strict';

quizApp.config(["$routeProvider",function($routeProvider){
    $routeProvider.
            when("/",{
                controller:"homeCtrl",
                templateUrl:"app/views/Home.html"
            })
            .when("/CoursAuditorUI",{
                controller:"coursAuditorCtrl",
                templateUrl:"app/views/CoursAuditor.html"
            }) 
            .when("/Statistics",{
                controller:"statisticsAuditorCtrl",
                templateUrl:"app/views/StatisticsAuditor.html"
            })
            .when("/ProfilUI",{
                controller:"profilCtrl",
                templateUrl:"app/views/ProfilUI.html"
            })
        
    ;
            
   
    
}]);

