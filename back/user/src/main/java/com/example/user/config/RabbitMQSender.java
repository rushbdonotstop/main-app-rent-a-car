package com.example.user.config;

import com.example.user.dto.EmailDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    public void send(EmailDTO emailDTO) {
        rabbitTemplate.convertAndSend(exchange, routingkey, emailDTO);
        System.out.println("Send msg = " + emailDTO);

    }

}
