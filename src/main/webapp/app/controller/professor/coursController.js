quizApp.controller('coursController', ["$scope", "$rootScope", "coursRestService", "$cookies", "$uibModal","webSocketService",
    function ($scope, $rootScope, coursRestService, $cookies, $uibModal,webSocketService) {
    
       refresh();
        
       function refresh (){
             var coursResource = coursRestService.getListCoursResource();
             $scope.listOfCours = coursResource.query({userId: $cookies.get("userId")});    
        };
          
        $scope.create = function (cours){
              var coursResource = coursRestService.getCoursResource();
              cours.subscribers = null;
              coursResource.save({ } , cours , function(res){
              refresh();
            },function(response){
                
            });
        };
        
         $scope.edit= function (cours){ 
             var coursResource  = coursRestService.getCoursResource();      
             coursResource.update({ } , cours , function(res){
                    $scope.selectCours (cours);
                    refresh();           
            },function(response){
              
            });
            
        };
            
       $scope.delete = function (){
           var coursResource  = coursRestService.getCoursResource();
           coursResource.delete({coursId:$scope.selectedCours.id} ,  function(res){
                       $rootScope.unSelectCours();
                       refresh();                     
               },function(response){
                      
               });
       }
       
        $scope.open = function (action, size) {
            var params = {'action': action};
            if (action === "Edit cours")
                params.selectedCours = $rootScope.selectedCours;

            var modalInstance = $uibModal.open({
                animation: false,
                templateUrl: 'coursModal.html',
                controller: 'coursForm',
                size: size,
                scope:$scope,
                resolve: {
                    params: function () {
                        return params;
                    }
                }
            });
        };
        
    }]);

quizApp.controller('coursForm', ["$scope","$cookies", "$uibModalInstance", "params", function ($scope, $cookies,$uibModalInstance, params) {
        $scope.action = params.action;
        $scope.cours = {
                name : null,
                description : null,
                active : false,
                userId : $cookies.get("userId")
        };
        if ($scope.action === "Edit cours" ) {
            $scope.cours.id = params.selectedCours.id;
            $scope.cours.name = params.selectedCours.name;
            $scope.cours.description = params.selectedCours.description;
            $scope.cours.active = params.selectedCours.active;
        }

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };

        $scope.validate = function () {
            if ( $scope.action === "Edit cours")
                $scope.edit($scope.cours);
            if ( $scope.action === "Create cours")   
                $scope.create($scope.cours);
            $uibModalInstance.close();
        };

    }]);

