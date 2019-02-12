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


<sql:query dataSource="${snapshot}" var="result" sql="SELECT * FROM list_categories;">   
</sql:query>

<c:out value="${changeProduct1}"></c:out>

<form method="GET" action="insertShoppingListServlet" enctype="multipart/form-data">
    <div class="row form-group">
        <div class="col">
            <label for="Email">Nome della lista <strong>*</strong> </label>
            <input name="name" type="text" class="form-control" id="name" required="true" aria-describedby="nameHelp" value="${requestScope.name}" required="true">
        </div>

    </div>
    <div class="form-group">
        <label for="LCID">Scegli la categoria di appartenza del prodotto</label>
            <select name="LCID" class="form-control" required="true">
                <option disabled selected value>Seleziona un'opzione</option>
                <c:forEach var="res" items="${result.rows}" >
                    <option value="${res.LCID}"> <c:out value="${res.Name}"/> </option>
                </c:forEach>
            </select>
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
    <div>
        <div class="col-sm">
            <button type="submit" class="btn displayCenter login-btn">Crea lista</button>
        </div>
    </div>
</form>
