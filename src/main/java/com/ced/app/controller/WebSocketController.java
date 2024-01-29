package com.ced.app.controller;

import com.ced.app.model.Message;
import com.ced.app.model.Messages;
import com.ced.app.model.ResponseMessage;
import com.ced.app.service.MessagesService;
import com.ced.app.socket.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ws")
@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.TRACE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class WebSocketController {

    @Autowired
    private MessagesService messagesService;

    @Autowired
    private WebSocketService wsservice;

    @PostMapping("/sendmessages/{sender}/{recipient}")
    public ResponseEntity<ResponseMessage> sendMessage(@PathVariable String sender,@PathVariable String recipient, @RequestBody Map<String, String> data){
        Message message = new Message(data.get("messageContent"));
        try {
            wsservice.notifyFrontend(sender, recipient,message.getMessageContent());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(headers)
                    .body(new ResponseMessage("Message envoyer"));

        } catch (Exception e){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            return ResponseEntity.internalServerError().body(new ResponseMessage("L'envoie du message a recontre des problemes pour cause de :"+e.getMessage()));
        }
    }

    @GetMapping("/{iduser1}/{iduser2}")
    public List<Messages> getDiscussionBetweenUsers(@PathVariable String iduser1, @PathVariable String iduser2)
    {
        return messagesService.getMessagesBetweenUsers(iduser1, iduser2);
    }

    @GetMapping("/all")
    public List<Messages> getAllMessages(){
        return messagesService.getAllMessages();
    }

    @GetMapping("/{iduser}")
    public Map<String, Messages> getDiscussion(@PathVariable String iduser){
        return messagesService.getDisc(iduser);
    }
}
