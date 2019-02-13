<!--
    Friday - Shopping List Manager
    Copyright (C) 2018-2019 Tommaso Bosetti, Sebastiano Chiari, Leonardo Remondini, Marta Toniolli
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
    	<link rel="shortcut icon" type="image/png" href="images/favicon.png">

        <title>Friday</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <!-- Personal stylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css" />
        
    </head>

    <body>

        <div class="container">

            <!-- LOGO Friday -->
            <a href="indexServlet">
                <div class="logo-header">
                    <img class="displayCenter auto-size" src="images/friday_icon_colored.png" alt="logo">
                </div>
            </a>

            <div class="displayCenter">
                <h4 class="mt-5">Ops! Qualcosa è andato storto.</h4>
                <p>
                    Sembra che qualcosa durante l'esecuzione non sia andato a buon fine.
                    <br>
                    Abbiamo sguinzagliato i nostri migliori segugi per scovare il problema.
                </p>
                <p>
                    Nel frattempo, per ritornare all'home page, <a href="indexServlet" class="text-link">clicca qui</a>.
                </p>
            </div>

        </div>

        <!-- JS Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

        <!-- personal JS -->
        <script type="text/javascript" src="js/main.js"></script>

    </body>

</html>
