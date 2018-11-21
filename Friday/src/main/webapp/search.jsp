<%-- 
    Document   : search
    Created on : 7-nov-2018, 16.04.34
    Author     : marta & remo 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
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
                                <li><a href="#">Alimentari</a></li>
                                <li><a href="#">Ferramenta</a></li>
                                <li><a href="#">Sport e benessere</a></li>
                                <li class="active"><a href="#">Scuola</a></li>
                            </ul>

                        </div>

                    </div>

                    <!-- START: main -->
                    <div id="main" class="col-md-9">

                        <!-- START: store -->
                        <div id="store">
                            
                            <!-- ORDINAMENTO RICERCA -->
                            <form class="form-inline">
                                <span class="text-uppercase mr-3">ordina:</span>
                                <select class="form-control" id="exampleFormControlSelect1">
                                    <option>per categoria</option>
                                    <option>alfabeticamente</option>
                                </select>
                            </form>
                            
<!--
                            <p class="mt-3">
                                Ci dispiace, <b>la ricerca non ha prodotto alcun tipo di risultato</b>.
                                <br>
                                Prova a <b>ritentare</b> cambiando qualche parola oppure <b>aggiungi il prodotto che desideri</b>.
                                <a href="#" class="text-link" data-toggle="modal" data-target="#addProductModal">Clicca qui</a>
                            </p>
-->

                            <div class="row">

                                <div class="col-md-4 col-sm-6 col-xs-6">
                                    <div class="product product-single">
                                        <div class="product-thumb">
                                            <div class="product-label">
                                                <span>Scuola</span>
                                            </div>
                                            <img src="images/prodotti/colla.JPG" alt="">
                                        </div>
                                        <div class="product-body">
                                            <h2 class="product-name">Pritt, colla stick</h2>
                                            <p class="product-description">Ideale per lavori di artigianato su carta, cartone, foto ed etichette. Senza solvente e PVC. </p>
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

                                <div class="col-md-4 col-sm-6 col-xs-6">
                                    <div class="product product-single">
                                        <div class="product-thumb">
                                            <div class="product-label">
                                                <span>Scuola</span>
                                            </div>
                                            <img src="images/prodotti/forbice.jpg" alt="">
                                        </div>
                                        <div class="product-body">
                                            <h2 class="product-name">Forbice</h2>
                                            <p class="product-description">Forbice con impugnatura morbida e lame in titanio, 20 cm</p>
                                            <div class="btn-group mt-auto" role="group" aria-label="Button group with nested dropdown">
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

                                <div class="col-md-4 col-sm-6 col-xs-6">
                                    <div class="product product-single">
                                        <div class="product-thumb">
                                            <div class="product-label">
                                                <span>Scuola</span>
                                            </div>
                                            <img src="images/prodotti/bic.jpg" alt="">
                                        </div>
                                        <div class="product-body">
                                            <h2 class="product-name">Bic Crystal Original</h2>
                                            <p class="product-description">
                                                Punta ad ago 0.7 mm
                                                <br>
                                                Inchiostro blu, nero e rosso
                                                <br>
                                                Tratto da 0.30 mm
                                            </p>
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

                            </div>

                            <div class="row">

                                <div class="col-md-4 col-sm-6 col-xs-6">
                                    <div class="product product-single">
                                        <div class="product-thumb">
                                            <div class="product-label">
                                                <span>Scuola</span>
                                            </div>
                                            <img src="images/prodotti/evidenziatore.jpg" alt="">
                                        </div>
                                        <div class="product-body">
                                            <h2 class="product-name">Evidenziatori Stabilo</h2>
                                            <p class="product-description">Evidenziatore dotato di tecnologia Stabilo Anti-Dry-Out: resiste fino a 4 ore senza cappuccio</p>
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

                                <div class="col-md-4 col-sm-6 col-xs-6">
                                    <div class="product product-single">
                                        <div class="product-thumb">
                                            <div class="product-label">
                                                <span>Scuola</span>
                                            </div>
                                            <img src="images/prodotti/pennarelli.jpg" alt="">
                                        </div>
                                        <div class="product-body">
                                            <h2 class="product-name">Pennarelli Stabilo</h2>
                                            <p class="product-description">
                                                STABILO Pen 68 Color Parade Pennarelli colori assortiti
                                                <br>
                                                Scatola da 20, colore brillante e luminoso
                                            </p>
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

                                <div class="col-md-4 col-sm-6 col-xs-6">
                                    <div class="product product-single">
                                        <div class="product-thumb">
                                            <div class="product-label">
                                                <span>Categoria</span>
                                            </div>
                                            <img src="images/prodotti/colla.JPG" alt="">
                                        </div>
                                        <div class="product-body">
                                            <h2 class="product-name">Inserire nome prodotto</h2>
                                            <p class="product-description">Inserire la descrizione del prodotto</p>
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

