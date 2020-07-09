package com.example.vehicle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//https://spring.io/guides/gs/messaging-rabbitmq/
@EnableEurekaClient
@SpringBootApplication // (1)
public class VehicleApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    static final String topicExchangeName = "spring-boot-exchange";
//
//    static final String queueName = "spring-boot";
//
//    // method creates an AMQP queue
//    @Bean
//    Queue queue() {
//        return new Queue(queueName, false);
//    }
//
//    //method creates a topic exchange
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange(topicExchangeName);
//    }
//
//    //method binds last two together, defining the behavior that occurs when RabbitTemplate publishes to an exchange
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
//    }
//
//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(queueName);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }
//
//    //registered as a message listener in the container (defined in container()). It listens for messages on
//    // the spring-boot queue. Because the Receiver class is a POJO, it needs to be wrapped in the
//    // MessageListenerAdapter, where you specify that it invokes receiveMessage
//    @Bean
//    MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }

}

//(1) Tags the class as a source of bean definitions for the application context.
//Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings
//Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers

