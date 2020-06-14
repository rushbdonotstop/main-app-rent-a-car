package com.example.message.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Conversation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_one_id", nullable = false)
    private Long userOneId;

    @Column(name = "user_two_id", nullable = false)
    private Long userTwoId;

    @Column(name = "last_message", nullable = false)
    private String lastMessage;

    @Column(name = "time_of_last_message", nullable = false)
    private LocalDateTime timeOfLastMessage;

    public Conversation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserOneId() {
        return userOneId;
    }

    public void setUserOneId(Long userOneId) {
        this.userOneId = userOneId;
    }

    public Long getUserTwoId() {
        return userTwoId;
    }

    public void setUserTwoId(Long userTwoId) {
        this.userTwoId = userTwoId;
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
        return "Conversation{" +
                "id=" + id +
                ", userOneId=" + userOneId +
                ", userTwoId=" + userTwoId +
                ", lastMessage='" + lastMessage + '\'' +
                ", timeOfLastMessage=" + timeOfLastMessage +
                '}';
    }
}
