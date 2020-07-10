package com.example.user.rabbitcloud;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EmailBinding {

    @Output("emailChannel")
    MessageChannel publishMessage();
}
