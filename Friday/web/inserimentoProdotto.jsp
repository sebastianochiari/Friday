<%-- 
    Document   : inserimentoProdotto
    Created on : 28-set-2018, 11.35.35
    Author     : tommi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <link rel="shortcut icon" type="image/png" href="images/favicon.png">

        <title>Friday Inserimento Prodotto</title>

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
                    <h3>Crea prodotto</h3>
                    <form method="GET" action="ProdottoServlet" enctype="multipart/form-data">
                        <div class="row form-group">
                            <div class="col">
                                <label>Nome del prodotto</label>
                                <input type="text" class="form-control" name="product">
                            </div>
                        </div>
                        <div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Note aggiuntive</label>
                                <textarea name="description" class="form-control" rows="3"  maxlength="250" id="description"></textarea>
                            </div>
                        </div>
                        <div>
                            <div class="col-sm">
                                <label for="productCategory">Scegli la categoria di appartenza del prodotto</label>
                                
  <!-- Qui abbiamo la versione in JSTL (moooooolto meglio). se si fa così bisogna mettere una sola FridayServletProdotto -->
                                        <sql:setDataSource var="fridayLocal" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/FridayLocal?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" user="root" password="appa">
      
                                        </sql:setDataSource>
  
  <!-- DA SISTEMARE! -->
                                
                                    <sql:query dataSource="${fridayLocal}" var="result" sql="SELECT * FROM fridaydb.product_categories;">
                                        
                                    </sql:query>
                                    
                                        
                                    <select name="productCategories" class="form-control">
                                         <option disabled selected value>Seleziona un'opzione</option>
                                         <c:forEach var="res" items="${result.rows}" begin="0">
                                             
                                             <option value="${res.Name}"> <c:out value="${res.Name}"/> </option>
                                         </c:forEach>
                                         
                                    </select>
                                
  <!-- Sotto abbiamo la versione spaghetti-code in JSP (meglio evitare) -->
                                <%--<%
                                    String[] productCategories = request.getParameterValues("name");
                                %>
                                <select name="productCategory" class="form-control">
                                    <option disabled selected value>Seleziona un'opzione</option>
                                    
                                <%
                                    for (int i = 0; i < productCategories.length; i++){
                                %> 
 <!-- !!! BISOGNA CONTROLLARE QUESTA COSA DEL SYSTEM.OUT.PRINT !!! -->
                                        <option value=" <% System.out.print(i); %> "> <% System.out.print(productCategories[i]); %></option>
                                    <% } %>
                                    <!--
                                    <option disabled selected value>Seleziona un'opzione</option>
                                    <option value="1">Verdure</option>
                                    <option value="2">Carne</option>
                                    <option value="3">Prodotti per la casa</option>
                                    <option value="4">Abbigliamento</option>
                                    <option value="5">Surgelati</option>
                                    <option value="6">sdfdsfsd</option>
                                </select>
--> --%>
                            </div>
                        </div>
                        <div>
                            <br>
                        </div>
                        <div class="row">
                            <div class="col-sm">
                                <label for="logo">Carica il logo del prodotto</label>
                                <input type="file" name ="productLogo" accept=".jpg, .jpeg, .png" id="productPhoto">
                            </div>
                        </div>
                        <div>
                            <br>
                        </div>
                        <div class="row">
                                <div class="col-sm">
                                    <label for="photo">Carica la foto del prodotto</label>
                                    <input type="file" name ="productPhoto" accept=".jpg, .jpeg, .png" id="productPhoto">
                                </div>
                        </div>
                        <div>
                            <br>
                        </div>
                        <div>
                            <div class="col-sm">
                                <button type="submit" class="btn displayCenter login-btn">Crea prodotto</button>
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
