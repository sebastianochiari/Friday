<!--
    Friday - Shopping List Manager
    Copyright (C) 2018-2019 Tommaso Bosetti, Sebastiano Chiari, Leonardo Remondini, Marta Toniolli
-->

<%@ page import="java.io.*"%>
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

        <!-- header -->
        <jsp:include page="jsp/components/header.jsp" />

        <!-- START: parte principale -->
        <main>

            <div class="section">
                
                <div class="container">
                
                    <div class="row">
                        
                        <!-- ASIDE -->
                        <div id="aside" class="col-md-3">
                            <!-- aside widget -->
                            <div class="aside">

                                <c:set var="listaAttiva" value="${listaAttiva}"></c:set>
                                <!-- START: liste personali -->
                                <h3 class="aside-title">Le mie liste:</h3>
                                <ul class="list-links">
                                    <form action="handlingListServlet" method="GET">
                                        <li>
                                            <button type="submit" value="0" name="selectedList" class="dropdown-item">
                                                Gestione Liste
                                            </button>
                                        </li>
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
                                </ul>                                
                                <!-- END: liste personali -->

                                <!-- START: liste condivise -->
                                <c:if test="${emailSession ne null}">
                                    <h3 class="aside-title">Liste condivise:</h3>
                                    <c:if test="${SharingListUserSessionSize eq 0}">
                                        <p>Non hai nessuna lista condivisa</p>
                                    </c:if>
                                    <ul class="list-links">
                                        <!-- START: liste condivise -->
                                        <form action="handlingListServlet" method="GET">
                                            <c:forEach items="${SharingListUserSession}" var="listaCondivisa">
                                                <li>
                                                    <button type="submit" value="${listaCondivisa[1]}" name="selectedList" class="dropdown-item">
                                                        ${listaCondivisa[0]}
                                                    </button>
                                                </li>
                                                <c:set var="attiva" value="active"></c:set>
                                            </c:forEach>
                                        </form>
                                    </ul>
                                </c:if>
                                <!-- END: liste condivise -->
                            </div>
                        </div>

                        <!-- START: main -->
                        <div id="main" class="col-md-9">
                            <!-- START: list -->
                            <div id="list" aria-labelledby="gestioneListe" data-parent="gestioneListe">
                                <c:set var="GestioneListe" value="${listaAttiva}"></c:set>
                                <c:if test="${GestioneListe eq 0}">
                                    <h4>Gestione Liste</h4>
                                    <p>
                                        Tramite questa pagina, potrai gestire comodamente tutte le tue liste, sia quelle personali che quelle condivise con altri utenti.
                                    </p>
                                    <c:if test="${resultListRandExist eq false || boolEmailSessionScriptlet eq true}">
                                        <p>
                                            Se vuoi creare una nuova lista clicca qui: <a class="text-link" href="#" data-toggle="modal" data-target="#addShoppingList">Crea nuova lista</a>
                                        </p>
                                        <div class="modal fade" id="addShoppingList" tabindex="-1" role="dialog" aria-labelledby="addShoppingListLabel" aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content shadow">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title">Crea una nuova lista della spesa</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <jsp:include page="insertShoppingList.jsp" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${resultListRandExist eq true && boolEmailSessionScriptlet eq false}">
                                        <p>
                                            Hai raggiunto il numero massimo di liste creabili come utente anonimo, clicca qui per registrarti: <a class="text-link" href="insertUser.jsp">Registrati</a>
                                        </p>
                                    </c:if>

                                    <h5>
                                        Liste personali
                                    </h5>
                                    <c:if test="${ListUserSessionSize eq 0}">
                                        <p>Non hai ancora nessuna lista</p>
                                    </c:if>
                                    <c:if test="${resultListRandExist eq true && boolEmailSessionScriptlet eq false}">
                                        <p>
                                            Hai raggiunto il numero massimo di liste creabili come utente anonimo, se vuoi creare o condividere le tue liste clicca qui per registrarti: <a class="text-link" href="insertUser.jsp">Registrati</a>
                                        </p>
                                    </c:if>
                                    <c:if test="${ListUserSessionSize ne 0}">
                                        <table class="table table-striped table-borderless">
                                            <thead>
                                                <tr>
                                                    <th style="text-align: center;" scope="col">#</th>
                                                    <th scope="col">Nome lista</th>
                                                    <th scope="col">Categoria di lista</th>
                                                    <th style="text-align: center;" scope="col">Condivisa</th>
                                                    <th style="text-align: center;" scope="col">Elimina</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:set var="counterPL" value="1"></c:set>
                                                <c:forEach items="${ListUserSession}" var="lista1">
                                                    <tr>
                                                        <th style="text-align: center;" scope="row">${counterPL}</th>
                                                        <td>
                                                            <form id="handlingListServlet${counterPL}" action="handlingListServlet" method="GET">
                                                                <input type="hidden" value="${lista1[1]}" name="selectedList">
                                                            </form>
                                                            <a href="#" onclick="submitForm('handlingListServlet${counterPL}')">${lista1[0]}</a>
                                                        </td>
                                                        <td>${lista1[2]}</td>
                                                        <td style="text-align: center;">
                                                            <c:if test="${lista1[3] eq 1}">
                                                                <i class="fas fa-check"></i>
                                                            </c:if>
                                                            <c:if test="${lista1[3] ne 1}">
                                                                <i class="fas fa-times"></i>
                                                            </c:if>
                                                        </td>
                                                        <td style="text-align: center;">
                                                            <form id="insertShoppingListServlet${counterPL}" action="insertShoppingListServlet" method="POST">
                                                                <input type="hidden" name="proprietario" value="1">
                                                                <input type="hidden" name="deleteList" value="${lista1[1]}">
                                                                <button type="submit"><i class="fas fa-trash"></i></button>
                                                            </form>
                                                                <%--
                                                            <a href="#" onclick="submit('insertShoppingListServlet')"><i class="fas fa-trash"></i></a>
                                                                --%>
                                                        </td>
                                                    </tr>
                                                    <c:set var="counterPL" value="${counterPL+1}"></c:set>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>

                                    <c:if test="${emailSession ne null}">
                    
                                        <h5 class="mt-4">
                                            Liste condivise
                                        </h5>
                                        <table class="table table-striped table-borderless">
                                            <thead>
                                                <tr>
                                                    <th style="text-align: center;" scope="col">#</th>
                                                    <th scope="col">Nome lista</th>
                                                    <th scope="col">Categoria di lista</th>
                                                    <th style="text-align: center;" scope="col">Esci</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:set var="counterSL" value="1"></c:set>
                                                <c:forEach items="${SharingListUserSession}" var="listaCondivisa1">
                                                    <tr>
                                                        <th style="text-align: center;" scope="row">${counterSL}</th>
                                                        <td>
                                                            <form id="handlingListServlet${counterPL}" action="handlingListServlet" method="GET">
                                                                <input type="hidden" value="${listaCondivisa1[1]}" name="selectedList">
                                                            </form>
                                                            <a href="#" onclick="submitForm('handlingListServlet${counterPL}')">${listaCondivisa1[0]}</a>
                                                        </td>
                                                        <td>${listaCondivisa1[2]}</td>
                                                        <td style="text-align: center;">
                                                            
                                                            <form id="insertShoppingListServlet${counterPL}" action="insertShoppingListServlet" method="POST">
                                                                <input type="hidden" name="proprietario" value="0">
                                                                <input type="hidden" name="deleteList" value="${listaCondivisa1[1]}">
                                                                <button type="submit"><i class="fas fa-sign-out-alt"></i></i></button>
                                                            </form>
                                                        
                                                        </td>
                                                    </tr>
                                                    <c:set var="counterSL" value="${counterSL + 1}"></c:set>
                                                    <c:set var="counterPL" value="${counterPL + 1}"></c:set>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>
                                </c:if>
                                <c:if test="${GestioneListe ne 0}">
                                    <jsp:include page="list.jsp"></jsp:include>
                                </c:if>
                            </div>
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

    </body>

</html>
