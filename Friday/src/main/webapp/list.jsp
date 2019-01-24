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
        
        <c:set var="selectedListToChangeProduct" value="${requestScope.selectedList}" scope="session"></c:set>
        
        <!-- Header -->
        <jsp:include page="jsp/components/header.jsp" />

        <!-- START: main carousel -->
        <main>

            <%-- con questa alternativa si hanno problemi di grafica quando si espande e si comprimono le informazioni aggiuntive dei prodotti --%>
            <%--
            <div class="container mt-4">

                <table class="table table-striped table-borderless">
                    <thead>
                        <tr>
                            
                            <th scope="col"></th>
                            <th scope="col">Nome prodotto</th>
                            <th scope="col">Note</th>
                            <th scope="col" class="collapse" id="accordion">Logo</th>
                            <th scope="col" class="collapse" id="accordion">Foto</th>
                            <th scope="col" class="collapse" id="accordion">Categoria</th>
                            <th scope="col" class="collapse" id="accordion">Proprietario prodotto</th>
                            <th scope="col" style="text-align: right;">Quantità</th>
                            <th style="text-align: center;" scope="col">Gestisci quantità</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="counterPL" value="1"></c:set>
                        <c:forEach items="${Prodotto}" var="prodotto">
                            <tr>
                                <td data-toggle="collapse" data-target="#accordion" class="clickable"><i class="fas fa-angle-down"></i></td>
                                <td scope="col">${prodotto[0]}</td>
                                <td scope="col">${prodotto[1]}</td>
                                <td scope="col" class="collapse" id="accordion">${prodotto[2]}</td>
                                <td scope="col" class="collapse" id="accordion">${prodotto[3]}</td>
                                <td scope="col" class="collapse" id="accordion">${prodotto[4]}</td>
                                <td scope="col" class="collapse" id="accordion">${prodotto[5]}</td>
                                <td scope="col" style="text-align: right;">${prodotto[6]}</td>
                                <td style="text-align: center;">
                                    <table class="table table-striped table-borderless">
                                        <tbody>
                                            <tr>
                                                <form action="insertProductServlet" method="POST">
                                                    <input type="hidden" value="${selectedListToChangeProduct}" name="selectedListToChangeProduct">
                                                    <input type="hidden" value="1" name="scelta">
                                                    <button type="submit" title="Diminuisci la quantità" name="changeProduct" value="${prodotto[7]}">
                                                        <i class="fa fa-minus-circle" aria-hidden="true"></i>
                                                    </button>
                                                </form>
                                                <form action="insertProductServlet" method="POST">
                                                    <input type="hidden" value="${selectedListToChangeProduct}" name="selectedListToChangeProduct">
                                                    <input type="hidden" value="2" name="scelta">
                                                    <button type="submit" title="Togli questo prodotto dalla lista" name="changeProduct" value="${prodotto[7]}">
                                                        <i class="fas fa-trash"></i>
                                                    </button>
                                                </form>
                                                <form action="insertProductServlet" method="POST">
                                                    <input type="hidden" value="${selectedListToChangeProduct}" name="selectedListToChangeProduct">
                                                    <input type="hidden" value="3" name="scelta">
                                                    <button type="submit" title="Aumenta la quantità" name="changeProduct" value="${prodotto[7]}">
                                                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                                    </button>
                                                </form>
                                                
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            <c:set var="counterPL" value="${counterPL+1}"></c:set>
                        </c:forEach>
                        <tr>
                            <td colspan="8" style="text-align: center;">
                                <a class="text-link" href="#" data-toggle="modal" data-target="#addProductToList">
                                    <i class="fa fa-plus-circle">Aggiungi prodotto alla lista</i>
                                </a>
                                <div class="modal fade" id="addProductToList" tabindex="-1" role="dialog" aria-labelledby="addProductToListLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content shadow">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Aggiungi prodotto alla lista</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <jsp:include page="showProducts.jsp" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>

            </div>
            --%>
            
            <%-- con questa versione invece non ci sono problemi di grafica, però secondo me è più brutto --%>
            <div class="container mt-4">

                <table class="table table-striped table-borderless">
                    <thead>
                        <tr>
                            <th style="text-align: right;" scope="col" ></th>
                            <th scope="col">Nome prodotto</th>
                            <th scope="col">Note</th>
                            <th scope="col" style="text-align: right;">Quantità</th>
                            <th style="text-align: center;" scope="col">Gestisci quantità</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="counterPL" value="1"></c:set>
                        <c:forEach items="${Prodotto}" var="prodotto">
                            <tr>
                                <td data-toggle="collapse" data-target="#accordion" class="clickable"><i class="fas fa-angle-down"></i></td>
                                <td scope="col">${prodotto[0]}</td>
                                <td scope="col">${prodotto[1]}</td>
                                <td scope="col" style="text-align: right;">${prodotto[6]}</td>
                                <td style="text-align: center;">
                                    <table class="table table-striped table-borderless">
                                        <tbody>
                                            <tr>
                                                <form action="insertProductServlet" method="POST">
                                                    <input type="hidden" value="${selectedListToChangeProduct}" name="selectedListToChangeProduct">
                                                    <input type="hidden" value="1" name="scelta">
                                                    <button type="submit" title="Diminuisci la quantità" name="changeProduct" value="${prodotto[7]}">
                                                        <i class="fa fa-minus-circle" aria-hidden="true"></i>
                                                    </button>
                                                </form>
                                                <form action="insertProductServlet" method="POST">
                                                    <input type="hidden" value="${selectedListToChangeProduct}" name="selectedListToChangeProduct">
                                                    <input type="hidden" value="2" name="scelta">
                                                    <button type="submit" title="Togli questo prodotto dalla lista" name="changeProduct" value="${prodotto[7]}">
                                                        <i class="fas fa-trash"></i>
                                                    </button>
                                                </form>
                                                <form action="insertProductServlet" method="POST">
                                                    <input type="hidden" value="${selectedListToChangeProduct}" name="selectedListToChangeProduct">
                                                    <input type="hidden" value="3" name="scelta">
                                                    <button type="submit" title="Aumenta la quantità" name="changeProduct" value="${prodotto[7]}">
                                                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                                    </button>
                                                </form>
                                                
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            <tr class="collapse" id="accordion">
                                <th scope="col"></th>
                                <th scope="col">Logo</th>
                                <th scope="col">Foto</th>
                                <th scope="col">Categoria</th>
                                <th scope="col">Proprietario prodotto</th>
                            </tr>
                            <tr class="collapse" id="accordion">
                                <td scope="col"></td>
                                <td scope="col">${prodotto[2]}</td>
                                <td scope="col">${prodotto[3]}</td>
                                <td scope="col">${prodotto[4]}</td>
                                <td scope="col">${prodotto[5]}</td>
                            </tr>
                            <tr class="collapse" id="accordion">
                                <td colspan="8"><hr align="center" size="5" width="1000" color="#000000"></td>
                            </tr>
                            <tr class="collapse" id="accordion">
                                <th scope="col"></th>
                                <th scope="col">Nome prodotto</th>
                                <th scope="col">Note</th>
                                <th scope="col" style="text-align: right;">Quantità</th>
                                <th style="text-align: center;" scope="col">Gestisci quantità</th>
                            </tr>
                            
                            <c:set var="counterPL" value="${counterPL+1}"></c:set>
                        </c:forEach>
                            <tr>
                                <td colspan="8" style="text-align: center;">
                                    <a class="text-link" href="#" data-toggle="modal" data-target="#addProductToList">
                                        <i class="fa fa-plus-circle">Aggiungi prodotto alla lista</i>
                                    </a>
                                    <div class="modal fade" id="addProductToList" tabindex="-1" role="dialog" aria-labelledby="addProductToListLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content shadow">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">Aggiungi prodotto alla lista</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <jsp:include page="showProducts.jsp" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                    </tbody>
                </table>

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
