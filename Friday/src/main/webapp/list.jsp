<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<c:set var="prodotto" value="${Prodotto}"></c:set>
<c:set var="listaSelezionata" value="${listaSelezionata}"></c:set>
<c:set var="utenteProprietario" value="${utenteProprietario}"></c:set>
<c:set var="listaCondivisa" value="${listaCondivisa}"></c:set>

<div class="clearfix">
    <div class="float-right">
        <!-- INFORMAZIONI LISTA -->
        <a href="#" class="shopping-link list-icon" title="Info" data-toggle="modal" data-target="#infoModal">
            <i class="fas fa-info-circle"></i>
        </a>
        <div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content shadow">
                    <div class="modal-header">
                        <h5 class="modal-title">Info sulla lista</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><b>proprietario: </b>${utenteProprietario[0]} ${utenteProprietario[1]}</p>
                        <p>
                            <b>condiviso con: </b>
                            <c:forEach items="${listaCondivisa}" var="ListaCondivisa">
                                ${ListaCondivisa[0]} ${ListaCondivisa[1]},
                            </c:forEach>
                        </p>
                        <p><b>immagine lista:</b></p>
                        <img src="images/${listaSelezionata[3]}" style="width: 100%">
                    </div>
                </div>
            </div>
        </div>

        <!-- CONDIVISIONE LISTA -->
        <a href="#" class="shopping-link list-icon" title="Condividi lista" data-toggle="modal" data-target="#shareModal">
            <i class="fas fa-share-alt"></i>
        </a>
        <div class="modal fade" id="shareModal" tabindex="-1" role="dialog" aria-labelledby="shareModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content shadow">
                    <div class="modal-header">
                        <h5 class="modal-title">Condividi con altri utenti</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="sharingListServlet" method="GET">
                        <input type="hidden" name="azioneLista" value="2">
                        <div class="modal-body">
                            <label for="exampleFormControlInput1">Persone</label>
                            <input type="email" class="form-control" id="invitationEmail" placeholder="name@example.com" name="invitationEmail">
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn std-button" value="${listaSelezionata[0]}" name="listToShare">Invita</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- MESSAGGISTICA LISTA -->
        <a href="#" class="shopping-link list-icon" title="Manda un messaggio">
            <i class="fas fa-comments"></i>
        </a>

        <!-- ELIMINAZIONE LISTA -->
        <c:if test="${utenteProprietario[2] eq emailSession}" var="uscitaLista">
            <a href="#" class="shopping-link list-icon" title="Rimuovi" data-toggle="modal" data-target="#deleteModal">
                <i class="fas fa-trash"></i>
            </a>
            <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content shadow">
                        <div class="modal-header">
                            <h5 class="modal-title">Elimina questa lista</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Vuoi veramente eliminare questa lista della spesa?</p>
                        </div>
                        <form method="GET" action="sharingListServlet">
                            <div class="modal-footer">
                                <input type="hidden" name="azioneLista" value="4">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <button type="submit" class="btn std-button" name="listToEliminate" value="${listaSelezionata[0]}">Elimina Lista</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${utenteProprietario[2] ne emailSession}">
            <a href="#" class="shopping-link list-icon" title="Rimuovi" data-toggle="modal" data-target="#deleteModal">
                <i class="fas fa-sign-out-alt shopping-icon"></i>
            </a>
            <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content shadow">
                        <div class="modal-header">
                            <h5 class="modal-title">Esci dalla lista</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Vuoi veramente eliminare questa lista della spesa?</p>
                        </div>
                        <form method="GET" action="sharingListServlet">
                            <div class="modal-footer">
                                <input type="hidden" name="azioneLista" value="5">
                                <input type="hidden" name="emailUtenteLoggato" value="${emailSession}">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <button type="submit" class="btn std-button" name="notToShareList" value="${listaSelezionata[0]}">Esci dalla lista</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>

<!-- MOSTRA LA PARTE PRINCIPALE DELLA PAGINA -->
<h4>${listaSelezionata[1]}<span class="badge badge-friday ml-2">${listaSelezionata[4]}</span></h4>
<p>${listaSelezionata[2]}</p>

<c:forEach items="${prodotto}" var="rigaProdotto">
    <div id="breadcrumb" class="list-element">
        <div class="row">
            <div class="col-md-2">
                <img class="list-image displayCenter" src="images/prodotti/${rigaProdotto[3]}">
            </div>
            <div class="col">
                <h5>${rigaProdotto[0]}</h5>
                <p class="mt-2 list-product-description">
                    ${rigaProdotto[1]}
                    <br>
                </p>
            </div>
            <div class="col-md-4">
                <div class="float-right">
                    <div class="qty-input">
                        <span>Quantità: </span>
                        ${rigaProdotto[6]}
                        <form action="insertProductServlet" method="POST" class="inline">
                            <input type="hidden" value="${listaSelezionata[0]}" name="selectedListToChangeProduct">
                            <input type="hidden" value="1" name="scelta">
                            <button type="submit" title="Diminuisci la quantità" name="changeProduct" value="${rigaProdotto[7]}">
                                <i class="fa fa-minus-circle" aria-hidden="true"></i>
                            </button>
                        </form>
                        <form action="insertProductServlet" method="POST" class="inline">
                            <input type="hidden" value="${listaSelezionata[0]}" name="selectedListToChangeProduct">
                            <input type="hidden" value="3" name="scelta">
                            <button type="submit" title="Aumenta la quantità" name="changeProduct" value="${rigaProdotto[7]}">
                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                            </button>
                        </form>
                        <form action="insertProductServlet" method="POST" class="inline">
                            <input type="hidden" value="${listaSelezionata[0]}" name="selectedListToChangeProduct">
                            <input type="hidden" value="2" name="scelta">
                            <button type="submit" title="Togli questo prodotto dalla lista" name="changeProduct" value="${rigaProdotto[7]}">
                                <i class="fas fa-trash"></i>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>

