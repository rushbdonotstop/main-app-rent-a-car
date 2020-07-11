package com.example.vehicle;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.config.annotation.*;


@Configuration
@EnableWebSocketMessageBroker
@RequestMapping(path = "ws")
public class WebSocketConfig {

    @Configuration
    @EnableWebSocketMessageBroker
    public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOrigins("*")
                .withSockJS();
    }

        @Override
        public void configureMessageBroker(MessageBrokerRegistry registry) {
            registry.setApplicationDestinationPrefixes("/app")
                    .enableSimpleBroker("/chat");
        }
    }

}