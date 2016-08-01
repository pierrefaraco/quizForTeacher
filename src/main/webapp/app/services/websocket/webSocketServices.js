quizApp.factory("webSocketService", ["$q", "$timeout", "$cookies",
    function ($q, $timeout, $cookies) {
        var service = {}, listener = $q.defer(), socket = {
            client: null,
            stomp: null
        }, messageIds = [];
        var subscription;
        service.RECONNECT_TIMEOUT = 30000;
        service.SOCKET_URL = "/quizForTeacher/all/socket"; // adrese du web socket
        service.COURS = "/app/cours"; // adresse de la methode sur le server
        service.COURS_SUSCRIBERS = "/auditors/cours";// adresse de la methodesur le client   
       
        var initialize = function () {
            socket.client = new SockJS(service.SOCKET_URL);
            socket.stomp = Stomp.over(socket.client);
            socket.stomp.connect({}, startListener);
            socket.stomp.onclose = reconnect;
        };

        var startListener = function () {
             subscription = socket.stomp.subscribe(service.COURS_SUSCRIBERS + $cookies.get("poolNumber"), function (data) {
                listener.notify(getQuestion(data.body));
            });
        };

        var reconnect = function () {
            $timeout(function () {
                initialize();
            }, this.RECONNECT_TIMEOUT);
        };

        var getQuestion = function (data) {
            var jsonFeedBack = JSON.parse(data), out = {};
            out.question = jsonFeedBack;
            return  jsonFeedBack;
        };

    

        initialize();

        return{
            unsubscribe: function () {
                subscription .unsubscribe();
            }
            ,    
            init: function () {
                subscription .unsubscribe();
                initialize();
            }
            ,
            receive: function () {
                return listener.promise;
            }
            ,
            sendQuestion: function (id) {
                socket.stomp.send(service.COURS + $cookies.get("poolNumber"), {
                    priority: 9
                }, JSON.stringify({
                    id: id
                }));
            }
        }
    }]);