<!--
    Friday - Shopping List Manager
    Copyright (C) 2018-2019 Tommaso Bosetti, Sebastiano Chiari, Leonardo Remondini, Marta Toniolli
-->

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
    
    <div class="form-group">
        <label for="Email">Nome della lista <strong>*</strong> </label>
        <input name="name" type="text" class="form-control" id="name" required="true" aria-describedby="nameHelp" value="${requestScope.name}" required="true">
    </div>
    
    <div class="form-group">
        <label for="description">Note aggiuntive</label>
        <textarea name="note" class="form-control" rows="3"  maxlength="250" id="description"></textarea>
    </div>

    <div class="form-group mb-4">
        <label for="logo">Carica il logo della categoria di prodotti</label>
        <input type="file" name ="image" accept=".jpg, .jpeg, .png" id="logo">
    </div>

    <input type="hidden" value="${sorgente}" name="sorgente">

    <button type="submit" value ="${changeProduct}" name="ProdottoDaInserire" class="btn displayCenter login-btn">Crea lista</button>

</form>
