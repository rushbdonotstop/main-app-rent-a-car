package com.example.user.model;

import javax.persistence.*;

@Entity
@Table(name="sys_user")
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "agent_app_id")
    private Long agentAppId;

    @OneToOne
    @JoinColumn(name = "user_details_id", referencedColumnName = "id", nullable = true, unique = true)
    private UserDetails userDetails;

    @Column(name = "salt", nullable = true)
    private String salt;

    @Column(name = "verified", nullable = true)
    private boolean verified;

    public User() {
    }

    public User(String username, String password, Long agentAppId, UserDetails userDetails, boolean verified) {
        this.username = username;
        this.password = password;
        this.agentAppId = agentAppId;
        this.userDetails = userDetails;
        this.verified = verified;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Long getAgentAppId() {
        return agentAppId;
    }

    public void setAgentAppId(Long agentAppId) {
        this.agentAppId = agentAppId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userDetails=" + userDetails +
                '}';
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
