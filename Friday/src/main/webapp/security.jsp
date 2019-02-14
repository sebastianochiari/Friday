<!--
    Friday - Shopping List Manager
    Copyright (C) 2018-2019 Tommaso Bosetti, Sebastiano Chiari, Leonardo Remondini, Marta Toniolli
-->

<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">

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

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css" />

    </head>

    <body id="top">

        <c:if test="${!boolEmailSessionScriptlet}">
            <c:redirect url="/error.jsp"/>
        </c:if>
        
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
                                
                        <!-- START: pannello laterale -->
                        <div id="aside" class="col-md-3">
                            <!-- aside widget -->
                            <div class="aside">

                                <h3 class="aside-title">Il mio account:</h3>
                                <ul class="list-links">
                                    <li><a href="myaccount.jsp">Il mio account</a></li>
                                    <li class="active"><a href="#">Impostazioni di sicurezza</a></li>
                                    <li><a href="adminSection.jsp">Sezione Admin</a></li>
                                </ul>

                            </div>
                        </div>
                        <!-- END: pannello laterale -->

                        <!-- START: main -->
                        <div id="main" class="col-md-9">

                            <!-- START: my account -->
                            <div id="store">

                                <h4>Impostazioni di sicurezza</h4>
                                <p>
                                    Attraverso questa pagina, potrai gestire il tuo account nei minimi dettagli
                                </p>
                                <div class="mt-4" id="accordion">

                                    <!-- START: modifica delle informazioni personali -->
                                    <div id="breadcrumb" class="mt-4">
                                        <div class="mt-4" id="personalArea">
                                            <a class="cart-toggle" data-toggle="collapse" href="#collapsePersonalInfo" role="button" data-target="#collapsePersonalInfo" aria-expanded="true" aria-controls="collapsePersonalInfo">
                                                <h5 class="mb-0" style="display: inline-block;">Modifica informazioni personali</h5>
                                            </a>
                                        </div>

                                        <c:set var="errorPresentPersonal" value="${requestScope.errorPresentPersonal}"></c:set>

                                        <c:if test="${errorPresentPersonal ne 'errorPresentPersonal'}">
                                            <c:set var="typeCollapse" value="collapse pb-4"></c:set>
                                        </c:if>
                                        <c:if test="${errorPresentPersonal eq 'errorPresentPersonal'}">
                                            <c:set var="typeCollapse" value="collapse pb-4 show"></c:set>
                                        </c:if>

                                        <div id="collapsePersonalInfo" class="${typeCollapse}" aria-labelledby="personalArea" data-parent="#accordion">
                                            <div class="card-body">
                                                <p>Tramite questa finestra di dialogo, potrai modificare la tua informazioni personali</p>

                                                <c:set var="oldName" value="${nameUserSession}"></c:set>
                                                <c:set var="oldSurname" value="${surnameUserSession}"></c:set>

                                                <form method="POST" action="securityServlet" enctype="application/x-www-form-urlencoded">
                                                    <div class="form-group">
                                                        <div class="row">
                                                            <div class="col">
                                                                <label for="newName">Nome</label>
                                                                <input name="newName" type="text" class="form-control" id="newName" placeholder="${oldName}" required="true">
                                                            </div>
                                                            <div class="col">
                                                                <label for="newSurname">Cognome</label>
                                                                <input name="newSurname" type="text" class="form-control" id="newSurname" placeholder="${oldSurname}" required="true">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="hidden" name="typeError" value="null">
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="hidden" name="changePersonal" value="security.jsp">
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="hidden" name="typeChange" value="personal">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="newAvatar">Aggiorna il tuo avatar</label>
                                                        <input type="file" name ="newAvatar" accept=".jpg, .jpeg, .png" id="newAvatar">
                                                    </div>
                                                    <button type="submit" class="btn std-button">Conferma</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- END: modifica delle informazioni personali -->

                                    <!-- START: modifica dell'email -->
                                    <div id="breadcrumb" class="mt-4">
                                        <div class="mt-4" id="emailArea">
                                            <a class="cart-toggle" data-toggle="collapse" href="#collapseEmail" role="button" data-target="#collapseEmail" aria-expanded="true" aria-controls="collapseEmail">
                                                <h5 class="mb-0" style="display: inline-block;">Modifica e-mail</h5>
                                            </a>
                                        </div>

                                        <c:set var="errorPresentEmail" value="${requestScope.errorPresentEmail}"></c:set>

                                        <c:if test="${errorPresentEmail ne 'errorPresentEmail'}">
                                            <c:set var="typeCollapse" value="collapse pb-4"></c:set>
                                        </c:if>
                                        <c:if test="${errorPresentEmail eq 'errorPresentEmail'}">
                                            <c:set var="typeCollapse" value="collapse pb-4 show"></c:set>
                                        </c:if>

                                        <div id="collapseEmail" class="${typeCollapse}" aria-labelledby="emailArea" data-parent="#accordion">
                                            <div class="card-body">
                                                <p>Tramite questa finestra di dialogo, potrai modificare la tua e-mail</p>
                                                <form method="POST" action="securityServlet" enctype="application/x-www-form-urlencoded">

                                                    <c:set var="errorOldEmail" value="${requestScope.errorOldEmail}"></c:set>
                                                    <c:set var="errorInputEmail" value="${requestScope.errorInputEmail}"></c:set>
                                                    <c:set var="errorInputEmailFormat" value="${requestScope.errorInputEmailFormat}"></c:set>
                                                    <c:set var="errorConfirmEmail" value="${requestScope.errorConfirmEmail}"></c:set>
                                                    <c:set var="inputEmail" value="${requestScope.inputEmail}"></c:set>
                                                    <c:set var="oldEmail" value="${requestScope.oldEmail}"></c:set>
                                                    <c:set var="errorPassword" value="${requestScope.errorPassword}"></c:set>

                                                    <div class="form-group">
                                                        <label for="oldEmail">Inserisci l'email attuale</label>
                                                        <c:if test="${errorOldEmail eq null}">
                                                            <input name="oldEmail" id="oldEmail" class="form-control security-form" aria-describedby="passwordHelpInline" placeholder="mario.rossi@gmail.com" required="true">
                                                        </c:if>
                                                        <c:if test="${errorOldEmail eq 'errorOldEmail'}">
                                                            <input name="oldEmail" id="oldEmail" class="form-control is-invalid security-form" aria-describedby="passwordHelpInline" placeholder="mario.rossi@gmail.com" value="${oldEmail}" required="true">
                                                            <div class="invalid-feedback">
                                                                ATTENZIONE! L'email inserita non è quella attualmente in uso. Riprovare con l'email corretta. 
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="inputNewEmail">Inserisci la nuova e-mail</label>
                                                        <c:if test="${errorInputEmail eq null}">
                                                            <input name="inputNewEmail" id="inputNewEmail" class="form-control security-form" aria-describedby="passwordHelpInline" placeholder="mario.rossi@gmail.com" value="${inputEmail}" required="true">
                                                        </c:if>
                                                        <c:if test="${errorInputEmail eq 'errorInputEmail'}">
                                                            <input name="inputNewEmail" id="inputNewEmail" class="form-control is-invalid security-form" aria-describedby="passwordHelpInline" value="${inputEmail}" required="true">
                                                        </c:if>
                                                        <div class="invalid-feedback">
                                                            <c:if test="${errorInputEmailFormat eq 'noErrorInputEmailFormat'}">
                                                                ATTENZIONE! L'email inserita è attualmente in uso. Riprovare con un'altra email. 
                                                            </c:if>
                                                            <c:if test="${errorInputEmailFormat eq 'errorInputEmailFormat'}">
                                                                ATTENZIONE! Formato email non valido. Inserire un'email valida.  
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">   
                                                        <label for="confirmEmail">Conferma la nuova e-mail</label>
                                                        <c:if test="${errorConfirmEmail eq null}">
                                                            <input name="confirmEmail" id="confirmEmail" class="form-control security-form" aria-describedby="passwordHelpInline" required="true">
                                                        </c:if>
                                                        <c:if test="${errorConfirmEmail eq 'errorConfirmEmail'}">
                                                            <input name="confirmEmail" id="confirmEmail" class="form-control is-invalid security-form" aria-describedby="passwordHelpInline" required="true">
                                                            <div class="invalid-feedback">
                                                                ATTENZIONE! Le email inserite non combaciano. Fai attenzione a copiare correttamente l'email inserito qui sopra.  
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="password">Inserisci la tua password</label>
                                                        <a href="#" class="text-link">
                                                            <p class="footer-info"><small>Hai dimenticato la password?</small></p>
                                                        </a>
                                                        <c:if test="${errorPassword eq null}">                                                        
                                                            <input name="password" type="password" class="form-control security-form johnCena" id="password" required="true" aria-describedby="passwordHelpInline" required="true">
                                                        </c:if>
                                                        <c:if test="${errorPassword eq 'errorPassword'}">
                                                            <input name="password" type="password" class="form-control is-invalid security-form johnCena" id="password" required="true" aria-describedby="passwordHelpInline" required="true">
                                                            <div class="invalid-feedback">
                                                                ATTENZIONE! La password inserita non è quella attualmente in uso. Riprovare con la password corretta. 
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                    <div class="form-check mb-3">
                                                        <input class="form-check-input" type="checkbox" onclick="revealPsw()" id="showInputEmail">
                                                        <label class="form-check-label" for="showInputEmail">Mostra password</label>
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
                                    </div>
                                    <!-- END: modifica dell'email -->

                                    <!-- START: modifica della password -->
                                    <div id="breadcrumb" class="mt-4">
                                        <div class="mt-4" id="passwordArea">
                                            <a  class="cart-toggle" data-toggle="collapse" href="#collapsePsw" data-target="#collapsePsw" aria-expanded="true" aria-controls="collapsePsw">
                                                <h5 class="mb-0" style="display: inline-block;">Modifica password</h5>
                                            </a>
                                        </div>

                                        <c:set var="errorPresentPassword" value="${requestScope.errorPresentPassword}"></c:set>

                                        <c:if test="${errorPresentPassword ne 'errorPresentPassword'}">
                                            <c:set var="typeCollapse" value="collapse pb-4"></c:set>
                                        </c:if>
                                        <c:if test="${errorPresentPassword eq 'errorPresentPassword'}">
                                            <c:set var="typeCollapse" value="collapse pb-4 show"></c:set>
                                        </c:if>

                                        <div id="collapsePsw" class="${typeCollapse}" aria-labelledby="passwordArea" data-parent="#accordion">
                                            <div class="card-body">
                                                <p>Tramite questa finestra di dialogo, potrai modificare la tua password</p>
                                                <form method="POST" action="securityServlet" enctype="application/x-www-form-urlencoded">

                                                    <c:set var="errorPreviousPassword" value="${requestScope.errorPreviousPassword}"></c:set>
                                                    <c:set var="errorInputPassword" value="${requestScope.errorInputPassword}"></c:set>
                                                    <c:set var="errorConfirmPassword" value="${requestScope.errorConfirmPassword}"></c:set>

                                                    <div class="form-group">
                                                        <label for="previousPassword">Inserisci la tua password attuale</label>
                                                        <a href="#" class="text-link">
                                                            <p class="footer-info"><small>Hai dimenticato la password?</small></p>
                                                        </a>
                                                        <c:if test="${errorPreviousPassword eq null}"> 
                                                            <input name="previousPassword" type="password" class="form-control security-form johnCena" id="previousPassword" required="true" aria-describedby="passwordHelpInline" required="true">
                                                        </c:if>
                                                        <c:if test="${errorPreviousPassword eq 'errorPreviousPassword'}">
                                                            <input name="previousPassword" type="password" class="form-control is-invalid security-form johnCena" id="previousPassword" required="true" aria-describedby="passwordHelpInline" required="true">
                                                            <div class="invalid-feedback">
                                                                ATTENZIONE! La password inserita non è quella attualmente in uso. Riprovare con la password corretta.
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                    <div class="form-group">
                                                        <c:if test="${errorInputPassword eq null}">
                                                            <label for="inputNewPassword">Inserisci la tua nuova password</label>
                                                            <input name="inputNewPassword" type="password" class="form-control security-form johnCena" id="inputNewPassword" required="true" aria-describedby="passwordHelpInline" required="true">
                                                            <p class="footer-info"><small>La password deve essere composta da almeno 6 caratteri, di cui almeno una maiuscola e da un numero o un carattere speciale</small></p>
                                                        </c:if>
                                                        <c:if test="${errorInputPassword eq 'errorInputPassword'}">
                                                            <label for="inputNewPassword">Inserisci la tua nuova password</label>
                                                            <input name="inputNewPassword" type="password" class="form-control is-invalid security-form johnCena" id="inputNewPassword" required="true" aria-describedby="passwordHelpInline" required="true">
                                                            <div class="invalid-feedback">
                                                                ATTENZIONE! La password non rispetta i parametri richiesti. Ricordati di inserire almeno 6 caratteri, di cui almeno una lettera maiuscola e almeno un numero o un carattere speciale. 
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                    <div class="form-group">
                                                        <c:if test="${errorConfirmPassword eq null}">
                                                            <label for="confirmPassword">Conferma la nuova password</label>
                                                            <input name="confirmPassword" type="password" class="form-control security-form johnCena" id="confirmPassword" required="true" aria-describedby="passwordHelpInline" required="true">
                                                        </c:if>
                                                        <c:if test="${errorConfirmPassword eq 'errorConfirmPassword'}">
                                                            <label for="confirmPassword">Conferma la nuova password</label>
                                                            <input name="confirmPassword" type="password" class="form-control is-invalid security-form johnCena" id="confirmPassword" required="true" aria-describedby="passwordHelpInline" required="true">
                                                            <div class="invalid-feedback">
                                                                ATTENZIONE! Le due password non coincidono.
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                    <div class="form-check mb-3">
                                                        <input class="form-check-input" type="checkbox" onclick="revealPsw()" id="showInputPassword">
                                                        <label class="form-check-label" for="showInputPassword">Mostra password</label>
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
                                    <!-- END: modifica della password -->

                                </div>

                            </div>
                            <!-- END: my account -->

                        </div>
                        <!-- END: main -->

                    </div>

                </div>

            </div>

        </main>
        <!-- END: parte principale -->

        <!-- footer -->
        <jsp:include page="jsp/components/footer.jsp" />

        <!-- JS Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

        <!-- personal JS -->
        <script type="text/javascript" src="js/main.js"></script>

    </body>

</html>