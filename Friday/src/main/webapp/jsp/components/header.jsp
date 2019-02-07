<!--

    Friday - Shopping List Manager
    Copyright (C) 2018 Tommaso Bosetti, Sebastiano Chiari, Leonardo Remondini, Marta Toniolli

    Questa è la componente JSP per l'HEADER
    author: @sebastianochiari

-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="pageCurrent" value="${requestScope.pageCurrent}" />
<c:set var="boolEmailSession" value="${boolEmailSessionScriptlet}"></c:set>

    <!-- START: main navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light section-grey">
        <div class="container">
            <div class="header-logo float-left">
                <a href="index.jsp">
                    <div class="logo-header">
                        <img class="displayCenter auto-size" src="images/friday_icon_colored.png" alt="logo">
                    </div>
                </a>
            </div>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown nav-category">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink1" name="navbarDropdownMenuLink1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            CATEGORIE
                        </a>
                         <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink1">

                            <sql:setDataSource var="snapshot" driver="${DBDriverSession}" url="${DBUrlSession}" user="${DBUserSession}" password="${DBPassSession}"/>
                            <sql:query dataSource="${snapshot}" var="result" sql="SELECT * FROM product_categories;"></sql:query>

                            <form action="searchServlet" method ="GET">
                                 <c:forEach var="res" items="${result.rows}" >
                                       <%-- <input type="hidden" value ="${res.PCID}" name ="selectedPCategory"> --%>
                                       <button type="submit" value ="${res.PCID}" class="dropdown-item" name ="inputClick" id="inputClick">
                                           ${res.Name}
                                       </button>
                                 </c:forEach>
                            </form>
                        </div>
                    </li>

                    <li class="nav-item dropdown nav-category">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-user nav-link-icon"></i>
                            Il mio account
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink2">
                            <c:if test="${!boolEmailSession}">
                                <a class="dropdown-item" href="login.jsp">Il mio account</a>
                                <a class="dropdown-item" href="login.jsp">Login</a>
                                <a class="dropdown-item" href="insertUser.jsp">Crea un'account</a>
                            </c:if>
                            <c:if test="${boolEmailSession}">
                                <a class="dropdown-item" href="myaccount.jsp">Il mio account</a>
                                <a class="dropdown-item" href="security.jsp">Impostazioni di sicurezza</a>
                                <c:if test="${adminUserSession}">
                                    <a class="dropdown-item" href="adminSection.jsp">Sezione admin</a>
                                </c:if>
                            </c:if>
                        </div>
                    </li>
                    <li class="nav-item dropdown nav-category">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-shopping-cart nav-link-icon"></i>
                            Le mie liste
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink3">

                            <sql:setDataSource var="snapshotList" driver="${DBDriverSession}" url="${DBUrlSession}" user="${DBUserSession}" password="${DBPassSession}"/>
                            <sql:query dataSource="${snapshotList}" var="resultList" sql="SELECT * FROM lists WHERE (List_Owner = '${emailSession}' or CookieID = '${cookieIDSession}');"></sql:query>
                            <sql:query dataSource="${snapshotList}" var="resultSharingList" sql="SELECT * FROM lists WHERE LID in (SELECT LID FROM sharing WHERE Email = '${emailSession}');"></sql:query>

                            <c:set scope="session" var="resultList" value="${resultList}"></c:set>
                            <c:set scope="session" var="resultSharingList" value="${resultSharingList}"></c:set>
                            <form action="handlingListServlet" method="GET">
                                <button type="submit" value="0" class="dropdown-item" name="selectedList" >
                                    Gestione Liste
                                </button>
                            </form>
                            <form action="handlingListServlet" method="GET">
                                <c:forEach var="resList" items="${resultList.rows}" >
                                    <button type="submit" value="${resList.LID}" class="dropdown-item" name="selectedList" >
                                        ${resList.Name}
                                    </button>
                                </c:forEach>
                            </form>
                            <form action="handlingListServlet" method="GET">
                                <c:forEach var="resSharingList" items="${resultSharingList.rows}" >
                                    <button type="submit" value="${resSharingList.LID}" class="dropdown-item" name="selectedList" >
                                        ${resSharingList.Name}
                                    </button>
                                </c:forEach>
                            </form>

                        </div>
                    </li>
                </ul>

                <div style="display: inline;">
                    <a href="#" class="shopping-link" style="margin-right: 5px; vertical-align: middle;">
                        <i class="fas fa-envelope shopping-icon"></i>
                    </a>
                    <a href="#" class="shopping-link" style="margin-right: 5px; vertical-align: middle;">
                        <i class="fas fa-shopping-cart shopping-icon"></i>
                    </a>
                    <c:if test="${emailSession ne null}">
                        <form style="display: inline-flex; padding: 0; margin: 0; vertical-align: sub; margin-left: 5px;" action="logoutServlet" method="POST">
                            <input type="hidden" name="boolEmailSession" value="false">
                            <button type="submit" class="btn std-button logout-button" style="background-color: transparent;">
                                <i class="fas fa-sign-out-alt shopping-icon displayCenter"></i>
                            </button>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </nav>
    <!-- END: main navbar -->

    <!-- START: search navbar -->
    <nav id="breadcrumb" class="navbar navbar-expand-lg navbar-light bg-light" style="padding-top: 0px;">
        <div class="container mb-1">
            <form action="searchServlet" method="GET" style="width: 100%;">
                <div class="row">
                    <div class="col-md-3 mt-1 nav-col">
                        <div class="col-sm">
                            <sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/fridaydb?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" user="root" password="root81097"/>

                            <sql:query dataSource="${snapshot}" var="result" sql="SELECT * FROM product_categories;">
                            </sql:query>

                            <select name="inputCategory" class="form-control">
                                <option value = "-1" >Tutte le Categorie</option>
                                <c:forEach var="res" items="${result.rows}" >
                                    <option value="${res.PCID}"> <c:out value="${res.Name}"/> </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="col-md-4 mt-1 nav-col">
                        <div class="input-group nav-search">
                            <input class="form-control" type="text" placeholder="Cerca" name="inputSearch" style="border-right: 0px;">
                            <div class="input-group-append">
                                <button type="submit" class="btn" type="button" style="border: 1px solid #ced4da; border-left: 0px;">
                                    <i class="fas fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </div>

                    <c:if test="${emailSession ne null}">
                        <div class="col align-right" style="text-align: right;">
                            <small class="text-muted">Logged as </small>
                            <br>
                            <c:out value=" ${emailSession}"></c:out>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>
    </nav>
    <!-- END: search navbar -->
