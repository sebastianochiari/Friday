<%-- 
    WebProgramming Project - Shopping List 
    2017-2018
    Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">

<!--
    Friday - Shopping List Manager
    Copyright (C) 2018 Tommaso Bosetti, Sebastiano Chiari, Leonardo Remondini, Marta Toniolli
-->

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

    <!-- Header -->
    <jsp:include page="jsp/components/header.jsp" />

    <!-- START: parte principale -->
    <main>
        
        <c:set var="RedirectAfterProduct" value="${0}" scope="session"></c:set>

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

                            <h3 class="aside-title">Categorie:</h3>
                            <ul class="list-links">

                            <form action="searchServlet" method ="GET">
                                 <c:forEach var="res" items="${productCategories}" >
                                       <button type="submit" value ="${res[0]}" class="dropdown-item" name ="CategoryLeft" id="CategoryLeft">
                                           ${res[1]}
                                       </button>
                                 </c:forEach>
                            </form>

                            </ul>

                        </div>

                    </div>

                    <!-- START: main -->
                    <div id="main" class="col-md-9">

                        <!-- START: store -->
                        <div id="store">
                            <div class="clearfix">
                                <div class="float-right">
                                    <p class="inline-flex">Ordina per: </p>
                                    <form class="inline-flex" id="ordinamento" action="searchServlet" method="GET">
                                    </form>
                                    <select id="order" name="order" form="ordinamento" onchange="submitForm('ordinamento')">
                                        <option selected value="${ordinamento[0]}" id="order">${ordinamento[0]}</option>
                                        <option value="${ordinamento[1]}" id="order">${ordinamento[1]}</option>
                                    </select>
                                </div>
                            </div>

                            <div class="row">

                                <c:set var="counter" value="${1}"/>
                                <c:forEach items="${resultSearch}" var="prodotto">
                                    <div class="col-md-4 col-sm-6 col-xs-6 pr0d0tt0">
                                        <div class="product product-single">
                                            <div class="product-thumb">
                                                <div class="product-label">
                                                    <span>${prodotto[5]}</span>
                                                </div>
                                                <a href="#" data-toggle="modal" data-target="#infoProduct${prodotto[0]}">
                                                    <img src="images/prodotti/${prodotto[4]}" alt="">
                                                </a>
                                                <div class="modal fade" id="infoProduct${prodotto[0]}" tabindex="-1" role="dialog" aria-labelledby="infoProductLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                                        <div class="modal-content shadow">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title">${prodotto[1]}</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <p><b>Descrizione: </b>
                                                                    ${prodotto[2]}
                                                                </p>
                                                                <p><b>Creatore: </b>${prodotto[7]} ${prodotto[8]}</p>
                                                                <p><b>Condiviso con:  </b>${prodotto[9]}</p>
                                                                <div class="row">
                                                                    <div class="col-6">
                                                                        <img src="images/prodotti/${prodotto[4]}" style="width: 100%">
                                                                    </div>
                                                                    <div class="col-6">
                                                                        <img src="images/loghi/${prodotto[3]}" style="width: 100%">
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="product-body">
                                                <h2 class="product-name">${prodotto[1]}</h2>
                                                <p class="product-description">${prodotto[2]}</p>

                                                <form action="insertProductServlet" method="POST" id="insertProductServlet${counter}">
                                                    <input type="hidden" value="4" name="scelta">
                                                    <input type="hidden" name="changeProduct" value="${prodotto[0]}">
                                                </form>
                                                <select class="displayCenter" id="selectedListToChangeProduct" name="selectedListToChangeProduct" form="insertProductServlet${counter}" onchange="submitForm('insertProductServlet${counter}')" style="width: 100%;">
                                                    <option disabled selected>Aggiungi alla lista</option>
                                                    <c:forEach items="${resultList}" var="lista">
                                                        <option value="${lista[0]}" name="changeProduct">
                                                            ${lista[1]}
                                                        </option>
                                                    </c:forEach>
                                                    <c:forEach items="${resultSharingList}" var="listaCondivisa">
                                                        <option value="${listaCondivisa[0]}" name="changeProduct">
                                                            ${listaCondivisa[1]}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                                <c:if test="${resultListRandExist eq false || boolEmailSessionScriptlet eq true}">  
                                                    <button class="btn search-btn mt-2" data-toggle="modal" data-target="#addShoppingList" style="width: 100%;">
                                                        <i class="fa fa-plus-circle" aria-hidden="true"></i> Crea Lista e aggiungi prodotto
                                                    </button>
                                                </c:if>                                                 
                                                    
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
                                                                <c:set scope="request" value="${prodotto[0]}" var="changeProduct"></c:set>
                                                                <c:set scope="request" var="sorgente" value="creoListaEProdotto"></c:set>
                                                                <jsp:include page="insertShoppingList.jsp" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <c:set var="counter" value="${counter + 1}"/>

                                </c:forEach>

                            </div>

                            <br>

                            <c:if test="${resultSearch ne null}">
                                <div class="clearfix">
                                    <nav class="float-right" aria-label="Page navigation example">
                                        <ul class="pagination" id="pagin">
                                            <li class="page-item">
                                              <a class="page-link" href="#" id="previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">0</span>
                                              </a>
                                            </li>
                                            <li class="page-item">
                                              <a class="page-link" href="#" id="next">
                                                <span aria-hidden="true">&raquo;</span>
                                                <span class="sr-only">2</span>
                                              </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </c:if>
                            
                            <br>

                            <div>
                                <p>
                                    Non trovi quello che stai cercando? Aggiungi il prodotto che desideri.
                                    <a href="#" class="text-link" data-toggle="modal" data-target="#addProductModal">Clicca qui</a>
                                </p>
                            </div>

                            <div class="modal fade" id="addProductModal" tabindex="-1" role="dialog" aria-labelledby="addProductModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content shadow">
                                        <div class="modal-header">
                                            <h5 class="modal-title">Aggiungi nuovo prodotto</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <jsp:include page="insertProduct.jsp" />
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- END: store -->

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

    <script type="text/javascript" src="js/pagination.js"></script>

</body>

</html>
