<%-- 
    Document   : search
    Created on : 7-nov-2018, 16.04.34
    Author     : marta & remo & tommi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

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
                            <sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/fridaydb?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" user="root" password="root81097"/>
                            <sql:query dataSource="${snapshot}" var="result" sql="SELECT * FROM product_categories;"></sql:query>
                             
<<<<<<< HEAD
                          
                            
                            <form action="searchServlet" method ="GET">
                                 <c:forEach var="res" items="${result.rows}" >
                                       <%-- <input type="hidden" value ="${res.PCID}" name ="selectedPCategory"> --%>
                                       <button type="submit" value ="${res.PCID}" class="dropdown-item" name ="selectedPCategory" >
                                           ${res.Name}
                                       </button>
                                 </c:forEach>
                            </form>
                                
                                
                                
                                
                                
                                
=======
                            <c:forEach var="res" items="${result.rows}" >
                                      
                                <li class="active" ><a href="faq.jsp"> ${res.Name} </a> </li>
                                <%-- <input type="hidden" value ="${res.PCID}" name ="selectedPCategory"> --%>
                                       
                            </c:forEach>
>>>>>>> l-back-end
                            
                              
                            </ul>

                        </div>

                    </div>

                    <!-- START: main -->
                    <div id="main" class="col-md-9">

                        <!-- START: store -->
                        <div id="store">
                            
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink1" name="navbarDropdownMenuLink1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                ORDINAMENTO
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink1">
                                <form action="searchServlet" method ="GET">
                                    <button type="submit" value ="alfabeticamente" class="dropdown-item" name ="order" id="order">
                                        alfabeticamente
                                    </button>
                                    <button type="submit" value ="categoria" class="dropdown-item" name ="order" id="order">
                                        per categoria
                                    </button>
                                </form>
                            </div>
                            
                            
                            
                            
                            
                            <%-- ORDINAMENTO RICERCA
                            <form class="form-inline" method="GET" action="searchServlet">
                                <span class="text-uppercase mr-3">ordina:</span>
                                <select class="form-control" id="exampleFormControlSelect1" name="exampleFormControlSelect1">
                                    <option>
                                        <button type="submit" value ="alfabeticamente" class="dropdown-item" name ="order" >
                                            alfabeticamente
                                        </button>
                                    </option>
                                    <option>       
                                        <button type="submit" value ="per categoria" class="dropdown-item" name ="order" >
                                            per categoria
                                        </button>
                                    </option>
                                </select>
                            </form>
                            --%>                           
                            
<!--
                            <p class="mt-3">
                                Ci dispiace, <b>la ricerca non ha prodotto alcun tipo di risultato</b>.
                                <br>
                                Prova a <b>ritentare</b> cambiando qualche parola oppure <b>aggiungi il prodotto che desideri</b>.
                                <a href="#" class="text-link" data-toggle="modal" data-target="#addProductModal">Clicca qui</a>
                            </p>
-->

                            <div class="row">
                                
                                <c:forEach items="${resultSearch}" var="prodotto">
                                    <div class="col-md-4 col-sm-6 col-xs-6">
                                        <div class="product product-single">
                                            <div class="product-thumb">
                                                <div class="product-label">
                                                    <span>${prodotto[5]}</span>
                                                </div>
                                                <img src="images/prodotti/${prodotto[4]}" alt="">
                                            </div>
                                            <div class="product-body">
                                                <h2 class="product-name">${prodotto[1]}</h2>
                                                <p class="product-description">${prodotto[2]}</p>
                                                <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                                                    <button type="button" class="btn std-button add-list-button">Aggiungi alla lista</button>
                                                    <div class="btn-group" role="group">
                                                        <button id="btnGroupDrop1" type="button" class="btn std-button add-list-button round-right" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                            <i class="fas fa-angle-down"></i>
                                                        </button>
                                                        <div class="dropdown-menu dropdown-list" aria-labelledby="btnGroupDrop1">
                                                        <a class="dropdown-item" href="#">Dropdown link</a>
                                                        <a class="dropdown-item" href="#">Dropdown link</a>
                                                            <div class="create-list">
                                                                <a class="dropdown-item" href="#"><i class="fas fa-plus mr-2"></i>Crea Lista</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>

                           
                            
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
                                        <div class="modal-body">
                                            <h5>Aggiungi il prodotto</h5>
                                            <br>
                                            <button type="button" class="btn std-button" data-dismiss="modal">Fine</button>
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

</body>

</html>

