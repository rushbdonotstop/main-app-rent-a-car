package com.example.message;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;

//receiver that responds to published messages
@Component
public class Receiver {

    // lets it signal that the message has been received
    private CountDownLatch latch = new CountDownLatch(1);

    //The Jackson2JsonMessageConverter will only perform conversion if the message has a content_type header that contains json.
    //Otherwise, it will return byte[]
    public void receiveMessage(byte[] message) throws UnsupportedEncodingException {
        String messageString= new String(message, "UTF-8");
        System.out.println("Received <" + messageString + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}