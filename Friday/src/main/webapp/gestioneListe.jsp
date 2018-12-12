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
    
    <!-- START: recupero delle liste dell'utente loggato -->
    <%
        Cookie[] cookies = request.getCookies();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
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

                            (request.getSession()).setAttribute("emailSession", result.getString("Email"));
                            (request.getSession()).setAttribute("cookieIDSession", result.getString("cookieID"));
                            (request.getSession()).setAttribute("deadlineSession", result.getString("Deadline"));
                            (request.getSession()).setAttribute("LIDSession", result.getString("LID"));
                            System.out.println("zao sono dentro l'if e usersession = "+(String)(request.getSession()).getAttribute("emailSession")+" cookieID = "+(String)(request.getSession()).getAttribute("cookieIDSession"));


                        }
                    }
                }

                preparedStatement = connection.prepareStatement("SELECT * FROM lists WHERE List_Owner = ?;");
                preparedStatement.setString(1, (String)(request.getSession()).getAttribute("emailSession"));
                preparedStatement.execute();
                result = preparedStatement.getResultSet();
                
                ShoppingListDAO shoppingListDAO = new MySQLShoppingListDAOImpl();
                
                List lists = null;
                lists = shoppingListDAO.getShoppingListsByOwner((String)(request.getSession()).getAttribute("emailSession"));

                String[][] searchListResult = new String[lists.size()][2];

                for(int i=0; i<lists.size(); i++){
                    searchListResult[i][0] = ((ShoppingList)(lists.get(i))).getName();
                    searchListResult[i][1] = Integer.toString(((ShoppingList)(lists.get(i))).getLID());
                }

                session.setAttribute("ListUserSession", searchListResult);
                session.setAttribute("ListUserSessionSize", lists.size());
                
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
    <!-- END: recupero delle liste dell'utente loggato -->
    
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

                            <h3 class="aside-title">Le mie liste:</h3>
                            <ul class="list-links">
                                
                                <form action="handlingListServlet" method="GET">
                                
                                    <c:set var="listaAttiva" value="${requestScope.listaAttiva}"></c:set>
                                    <!-- so che è codice ripetuto ma non so come fare altrimenti -->
                                    <c:if test="${listaAttiva eq 0}">
                                        <li><button type="submit" value="0" name="selectedList">
                                            Gestione Liste
                                        </button></li>
                                        <%--<li class="active"><a href="#" id="0">Gestione liste</a></li>--%>
                                        <c:set var="attiva0" value="active <- attiva0"></c:set>
                                    </c:if>
                                    <c:if test="${listaAttiva ne 0}">
                                         <li><button type="submit" value="0" name="selectedList">
                                            Gestione Liste
                                        </button></li>
                                        <%--
                                        <li><a href="#" id="0">Gestione liste</a></li>--%>
                                        <c:set var="attiva0" value="notActive <- attiva0"></c:set>
                                    </c:if>

                                    <c:forEach items="${ListUserSession}" var="lista">
                                        <c:if test="${listaAttiva eq lista[1]}">
                                            <li>
                                            <button type="submit" value="${lista[1]}" name="selectedList">
                                                ${lista[0]}
                                            </button>
                                            </li>
                                            <%--<li class="active"><a href="#" id="${lista[1]}">${lista[0]}</a></li>--%>
                                            <c:set var="attiva" value="active"></c:set>
                                        </c:if>
                                        <c:if test="${listaAttiva ne lista[1]}">
                                            <li>
                                            <button type="submit" value="${lista[1]}" name="selectedList">
                                                ${lista[0]}
                                            </button>
                                            </li>
                                            <%--
                                            <li><a href="#" id="${lista[1]}">${lista[0]}</a></li>--%>
                                            <c:set var="attiva" value="notActive"></c:set>
                                        </c:if> 


                                    </c:forEach>
                                </form>
                            </ul>

                            <h3 class="aside-title">Liste condivise:</h3>
                            <p>Non hai nessuna lista condivisa</p>
                            <ul class="list-links">
                                <li><a href="#">Lista #1</a></li>
                                <li><a href="#">Lista #2</a></li>
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
                                <a href="#" class="text-link">Clicca qui</a> nel caso tu voglia creare una nuova lista
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
                                    <tr>
                                        <th style="text-align: center;" scope="row">1</th>
                                        <td>Scuola Giovanni</td>
                                        <td>Scuola</td>
                                        <td style="text-align: center;">
                                            <i class="fas fa-check"></i>
                                        </td>
                                        <td style="text-align: center;">
                                            <a href="#" title="Elimina questa lista">
                                                <i class="fas fa-trash"></i>
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th style="text-align: center;" scope="row">2</th>
                                        <td>Lasagne</td>
                                        <td>Alimentari</td>
                                        <td style="text-align: center;">
                                            <i class="fas fa-times"></i>
                                        </td>
                                        <td style="text-align: center;">
                                            <a href="#" title="Elimina questa lista">
                                                <i class="fas fa-trash"></i>
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th style="text-align: center;" scope="row">3</th>
                                        <td>Beer Pong</td>
                                        <td>Alcolici</td>
                                        <td style="text-align: center;">
                                            <i class="fas fa-times"></i>
                                        </td>
                                        <td style="text-align: center;">
                                            <a href="#" title="Elimina questa lista">
                                                <i class="fas fa-trash"></i>
                                            </a>
                                        </td>
                                    </tr>
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
                                    <tr>
                                        <th style="text-align: center;" scope="row">1</th>
                                        <td>Scuola Giovanni</td>
                                        <td>Scuola</td>
                                        <td style="text-align: center;">
                                            <a href="#">
                                                <i class="fas fa-sign-out-alt"></i>
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th style="text-align: center;" scope="row">2</th>
                                        <td>Lasagne</td>
                                        <td>Alimentari</td>
                                        <td style="text-align: center;">
                                            <a href="#">
                                                <i class="fas fa-sign-out-alt"></i>
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th style="text-align: center;" scope="row">3</th>
                                        <td>Beer Pong</td>
                                        <td>Alcolici</td>
                                        <td style="text-align: center;">
                                            <a href="#">
                                                <i class="fas fa-sign-out-alt"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            
                        
                        </div>
                        <!-- END: list -->
                        
                        
                        <div id="prova" aria-labelledby="lista1">
                            ciao
                        </div>
                    
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
