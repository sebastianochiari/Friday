<%-- 
    Document   : registrationAccepted
    Created on : 11-Nov-2018, 14:49:40
    Author     : leo97
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.*"%>
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

        <!-- START: header -->
        <nav id="breadcrumb" class="navbar">
            <div class="container">
                <div class="float-left">
                        <span>Benvenuto su <b>Friday</b>, l'innovativo gestore di <b>liste della spesa</b></span>
                </div>
                <div class="float-right">
                    <ul class="header-top-links">
                        <li><a href="#">Newsletter</a></li>
                        <li><a href="faq.html">FAQ</a></li>
                        <li><a href="login.jsp">Login</a></li>
                        <li><a href="insertUser.jsp">Registrati</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- END: header -->            

        <!-- START: main navbar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <div class="header-logo float-left">
                    <a href="#" alt="logo">
                            <img src="images/friday_icon_colored.png" class="logo">
                        </a>
                </div>

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item dropdown nav-category">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                CATEGORIE
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink1">
                                <a class="dropdown-item" href="#">Alimentari</a>
                                <a class="dropdown-item" href="#">Ferramenta</a>
                                <a class="dropdown-item" href="#">Sport e Benessere</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown nav-category">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-user nav-link-icon"></i>
                                Il mio account
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink2">
                                <a class="dropdown-item" href="myaccount.jsp">Il mio account</a>
                                <c:if test="${boolEmailSessionTrue}">
                                    <a class="dropdown-item" href="login.jsp">Login</a>
                                    <a class="dropdown-item" href="insertUser.jsp">Crea un'account</a>
                                </c:if>
                            </div>
                        </li>
                        <li class="nav-item dropdown nav-category">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-shopping-cart nav-link-icon"></i>
                                Le mie liste
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink3">
                                <a class="dropdown-item" href="#">Lista #1</a>
                                <a class="dropdown-item" href="#">Lista #2</a>
                                <a class="dropdown-item" href="#">Gestisci liste</a>
                            </div>
                        </li>
                    </ul>
                    <div>
                        <a href="#" class="shopping-link" style="margin-right: 5px;">
                            <i class="fas fa-envelope shopping-icon"></i>
                        </a>
                        <a href="#" class="shopping-link">
                            <i class="fas fa-shopping-cart shopping-icon"></i>
                        </a>
                    </div>
                </div>
            </div>
        </nav>
        <!-- END: main navbar -->

        <!-- START: search navbar -->
        <nav id="breadcrumb" class="navbar navbar-expand-lg navbar-light bg-light" style="padding-top: 0px;">
            <div class="container mb-1">
                <form action="searchServlet" method="GET">
                    <div class="row">
                        <div class="col mt-1 nav-col">
                            <div class="col-sm">
                   
                                <sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/fridaydb?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" user="root" password="root"/>

                                <sql:query dataSource="${snapshot}" var="result" sql="SELECT * FROM product_categories;">   
                                </sql:query>
                                        
                                <select name="inputCategory" class="form-control">
                                    <option value="-1">Tutte le Categorie</option>
                                    <c:forEach var="res" items="${result.rows}" >
                                        <option value="${res.PCID}"> <c:out value="${res.Name}"/> </option>
                                    </c:forEach>
                                </select>
                                
                            </div>
                        </div>
                                    
                        <div class="col-md mt-1 nav-col">
                            <input class="form-control nav-search" type="text" placeholder="Cerca" name="inputSearch">
                        </div>
                        <div>
                            <button type="submit" class="btn displayCenter login-btn">Search</button>
                        
                        </div>
                        </div>
                </form>
            </div>
        </nav>
        <!-- END: search navbar -->

        <!-- START: main carousel -->
        <main>
            
            <div class="container">
                <br>
                <span>Conferma di registrazione ultimata! Effettua nuovamente il login per accedere al magico mondo di Friday! </span>
            </div>

        </main>
        <!-- END: main carousel -->

        <!-- START: footer -->
        <footer id="footer" class="section section-grey mt-4" style="padding-top: 0rem;">
            <div id="breadcrumb">
                <div class="container">
                    <a href="#top">
                        <h6 style="text-align: center; padding: 1rem 0rem 1rem 0rem;">Torna su</h6>
                    </a>
                </div>
            </div>

            <div class="container pt-3">
                <div class="row">
                    <div class="col-md-3 col-sm-6 col-xs-6">
                        <div class="footer">
                            <img class="footer-logo" src="images/friday_icon_black.png" alt="logo">
                            <ul class="footer-social mt-4">
                                <li><a href="https://www.youtube.com/watch?v=kfVsfOSbJY0" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
                                <li><a href="https://www.youtube.com/watch?v=pi00ykRg_5c" target="_blank"><i class="fab fa-twitter"></i></a></li>
                                <li><a href="https://www.youtube.com/watch?v=kfVsfOSbJY0" target="_blank"><i class="fab fa-instagram"></i></a></li>
                                <li><a href="https://www.youtube.com/watch?v=pi00ykRg_5c" target="_blank"><i class="fab fa-google-plus-g"></i></a></li>
                                <li><a href="https://www.youtube.com/watch?v=kfVsfOSbJY0" target="_blank"><i class="fab fa-pinterest-p"></i></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-6">
                        <div class="footer">
                            <h6 class="footer-header">IL MIO ACCOUNT</h6>
                            <ul class="list-links">
                                <li><a href="myaccount.jsp">Il mio account</a></li>
                                <li><a href="#">Le mie liste</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-6">
                        <div class="footer">
                            <h6 class="footer-header">AIUTO</h6>
                            <ul class="list-links">
                                <li><a href="faq.html">FAQ</a></li>
                                <li><a href="#">Informativa sui Cookie</a></li>
                                <li><a href="#">Informativa sulla Privacy</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-6">
                        <div class="footer">
                            <h6 class="footer-header">RIMANI CONNESSO</h6>
                            <form>
                                <div class="form-group">
                                    <input class="input" placeholder="Enter Email Address">
                                </div>
                                    <button class="btn std-button">Join newsletter</button>
                            </form>
                        </div>
                    </div>
                </div>

                <hr>

            </div>

            <div class="displayCenter">
                <p class="footer-copyright">COPYRIGHT ©2018 | ALL RIGHTS RESERVED, Friday.com, Inc o società affiliate</p>
            </div>

        </footer>
        <!-- END: footer -->

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
