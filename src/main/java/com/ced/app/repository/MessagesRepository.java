package com.ced.app.repository;

import com.ced.app.model.Messages;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessagesRepository extends MongoRepository<Messages, String> {
    List<Messages> findBySenderIdAndReceiverId(String senderId, String receiverId);

    List<Messages> findByReceiverIdAndSenderId(String receiverId, String senderId);
}
