'use strict';

quizApp.config(["$routeProvider",function($routeProvider){
    $routeProvider.
            when("/",{
                controller:"homeController",
                templateUrl:"app/views/HomeUI.html"
            })
            .when("/CoursAuditorUI",{
                controller:"coursController",
                templateUrl:"app/views/CoursAuditorUI.html"
            }) 
            .when("/Statistics",{
                controller:"statisticsController",
                templateUrl:"app/views/StatisticsAuditorUI.html"
            })
            .when("/ProfilUI",{
                controller:"profilController",
                templateUrl:"app/views/ProfilUI.html"
            })
        
    ;
            
   
    
}]);

