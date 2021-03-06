<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                            
                    </div>
                </div>

                <div class="col-md-3 col-sm-6 col-xs-6">
                    <div class="footer">
                        <h6 class="footer-header">IL MIO ACCOUNT</h6>
                        <ul class="list-links">

                           <!-- se l'utente non è ancora loggato, questa rimanda alla pagina di login, altrimenti ai rispettivi link -->
                            <c:if test="${emailSession eq null}">
                                <li><a href="login.jsp">Il mio account</a></li>
                            </c:if>    
                            
                            <c:if test="${emailSession ne null}">
                                <li><a href="myaccount.jsp">Il mio account</a></li>
                            </c:if> 
                                
                            <li>
                                <%--
                                <form action="handlingListServlet" method="GET">
                                    <button type="submit" value="0" class="" name="selectedList" >
                                        Le mie liste
                                    </button>
                                </form>
                                --%>
                                <!-- diciamo che questa soluzione non è proprio elegante -->
                                <a href="handlingListServlet?selectedList=0">Le mie liste</a>
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

            </div>
            <br>
        </div>

        <!-- COPYRIGHT INFO -->
        <div class="displayCenter">

            <p class="footer-copyright">
                Friday.com, Inc o società affiliate
            </p>
            <p class="footer-copyright">
                COPYRIGHT ©2018 | ALL RIGHTS RESERVED
                <br>
                Tommaso Bosetti, Sebastiano Chiari, Leonardo Remondini, Marta Toniolli
            </p>

        </div>

    </footer>
    <!-- END: footer -->
