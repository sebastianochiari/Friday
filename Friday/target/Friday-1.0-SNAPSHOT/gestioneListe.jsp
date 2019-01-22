
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

                                <c:set var="listaAttiva" value="${requestScope.listaAttiva}"></c:set>

                                <h3 class="aside-title">Le mie liste:</h3>
                                <ul class="list-links">
                                    <!-- START: liste personali -->
                                    <form action="handlingListServlet" method="GET">
                                        <c:if test="${listaAttiva eq 0}">
                                            <li><button type="submit" value="10" name="selectedList" class="dropdown-item">
                                                Gestione Liste
                                            </button></li>
                                            <c:set var="attiva0" value="active <- attiva0"></c:set>
                                        </c:if>
                                        <c:if test="${listaAttiva ne 0}">
                                            <li><button type="submit" value="10" name="selectedList" class="dropdown-item">
                                                Gestione Liste
                                            </button></li>
                                            <c:set var="attiva0" value="notActive <- attiva0"></c:set>
                                        </c:if>
    <c:forEach items="${ListUserSession}" var="lista">
                                            <c:set var="listaUser" value="${lista}"></c:set>
                                            <c:if test="${listaAttiva eq lista[1]}">
                                                <li>
                                                <button type="submit" value="${1}${lista[1]}" name="selectedList" class="dropdown-item">
                                                    ${lista[0]}
                                                </button>
                                                </li>
                                                <c:set var="attiva" value="active"></c:set>
                                            </c:if>
                                            <c:if test="${listaAttiva ne lista[1]}">
                                                <li>
                                                <button type="submit" value="${1}${lista[1]}" name="selectedList" class="dropdown-item">
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
                                                <button type="submit" value="${2}${listaCondivisa[1]}" name="selectedList" class="dropdown-item">
                                                    ${listaCondivisa[0]}
                                                </button>
                                                </li>
                                                <c:set var="attiva" value="active"></c:set>
                                            </c:if>
                                            <c:if test="${listaAttiva ne listaCondivisa[1]}">
                                                <li>
                                                <button type="submit" value="${2}${listaCondivisa[1]}" name="selectedList" class="dropdown-item">
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

                                <h4>Gestione Liste</h4>
                                <p>
                                    Tramite questa pagina, potrai gestire comodamente tutte le tue liste, sia quelle personali che quelle condivise con altri utenti. 
                                </p>
                                <p>
                                    <a class="text-link" href="#" data-toggle="modal" data-target="#addShoppingList">Clicca qui</a> nel caso tu voglia creare una nuova lista
                                    <c:set var="goodInsertShoppingList" value="requestScope.goodInsertShoppingList"></c:set>
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
                                </p>

                                <h5>
                                    Liste personali
                                </h5>
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
                                                    <form action="handlingListServlet" method="GET">
                                                        <button type="submit" value="${lista1[1]}" name="selectedList" class="dropdown-item">
                                                            ${lista1[0]}
                                                        </button>
                                                    </form>
                                                </td>
                                                <td>${lista1[2]}</td>
                                                <td style="text-align: center;">
                                                    <c:forEach items="${SharingListUserSession}" var="listaCondivisa">
                                                        <c:if test="${lista1[0] eq listaCondivisa[0]}">
                                                            <i class="fas fa-check"></i>
                                                        </c:if>
                                                        <c:if test="${lista1[0] ne listaCondivisa[0]}">
                                                            <i class="fas fa-times"></i>
                                                        </c:if>
    </c:forEach>
                                                </td>
                                                <td style="text-align: center;">
                                                    <a href="#" title="Elimina questa lista">
                                                        <i class="fas fa-trash"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                            <c:set var="counterPL" value="${counterPL+1}"></c:set>
                                        </c:forEach>
                                    </tbody>
                                </table>

                                <h5 class="mt-5">
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

                                                <td>${listaCondivisa1[0]}</td>
                                                <td>${listaCondivisa1[2]}</td>
                                                <td style="text-align: center;">
                                                    <a href="#">
                                                    <i class="fas fa-sign-out-alt"></i>
                                                </a>
                                                </td>
                                            </tr>
                                            <c:set var="counterSL" value="${counterSL+1}"></c:set>
                                        </c:forEach>
                                    </tbody>
                                </table>


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

    </body>

</html>