<%-- 
    Document   : insertUser
    Created on : 12-ott-2018, 10.40.00
    Author     : tommi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <link rel="shortcut icon" type="image/png" href="images/favicon.png">

        <title>Friday Inserimento Nuovo Utente</title>

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
                    <h3>Crea nuovo utente</h3>
                    <form action="insertUserServlet" method="POST">
                        <div class="row form-group">
                            <div class="col">
                                <label for="exampleInputEmail1">Nome</label>
                                <input type="text" class="form-control" name="Name1">
                            </div>
                            <div class="col">
                                <label for="exampleInputEmail1">Cognome</label>
                                <input type="text" class="form-control" name="Surname1">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">E-mail</label>
                            <input type="email" class="form-control" name="exampleInputEmail1" aria-describedby="emailHelp">
                        </div>
                        <div class="form-group">
                            
                            <c:set var="servlet" value="${param.origin}"></c:set>
                                <c:out value="${servlet}"></c:out>
                                
                                <c:if test="${servlet eq 'insertUserServlet'}">
                            <label for="exampleInputPassword1">Password</label>
                            <input type="password" class="form-control is-invalid" name="exampleInputPassword1">
                            
                            </c:if>
                                <c:if test="${servlet eq null}">
                            
                            <label for="exampleInputPassword1">Password</label>
                            <input type="password" class="form-control" name="exampleInputPassword1">
                            </c:if>
                            
                            <p class="footer-info">La password deve essere composta da almeno 6 caratteri, di cui almeno una maiuscola e un numero o un carattere speciale</p>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Conferma password</label>
                            <input type="password" class="form-control" name="exampleInputPassword1">
                        </div>
                        <div class="form-group form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">
                                Dichiaro di aver preso visione e di accettare integralmente la nostra <a href="#" class="">Informativa sulla privacy</a>.
                            </label>
                        </div>
                        <div class="row">
                            <div class="col-sm mt-1 mb-1">
                                <button type="submit" class="btn displayCenter login-btn">Registrati</button>
                            </div>
                            <div class="col-sm mt-1 mb-1">
                                <button type="button" onclick="goBack()" class="btn displayCenter login-btn">Annulla</button>
                            </div>
                        </div>
                        <p class="mt-4">Hai già un account Friday? <a href="login.html" class="text-link">Accedi</a></p>
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
