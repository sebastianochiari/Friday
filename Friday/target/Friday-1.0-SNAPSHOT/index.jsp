<%-- 
    Document   : index
    Created on : 19-ott-2018, 10.35.28
    Author     : tommi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            <a class="dropdown-item" href="#">Il mio account</a>
                            <a class="dropdown-item" href="login.jsp">Login</a>
                            <a class="dropdown-item" href="insertUser.jsp">Crea un'account</a>
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
                    </div>
            </form>
        </div>
    </nav>
    <!-- END: search navbar -->

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
            <div id="breadcrumb" class="mt-4">
                <a class="cart-toggle" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                    <h5 style="display: inline-block;">La mia lista</h5>
                    <p style="display: inline-block;"><i>predefinita</i></p>
                </a>
                <div class="collapse" id="collapseExample">
                    <div class="card card-body">
                        <div class="cart-carousel">
                            <div>
                                <img class="cart-image" src="images/prodotti/bic.jpg">
                                <a href="#" class="btn std-button displayCenter">Aggiungi alla lista</a>
                            </div>
                            <div>
                                <img class="cart-image" src="images/prodotti/colla.JPG">
                                <a href="#" class="btn std-button displayCenter">Aggiungi alla lista</a>
                            </div>
                            <div>
                                <img class="cart-image" src="images/prodotti/evidenziatore.jpg">
                                <a href="#" class="btn std-button displayCenter">Aggiungi alla lista</a>
                            </div>
                            <div>
                                <img class="cart-image" src="images/prodotti/forbice.jpg">
                                <a href="#" class="btn std-button displayCenter">Aggiungi alla lista</a>
                            </div>
                            <div>
                                <img class="cart-image" src="images/prodotti/pennarelli.jpg">
                                <a href="#" class="btn std-button displayCenter">Aggiungi alla lista</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END: personal shopping cart -->

            <div class="mt-4">
                <h5>Prodotti scelti per te</h5>
                <div class="cart-carousel">
                    <div>
                        <img class="cart-image" src="images/prodotti/bic.jpg">
                        <a href="#" class="btn std-button displayCenter">Aggiungi alla lista</a>
                    </div>
                    <div>
                        <img class="cart-image" src="images/prodotti/colla.JPG">
                        <a href="#" class="btn std-button displayCenter">Aggiungi alla lista</a>
                    </div>
                    <div>
                        <img class="cart-image" src="images/prodotti/evidenziatore.jpg">
                        <a href="#" class="btn std-button displayCenter">Aggiungi alla lista</a>
                    </div>
                    <div>
                        <img class="cart-image" src="images/prodotti/forbice.jpg">
                        <a href="#" class="btn std-button displayCenter">Aggiungi alla lista</a>
                    </div>
                    <div>
                        <img class="cart-image" src="images/prodotti/pennarelli.jpg">
                        <a href="#" class="btn std-button displayCenter">Aggiungi alla lista</a>
                    </div>
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
                            <li><a href="login.jsp">Login</a></li>
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
