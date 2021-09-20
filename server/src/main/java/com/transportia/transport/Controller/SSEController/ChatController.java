package com.transportia.transport.Controller.SSEController;

import com.transportia.transport.Controller.Wrappers.ChatMessageList;
import com.transportia.transport.Controller.Wrappers.DriverWithMessages;
import com.transportia.transport.Model.ChatMessage;
import com.transportia.transport.Model.Driver;
import com.transportia.transport.Service.ChatService;
import com.transportia.transport.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/chat")
public class ChatController {

    private  final ChatService chatService;
    private final DriverService driverService;

    private Map<String, SseEmitter> drivers = new HashMap<>();
    private Map<String, SseEmitter> trafficManagers = new HashMap<>();

    @Autowired
    public ChatController(ChatService chatService, DriverService driverService) {
        this.chatService = chatService;
        this.driverService = driverService;
    }

    @GetMapping
    public ChatMessageList findChatMessagesByUsername(@RequestParam String username){
        return new ChatMessageList(chatService.findChatMessagesByUsername(username));
    }

    // method for client sub
    @GetMapping("/subscribe")
    public SseEmitter subscribeToChat(@RequestParam String username, @RequestParam String role){
        if(username != null && role != null){

            SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
            if(role.equals("driver")){
                sendInitEvent(sseEmitter);
                drivers.put(username, sseEmitter);
                // ADD ON TIMEOUT, ONERROR
                sseEmitter.onCompletion(() -> drivers.remove(sseEmitter));
            }else if(role.equals("trafficManager")){
                sendInitEvent(sseEmitter);
                trafficManagers.put(username, sseEmitter);
                // ADD ON TIMEOUT, ONERROR
                sseEmitter.onCompletion(() -> trafficManagers.remove(sseEmitter));
            }
            return sseEmitter;
        }
        return null;
    }

    private void sendInitEvent(SseEmitter sseEmitter){
        try {
            sseEmitter.send(SseEmitter.event().name("INIT CHAT"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping
    public DriverWithMessages setAvailability(@RequestBody Driver driver){
        //TODO: this method should return the driver from DB
        driverService.setDriverAvailability(driver);
        if(driver.getIsAvailable()){
            return new DriverWithMessages(driver.getUsername(), chatService.findChatMessagesByUsername(driver.getUsername()));
        }
        return null;
    }



    @PostMapping("publish")
    public void dispatchEventToClients(@RequestBody ChatMessage chatMessage){
        chatService.addMessage(chatMessage);

        for(Map.Entry mapElement : trafficManagers.entrySet()){
            SseEmitter trafficfManagerEmitter = (SseEmitter) mapElement.getValue();
            try {
                trafficfManagerEmitter.send(SseEmitter.event().name("chatData").data(chatMessage));
            } catch (IOException e) {
                trafficManagers.remove(trafficfManagerEmitter);
                //e.printStackTrace();
            }
        }
        SseEmitter sseEmitter = drivers.get(chatMessage.getReceiver());
        if(sseEmitter != null){
            try {
                sseEmitter.send(SseEmitter.event().name("chatData").data(chatMessage));
            } catch (IOException e) {
                drivers.remove(sseEmitter);
            }
        }
    }
    

}
