<%-- 
    WebProgramming Project - Shopping List 
    2017-2018
    Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
--%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<!DOCTYPE html>
<html lang="it">

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


    <main>

        <div id="breadcrumb">
            <div class="container pt-4 pb-1">
                <h4>FAQ - Frequently Asked Questions</h4>
            </div>
        </div>

        <div class="container">

            <br>

            <h5>Q: Che cos'è Friday?</h5>
            <p>
                <b>A: </b>Friday è un innovativo <b>gestore di liste della spesa</b>. Stanco/a di riempirti di foglietti di carta di vario genere che poi perderai proprio davanti al banco frigo? Stanco/a di arrivare al supermercato e perdere ore davanti agli scaffali perché non ti ricordi che cosa manca nella tua dispensa?
                <br>
                Grazie a <b>Friday</b>, potrai gestire le tue liste della spesa e avere sempre a portata di mano tutto ciò che ti serve per districarti non solo nel labirinto del tuo supermercato preferito, ma anche tra gli scaffali di una ferramenta, o nelle fila di un vivaio. <b>Crea la tua lista personalizzata</b>, aggiungi gli elementi che necessitano di essere comprati, <b>condividi</b> le tue liste <b>con gli amici</b> per organizzare il party del secolo. <b>Con Friday, non mancherà più niente perché ti ricorderai sempre cosa ti serve e cosa no</b>.
                <br>
                Prova Friday,
                
                    <c:if test="${!boolEmailSessionScriptlet}">
                        <a class="text-link" href="login.jsp"><b>crea il tuo account</b></a> <b>gratuitamente</b>
                    </c:if>
            
                    
            </p>

            <br>

            <h5>Q: Quante liste posso creare?</h5>
            <p>
                <b>A: </b> Tutte quelle di cui hai bisogno. Friday è un servizio completamente gratuito che non prevede limitazioni in base a un abbonamento. Lascia spazio ai tuoi bisogni. Noi siamo qui per aiutarti.
            </p>

            <br>

            <h5>Q: E se non trovo un prodotto?</h5>
            <p>
                <b>A: </b>Nessun problema. Nel caso la tua ricerca non abbia prodotto nessun risultato, puoi comodamente aggiungere il prodotto che desideri tramite una funzione specifica da barra di ricerca, oppure cliccando qui. Ricordati però che tutti i prodotti che aggiungerai saranno visualizzabili e utilizzabili solo da te e dalle persone con cui deciderai di condividere una lista contenente quel prodotto.
                <br>
                Per una questione di sicurezza e di controllo dei servizi, ci riserviamo di non rendere pubblici tutti i prodotti inseriti dall'utente. Stiamo comunque lavorando per aggiornare e ampliare la scelta di possibilità che Friday offre ai suoi utenti. Grazie per contribuire a rendere questo posto migliore
            </p>

            <br>

            <h5>Q: Posso modificare l'email e/o la password?</h5>
            <p>
                <b>A: </b>Certamente. Una volta effettuato l'accesso, recati nella sezione privata e all'interno di "gestisci account" potrai effettuare tutti i cambiamenti che desideri. Più semplice di così, si muore.
            </p>

            <br>

            <h5>Q: Come verranno trattati i miei dati?</h5>
            <p>
                <b>A: </b> BOH
                <br>
                Per maggiori informazioni, ti invitiamo a leggere la nostra <b>Informativa sulla privacy</b>.
            </p>

            <br>

        </div>

    </main>



    <!-- Footer -->
        <jsp:include page="jsp/components/footer.jsp" />
    <!-- problemi in footer, se non sono loggata ho comunque pulsante logout -->
        
        

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

