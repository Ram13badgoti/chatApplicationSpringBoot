package com.spring.boot.Example.controller;
import com.spring.boot.Example.models.WebSocketChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;


@Controller
public class WebSocketChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public WebSocketChatMessage sendMessage(@Payload WebSocketChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public WebSocketChatMessage addUser(@Payload WebSocketChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
