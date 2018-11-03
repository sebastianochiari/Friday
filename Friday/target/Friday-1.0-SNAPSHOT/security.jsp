<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

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

    <title>Il mio account - Friday</title>

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

    <!-- START: main navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light section-grey">
        <div class="container">
            <div class="header-logo float-left">
                <a href="index.jsp">
                    <div class="logo-header">
                        <img class="displayCenter auto-size" src="images/friday_icon_colored.png" alt="logo">
                    </div>
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
                            <a class="dropdown-item" href="myaccount.html">Il mio account</a>
                            <c:if test="${emailSession eq null}">
                                <a class="dropdown-item" href="login.jsp">Login</a>
                                <a class="dropdown-item" href="insertUser.jsp">Crea un'account</a>
                            </c:if>
                            <c:if test="${emailSession ne null}">
                                <a class="dropdown-item">
                                    <div><small class="text-muted">Logged as</small></div>
                                    <div><c:out value="${emailSession}"></c:out></div>
                                </a>
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
                            <a class="dropdown-item" href="#">Liste condivise</a>
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
            <form>
                <div class="row">
                    <div class="col mt-1 nav-col">
                        <select class="form-control">
                            <option>Tutte le categorie</option>
                            <option>Alimentari</option>
                            <option>Ferramenta</option>
                            <option>Alcolici</option>
                        </select>
                    </div>
                    <div class="col-md mt-1 nav-col">
                        <input class="form-control nav-search" type="text" placeholder="Cerca">
                    </div>
                    <div class="col-md mt-1 nav-col">
                        <div class="float-right">
                            <c:if test="${emailSession ne null}">
                                <div class="float-right">
                                    <c:out value=" ${emailSession}"></c:out>
                                    <form action="logoutServlet" method="POST">
                                        <button type="submit" class="btn displayCenter login-btn">Logout</button>
                                    </form>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </nav>
    <!-- END: search navbar -->

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

                            <h3 class="aside-title">Il mio account:</h3>
                            <ul class="list-links">
                                <li><a href="myaccount.jsp">Il mio account</a></li>
                                <li class="active"><a href="#">Impostazioni di sicurezza</a></li>
                            </ul>

                        </div>

                    </div>

                    <!-- START: main -->
                    <div id="main" class="col-md-9">

                        <!-- START: my account -->
                        <div id="store">

                            <h4>Impostazioni di sicurezza</h4>

                            <p>
                                Attraverso questa pagina, potrai gestire il tuo account nei minimi dettagli
                            </p>
                            
                            <div id="breadcrumb" class="mt-4">
                                <a class="cart-toggle" data-toggle="collapse" href="#collapsePersonalInfo" role="button" aria-expanded="false" aria-controls="collapseExample">
                                    <h5 style="display: inline-block;">Modifica informazioni personali</h5>
                                </a>
                                <div class="collapse pb-4" id="collapsePersonalInfo">
                                    <p>Tramite questa finestra di dialogo, potrai modificare la tua informazioni personali</p>
                                <form>
                                    <div class="form-group">
                                        <div class="row">
                                        <div class="col">
                                            <input type="text" class="form-control" placeholder="Nome">
                                        </div>
                                        <div class="col">
                                            <input type="text" class="form-control" placeholder="Cognome">
                                        </div>
                                    </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="photo">Aggiorna il tuo avatar</label>
                                        <input type="file" name ="photo" accept=".jpg, .jpeg, .png" id="photo">
                                    </div>
                                    <button type="submit" class="btn std-button">Conferma</button>
                                </form>
                                </div>
                            </div>
                            
                            <div id="breadcrumb" class="mt-4">
                                <a class="cart-toggle" data-toggle="collapse" href="#collapseEmail" role="button" aria-expanded="false" aria-controls="collapseExample">
                                    <h5 style="display: inline-block;">Modifica e-mail</h5>
                                </a>
                                <div class="collapse pb-4" id="collapseEmail">
                                    <p>Tramite questa finestra di dialogo, potrai modificare la tua e-mail</p>
                                <form method="POST" action="securityServlet" enctype="application/x-www-form-urlencoded">
                                    
                                    <c:set var="errorInputEmail" value="${requestScope.errorInputEmail}"></c:set>
                                    <c:set var="errorConfirmEmail" value="${requestScope.errorConfirmEmail}"></c:set>
                                    <c:set var="inputEmail" value="${requestScope.inputEmail}"></c:set>
                                    
                                    <div class="form-group">
                                        
                                        <c:if test="${errorInputEmail eq null}">
                                            <label for="inputNewEmail">Inserisci la nuova e-mail</label>
                                            <input name="inputNewEmail" id="inputNewEmail" class="form-control security-form" aria-describedby="passwordHelpInline" placeholder="mario.rossi@gmail.com" value="${inputEmail}">
                                        </c:if>
                                        <c:if test="${errorInputEmail eq 'errorInputEmail'}">
                                            <label for="inputNewEmail">Inserisci la nuova e-mail</label>
                                            <input name="inputNewEmail" id="inputNewEmail" class="form-control is-invalid security-form" aria-describedby="passwordHelpInline" value="${inputEmail}">
                                            <div class="invalid-feedback">
                                                ATTENZIONE! L'email inserita è attualmente in uso. Riprovare con un'altra email. 
                                            </div>
                                        </c:if>
                                        
                                    </div>
                                    <div class="form-group">
                                        
                                        <c:if test="${errorConfirmEmail eq null}">
                                            <label for="confirmEmail">Conferma la nuova e-mail</label>
                                            <input name="confirmEmail" id="confirmEmail" class="form-control security-form" aria-describedby="passwordHelpInline">
                                        </c:if>
                                        <c:if test="${errorConfirmEmail eq 'errorConfirmEmail'}">
                                            <label for="confirmEmail">Conferma la nuova e-mail</label>
                                            <input name="confirmEmail" id="confirmEmail" class="form-control is-invalid security-form" aria-describedby="passwordHelpInline">
                                            <div class="invalid-feedback">
                                                ATTENZIONE! Le email inserite non combaciano. Fai attenzione a copiare correttamente l'email inserito qui sopra.  
                                            </div>
                                        </c:if>
                                            
                                    </div>
                                    <button type="submit" class="btn std-button">Conferma</button>
                                    <div class="form-group">
                                        <input type="hidden" name="typeError" value="null">
                                    </div>
                                    <div class="form-group">
                                        <input type="hidden" name="changeEmail" value="security.jsp">
                                    </div>
                                    <div class="form-group">
                                        <input type="hidden" name="typeChange" value="email">
                                    </div>
                                </form>
                                </div>
                            </div>
                            
                            <div id="breadcrumb" class="mt-4">
                                <a class="cart-toggle" data-toggle="collapse" href="#collapsePsw" role="button" aria-expanded="false" aria-controls="collapseExample">
                                    <h5 style="display: inline-block;">Modifica password</h5>
                                </a>
                                
                                <div class="collapse pb-4" id="collapsePsw">
                                    <p>Tramite questa finestra di dialogo, potrai modificare la tua password</p>
                                <form method="POST" action="securityServlet" enctype="application/x-www-form-urlencoded">
                                    
                                    <c:set var="errorPreviousPassword" value="${requestScope.errorPreviousPassword}"></c:set>
                                    <c:set var="errorInputPassword" value="${requestScope.errorInputPassword}"></c:set>
                                    <c:set var="errorConfirmPassword" value="${requestScope.errorConfirmPassword}"></c:set>
                                    
                                    <div class="form-group">
                                        
                                        <c:if test="${errorPreviousPassword eq null}">
                                            <label for="previousPassword">Inserisci la tua precendente password</label>
                                            <input name="previousPassword" type="password" class="form-control security-form johnCena" id="previousPassword" required="true" aria-describedby="passwordHelpInline">
                                        </c:if>
                                        <c:if test="${errorPreviousPassword eq 'errorPreviousPassword'}">
                                            <label for="previousPassword">Inserisci la tua precendente password</label>
                                            <input name="previousPassword" type="password" class="form-control is-invalid" id="previousPassword" required="true" aria-describedby="passwordHelpInline">
                                            <div class="invalid-feedback">
                                                ATTENZIONE! La password inserita non è quella attualmente in uso. Riprovare con la password corretta. 
                                            </div>
                                        </c:if>
                                        <a href="#" class="text-link">
                                            <p>Hai dimenticato la password?</p>
                                        </a>
                                            
                                    </div>
                                    <div class="form-group">
                                        
                                        <c:if test="${errorInputPassword eq null}">
                                            <label for="inputNewPassword">Inserisci la tua precendente password</label>
                                            <input name="inputNewPassword" type="password" class="form-control security-form johnCena" id="inputNewPassword" required="true" aria-describedby="passwordHelpInline">
                                            <p class="footer-info"><small>La password deve essere composta da almeno 6 caratteri, di cui almeno una maiuscola e da un numero o un carattere speciale</small></p>
                                        </c:if>
                                            
                                        <c:if test="${errorInputPassword eq 'errorInputPassword'}">
                                            <label for="inputNewPassword">Inserisci la tua precendente password</label>
                                            <input name="inputNewPassword" type="password" class="form-control is-invalid" id="inputNewPassword" required="true" aria-describedby="passwordHelpInline">
                                            <div class="invalid-feedback">
                                                ATTENZIONE! La password non rispetta i parametri richiesti. Ricordati di inserire almeno 6 caratteri, di cui almeno una lettere maiuscola e almeno un numero o un carattere speciale. 
                                            </div>
                                        </c:if>
                                        
                                    </div>
                                    <div class="form-group">
                                        
                                        <c:if test="${errorConfirmPassword eq null}">
                                            <label for="confirmPassword">Inserisci nuovamente la password</label>
                                            <input name="confirmPassword" type="password" class="form-control security-form johnCena" id="confirmPassword" required="true" aria-describedby="passwordHelpInline">
                                        </c:if>
                                            
                                        <c:if test="${errorConfirmPassword eq 'errorConfirmPassword'}">
                                            <label for="confirmPassword">Inserisci nuovamente la password</label>
                                            <input name="confirmPassword" type="password" class="form-control is-invalid" id="confirmPassword" required="true" aria-describedby="passwordHelpInline">
                                            <div class="invalid-feedback">
                                                ATTENZIONE! Le password non coincidono. Perfavore, inserisci nuovamente la tua password e fai attenzione nel riscriverla uguale la seconda volta.
                                            </div>
                                        </c:if>
                                    </div>
                                    <div class="form-check mb-3">
                                        <input class="form-check-input" type="checkbox" onclick="revealPsw()" id="showInput">
                                        <label class="form-check-label" for="showInput">Mostra password</label>
                                    </div>
                                    <div class="form-group">
                                        <input type="hidden" name="typeError" value="null">
                                    </div>
                                    <div class="form-group">
                                        <input type="hidden" name="changePassword" value="security.jsp">
                                    </div>
                                    <div class="form-group">
                                        <input type="hidden" name="typeChange" value="password">
                                    </div>
                                    <button type="submit" class="btn std-button">Conferma</button>
                                </form>
                                </div>
                            </div>

                        </div>
                        <!-- END: my account -->

                    </div>
                    <!-- END: main -->

                </div>

            </div>

        </div>

    </main>

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
                            <li><a href="#">Il mio account</a></li>
                            <li><a href="#">Le mie liste</a></li>
                            <li>
                                <c:if test="${emailSession eq null}">
                                    <a class="dropdown-item" href="login.jsp">Login</a>
                                </c:if>
                                <c:if test="${emailSession ne null}">
                                    <a class="dropdown-item">
                                        <div><small class="text-muted">Logged as</small></div>
                                        <div><c:out value="${emailSession}"></c:out></div>
                                    </a>
                                </c:if>
                            </li>
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
            <p class="footer-copyright">
                Friday.com, Inc o società affiliate
<!--
                <br>
                Telefono: +39 0123 456789
                Indirizzo: Via Sommarive, 9, Povo (TN)
                <br>
                Partita IVA: 01234561001 - C.F. 01234561001
-->
            </p>
            <p class="footer-copyright">
                COPYRIGHT ©2018 | ALL RIGHTS RESERVED
                <br>
                Tommaso Bosetti, Sebastiano Chiari, Leonardo Remondini, Marta Toniolli
            </p>
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
