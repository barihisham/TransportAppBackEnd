package com.transportia.transport.Repository;

import com.transportia.transport.Model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findAllBySenderOrReceiverOrderByTimeAsc(String username, String nameuser);
}
