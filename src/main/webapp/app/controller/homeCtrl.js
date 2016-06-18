'use strict';
quizApp.controller('homeCtrl',
		[
				"$scope",
				"userService",
				"$cookies",
				"AuthSharedService",
				function($scope, userService, $cookies, AuthSharedService) {
					var loginRestService = userService.getLoginRest();

					$scope.connected = $cookies.get("testCookies");

					$scope.login = function() {
						AuthSharedService.login($scope.Email, $scope.Password,
								'false');
					};
					$scope.display = function() {

					};
				} ]);
