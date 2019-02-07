<%-- 
    Document   : inserimentoProdotto
    Created on : 28-set-2018, 11.35.35
    Author     : leonardo
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
   

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
<%--
<table class="table table-striped table-borderless">
    <thead>
        <tr>
            <th scope="col">Nome prodotto</th>
            <th scope="col">Note</th>
            <th scope="col">Logo</th>
            <th scope="col">Foto</th>
            <th scope="col">Categoria</th>
            <th scope="col">Gestore prodotto</th>
            <th scope="col">Aggiungi prodotto</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${resultSearch}" var="prodotto">
            <tr>
                <td>${prodotto[1]}</td>
                <td>${prodotto[2]}</td>
                <td>${prodotto[3]}</td>
                <td>${prodotto[4]}</td>
                <td>${prodotto[5]}</td>
                <td>${prodotto[6]}</td>
                <td>
                    <form action="insertProductServlet" method="POST">
                        <input type="hidden" value="${selectedListToChangeProduct}" name="selectedListToChangeProduct">
                        <input type="hidden" value="4" name="scelta">
                        <button type="submit" title="Aggiungi Prodotto" name="changeProduct" value="${prodotto[0]}">
                            <i class="fa fa-plus-circle" aria-hidden="true"></i>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
    
--%>