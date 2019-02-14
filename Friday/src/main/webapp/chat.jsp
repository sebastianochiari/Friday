<!--
    Friday - Shopping List Manager
    Copyright (C) 2018-2019 Tommaso Bosetti, Sebastiano Chiari, Leonardo Remondini, Marta Toniolli
-->

<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="it.unitn.aa1718.webprogramming.connection.*"%>
<%@ page import="it.unitn.aa1718.webprogramming.dao.*"%>
<%@ page import="it.unitn.aa1718.webprogramming.dao.entities.*"%>
<%@ page import="it.unitn.aa1718.webprogramming.friday.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="it">

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

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css" />

    </head>

    <body id="top">

        <c:if test="${!boolEmailSessionScriptlet}">
                <c:redirect url="/error.jsp"/>
        </c:if>
        <c:if test="${passaggioServlet eq true}">
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

                                    <h3 class="aside-title">Chat</h3>
                                    <ul class="list-links">
                                        <!-- START: liste personali -->
                                        <form action="sharingListServlet" method="GET">
                                            <c:forEach items="${ListUserSession}" var="lista">
                                                <input type="hidden" name="azioneLista" value="3">
                                                    <li>
                                                        <button type="submit" value="${lista[1]}" name="messageToList" class="dropdown-item">
                                                            ${lista[0]}
                                                        </button>
                                                    </li>
                                            </c:forEach>
                                        </form>
                                        <!-- END: liste personali -->
                                    </ul>                            
                                    <ul class="list-links">
                                        <!-- START: liste condivise -->
                                        <form action="sharingListServlet" method="GET">
                                            <c:forEach items="${SharingListUserSession}" var="listaCondivisa">
                                                <input type="hidden" name="azioneLista" value="3">
                                                    <li>
                                                        <button type="submit" value="${listaCondivisa[1]}" name="messageToList" class="dropdown-item">
                                                            ${listaCondivisa[0]}
                                                        </button>
                                                    </li>
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
                                                <!-- <a href="handlingListServlet?selectedList=
                                                --> 
                                                <%--${listaCorrente[0]}">
                                                        ${listaCorrente[1]}
                                                --%>

                                                    <a href="handlingListServlet?selectedList=${listaChat[1]}">
                                                        ${listaChat[0]}
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
                                                                    <div class="incoming_msg_img"> <img src="images/users/${messaggio[4]}" alt="sunil"> </div>
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

            <!-- footer -->
            <jsp:include page="jsp/components/footer.jsp" />

            <!-- JS Bootstrap -->
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

            <!-- personal JS -->
            <script type="text/javascript" src="js/main.js"></script>

            <script type="text/javascript" src="js/chat.js"></script>

            <script>
                onEnter("newMessage", "sendMessage");
            </script>
        </c:if>
        <c:if test="${passaggioServlet ne true}">
            <c:redirect url="/error.jsp"></c:redirect>
        </c:if>
            
    </body>

</html>                        