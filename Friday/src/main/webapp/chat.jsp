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