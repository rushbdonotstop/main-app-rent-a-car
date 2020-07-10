package com.example.message.dto;

import com.example.message.model.Message;
import com.example.message.model.enums.MessageType;

import java.time.LocalDateTime;

public class MessageDTO implements Comparable<MessageDTO>{

    private Long id;
    private Long senderId;
    private Long receiverId;
    private LocalDateTime dateAndTime;
    private String text;
    private Long conversationId;
    private MessageType messageType;

    public MessageDTO() {
    }

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.text = message.getText();
        this.senderId = message.getSenderId();
        this.receiverId = message.getReceiverId();
        this.dateAndTime = message.getDateAndTime();
        this.conversationId = message.getConversationId();
        this.messageType = message.getMessageType();
    }

    public MessageDTO(Long id, Long senderId, Long receiverId, LocalDateTime dateAndTime, String text, Long conversationId, MessageType messageType) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.dateAndTime = dateAndTime;
        this.text = text;
        this.conversationId = conversationId;
        this.messageType = messageType;
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

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
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

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", dateAndTime=" + dateAndTime +
                ", text='" + text + '\'' +
                ", conversationId=" + conversationId +
                ", messageType=" + messageType +
                '}';
    }

    @Override
    public int compareTo(MessageDTO o) {
        return this.getDateAndTime().compareTo(o.getDateAndTime());
    }
}
