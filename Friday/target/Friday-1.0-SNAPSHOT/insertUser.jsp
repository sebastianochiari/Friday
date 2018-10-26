<%-- 
    Document   : insertUser
    Created on : 12-ott-2018, 10.40.00
    Author     : tommi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
           
        <script type="text/javascript">
            
                var stile = "top=10, left=10, width=250, height=200, status=no, menubar=no, toolbar=no scrollbars=no";
                function mostraMessaggio(email) {
                    
                    
                    <sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/fridaydb?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" user="root" password="root"/>;
                    var query = "SELECT * FROM users WHERE Email = "+email+";";
                    <sql:query dataSource="${snapshot}" var="result" sql=${query}>
                    </sql:query>
                    if (result !== null) {
                        window.alert("Email già registrata. Esegui il login oppure cambia email di registrazione.");
                    }
                    
                }
            
        </script>
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
                    <h3>Registrati</h3>
                    
                    <!-- enctype="application/x-www-form-urlencoded" -->
                
                    <form method="POST" action="insertUserServlet" enctype="application/x-www-form-urlencoded" >
                        <c:set var="servlet" value="${param.originServlet}"></c:set>
                        <div>
                            <div class="form-group">
                                <label for="Name">Nome</label>
                                <input name="name" type="text" class="form-control" id="name" placeholder="Mario">
                            </div>
                        </div>
                        <div>
                            <div class="form-group">
                                <label for="Surname">Cognome</label>
                                <input name="surname" type="text" class="form-control" id="surname" placeholder="Rossi">
                            </div>
                        </div>
                         <div>
                            <div class="form-group">
                                <input type="hidden" name="originServlet" value="insertUserServlet.java">
                            </div>
                            <div class="form-group">
                                <input type="hidden" name="registerForm" value="insertUser.jsp">
                            </div>
                        </div>
                        
                       
                        
                        <div class="row form-group">
                            <div class="col">
                                
                             <c:if test="${servlet eq null} ">
                                <label for="Email">* Email</label>
                                <input name="email" type="text" class="form-control" id="email" placeholder="mario.rossi@esempio.it" required="true" aria-describedby="emailHelp">
                                 </c:if>
                                <c:if test="${servlet eq 'insertUserServlet.java'}">
                                     <label for="Email">* Email</label>
                                <input name="email" type="text" class="form-control" id="email" placeholder="mario.rossi@esempio.it" required="true" aria-describedby="emailHelp">
                                    <div class="invalid-feedback">
                                        ATTENZIONE! L'email inserita è già utilizzata. Scegli un'altra email oppure esegui il login se sei già registrato.
                                    </div>
                                </c:if>
                     
                            </div>
                        </div>
                        <div class="form-group">
                            
                            
                          
                            
                            <c:if test="${servlet eq null}">
                                 <label for="Password">* Password</label>
                            <input name="password" type="password" class="form-control" id="password" required="true">
                            <p class="footer-info">La password deve essere composta da almeno 6 caratteri, di cui almeno una maiuscola e da un numero o un carattere speciale</p>
                            </c:if>
                            <c:if test="${servlet eq 'insertUserServlet.java'}">
                                <label for="Password">* Password</label>
                            <input name="password" type="password" class="form-control is-invalid" id="password" required="true">
                                <div class="invalid-feedback">
                                    ATTENZIONE! La password non rispetta i parametri richiesti. Ricordati di inserire almeno 6 caratteri, di cui almeno una lettere maiuscola e almeno un numero o un carattere speciale. 
                                </div>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label for="Password1">* Conferma password</label>
                            <input name ="password1" type="password" class="form-control" id="password1" require="true">
                            
                        </div>
                        <div class="row">
                            <div class="col-sm">
                                <label for="Avatar">Avatar</label>
                                <input name ="avatar" type="file" accept=".jpg, .jpeg, .png" id="avatar"> 
                            </div>
                        </div>
                        <div class="form-group form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1" required="true">
                            <label class="form-check-label" for="exampleCheck1">
                                * Dichiaro di aver preso visione e di accettare integralmente la nostra <a href="#" class="">informativa sulla privacy</a>. I campi contrassegnati con * sono obbligatori. 
                            </label>
                        </div>
                        <div>
                            <br>
                        </div>
                        <div>
                            <div class="col-sm">
                                <button type="submit" class="btn displayCenter login-btn" onclick="mostraMessaggio(email)">Registrati</button>
                            </div>
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
