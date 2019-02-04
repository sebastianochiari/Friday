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

        <%
            (request.getSession()).setAttribute("emailSession", null);
            (request.getSession()).setAttribute("cookieIDSession", null);
            (request.getSession()).setAttribute("nameUserSession", null);

            String DBUrl = MySQLDAOFactory.getDBUrl();
            String DBUser = MySQLDAOFactory.getDBUser();
            String DBPass = MySQLDAOFactory.getDBPass();
            String DBDriver = MySQLDAOFactory.getDBDriver();

            (request.getSession()).setAttribute("DBUrlSession", DBUrl);
            (request.getSession()).setAttribute("DBUserSession", DBUser);
            (request.getSession()).setAttribute("DBPassSession", DBPass);
            (request.getSession()).setAttribute("DBDriverSession", DBDriver);

            Cookie[] cookies = request.getCookies();
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet result = null;
            boolean boolEmailSession = false;

            try {
                connection = MySQLDAOFactory.createConnection();
                preparedStatement = connection.prepareStatement("SELECT * FROM cookies;");
                preparedStatement.execute();
                result = preparedStatement.getResultSet();

                if(cookies != null){

                    while (result.next()) {

                        for(int i=0; i<cookies.length; i++){

                            System.out.println("browser cookie = "+cookies[i].getValue()+"  db cookie = "+result.getString("cookieID"));
                            if((cookies[i].getValue()).equals(result.getString("cookieID"))){

                                String emailSession = result.getString("Email");
                                (request.getSession()).setAttribute("emailSession", emailSession);
                                (request.getSession()).setAttribute("cookieIDSession", result.getString("cookieID"));
                                (request.getSession()).setAttribute("deadlineSession", result.getString("Deadline"));
                                (request.getSession()).setAttribute("LIDSession", result.getString("LID"));
                                System.out.println("zao sono dentro l'if e usersession = "+(String)(request.getSession()).getAttribute("emailSession")+" cookieID = "+(String)(request.getSession()).getAttribute("cookieIDSession"));

                                if (emailSession.equals(null)){
                                    boolEmailSession = false;
                                } else {
                                    boolEmailSession = true;
                                }

                                (request.getSession()).setAttribute("boolEmailSessionScriptlet", boolEmailSession);

                            }
                        }
                    }

                    preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE Email = ?;");
                    preparedStatement.setString(1, (String)(request.getSession()).getAttribute("emailSession"));
                    preparedStatement.execute();
                    result = preparedStatement.getResultSet();

                    while (result.next()) {
                        (request.getSession()).setAttribute("nameUserSession", result.getString("Name"));
                        (request.getSession()).setAttribute("surnameUserSession", result.getString("Surname"));
                        (request.getSession()).setAttribute("avatarUserSession", result.getString("Avatar"));
                        (request.getSession()).setAttribute("adminUserSession", result.getBoolean("Admin"));
                        (request.getSession()).setAttribute("list_OwnerUserSession", result.getBoolean("List_Owner"));
                        (request.getSession()).setAttribute("confirmedUserSession", result.getBoolean("Confirmed"));
                    }

                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    result.close();
                } catch (Exception rse) {
                    rse.printStackTrace();
                }
                try {
                    preparedStatement.close();
                } catch (Exception sse) {
                    sse.printStackTrace();
                }
                try {
                    connection.close();
                } catch (Exception cse) {
                    cse.printStackTrace();
                }
            }

        %>

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
                            <img class="d-block w-100" src="images/crea-account-friday.jpg" alt="Second slide">
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
                                <select name="selectedListToChangeProduct" class="form-group-sm">
                                    <c:forEach items="${ListUserSession}" var="lista">
                                        <option value="${lista[1]}">
                                            ${lista[0]}
                                        </option>
                                    </c:forEach>
                                    <c:forEach items="${SharingListUserSession}" var="listaCondivisa">
                                        <option value="${listaCondivisa[1]}">
                                            ${listaCondivisa[0]}
                                        </option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" value="4" name="scelta">
                            </i>
                        </p>
                        <a class="cart-toggle" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample"></a>
                        <div class="collapse" id="collapseExample">
                            <div class="card card-body">
                                <div class="cart-carousel">
                                    <c:forEach items="${resultSearch}" var="prodotto">
                                        <div>
                                            <img class="cart-image" src="images/prodotti/${prodotto[4]}">
                                            <button type="submit" title="Aggiungi Prodotto" name="changeProduct" value="${prodotto[0]}" class="btn std-button displayCenter">
                                                Aggiungi alla lista
                                            </button>
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
                            <sql:setDataSource var="snapshot1" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/fridaydb?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" user="root" password="root81097"/>
                            <sql:query dataSource="${snapshot1}" var="resultRand" sql="SELECT * FROM products order by RAND() LIMIT 5;"></sql:query>
                            <c:forEach items="${resultRand}" var="prodottoRand">
                                <div>
                                    <img class="cart-image" src="images/prodotti/${prodottoRand[4]}">
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
