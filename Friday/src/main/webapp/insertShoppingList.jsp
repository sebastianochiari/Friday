<%-- 
    WebProgramming Project - Shopping List 
    2017-2018
    Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form method="GET" action="insertShoppingListServlet" enctype="multipart/form-data">
    
    <div class="form-group">
        <label for="LCID">Scegli la categoria di appartenza del prodotto</label>
        <select name="LCID" class="form-control" required="true">
            <option disabled selected value>Seleziona un'opzione</option>
            <c:forEach var="res" items="${shoppingListCategories}" >
                <option value="${res[0]}"> <c:out value="${res[1]}"/> </option>
            </c:forEach>
        </select>
    </div>
    
    <div class="row form-group">
        <div class="col">
            <label for="Email">Nome della lista <strong>*</strong> </label>
            <input name="name" type="text" class="form-control" id="name" required="true" aria-describedby="nameHelp" value="${requestScope.name}" required="true">
        </div>

    </div>
    
    <div>
        <div class="form-group">
            <label for="description">Note aggiuntive</label>
            <textarea name="note" class="form-control" rows="3"  maxlength="250" id="description"></textarea>
        </div>
    </div>
    <div class="row">
        <div class="col-sm">
            <label for="logo">Carica il logo della categoria di prodotti</label>
            <input type="file" name ="image" accept=".jpg, .jpeg, .png" id="logo">
        </div>
    </div>
    <div>
        <br>
    </div>
    <!-- l'email la prendiamo con i cookie -->
    <div>
        <br>
    </div>
    <input type="hidden" value="${sorgente}" name="sorgente">
    <div>
        <div class="col-sm">
            <button type="submit" value ="${changeProduct}" name="ProdottoDaInserire" class="btn displayCenter login-btn">Crea lista</button>
        </div>
    </div>
</form>
