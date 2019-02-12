<%-- 
    WebProgramming Project - Shopping List 
    2017-2018
    Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
--%>

<!DOCTYPE html>
<html lang="it">

<!--
    Friday - Shopping List Manager
    Copyright (C) 2018 Tommaso Bosetti, Sebastiano Chiari, Leonardo Remondini, Marta Toniolli
-->

<!-- @author: Sebastiano Chiari -->

<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="it.unitn.aa1718.webprogramming.connection.*"%>
<%@ page import="it.unitn.aa1718.webprogramming.dao.*"%>
<%@ page import="it.unitn.aa1718.webprogramming.dao.entities.*"%>
<%@ page import="it.unitn.aa1718.webprogramming.friday.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Friday</title>

    <link rel="shortcut icon" type="image/png" href="images/favicon.png">

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!-- Font Awesome Icon -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    <!-- Slick -->
    <link rel="stylesheet" type="text/css" href="slick/slick.css" />
    <link rel="stylesheet" type="text/css" href="slick/slick-theme.css" />

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="css/style.css" />

</head>

<body id="top">

    <c:if test="${!boolEmailSessionScriptlet}">
            <c:redirect url="/error.jsp"/>
    </c:if>
        
    
    
    
    <!-- HEADER -->
    <jsp:include page="jsp/components/header.jsp" />
    
    

    <!-- START: parte principale -->
    <main>

        <!-- section -->
        <div class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <!-- ASIDE -->
                    <div id="aside" class="col-md-3">
                        <!-- aside widget -->
                        <div class="aside">

                            <c:set var="listaAttiva" value="${listaAttiva}"></c:set>

                            <h3 class="aside-title">Chat</h3>
                            <ul class="list-links">
                                <!-- START: liste personali -->
                                <form action="handlingListServlet" method="GET">
                                    <c:forEach items="${ListUserSession}" var="lista">
                                        <input type="hidden" name="azioneLista" value="3">
                                        <c:set var="listaUser" value="${lista}"></c:set>
                                        <c:if test="${listaAttiva eq lista[1]}">
                                            <li>
                                            <button type="submit" value="${lista[1]}" name="selectedList" class="dropdown-item">
                                                ${lista[0]}
                                            </button>
                                            </li>
                                            <c:set var="attiva" value="active"></c:set>
                                        </c:if>
                                        <c:if test="${listaAttiva ne lista[1]}">
                                            <li>
                                            <button type="submit" value="${lista[1]}" name="selectedList" class="dropdown-item">
                                                ${lista[0]}
                                            </button>
                                            </li>
                                            <c:set var="attiva" value="notActive"></c:set>
                                        </c:if> 
                                    </c:forEach>
                                </form>
                                <!-- END: liste personali -->
                            </ul>                            
                            <ul class="list-links">
                                <!-- START: liste condivise -->
                                <form action="handlingListServlet" method="GET">
                                    <c:forEach items="${SharingListUserSession}" var="listaCondivisa">
                                        <c:if test="${listaAttiva eq listaCondivisa[1]}">
                                            <li>
                                            <button type="submit" value="${listaCondivisa[1]}" name="selectedList" class="dropdown-item">
                                                ${listaCondivisa[0]}
                                            </button>
                                            </li>
                                            <c:set var="attiva" value="active"></c:set>
                                        </c:if>
                                        <c:if test="${listaAttiva ne listaCondivisa[1]}">
                                            <li>
                                            <button type="submit" value="${listaCondivisa[1]}" name="selectedList" class="dropdown-item">
                                                ${listaCondivisa[0]}
                                            </button>
                                            </li>
                                            <c:set var="attiva" value="notActive"></c:set>
                                        </c:if> 
                                    </c:forEach>
                                </form>
                                <!-- END: liste condivise -->
                            </ul>

                        </div>

                    </div>

                    <!-- START: main -->
                    <div id="main" class="col-md-9">
                        <!-- START: list -->
                        <div id="list" aria-labelledby="gestioneListe" data-parent="gestioneListe">
                        <div class="container mt-4">
                
                <div class="container">
                    <div class="messaging">
                        <h4 class="float-right">
                            <a href="handlingListServlet?selectedList=${listaCorrente[0]}">
                                ${listaCorrente[1]}
                            </a> 
                        </h4>
                          <div class="inbox_msg">
                            <div class="mesgs" id="my-chat">
                              <div class="msg_history" id="chat">
                                  
                                  <c:forEach items="${messaggiChat}" var="messaggio">
                                      
                                      <c:if test="${emailSession eq messaggio[3]}">
                                          
                                          <div class="outgoing_msg">
                                            <div class="sent_msg">
                                              <p>${messaggio[2]}</p>
                                              <span class="time_date">Io</span> 
                                            </div>
                                          </div>
                                          
                                          
                                      </c:if>
                                      <c:if test="${emailSession ne messaggio[3]}">
                                          
                                          <div class="incoming_msg">
                                            <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                                            <div class="received_msg">
                                              <div class="received_withd_msg">
                                                <p>${messaggio[2]}</p>
                                                <span class="time_date">${messaggio[0]} ${messaggio[1]}</span></div>
                                            </div>
                                          </div>
                                          
                                      </c:if>
                                      
                                      
                                  </c:forEach>
                            
                              </div>
                            <div class="row">
                                <div class="col-md-4 mt-1 mb-1 align-self-center">
                                    <div class="displayCenter">
                                        <input type="hidden" value="Sto andando a fare la spesa, manca qualcosa?" id="buttonMessage1">
                                        <button class="search-btn" onclick="send('buttonMessage1')">
                                            Sto andando a fare la spesa, manca qualcosa?
                                        </button>
                                    </div>
                                </div>
                                <div class="col-md-4 mt-1 mb-1 align-self-center">
                                    <div class="displayCenter">
                                        <input type="hidden" value="Lista modificata. Guarda cosa ho aggiunto!" id="buttonMessage2">
                                        <button class="search-btn" onclick="send('buttonMessage2')">
                                            Lista modificata. Guarda cosa ho aggiunto!
                                        </button>
                                    </div>
                                </div>
                                <div class="col-md-4 mt-1 mb-1 align-self-center">
                                    <div class="displayCenter">
                                        <input type="hidden" value="Spesa fatta. Ti puoi rilassare." id="buttonMessage3">
                                        <button class="search-btn" onclick="send('buttonMessage3')">
                                            Spesa fatta. Ti puoi rilassare.
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="type_msg">
                                <div class="input_msg_write" id="input_message">
                                    <input type="text" class="write_msg" placeholder="Type a message" name="newMessage" id="newMessage"/>
                                    <input type="hidden" name="azioneLista" value="${3}" />
                                    <input type="hidden" name="messageToList" value="${listaCorrente[0]}" id="messageToList"/>
                                    <input type="hidden" name="userToList" value="${emailSession}" id="userToList"/>
                                    <button id="sendMessage" class="msg_send_btn" onclick="send('newMessage');">
                                        <i class="fas fa-arrow-up"></i>
                                    </button>
                                  </div>
                            </div>
                            </div>
                          </div>

                        </div>
                </div>
            </div>    
                               
                            


                        </div>
                        <!-- END: list -->

                    </div>
                    <!-- END: main -->

                </div>

            </div>

        </div>

    </main>
    <!-- END: parte principale -->

    <!-- Footer -->
    <jsp:include page="jsp/components/footer.jsp" />



    <!-- JS Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <!-- slick JS -->
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.zoom.min.js"></script>
    <script type="text/javascript" src="slick/slick.min.js"></script>

    <!-- personal JS -->
    <script type="text/javascript" src="js/main.js"></script>

    <script>
        onEnter("newMessage", "sendMessage");
    </script>
    
    <script>
        var objDiv = document.getElementById("chat");

        $(document).ready(function(){
            objDiv.scrollTop = objDiv.scrollHeight;
        });

        var ws;
        
        var LID = document.getElementById("messageToList").value;
        var username = document.getElementById("userToList").value;

        var host = document.location.host;
        var pathname = document.location.pathname;

        console.log(username);
        console.log(host);

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
            console.log(content);
            var json = JSON.stringify({
                "text":content
            });

            ws.send(json);
        }
    </script>

</body>

</html>                        