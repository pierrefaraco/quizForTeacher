'use strict';

quizApp.config(["$routeProvider",function($routeProvider){
    $routeProvider.
            when("/",{
                templateUrl:"app/views/Home.html"
            })
            .when("/CoursUI",{
                templateUrl:"app/views/Cours.html"
            }) 
             .when("/SubscribersUI",{
                templateUrl:"app/views/Subscribers.html"
            })
            .when("/UsersUI",{
                templateUrl:"app/views/Home.html"
  
            })
            .when("/QuestionsUI",{
                templateUrl:"app/views/QuestionUI.html"
            })
            .when("/SessionUI",{
                templateUrl:"app/views/Home.html"
            })
            .when("/ProfilUI",{
                templateUrl:"app/views/ProfilUI.html"
            })
        
    ;
            
   
    
}]);

