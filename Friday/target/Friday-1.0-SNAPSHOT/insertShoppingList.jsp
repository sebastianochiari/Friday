<%-- 
    Document   : insertList
    Created on : 17-ott-2018, 7.26.23
    Author     : tommi
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<!DOCTYPE html>
<html lang="en">
    
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <link rel="shortcut icon" type="image/png" href="images/favicon.png">

        <title>Friday Inserimento Lista</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!-- Slick -->
        <!-- <link type="text/css" rel="stylesheet" href="css/slick.css" />
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css" /> -->

        <!-- nouislider -->
        <!-- <link type="text/css" rel="stylesheet" href="css/nouislider.min.css" /> -->

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <!-- Personal stylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css" />

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

    </head>

<body>
    
     
    
    <div class="container">

        <!-- LOGO Friday -->
        <div class="logo-header">
            <img class="displayCenter auto-size" src="images/friday_icon_colored.png" alt="logo">
        </div>

        <div class="width-30 displayCenter">
            <div class="card">
                <div class="card-body">
                    <h3>Crea lista della spesa</h3>
                    <form method="GET" action="insertShoppingListServlet" enctype="multipart/form-data">
                        <div class="row form-group">
                            <div class="col">
                                <label>Nome della lista della spesa</label>
                                <input type="text" class="form-control" name="name">
                            </div>
                        </div>
                        <div>
                            <div class="form-group">
                                <label for="description">Note aggiuntive</label>
                                <textarea name="note" class="form-control" rows="3"  maxlength="250" id="description"></textarea>
                            </div>
                        </div>
                        <div>
                            <div class="col-sm">
                                <label for="LCID">Scegli la categoria di appartenza della lista della spesa</label>
                                
  <!-- Qui abbiamo la versione in JSTL (moooooolto meglio). se si fa così bisogna mettere una sola ProdottoServlet -->                                
                                        <sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/fridaydb?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" user="root" password="root"/>

                                    <sql:query dataSource="${snapshot}" var="result" sql="SELECT * FROM list_categories;">   
                                    </sql:query>
                                        
                                    <select name="LCID" class="form-control">
                                        <option disabled selected value>Seleziona un'opzione</option>
                                        <c:forEach var="res" items="${result.rows}" >
                                            <option value="${res.LCID}"> <c:out value="${res.Name}"/> </option>
                                        </c:forEach>
                                    </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm">
                                <label for="logo">Carica il logo della lista della spesa</label>
                                <input type="file" name ="image" accept=".jpg, .jpeg, .png" id="logo">
                            </div>
                        </div>
                        <div>
                            <br>
                        </div>
                        <!-- l'email la prendiamo con i cookie -->
                        <div>
                            <br>
                        </div>
                        <div>
                            <div class="col-sm">
                                <button type="submit" class="btn displayCenter login-btn">Crea lista</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>

    </div>

    <br></br>

    <div class="footer">
        <!-- Informativa sulla Privacy - Informativa sui Cookie -->
        <div class="container">
            <div class="row">
                <div class="col-sm">
                </div>
                <div class="col-sm">
                    <a href="#" class="text-link">
                        <p class="displayCenter">Informativa sulla Privacy</p>
                    </a>
                </div>
                <div class="col-sm">
                    <a href="#" class="text-link">
                        <p class="displayCenter">Informativa sui Cookie</p>
                    </a>
                </div>
                <div class="col-sm">
                </div>
            </div>
            <div class="displayCenter">
                <p class="footer-info">© 2018, Friday.com, Inc o società affiliate</p>
            </div>
        </div>
        <br></br>
    </div>

</body>
    
</html>
