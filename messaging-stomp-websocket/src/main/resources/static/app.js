var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#register").prop("disabled", !connected);
    if (connected) {
        $("#conversation0").show();
        $("#conversation").show();
        $("#conversation1").show();
    }
    else {
        $("#conversation0").hide();
        $("#conversation").hide();
        $("#conversation1").hide();
    }
    $("#greetings").html("");
    $("#userName").html("");
    $("#messageInfo").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        //for users
        stompClient.subscribe('/topic/users', function (users) {
            showUsers(users);
        });
        //for new user
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        //for messages
        stompClient.subscribe('/user/'+$("#name").val()+'/ptp', function (data) {
            showJob(data);
        });
        //for error messages
        stompClient.subscribe('/user/'+$("#name").val()+'/self', function (data) {
            showError(data);
        });
        //for all messages
        stompClient.subscribe('/topic/public', function (data) {
            showJob(data);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    var info = {
        name : $("#name").val(),
        message : $("#message").val(),
        sendTo : $("#sendTo").val()
    }
    stompClient.send("/app/message", {}, JSON.stringify(info));
}

function registerUser() {
    var info = {
        name : $("#name").val()
    }
    stompClient.send("/app/register", {}, JSON.stringify(info));
}

function showUsers(messages) {
    $("#userName").html("");
    var userList = JSON.parse(messages.body);
    for(var i=0;i<userList.length;i++){
        $("#userName").append("<tr><td>" + userList[i].name + "</td></tr>");
    }

}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function showJob(message) {
    var data = JSON.parse(message.body);
    $("#messageInfo").append("<tr><td>" + data.message + "</td></tr>");
    $("#sendFrom").append("<tr><td>" + data.name + "</td></tr>");
    $("#to").append("<tr><td>" + data.sendTo + "</td></tr>");
}

function showError(message) {
    var data = JSON.parse(message.body);
    $("#messageInfo").append("<tr><td>" + data.message + "</td></tr>");
    $("#sendFrom").append("<tr><td>" + data.name + "</td></tr>");
    $("#to").append("<tr><td>" + data.sendTo + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
    $( "#register" ).click(function() { registerUser(); });
});