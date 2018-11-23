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
                                    <li class="active"><a href="#">Il mio account</a></li>
                                    <li><a href="security.jsp">Impostazioni di sicurezza</a></li>
                                    <li><a href="adminSection.jsp">Sezione Admin</a></li>
                                </ul>

                            </div>
                        </div>
                        <!-- END: pannello laterale -->

                        <!-- START: main -->
                        <div id="main" class="col-md-9">

                            <!-- START: my account -->
                            <div id="store">

                                <h4>Il mio account</h4>

                                <img class="account-image" src="images/icon/icon2.png" alt="account image">


                                <div class="mt-3">
                                    <div>
                                        <p style="display: inline-block;"><b>Nome: </b></p>
                                        <p class="ml-2" style="display: inline-block;">
                                            <c:out value=" ${nameUserSession}"></c:out>
                                        </p>
                                    </div>
                                    <div>
                                        <p style="display: inline-block"><b>Cognome: </b></p>
                                        <p class="ml-2" style="display: inline-block">
                                            <c:out value=" ${surnameUserSession}"></c:out>
                                        </p>
                                    </div>
                                    <div>
                                        <p style="display: inline-block"><b>Email: </b></p>
                                        <p class="ml-2" style="display: inline-block">
                                            <c:out value=" ${emailSession}"></c:out>
                                        </p>
                                        <p class="ml-2" style="display: inline-block">(<a class="text-link" href="security.jsp#emailArea">modifica e-mail</a>)</p>
                                    </div>
                                    <div>
                                        <p style="display: inline-block"><b>Password: </b></p>
                                        <p class="ml-2" style="display: inline-block">&#9679 &#9679 &#9679 &#9679 &#9679 &#9679 &#9679 &#9679 &#9679</p>
                                        <p class="ml-2" style="display: inline-block">(<a class="text-link" href="security.jsp#modifyPsw">modifica password</a>)</p>
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
        <!-- END: parte principale -->

        <!-- FOOTER -->
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