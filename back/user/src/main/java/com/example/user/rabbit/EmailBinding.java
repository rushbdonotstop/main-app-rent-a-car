package com.example.user.rabbit;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EmailBinding {

    @Output("emailChannel")
    MessageChannel publishMessage();
}
