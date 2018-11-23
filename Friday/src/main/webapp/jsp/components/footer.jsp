<%@page contentType="text/html" pageEncoding="UTF-8"%>
    
    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Hind:400,700" rel="stylesheet">
    
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
                        
                        <!-- LOGO IMAGE -->
                        <img class="footer-logo" src="images/friday_icon_black.png" alt="logo">
                        
                        <!-- SOCIAL LINKS -->
                        <ul class="footer-social mt-4">
                            <li><a href="https://www.youtube.com/watch?v=kfVsfOSbJY0" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
                            <li><a href="https://www.youtube.com/watch?v=pi00ykRg_5c" target="_blank"><i class="fab fa-twitter"></i></a></li>
                            <li><a href="https://www.youtube.com/watch?v=kfVsfOSbJY0" target="_blank"><i class="fab fa-instagram"></i></a></li>
                            <li><a href="https://www.youtube.com/watch?v=pi00ykRg_5c" target="_blank"><i class="fab fa-google-plus-g"></i></a></li>
                            <li><a href="https://www.youtube.com/watch?v=kfVsfOSbJY0" target="_blank"><i class="fab fa-pinterest-p" ></i></a></li>
                        </ul>
                    </div>
                </div>
                
                <div class="col-md-3 col-sm-6 col-xs-6">
                    <div class="footer">
                        <h6 class="footer-header">IL MIO ACCOUNT</h6>
                        <ul class="list-links">
                            
                            <!-- se l'utente non è ancora loggato, questa rimanda alla pagina di login, altrimenti ai rispettivi link -->
                            <li><a href="myaccount.jsp">Il mio account</a></li>
                            
                            <li><a href="#">Le mie liste</a></li>
                            
                            <!-- se l'utente è già loggato, questo non deve comparire -->
                            <li>
                                <c:if test="${!boolEmailSessionTrue}">
                                    <form action="logoutServlet" method="POST">
                                        <button type="submit" class="btn displayCenter login-btn">Logout</button>
                                    </form>
                                </c:if>
                            </li>
                        </ul>
                    </div>
                </div>
                
                <div class="col-md-3 col-sm-6 col-xs-6">
                    <div class="footer">
                        <h6 class="footer-header">AIUTO</h6>
                        <ul class="list-links">
                            <li><a href="faq.jsp">FAQ</a></li>
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

        <!-- COPYRIGHT INFO -->
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