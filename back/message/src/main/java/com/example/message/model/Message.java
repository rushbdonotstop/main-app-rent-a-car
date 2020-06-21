package com.example.message.model;


import com.example.message.DTO.MessageDTO;
import com.example.message.model.enums.MessageType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_id", nullable = false)
    private Long senderId;

    @Column(name = "receiver_id", nullable = false)
    private Long receiverId;

    @Column(name = "date_and_time", nullable = false)
    private LocalDateTime dateAndTime;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "conversation_id", nullable = false)
    private Long conversationId;

    @Column(name = "message_type", nullable = false)
    private MessageType messageType;

    public Message() {
    }

    public Message(Long id, Long senderId, Long receiverId, LocalDateTime dateAndTime, String text, Long conversationId, MessageType messageType) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.dateAndTime = dateAndTime;
        this.text = text;
        this.conversationId = conversationId;
        this.messageType = messageType;
    }

    public Message(MessageDTO messageDTO) {
        this.senderId = messageDTO.getSenderId();
        this.receiverId = messageDTO.getReceiverId();
        this.dateAndTime = messageDTO.getDateAndTime();
        this.text = messageDTO.getText();
        this.conversationId = messageDTO.getConversationId();
        this.messageType = messageDTO.getMessageType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", dateAndTime=" + dateAndTime +
                ", text='" + text + '\'' +
                ", conversationId=" + conversationId +
                ", messageType=" + messageType +
                '}';
    }
}
