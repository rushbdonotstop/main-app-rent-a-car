package com.example.vehicle;

//import com.example.vehicle.model.SocketMessage;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.*;
//
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.SocketHandler;
//
//@Configuration
//@EnableWebSocketMessageBroker
//@RequestMapping(path = "ws")
//public class WebSocketConfig {
//
//    @Configuration
//    @EnableWebSocketMessageBroker
//    public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/socket")
//                .setAllowedOrigins("*")
//                .withSockJS();
//    }
//
//        @Override
//        public void configureMessageBroker(MessageBrokerRegistry registry) {
//            registry.setApplicationDestinationPrefixes("/app")
//                    .enableSimpleBroker("/chat");
//        }
//    }
//
//}