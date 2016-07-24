quizApp.controller('subscribersCtrl', ["$scope", "coursRestClient", "$cookies",
    function ($scope, coursRestClient, $cookies) {

        $scope.statusData = {
            statusList: [
                {value: "ACCEPTED", name: "Accepted"},
                {value: "DENIED", name: "Denied"},
                {value: "WAITING_ANSWER", name: "Waiting"
                }]};
        $scope.selectedStatus = $scope.statusData.statusList[0];
        $scope.selectAllValue = false;
        refresh();

        function refresh() {
            var subscribersRestService = coursRestClient.getSubscribersResource();
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
                        $scope.coursWithSubscribers.subscribers[i].status  =  $scope.selectedStatus.value;
            
         var subscribersRestService = coursRestClient.getSubscribersResource();
         subscribersRestService.update({coursId:""}, $scope.coursWithSubscribers); 
        };

    }]);



