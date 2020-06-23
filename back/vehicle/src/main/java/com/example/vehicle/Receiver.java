package com.example.vehicle;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

import com.example.vehicle.model.Message;
import com.example.vehicle.service.CoordinateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

//receiver that responds to published messages
@Component
public class Receiver {

    private final SimpMessagingTemplate template;

    // lets it signal that the message has been received
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    public Receiver(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void receiveMessage(byte[] messageByte) throws UnsupportedEncodingException, JsonProcessingException {
            System.out.println("coordinates recieved:");
            String messageStringJSON = new String(messageByte, "UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            Message message = mapper.readValue(messageStringJSON, new TypeReference<Message>() {
            });
            if(message.getHeaders().getAuthorization()!=null) {
                System.out.println("Received <" + message.getBody() + ">");
                //socket.echoTextMessage(message.getBody());
                this.template.convertAndSend("/chat", message.getBody());
            }

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}