package com.ced.app.socket.service;

import com.ced.app.model.Messages;
import com.ced.app.model.ResponseMessage;
import com.ced.app.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WebSocketService {
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessagesService messagesService;

    @Autowired
    public WebSocketService(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyFrontend(String idsender, String idreceiver, final String message){
        System.out.println("sending message to `"+idreceiver+"`: '"+message+"'");
        ResponseMessage response = new ResponseMessage(message);
        Messages base = new Messages(idsender, idreceiver, message, new Date(System.currentTimeMillis()));
        messagesService.saveMessages(base);
        messagingTemplate.convertAndSendToUser(idreceiver, "/topic/private-messages", response);
    }
}
