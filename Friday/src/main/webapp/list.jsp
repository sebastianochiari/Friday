<%--
    Document   : index
    Created on : 19-ott-2018, 10.35.28
    Author     : marta & remo
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="it.unitn.aa1718.webprogramming.connection.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html lang="it">

<!--
    Friday - Shopping List Manager
    Copyright (C) 2018 Tommaso Bosetti, Sebastiano Chiari, Leonardo Remondini, Marta Toniolli
-->

<!-- @author: Sebastiano Chiari -->

    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet"

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
        
        <!-- START: topHeader -->
        <nav id="breadcrumb" class="navbar">
            <div class="container">
                <div class="float-left">
                    <c:set var="boolEmailSession" value="${boolEmailSessionScriptlet}"></c:set>
                    <c:if test="${!boolEmailSession}">
                        <span>Benvenuto su <b>Friday</b>, l'innovativo gestore di <b>liste della spesa</b></span>
                    </c:if>
                    <c:if test="${boolEmailSession}">
                        <span>Bentornato su <b>Friday</b> <c:out value="${nameUserSession}"></c:out>, l'innovativo gestore di <b>liste della spesa</b></span>
                    </c:if>

                </div>
                <div class="float-right">
                    <ul class="header-top-links">
                        <li><a href="#">Newsletter</a></li>
                        <li><a href="faq.jsp">FAQ</a></li>
                        <c:if test="${!boolEmailSession}">
                            <li><a href="login.jsp">Login</a></li>
                            <li><a href="insertUser.jsp">Registrati</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- END: topHeader -->

        <!-- Header -->
        <jsp:include page="jsp/components/header.jsp" />

        <!-- START: main carousel -->
        <main>

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
                                              <span class="time_date">Io</span> </div>
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
                                <form action="handlingListServlet" method="GET">
                                    <div class="type_msg">
                                      <div class="input_msg_write">
                                        <input type="text" class="write_msg" placeholder="Type a message" name="newMessage" />
                                        <input type="hidden" name="selectedList" value="${selectedList}" />
                                        <button href="list.js" class="msg_send_btn" type="submit"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
                                      </div>
                                    </div>
                                </form>
                            </div>
                          </div>

                        </div>
                </div>



            </div>

        </main>
        <!-- END: main carousel -->

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

    </body>

</html>
