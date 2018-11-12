<%-- 
    Document   : insertShoppingListCategory
    Created on : 18-Oct-2018, 13:44:07
    Author     : leo97
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<form method="GET" action="insertShoppingListCategoryServlet" enctype="multipart/form-data">
    <div class="form-group">
        <label>Nome della categoria di lista della spesa</label>
        <input type="text" class="form-control" name="name">
    </div>
    <div class="form-group">
        <label for="description">Note aggiuntive</label>
        <textarea name="note" class="form-control" rows="3"  maxlength="250" id="description"></textarea>
    </div>
    <div class="form-group">
        <label for="logo">Carica il logo della categoria di lista della spesa</label>
        <input type="file" name ="image" accept=".jpg, .jpeg, .png" id="logo">
    </div>
    <input type="hidden" name="goodInsertShoppingListCategory">
    <div class="form-group">
        <button type="submit" class="btn displayCenter login-btn">Crea categoria di lista</button>
    </div>
</form>
                