<!--
    Friday - Shopping List Manager
    Copyright (C) 2018-2019 Tommaso Bosetti, Sebastiano Chiari, Leonardo Remondini, Marta Toniolli
-->
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                        
                        <!-- START: pannello laterale -->
                        <div id="aside" class="col-md-3">
                            <!-- aside widget -->
                            <div class="aside">

                                <h3 class="aside-title">Il mio account</h3>
                                <ul class="list-links">
                                    <li><a href="myaccount.jsp">Il mio account</a></li>
                                    <li><a href="security.jsp">Impostazioni di sicurezza</a></li>
                                    <li class="active"><a href="#">Sezione Admin</a></li>
                                </ul>

                            </div>

                        </div>
                        <!-- END: pannello laterale -->
                        
                            <!-- START: main -->
                            <div id="main" class="col-md-9">
                                
                                <!-- START: gestione database -->
                                <div id="store">

                                    <h4>Gestione database</h4>
                                    <c:if test="${adminUserSession}">
                                        <c:set var="RedirectAfterProduct" value="${1}" scope="session"></c:set>
                                        <p>
                                            Tramite questa pagina, puoi esercitare i tuoi poteri da <b>admin</b> che Friday ti ha conferito.
                                            <br>
                                            Usali con giudizio, da grandi poteri derivano grandi responsabilità
                                        </p>

                                        <p>
                                            <a class="text-link" href="#" data-toggle="modal" data-target="#addProduct">Crea un nuovo prodotto</a>
                                        </p>
                                        <c:set var="goodInsertProduct" value="requestScope.goodInsertProduct"></c:set>
                                        <c:if test="${!goodInsertProduct}">
                                            <div class="modal fade" id="addProduct" tabindex="-1" role="dialog" aria-labelledby="addProductLabel" aria-hidden="true">
                                        </c:if>
                                        <c:if test="${goodInsertProduct}">
                                            <div class="modal fade" id="addProduct" tabindex="-1" role="dialog" aria-labelledby="addProductLabel" aria-hidden="true" style="display: block">
                                        </c:if>
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content shadow">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title">Crea un nuovo prodotto</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <jsp:include page="insertProduct.jsp" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <p>
                                            <a class="text-link" href="#" data-toggle="modal" data-target="#addProductCategory">Crea una nuova categoria di prodotti</a>
                                        </p>
                                        <c:set var="goodInsertProductCategory" value="requestScope.goodInsertProductCategory"></c:set>
                                        <c:if test="${!goodInsertProductCategory}">
                                            <div class="modal fade" id="addProductCategory" tabindex="-1" role="dialog" aria-labelledby="addProductCategoryLabel" aria-hidden="true">
                                        </c:if>
                                        <c:if test="${goodInsertProductCategory}">
                                            <div class="modal fade" id="addProductCategory" tabindex="-1" role="dialog" aria-labelledby="addProductCategoryLabel" aria-hidden="true" style="display: block">
                                        </c:if>  
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content shadow">
                                                     <div class="modal-header">
                                                         <h5 class="modal-title">Crea una nuova categoria di prodotti</h5>
                                                         <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                             <span aria-hidden="true">&times;</span>
                                                         </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <jsp:include page="insertProductCategory.jsp" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <p>
                                            <a class="text-link" href="#" data-toggle="modal" data-target="#addListCategory">Crea una nuova categoria di lista</a>
                                        </p>
                                        <c:set var="goodInsertShoppingListCategory" value="requestScope.goodInsertShoppingListCategory"></c:set>
                                        <c:if test="${!goodInsertShoppingListCategory}">
                                            <div class="modal fade" id="addListCategory" tabindex="-1" role="dialog" aria-labelledby="addListCategoryLabel" aria-hidden="true">
                                        </c:if>
                                        <c:if test="${goodInsertShoppingListCategory}">
                                            <div class="modal fade" id="addListCategory" tabindex="-1" role="dialog" aria-labelledby="addListCategoryLabel" aria-hidden="true" style="display: block">
                                        </c:if>
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content shadow">
                                                     <div class="modal-header">
                                                         <h5 class="modal-title">Crea una nuova categoria di lista</h5>
                                                         <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                             <span aria-hidden="true">&times;</span>
                                                         </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <jsp:include page="insertShoppingListCategory.jsp" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </c:if>
                                    <c:if test="${!adminUserSession}">
                                        <p>
                                            <a class="text-link mt-4" href="#" data-toggle="modal" data-target="#newAdmin">Diventa Admin</a>
                                        </p>
                                        <div class="modal fade" id="newAdmin" tabindex="-1" role="dialog" aria-labelledby="newAdmin" aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-centered" role="document">
                                                <div class="modal-content shadow">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title">
                                                            Diventa Admin
                                                        </h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form method="POST" action="securityServlet" enctype="application/x-www-form-urlencoded">
                                                            <div class="form-group">
                                                                <c:if test="${errorPassword eq null}">
                                                                    <label for="password">Password <strong>*</strong></label>
                                                                    <input name="password" type="password" class="form-control security-form johnCena" id="passwordAdmin" required="true" aria-describedby="passwordHelpInline"  required="true">
                                                                </c:if>
                                                                <c:if test="${errorPassword eq 'errorPassword'}">
                                                                    <input type="password" class="form-control is-invalid security-form johnCena" id="passwordforAdmin" name="passwordforAdmin" required="true">
                                                                    <div class="invalid-feedback">
                                                                        ATTENZIONE! La password è errata.
                                                                    </div>
                                                                </c:if>
                                                            </div>
                                                            <div class="form-group form-check">
                                                                <input class="form-check-input" type="checkbox" onclick="revealPsw()" id="showInput">
                                                                <label class="form-check-label" for="showInput">Mostra password</label>
                                                            </div>
                                                            <div class="form-group">
                                                                <div class="form-group form-check">
                                                                    <input type="checkbox" class="form-check-input" id="exampleCheck1" required="true">
                                                                    <label class="form-check-label" for="exampleCheck1">
                                                                        <small>
                                                                            Dichiaro di aver preso visione e di accettare integralmente la nostra <a href="#" class="text-link">informativa sulla privacy</a> <strong>*</strong>
                                                                        </small>
                                                                    </label>
                                                                </div>
                                                                <small><strong> I campi contrassegnati con * sono obbligatori. </strong></small>
                                                            </div>
                                                            <div class="form-group">
                                                                <input type="hidden" name="typeChange" value="admin">
                                                                <button type="submit" class="btn std-button">Diventa Admin</button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                                <!-- END: gestione database -->

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
