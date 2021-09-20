package com.transportia.transport.Controller.Wrappers;

import com.transportia.transport.Model.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriverWithMessages {

    private String username;
    private List<ChatMessage> chatMessages;

}
