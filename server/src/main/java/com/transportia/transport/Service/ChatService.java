package com.transportia.transport.Service;

import com.transportia.transport.Model.ChatMessage;
import com.transportia.transport.Repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void addMessage(ChatMessage chatMessage){
        chatRepository.save(chatMessage);
    }

    public List<ChatMessage> findChatMessagesByUsername(String username){
        return chatRepository.findAllBySenderOrReceiverOrderByTimeAsc(username, username);
    }
}
