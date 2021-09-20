package com.transportia.transport.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatMessage {

    @Id
    @JsonIgnore
    private String id;
    private String sender;
    private String receiver;
    private String sendersText;
    private String time;

    public ChatMessage(String sender, String receiver, String sendersText, String date){
        this.sender = sender;
        this.receiver = receiver;
        this.sendersText = sendersText;
        this.time = date;
    }
}
