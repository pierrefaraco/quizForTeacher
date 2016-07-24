'use strict';

quizApp.config(["$routeProvider",function($routeProvider){
    $routeProvider.
            when("/",{
                controller:"homeCtrl",
                templateUrl:"app/views/Home.html"
            })
            .when("/CoursUI",{
                controller:"coursCtrl",
                templateUrl:"app/views/Cours.html"
            }) 
             .when("/SubscribersUI",{
                controller:"subscribersCtrl",
                templateUrl:"app/views/Subscribers.html"
            })
   /*         .when("/UsersUI",{
                controller:"homeCtrl",
                templateUrl:"app/views/Home.html"
  
            })*/
            .when("/QuizUI",{
                controller:"quizCtrl",
                templateUrl:"app/views/Quiz.html"
            })
            .when("/SessionUI",{
                controller:"sessionCtrl",
                templateUrl:"app/views/SessionUI.html"
            })
            .when("/ProfilUI",{
                controller:"profilCtrl",
                templateUrl:"app/views/ProfilUI.html"
            })
            .when("/StatisticsUI",{
                controller:"statisticsCtrl",
                templateUrl:"app/views/Statistics.html"
            })
        
    ;
            
   
    
}]);

