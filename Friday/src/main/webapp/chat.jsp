
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

                            <h3 class="aside-title">Le mie liste:</h3>
                            <ul class="list-links">
                                <!-- START: liste personali -->
                                <form action="handlingListServlet" method="GET">
                                    <c:if test="${listaAttiva eq 0}">
                                        <li><button type="submit" value="0" name="selectedList" class="dropdown-item">
                                            Gestione Liste
                                        </button></li>
                                        <c:set var="attiva0" value="active <- attiva0"></c:set>
                                    </c:if>
                                    <c:if test="${listaAttiva ne 0}">
                                        <li><button type="submit" value="0" name="selectedList" class="dropdown-item">
                                            Gestione Liste
                                        </button></li>
                                        <c:set var="attiva0" value="notActive <- attiva0"></c:set>
                                    </c:if>
                                    <c:forEach items="${ListUserSession}" var="lista">
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

                            <h3 class="aside-title">Liste condivise:</h3>
                            <c:if test="${SharingListUserSessionSize eq 0}">
                                <p>Non hai nessuna lista condivisa</p>
                            </c:if>
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
                    <h3 class=" text-center">Messaging</h3>
                    <div class="messaging">
                          <div class="inbox_msg">
                            <div class="inbox_people">
                              <div class="headind_srch">
                                  <h4 align="center">Partecipanti alla Lista</h4> 
                              </div>
                              <div class="inbox_chat">
                                  
                                  <c:forEach items="${partecipantiChat}" var="partecipante">
                                   
                                    <div class="chat_list">
                                        <div class="chat_people">
                                            <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                                            <div class="chat_ib">
                                                <h5>${partecipante[1]} ${partecipante[2]}<span class="chat_date">LAMARTAPUZZA</span></h5>
                                                <p>Ciaone.</p>
                                            </div>
                                        </div>
                                    </div>
                                                
                                  </c:forEach>
                
                              </div>
                            </div>
                            <div class="mesgs">
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
                                <div class="type_msg">
                                  <div class="input_msg_write">
                                    <input type="text" class="write_msg" placeholder="Type a message" name="newMessage" id="newMessage"/>
                                    <input type="hidden" name="azioneLista" value="${3}" />
                                    <input type="hidden" name="messageToList" value="${listaCorrente[0]}" id="messageToList"/>
                                    <input type="hidden" name="userToList" value="${emailSession}" id="userToList"/>
                                    <button class="msg_send_btn" onclick="send();"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
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

            document.getElementById("chat").appendChild(div);
        };

        ws.onopen = function (event) {

        };

        ws.onclose = function (event) {

        };

        ws.onerror = function (event) {

        };

        function send() {
            var content = document.getElementById("newMessage").value;
            var json = JSON.stringify({
                "text":content
            });

            ws.send(json);
        }
    </script>

</body>

</html>                        