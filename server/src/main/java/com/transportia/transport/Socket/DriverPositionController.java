package com.transportia.transport.Socket;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
@CrossOrigin
public class DriverPositionController {


    @MessageMapping("/user-all")
    @SendTo("/topic/user")
    public DriverPosition greeting(DriverPosition driverPosition) throws Exception {
        return driverPosition;
    }

    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
        System.out.println(event.getUser());
    }
}
