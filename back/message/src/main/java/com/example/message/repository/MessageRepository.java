package com.example.message.repository;

import com.example.message.model.Message;
import com.example.message.model.enums.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAll();

    Message findOneById(Long id);

    Message save(Message message);

    List<Message> findAllBySenderId(Long senderId);

    List<Message> findALlByMessageType(MessageType messageType);

    List<Message> findAllByConversationId(Long conversationId);

}
