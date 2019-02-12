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


<sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/fridaydb?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" user="root" password="root81097"/>

<sql:query dataSource="${snapshot}" var="result" sql="SELECT * FROM product_categories;" />
   
<form method="GET" action="insertProductServlet" enctype="multipart/form-data">
    <div class="form-group">
        <label>Nome del prodotto</label>
        <input type="text" class="form-control" name="name">
    </div>
    <div class="form-group">
        <label for="exampleInputEmail1">Note aggiuntive</label>
        <textarea name="note" class="form-control" rows="3"  maxlength="250" id="description"></textarea>
    </div>
    <div class="form-group">
        <label for="PCID">Scegli la categoria di appartenza del prodotto</label>
        <select name="PCID" class="form-control" required="true">
            <option disabled selected value>Seleziona un'opzione</option>
            <c:forEach var="res" items="${result.rows}" >
                <option value="${res.PCID}"> <c:out value="${res.Name}"/> </option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="logo">Carica il logo del prodotto</label>
        <input type="file" name ="logo" accept=".jpg, .jpeg, .png" id="logo">
    </div>
    <div class="form-group">
        <label for="photo">Carica la foto del prodotto</label>
        <input type="file" name ="photo" accept=".jpg, .jpeg, .png" id="photo">
    </div>
    <input type="hidden" name="goodInsertProduct">
    <button type="submit" class="btn displayCenter login-btn">Crea prodotto</button>
</form>
    
