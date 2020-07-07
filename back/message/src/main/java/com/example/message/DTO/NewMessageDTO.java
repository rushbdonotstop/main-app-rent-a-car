package com.example.message.DTO;

import com.example.message.model.enums.MessageType;

import java.time.LocalDateTime;

public class NewMessageDTO {

    private Long senderId;
    private String receiverUsername;
    private LocalDateTime dateAndTime;
    private String text;
    private MessageType messageType;

    public NewMessageDTO() {
    }

    public NewMessageDTO(Long senderId, String receiverUsername, LocalDateTime dateAndTime, String text, MessageType messageType) {
        this.senderId = senderId;
        this.receiverUsername = receiverUsername;
        this.dateAndTime = dateAndTime;
        this.text = text;
        this.messageType = messageType;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
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

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "NewMessageDTO{" +
                "senderId=" + senderId +
                ", receiverUsername='" + receiverUsername + '\'' +
                ", dateAndTime=" + dateAndTime +
                ", text='" + text + '\'' +
                ", messageType=" + messageType +
                '}';
    }
}
