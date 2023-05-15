package it.develhope.websocket.Services.Web.Socket2.controllers;

import it.develhope.websocket.Services.Web.Socket2.entities.ClientMessageDTO;
import it.develhope.websocket.Services.Web.Socket2.entities.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/notification")
    public ResponseEntity getBroadcastMessage(@RequestBody MessageDTO messageDTO){
        simpMessagingTemplate.convertAndSend("/topic/broadcast-message", messageDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @MessageMapping("/client-message")
    @SendTo("/topic/broadcast")
    public MessageDTO handleMessageFromWebSocket(ClientMessageDTO clientMessageDTO){
        System.out.println("Arrived a message from /app/client-message " + clientMessageDTO.toString());
        return new MessageDTO(clientMessageDTO.getClientName(), clientMessageDTO.getClientAlert(), clientMessageDTO.getClientMsg());
    }
}
