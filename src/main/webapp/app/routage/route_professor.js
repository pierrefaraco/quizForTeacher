'use strict';

quizApp.config(["$routeProvider",function($routeProvider){
    $routeProvider.
            when("/",{
                controller:"homeController",
                templateUrl:"app/views/HomeUI.html"
            })
            .when("/CoursUI",{
                controller:"coursController",
                templateUrl:"app/views/CoursUI.html"
            }) 
             .when("/SubscribersUI",{
                controller:"subscribersController",
                templateUrl:"app/views/SubscribersUI.html"
            })
            .when("/QuestionUI",{
                controller:"quizBuilderController",
                templateUrl:"app/views/QuizBuilderUI.html"
            })
            .when("/SessionUI",{
                controller:"sessionController",
                templateUrl:"app/views/SessionUI.html"
            })
            .when("/ProfilUI",{
                controller:"profilController",
                templateUrl:"app/views/ProfilUI.html"
            })
            .when("/StatisticsUI",{
                controller:"statisticsController",
                templateUrl:"app/views/StatisticsUI.html"
            })
        
    ;
            
   
    
}]);

