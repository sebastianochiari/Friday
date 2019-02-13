<%-- 
    WebProgramming Project - Shopping List 
    2017-2018
    Tommaso Bosetti - Sebastiano Chiari - Leonardo Remondini - Marta Toniolli
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="breadcrumb" class="mt-4">
    <div class="mt-4" id="infoList">
            <h5 class="mb-0" style="display: inline-block;">Modifica informazione della lista</h5>
    </div>
        <div class="card-body">
            <p>Qui puoi modificare informazioni della lista</p>

            <form method="POST" action="sharingListServlet" enctype="application/x-www-form-urlencoded">
                <div class="form-group">
                    <div class="row">
                        <input type="hidden" name="LID" value="${listaCorrente[0]}">
                        <input type="hidden" name="LCID" value="${listaCorrente[6]}">
                        <input type="hidden" name="ListOwner" value="${utenteProprietario[2]}">
                        <input type="hidden" name="CookieID" value="${listaCorrente[5]}">
                        <div class="col">
                            <label for="newName">Nome</label>
                            <input name="newName" type="text" class="form-control" id="newName" placeholder="${listaCorrente[1]}" required="true">
                        </div>
                        <div class="col">
                            <label for="newNote">Note</label>
                            <input name="newNote" type="text" class="form-control" id="newNote" placeholder="${listaCorrente[2]}" required="true">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="newPhoto">Aggiorna l'immagine della lista</label>
                    <input type="file" name ="newPhoto" accept=".jpg, .jpeg, .png" id="newPhoto">
                </div>
                <button type="submit" class="btn std-button">Conferma</button>
            </form>
        </div>
</div>