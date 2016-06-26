'use strict';
quizApp.controller('homeCtrl',
		[
				"$scope",
				"restService",
                                "$location",
				"$cookies",
				"AuthSharedService",
				function($scope,restService,$location,$cookies, AuthSharedService) {
					
                                        $scope.login = function() {
						AuthSharedService.login($scope.Email, $scope.Password,
								'false');    
                                                $scope.$parent.connected = true;
					};
					
					$scope.signup = function () {                                   
                                             $location.path("/ProfilUI");
					};
				} ]);
