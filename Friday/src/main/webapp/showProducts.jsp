<%-- 
    WebProgramming Project - Shopping List 
    2017-2018
    Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
   
<c:set var="listaCorrente" value="${listaCorrente}"></c:set>

<div id="store">
    <div class="row">
        <c:forEach items="${resultSearch}" var="prodotto">
            <div class="col-md-4 col-sm-6 col-xs-6">
                <div class="product product-single">
                    <div class="product-thumb">
                        <div class="product-label">
                            <span>${prodotto[5]}</span>
                        </div>
                        <img src="images/prodotti/${prodotto[4]}" alt="">
                    </div>
                    <div class="product-body">
                        <h2 class="product-name">${prodotto[1]}</h2>
                        <p class="product-description">${prodotto[2]}</p>
                        <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                            <form action="insertProductServlet" method="POST">
                                <input type="hidden" value="${listaCorrente[0]}" name="selectedListToChangeProduct">
                                <input type="hidden" value="4" name="scelta">
                                <button type="submit" title="Aggiungi Prodotto" name="changeProduct" value="${prodotto[0]}" class="btn std-button add-list-button" style="color: #F8694A;">
                                    <i class="fa fa-plus-circle" aria-hidden="true"></i> Aggiungi alla lista
                                </button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
    
    <div class="clearfix">
        <nav class="float-right" aria-label="Page navigation example">
            <ul class="pagination" id="pagin">
                <li class="page-item">
                  <a class="page-link" href="#" id="previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">0</span>
                  </a>
                </li>
                <li class="page-item">
                  <a class="page-link" href="#" id="next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">2</span>
                  </a>
                </li>
            </ul>
        </nav>
    </div>

</div>


<script type="text/javascript" src="js/pagination.js"></script>