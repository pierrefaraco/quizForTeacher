'use strict';

var quizApp = angular.module('quizApp',['ngRoute','ngResource','ngCookies','ui.bootstrap']);

quizApp.config(['$httpProvider',function($httpProvider){
       $httpProvider.defaults.headers.common['Access-Control-Allow-Headers'] = 'Origin, X-Requested-With';
        //  $httpProvider.defaults.withCredentials = true;
        // $httpProvider.defaults.useXDomain = true;
        // delete $httpProvider.defaults.headers.common['X-Requested-With'];
}]);

