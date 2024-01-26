package com.ced.app.controller;

import com.ced.app.model.Messages;
import com.ced.app.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessagesService messagesService;

    @GetMapping("/{iduser1}/{iduser2}")
    public List<Messages> getDiscussionBetweenUsers(@PathVariable String iduser1, @PathVariable String iduser2)
    {
        return messagesService.getMessagesBetweenUsers(iduser1, iduser2);
    }
}
