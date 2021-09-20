package com.transportia.transport.Controller.Wrappers;

import com.transportia.transport.Model.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageList {

    List<ChatMessage> chatMessages = new ArrayList<>();
}
