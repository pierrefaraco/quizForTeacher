
quizApp.factory("userService", [ "$resource", "$http", "SERVER_URL",
		function($resource, $http, SERVER_URL) {

			return {
				getUserRest : function() {
					return $resource(SERVER_URL + 'signin/user/:userId', {
						userId : 'id'
					}, {
						'update' : {
							method : 'PUT'
						},
					});
				}

				,
				getLoginRest : function() {

					return $resource(SERVER_URL + 'user/:email/:password', {
						email : 'email',
						password : 'password'
					}, {
						get : {
							method : 'GET'
						}
					});
				}
				
				getCoursRestObject : function() {

					return $resource(SERVER_URL + 'User/:id/cours/', {
						id : 'cours',	
					}, {
						get : {
							method : 'GET'
						}
					});
				}
			};

		} ]);
