package com.example.message.dto;

import java.time.LocalDateTime;

public class ConversationDTO implements Comparable<ConversationDTO> {

    private Long id;
    private String username;
    private Long myUserId;
    private Long otherUserId;
    private String lastMessage;
    private LocalDateTime timeOfLastMessage;

    public ConversationDTO() {
    }

    public ConversationDTO(Long id, String username, Long myUserId, Long otherUserId, String lastMessage, LocalDateTime timeOfLastMessage) {
        this.id = id;
        this.username = username;
        this.myUserId = myUserId;
        this.otherUserId = otherUserId;
        this.lastMessage = lastMessage;
        this.timeOfLastMessage = timeOfLastMessage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(Long myUserId) {
        this.myUserId = myUserId;
    }

    public Long getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(Long otherUserId) {
        this.otherUserId = otherUserId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public LocalDateTime getTimeOfLastMessage() {
        return timeOfLastMessage;
    }

    public void setTimeOfLastMessage(LocalDateTime timeOfLastMessage) {
        this.timeOfLastMessage = timeOfLastMessage;
    }

    @Override
    public String toString() {
        return "ConversationDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", myUserId=" + myUserId +
                ", otherUserId=" + otherUserId +
                ", lastMessage='" + lastMessage + '\'' +
                ", timeOfLastMessage=" + timeOfLastMessage +
                '}';
    }

    @Override
    public int compareTo(ConversationDTO o) {
        return this.timeOfLastMessage.compareTo(o.timeOfLastMessage);
    }
}
