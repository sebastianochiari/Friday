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
   

<c:set value="${prodotto}" var="prodotto"></c:set>
<table class="table table-striped table-borderless">
<thead>
    <tr>
        <th scope="col">Nome prodotto</th>
        <th scope="col">Note</th>
        <th scope="col">Logo</th>
        <th scope="col">Foto</th>
        <th scope="col">Categoria</th>
        <th scope="col">Gestore prodotto</th>
    </tr>
</thead>
<tbody>
    <tr>
        <td>${prodotto[0]}</td>
        <td>${prodotto[1]}</td>
        <td>${prodotto[2]}</td>
        <td>${prodotto[3]}</td>
        <td>${prodotto[4]}</td>
        <td>${prodotto[5]}</td>
    </tr>
</tbody>
</table>
    