<div id="breadcrumb" class="list-element">
    <div class="row">
        <a class="text-link" href="#" data-toggle="modal" data-target="#addProductToList">
            <i class="fa fa-plus-circle"></i> Aggiungi prodotto alla lista
        </a>
        <div class="modal fade" id="addProductToList" tabindex="-1" role="dialog" aria-labelledby="addProductToListLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content shadow">
                    <div class="modal-header">
                        <h5 class="modal-title">Aggiungi prodotto alla lista</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <jsp:include page="showProducts.jsp" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
        

        <!-- START: main carousel -->
        <main>

            <div class="container mt-4">
                
                <div class="container">
                    <h3 class=" text-center">Messaging</h3>
                    <div class="messaging">
                          <div class="inbox_msg">
                            <div class="inbox_people">
                              <div class="headind_srch">
                                  <h4 align="center">Partecipanti alla Lista</h4> 
                              </div>
                              <div class="inbox_chat">
                                  
                                  <c:forEach items="${partecipantiChat}" var="partecipante">
                                   
                                    <div class="chat_list">
                                        <div class="chat_people">
                                            <div class="chat_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                                            <div class="chat_ib">
                                                <h5>${partecipante[1]} ${partecipante[2]}<span class="chat_date">LAMARTAPUZZA</span></h5>
                                                <p>Ciaone.</p>
                                            </div>
                                        </div>
                                    </div>
                                                
                                  </c:forEach>
                
                              </div>
                            </div>
                            <div class="mesgs">
                              <div class="msg_history" id="chat">
                                  
                                  <c:forEach items="${messaggiChat}" var="messaggio">
                                      
                                      <c:if test="${emailSession eq messaggio[3]}">
                                          
                                          <div class="outgoing_msg">
                                            <div class="sent_msg">
                                              <p>${messaggio[2]}</p>
                                              <span class="time_date">Io</span> </div>
                                          </div>
                                          
                                          
                                      </c:if>
                                      <c:if test="${emailSession ne messaggio[3]}">
                                          
                                          <div class="incoming_msg">
                                            <div class="incoming_msg_img"> <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                                            <div class="received_msg">
                                              <div class="received_withd_msg">
                                                <p>${messaggio[2]}</p>
                                                <span class="time_date">${messaggio[0]} ${messaggio[1]}</span></div>
                                            </div>
                                          </div>
                                          
                                      </c:if>
                                      
                                      
                                  </c:forEach>
                            
                              </div>
                                <form action="handlingListServlet" method="GET">
                                    <div class="type_msg">
                                      <div class="input_msg_write">
                                        <input type="text" class="write_msg" placeholder="Type a message" name="newMessage" />
                                        <input type="hidden" name="selectedList" value="${selectedList}" />
                                        <button href="list.js" class="msg_send_btn" type="submit"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
                                      </div>
                                    </div>
                                </form>
                            </div>
                          </div>

                        </div>
                </div>



            

<a href="#" class="text-link" title="Condividi lista" data-toggle="modal" data-target="#deleteProductModal">
                    Sposta nella lista
                </a>
                <div class="modal fade" id="deleteProductModal" tabindex="-1" role="dialog" aria-labelledby="deleteProductModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content shadow">
                            <div class="modal-header">
                                <h5 class="modal-title">Sposta il prodotto</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <p>Selezione la lista in cui vuoi spostare il prodotto</p>
                                <form>
                                    <div class="form-check">
                                      <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1" checked>
                                      <label class="form-check-label" for="exampleRadios1">
                                        Scuola Giovanni
                                      </label>
                                    </div>
                                    <div class="form-check">
                                      <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios2" value="option2">
                                      <label class="form-check-label" for="exampleRadios2">
                                        Lasagne
                                      </label>
                                    </div>
                                    <div class="form-check">
                                      <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios3" value="option3">
                                      <label class="form-check-label" for="exampleRadios3">
                                        Beer Pong
                                      </label>
                                    </div>
                                </form>
                                <p class="mt-3">Altrimenti, <a href="#" class="text-link">crea una nuova lista</a></p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                                <button type="button" class="btn std-button">Fine</button>
                            </div>
                        </div>
                    </div>
                </div>