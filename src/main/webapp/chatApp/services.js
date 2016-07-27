angular.module("chatApp.services").service("ChatService", function ($q, $timeout) {

    var service = {}, listener = $q.defer(), socket = {
        client: null,
        stomp: null
    }, messageIds = [];

    service.RECONNECT_TIMEOUT = 30000;
    service.SOCKET_URL = "/quizForTeacher/all/socket"; // adrese du web socket
    service.CHAT_TOPIC = "/topic/message"; // adresse de la methode de reception du client
    service.CHAT_BROKER = "/app/chat"; // adresse de la methode de reception  du serveur
    service.QUESTION_DIFFUSE = "/app/question";
    service.QUESTION = "/topic/show";
    
    service.receive = function () {
        return listener.promise;
    };

    var initialize = function () {
          socket.client = new SockJS(service.SOCKET_URL);
          socket.stomp = Stomp.over(socket.client);
          socket.stomp.connect({}, startListener);
          socket.stomp.onclose = reconnect;
      };

    var startListener = function () {
          socket.stomp.subscribe(service.QUESTION, function (data) {
              listener.notify(getQuestion(data.body));
          });
      };

    var reconnect = function () {
        $timeout(function () {
            initialize();
        }, this.RECONNECT_TIMEOUT);
    };

    service.sendQuestion = function (id) {
        socket.stomp.send(service.QUESTION_DIFFUSE, {
            priority: 9
        }, JSON.stringify({
            id: id
        }));
    };
    
     var getQuestion = function (data) {
        var jsonFeedBack = JSON.parse(data), out = {};
        out.question = jsonFeedBack;
        return  jsonFeedBack ;
    };
    
    initialize();
    return service;
});