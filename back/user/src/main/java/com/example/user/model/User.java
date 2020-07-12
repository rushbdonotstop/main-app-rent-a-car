package com.example.user.model;

import com.example.user.xmlmodel.user.user_penalty.Penalty;

import javax.persistence.*;
import javax.xml.datatype.DatatypeConfigurationException;

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
    @JoinColumn(name = "user_details_id", referencedColumnName = "id")
    private UserDetails userDetails;

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

    public boolean getVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public com.example.user.xmlmodel.user.User toXml(User user) throws DatatypeConfigurationException {
        com.example.user.xmlmodel.user.User xmlUserModel = new com.example.user.xmlmodel.user.User();

        com.example.user.xmlmodel.user.user_details.UserDetails xmlUserDetailsModel = new com.example.user.xmlmodel.user.user_details.UserDetails();

        com.example.user.xmlmodel.user.user_details.UserDetails.Penalties penalties = new com.example.user.xmlmodel.user.user_details.UserDetails.Penalties();
        for(com.example.user.model.Penalty penalty : user.getUserDetails().getPenalties()) {
            Penalty newPenalty = new Penalty();
            newPenalty.setId(penalty.getId());
            newPenalty.setTotal(penalty.getTotal());
            newPenalty.setPenaltyStatus(penalty.getPenaltyStatus().toString());
            penalties.getPenalty().add(newPenalty);
        }

        xmlUserDetailsModel.setPenalties(penalties);
        xmlUserDetailsModel.setId(user.getUserDetails().getId());
        xmlUserDetailsModel.setAddress(user.getUserDetails().getAddress());
        xmlUserDetailsModel.setBusinessNum(user.getUserDetails().getBusinessNum());
        xmlUserDetailsModel.setEmail(user.getUserDetails().getEmail());
        xmlUserDetailsModel.setFullName(user.getUserDetails().getFullName());
        xmlUserDetailsModel.setUserType(user.getUserDetails().getUserType().toString());
        xmlUserDetailsModel.setVehicleNum(user.getUserDetails().getVehicleNum());

        xmlUserModel.setId(user.getId());
        xmlUserModel.setPassword(user.getPassword());
        xmlUserModel.setUsername(user.getUsername());
        xmlUserModel.setUserDetails(xmlUserDetailsModel);
        xmlUserModel.setVerified(user.getVerified());

        return xmlUserModel;
    }
}