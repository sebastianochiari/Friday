<!--
    Friday - Shopping List Manager
    Copyright (C) 2018-2019 Tommaso Bosetti, Sebastiano Chiari, Leonardo Remondini, Marta Toniolli
-->

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

        <!-- START: header -->
        <jsp:include page="jsp/components/header.jsp" />
        <!-- END: header -->

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

                <c:if test="${resultListRandExist eq true}">
                    <!-- START: personal shopping cart -->
                    <div class="mt-4" id="breadcrumb">
                        <h5 style="display: inline-block;">La mia lista</h5>
                        <h5 style="display: inline-block;">
                            <i>
                                <c:forEach items="${resultListRand}" var="lista">
                                    <a href="handlingListServlet?selectedList=${lista[0]}">
                                        ${lista[1]}
                                    </a>
                                    <c:set var="listaLID" value="${lista[0]}"></c:set>
                                </c:forEach>
                            </i>
                        </h5>
                        <a class="cart-toggle" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample"></a>
                        <div class="collapse" id="collapseExample">
                            <div class="row">
                                <c:forEach items="${AllProductInListRand}" var="Product">
                                    <div class="col-md-3">
                                        <div class="product product-single">
                                            <div class="product-thumb">
                                                <div class="product-label">
                                                    <span>${Product[7]}</span>
                                                </div>
                                                <a href="#" data-toggle="modal" data-target="#infoProduct${Product[0]}">
                                                    <img src="images/prodotti/${Product[4]}" style="padding: 1rem;" alt="">
                                                </a>
                                                <div class="modal fade" id="infoProduct${Product[0]}" tabindex="-1" role="dialog" aria-labelledby="infoProductLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                                        <div class="modal-content shadow">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title">${Product[1]}</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <p><b>Descrizione: </b>
                                                                    ${Product[2]}
                                                                </p>
                                                                <p><b>Creatore: </b>${Product[5]} ${Product[6]}</p>
                                                                <p><b>Condiviso con:  </b>${Product[8]}</p>
                                                                <div class="row">
                                                                    <div class="col-6">
                                                                        <img src="images/prodotti/${Product[4]}" style="width: 100%">
                                                                    </div>
                                                                    <div class="col-6">
                                                                        <img src="images/loghi/${Product[3]}" style="width: 100%">
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <!-- END: personal shopping cart -->
                </c:if>
                
                
                <!-- START: prodotti scelti per te -->
                <div class="mt-4">
                    <h5>Prodotti scelti per te</h5>
                    <div class="row">
                        <c:forEach items="${prodottiRand}" var="prodottoRand">
                            <div class="col-md-3">
                                <div class="product product-single">
                                    <div class="product-thumb">
                                        <div class="product-label">
                                            <span>${prodottoRand[5]}</span>
                                        </div>
                                        <a href="#" data-toggle="modal" data-target="#infoProduct${prodottoRand[0]}">
                                            <img src="images/prodotti/${prodottoRand[4]}" style="padding: 1rem;" alt="">
                                        </a>
                                        <div class="modal fade" id="infoProduct${prodottoRand[0]}" tabindex="-1" role="dialog" aria-labelledby="infoProductLabel" aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content shadow">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title">${prodottoRand[1]}</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <p><b>Descrizione: </b>
                                                            ${prodottoRand[2]}
                                                        </p>
                                                        <p><b>Creatore: </b>${prodottoRand[6]} ${prodottoRand[7]}</p>
                                                        <p><b>Condiviso con:  </b>${prodottoRand[8]}</p>
                                                        <div class="row">
                                                            <div class="col-6">
                                                                <img src="images/prodotti/${prodottoRand[4]}" style="width: 100%">
                                                            </div>
                                                            <div class="col-6">
                                                                <img src="images/loghi/${prodottoRand[3]}" style="width: 100%">
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${resultListRandExist eq false}">
                                        
                                        <button class="btn search-btn mt-2" data-toggle="modal" data-target="#addShoppingList${prodottoRand[0]}" style="width: 100%;">
                                            <i class="fa fa-plus-circle" aria-hidden="true"></i> Crea Lista e aggiungi prodotto
                                        </button>
                                        <div class="modal fade" id="addShoppingList${prodottoRand[0]}" tabindex="-1" role="dialog" aria-labelledby="addShoppingListLabel" aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content shadow">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title">Crea una nuova lista della spesa</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <c:set scope="session" value="${prodottoRand[0]}" var="changeProduct"></c:set>
                                                        <c:set scope="session" var="sorgente" value="creoListaEProdotto"></c:set>
                                                        <jsp:include page="insertShoppingList.jsp" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                                    
                                    </c:if>
                                    <c:if test="${resultListRandExist eq true}">
                                        <form action="insertProductServlet" method="POST">
                                            <input type="hidden" name="selectedListToChangeProduct" value="${listaLID}">
                                            <input type="hidden" value="4" name="scelta">
                                            <button type="submit" title="Aggiungi Prodotto" name="changeProduct" value="${prodottoRand[0]}" class="btn search-btn mt-1 displayCenter">
                                                Aggiungi alla lista
                                            </button>
                                        </form>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                
                </div>
                <!-- END: prodotti scelti per te -->

            </div>

            <c:if test="${bannerCookie eq true}">
                <!-- START: cookie modal -->
                <div class="modal hide fade" id="myModal">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Cookie su <b style="color: #F8694A">Friday</b></h5>
                            </div>
                            <div class="modal-body">
                                <p>
                                    Utilizziamo i cookie per una serie di motivi, come mantenere affidabili e sicuri i siti Friday,
                                    personalizzare i contenuti e garantire la migliore esperienza possibile per l'utente. 
                                </p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-success" data-dismiss="modal">Accetto e continua</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END: cookie modal -->
            </c:if>

        </main>

        <!-- Footer -->
        <jsp:include page="jsp/components/footer.jsp" />

        <!-- JS Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

        <!-- personal JS -->
        <script type="text/javascript" src="js/main.js"></script>

        <script type="text/javascript">
            $(window).on('load',function(){
                $('#myModal').modal('show');
            });
        </script>

    </body>

</html>
