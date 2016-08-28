quizApp.controller('subscribersController', ["$scope", "coursRestService", "$cookies",
    function ($scope, coursRestService, $cookies) {

        $scope.statusData = {
            statusList: [
                {value: "ACCEPTED", name: "Accepted"},
                {value: "DENIED", name: "Denied"},
                {value: "WAITING_ANSWER", name: "Waiting"
                }],
            selectedStatus :null
        };   
        $scope.selectAllValue = false;
        refresh();

        function refresh() {
            var subscribersRestService = coursRestService.getSubscribersResource();
            if($cookies.get("selectedCours") != null && $cookies.get("selectedCours") !="null")
                $scope.coursWithSubscribers = subscribersRestService.get({coursId: $cookies.get("selectedCours")});
        }
        ;

        $scope.selectAll = function () {


            for (var i in  $scope.coursWithSubscribers.subscribers)
                $scope.coursWithSubscribers.subscribers[i].selected = $scope.selectAllValue;
        };

        $scope.selectSubscriber = function (selectedSubscriber) {

            for (var i in  $scope.coursWithSubscribers.subscribers) {
                if ($scope.coursWithSubscribers.subscribers[i].id === selectedSubscriber.id)
                    if ($scope.coursWithSubscribers.subscribers[i].selected)
                        $scope.coursWithSubscribers.subscribers[i].selected = false;
                    else
                        $scope.coursWithSubscribers.subscribers[i].selected = true;
            }
        };

        $scope.updateStatus = function () {
            for (var i in  $scope.coursWithSubscribers.subscribers)
                if ($scope.coursWithSubscribers.subscribers[i].selected) 
                        $scope.coursWithSubscribers.subscribers[i].status  =  $scope.statusData.selectedStatus.value;
            
         var subscribersRestService = coursRestService.getSubscribersResource();
         subscribersRestService.update({coursId:""}, $scope.coursWithSubscribers); 
        };

    }]);



