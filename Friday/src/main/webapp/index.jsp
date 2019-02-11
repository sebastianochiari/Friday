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
<%@ page import="it.unitn.aa1718.webprogramming.dao.*"%>
<%@ page import="it.unitn.aa1718.webprogramming.dao.entities.*"%>
<%@ page import="it.unitn.aa1718.webprogramming.friday.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>--%>
<!DOCTYPE html>
<html lang="it">

<!--
    Friday - Shopping List Manager
    Copyright (C) 2018 Tommaso Bosetti, Sebastiano Chiari, Leonardo Remondini, Marta Toniolli
-->

<!-- @author: Sebastiano Chiari -->

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

                <!-- START: Carousel -->
                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <a href="insertUser.jsp">
                            <img class="d-block w-100" src="images/crea-account-friday.jpg" alt="First slide">
                        </a>
                        </div>
                        <div class="carousel-item">
                            <a href="#">
                            <img class="d-block w-100" src="images/perdere-tempo.jpg" alt="Second slide">
                        </a>
                        </div>
                        <div class="carousel-item">
                            <a href="#">
                            <img class="d-block w-100" src="images/backtoschool.jpg" alt="Third slide">
                        </a>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
                <!-- END: Carousel -->

                <!-- START: personal shopping cart -->
                <form action="insertProductServlet" method="POST">
                    <div id="breadcrumb" class="mt-4">

                        <h5 style="display: inline-block;">La mia lista</h5>
                        <p style="display: inline-block;">
                            <i>
                                <c:forEach items="${resultListRand}" var="lista">
                                    ${lista[1]}
                                    <c:set var="listaLID" value="${lista[0]}"></c:set>

                                    <%--<sql:query dataSource="${snapshotList}" var="resultProduct" sql="SELECT * FROM products WHERE PID in (SELECT PID FROM product_lists WHERE (LID = '${listaLID}'));"></sql:query>--%>
                                </c:forEach>
                            </i>
                        </p>
                        <a class="cart-toggle" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample"></a>
                        <div class="collapse" id="collapseExample">
                            <div class="card card-body">
                                <div class="cart-carousel">

                                    <c:forEach items="${AllProductInListRand}" var="Product">
                                        <div>
                                            <div class="mt-4" id="infoProduct">
                                                <a href="#infoPersonalProduct${Product[0]}" class="text-link" data-toggle="modal" data-target="#infoPersonalProduct${Product[0]}">
                                                    <p>${Product[1]}</p>
                                                </a>
                                            </div>
                                            <div class="modal fade" id="infoPersonalProduct${Product[0]}" tabindex="-1" role="dialog" aria-labelledby="infoProduct" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered" role="document">
                                                    <div class="modal-content shadow">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title">${Product[1]}</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <%--<sql:query dataSource="${snapshotList}" var="resultProductOwner" sql="SELECT * FROM users WHERE Email = '${Product.email}';"></sql:query>--%>
                                                            <p>
                                                                <b>Creatore: </b>
                                                                    ${Product[5]} ${Product[6]}
                                                            </p>
                                                            <p><b>Note aggiuntive: </b>
                                                                ${Product[2]}
                                                            </p>
                                                            <p><b>Immagine del prodotto:</b></p>
                                                            <img src="images/prodotti/${Product[4]}" style="width: 100%">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <img class="cart-image" src="images/prodotti/${Product[4]}">
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                <!-- END: personal shopping cart -->

                    <div class="mt-4">
                        <h5>Prodotti scelti per te</h5>
                        <div class="cart-carousel">
                            <c:forEach items="${prodottiRand}" var="prodottoRand">
                                <div>
                                    <a href="#" class="text-link" data-toggle="modal" data-target="#infoProduct">
                                        <p>${prodottoRand[1]}</p>
                                    </a>
                                    <div class="modal fade" id="infoProduct" tabindex="-1" role="dialog" aria-labelledby="infoProductLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content shadow">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">${prodottoRand[1]}</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <p><b>Creatore: </b>${prodottoRand[6]} ${prodottoRand[7]}</p>
                                                    <p><b>Note aggiuntive: </b>
                                                        ${prodottoRand[2]}
                                                    </p>
                                                    <p><b>Immagine del prodotto:</b></p>
                                                    <img src="images/prodotti/${prodottoRand[4]}" style="width: 100%">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <img class="cart-image" src="images/prodotti/${prodottoRand[4]}">
                                    <input type="hidden" name="selectedListToChangeProduct" value="${listaLID}">
                                    <input type="hidden" value="4" name="scelta">
                                    <button type="submit" title="Aggiungi Prodotto" name="changeProduct" value="${prodottoRand[0]}" class="btn std-button displayCenter">
                                        Aggiungi alla lista
                                    </button>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </form>

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
