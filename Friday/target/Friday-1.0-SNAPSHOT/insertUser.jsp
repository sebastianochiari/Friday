<%-- 
    Document   : insertUser
    Created on : 12-ott-2018, 10.40.00
    Author     : tommi
--%>

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
                    <form method="GET" action="insertUserServlet" enctype="multipart/form-data">
                        <div class="row form-group">
                            <div class="col">
                                <label for="Email">Email</label>
                                <input name="email" type="text" class="form-control" id="email" placeholder="mario.rossi@esempio.it">
                            </div>
                        </div>
                        <div>
                            <div class="form-group">
                                <label for="Password">Password</label>
                                <input name="password" type="password" class="form-control" id="password">
                            </div>
                        </div>
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
                        <div class="row">
                            <div class="col-sm">
                                <label for="Avatar">Avatar</label>
                                <input name ="avatar" type="file" accept=".jpg, .jpeg, .png" id="avatar">
                            </div>
                        </div>
                        <div>
                            <br>
                        </div>
                        <div>
                            <div class="col-sm">
                                <button type="submit" class="btn displayCenter login-btn">Crea Utente</button>
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
