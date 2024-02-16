package com.websocket.demo.config;

import com.websocket.demo.chat.ChatMessage;
import com.websocket.demo.chat.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageSendingOperations;
//    private final SimpMessageSendingOperations count;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        ChatMessage.setCount(-1);
        int countEx;
        countEx = ChatMessage.getCount();
        if (username != null) {
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .countEx(countEx)
                    .sender(username)
                    .build();
            messageSendingOperations.convertAndSend("/topic/public", chatMessage);
//            count.convertAndSend("/topic/count",ChatMessage.getCount());
        }
    }
}
