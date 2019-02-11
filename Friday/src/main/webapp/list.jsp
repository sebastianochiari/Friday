<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="prodotto" value="${Prodotto}"></c:set>
<c:set var="listaCorrente" value="${listaCorrente}"></c:set>
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
                        <img src="images/list-category/${listaCorrente[3]}" style="width: 100%">
                    </div>
                </div>
            </div>
        </div>

        <c:if test="${emailSession ne null}">

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
                                <button type="submit" class="btn std-button" value="${listaCorrente[0]}" name="listToShare">Invita</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- MESSAGGISTICA LISTA -->
            <a href="#" class="shopping-link list-icon" title="Manda un messaggio" onclick="submitForm('sharingListServlet')">
                <i class="fas fa-comment"></i>
                <form method="GET" action="sharingListServlet" class="inline" id="sharingListServlet">
                    <input type="hidden" name="azioneLista" value="3">
                    <input type="hidden" name="emailUtenteLoggato" value="${emailSession}">
                    <input type="hidden" name="messageToList" value="${listaCorrente[0]}">
                </form>
            </a>
        </c:if>

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
                                <button type="submit" class="btn std-button" name="listToEliminate" value="${listaCorrente[0]}">Elimina Lista</button>
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
                                <button type="submit" class="btn std-button" name="notToShareList" value="${listaCorrente[0]}">Esci dalla lista</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>

<!-- MOSTRA LA PARTE PRINCIPALE DELLA PAGINA -->
<h4>${listaCorrente[1]}<span class="badge badge-friday ml-2">${listaCorrente[4]}</span></h4>
<p>${listaCorrente[2]}</p>

<c:forEach items="${prodotto}" var="rigaProdotto">
    <div class="mt-4" id="accordion">
    <div id="breadcrumb" class="mt-4">
        <div class="row">
            <div class="col-md-2">
                <img class="list-image displayCenter" src="images/prodotti/${rigaProdotto[3]}">
            </div>
            <div class="col">
                
                <h5>
                    <div class="mt-4" id="infoProduct">
                    <%--<a href="#" class="text-link" data-toggle="modal" data-target="#infoPersonalProduct">--%>
                        <a class="cart-toggle" data-toggle="collapse" href="#infoProduct${rigaProdotto[7]}" role="button" data-target="#infoProduct${rigaProdotto[7]}" aria-expanded="true" aria-controls="infoProduct${rigaProdotto[7]}">
                        <p>${rigaProdotto[0]}</p>
                    </a>
                    </div>
                </h5>
                    <%--
                    <div class="modal fade" id="infoPersonalProduct" tabindex="-1" role="dialog" aria-labelledby="infoPersonalProductLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content shadow">
                                <div class="modal-header">
                                    <h5 class="modal-title">${rigaProdotto[0]} </h5> 
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    --%>
                            <div id="infoProduct${rigaProdotto[7]}" class="collapse pb-4" aria-labelledby="infoProduct" data-parent="#accordion">
                                <div class="card-body">
                                    <p>
                                        <b>Creatore: </b>${rigaProdotto[5]} ${rigaProdotto[8]}
                                    </p>
                                    <p>
                                        <b>Condiviso con: </b>${rigaProdotto[9]}
                                        
                                    </p>
                                    <!-- forse sorgente immagini dei loghi è diverso -->
                                    <p><b>Marca: </b> <img src="images/loghi/${rigaProdotto[2]}" style="width: 10%"></p>
                                    <p><b>Note aggiuntive: </b>
                                        ${rigaProdotto[1]}
                                    </p>
                                    <p><b>Immagine del prodotto:</b></p>
                                    <img src="images/prodotti/${rigaProdotto[3]}" style="width: 80%">
                                </div>
                            </div>
                                <%--
                        </div>
                    </div>
                                --%>
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
                            <input type="hidden" value="${listaCorrente[0]}" name="selectedListToChangeProduct">
                            <input type="hidden" value="1" name="scelta">
                            <button type="submit" title="Diminuisci la quantità" name="changeProduct" value="${rigaProdotto[7]}">
                                <i class="fa fa-minus-circle" aria-hidden="true"></i>
                            </button>
                        </form>
                        <form action="insertProductServlet" method="POST" class="inline">
                            <input type="hidden" value="${listaCorrente[0]}" name="selectedListToChangeProduct">
                            <input type="hidden" value="3" name="scelta">
                            <button type="submit" title="Aumenta la quantità" name="changeProduct" value="${rigaProdotto[7]}">
                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                            </button>
                        </form>
                        <form action="insertProductServlet" method="POST" class="inline">
                            <input type="hidden" value="${listaCorrente[0]}" name="selectedListToChangeProduct">
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
    </div>
</c:forEach>


<!-- MESSAGGI CHE BOH -->


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