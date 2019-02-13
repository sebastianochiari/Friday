var objDiv = document.getElementById("chat");

$(document).ready(function(){
    objDiv.scrollTop = objDiv.scrollHeight;
});

var ws;

var LID = document.getElementById("messageToList").value;
var username = document.getElementById("userToList").value;

var host = document.location.host;
var pathname = document.location.pathname;

// console.log(username);
// console.log(host);

ws = new WebSocket ("ws://" + document.location.host+"/Friday/chat/"+LID+"/"+username);
            
ws.onmessage = function(event) {

    var message = JSON.parse(event.data);

    if((message.sender === username)){
        var div = document.createElement("div");
        div.classList.add("outgoing_msg");
        div.innerHTML = "<div class=\"sent_msg\"> <p>"+ message.text +"</p> <span class=\"time_date\"> Io </span> </div> </div>";
    } else {
        var div = document.createElement("div");
        div.classList.add("incoming_msg");
        div.innerHTML = "<div class=\"received_msg\"> <div class=\"received_withd_msg\"><p>"+message.text+"</p><span class=\"time_date\">"+message.sender+"</span></div></div>"
    }

    $('#input_message').find("input, textarea").val("");

    document.getElementById("chat").appendChild(div);

    objDiv.scrollTop = objDiv.scrollHeight;

};

ws.onopen = function (event) {

};

ws.onclose = function (event) {

};

ws.onerror = function (event) {

};

function send(id) {
    var content = document.getElementById(id).value;
    // console.log(content);
    var json = JSON.stringify({
        "text":content
    });

    ws.send(json);
}