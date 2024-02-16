package com.websocket.demo.chat;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ChatMessage {
    @Getter
    static private int count=0;
    private int countEx;
    private String content;
    private String timestamp;
    private String sender;
    private MessageType type;

    public static void setCount(int i){
        count += i;
    }
    public void setCountEx(){
        countEx = count;
    }
}
