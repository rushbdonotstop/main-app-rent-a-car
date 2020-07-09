//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.08 at 04:10:33 AM CEST 
//


package com.example.user.xmlmodel.user;

import com.example.user.model.Penalty;
import com.example.user.model.PenaltyStatus;
import com.example.user.model.enums.Privilege;
import com.example.user.model.enums.UserType;
import com.example.user.xmlmodel.user.user_details.UserDetails;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://rentacar.com/user-details}userDetails"/>
 *         &lt;element name="verified" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "username",
    "password",
    "userDetails",
    "verified"
})
@XmlRootElement(name = "user")
public class User {

    protected long id;
    @XmlElement(required = true)
    protected String username;
    @XmlElement(required = true)
    protected String password;
    @XmlElement(namespace = "http://rentacar.com/user-details", required = true)
    protected UserDetails userDetails;
    protected boolean verified;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the userDetails property.
     * 
     * @return
     *     possible object is
     *     {@link UserDetails }
     *     
     */
    public UserDetails getUserDetails() {
        return userDetails;
    }

    /**
     * Sets the value of the userDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserDetails }
     *     
     */
    public void setUserDetails(UserDetails value) {
        this.userDetails = value;
    }

    /**
     * Gets the value of the verified property.
     * 
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * Sets the value of the verified property.
     * 
     */
    public void setVerified(boolean value) {
        this.verified = value;
    }

    public com.example.user.model.User toModel(User user) {
        com.example.user.model.User newUser = new com.example.user.model.User();

        com.example.user.model.UserDetails newUserDetails = new com.example.user.model.UserDetails();

        newUserDetails.setId(user.getUserDetails().getId());
        newUserDetails.setVehicleNum(user.getUserDetails().getVehicleNum());
        UserType newType = UserType.toEnum(user.getUserDetails().getUserType());
        newUserDetails.setUserType(newType);
        newUserDetails.setAddress(user.getUserDetails().getAddress());
        newUserDetails.setBusinessNum(user.getUserDetails().getBusinessNum());
        newUserDetails.setEmail(user.getUserDetails().getEmail());
        newUserDetails.setFullName(user.getUserDetails().getFullName());


        Set<Penalty> newPenalties = new HashSet<>();

        for (com.example.user.xmlmodel.user.user_penalty.Penalty penalty : user.getUserDetails().getPenalties().getPenalty()) {
            Penalty newPenalty = new Penalty();
            newPenalty.setId(penalty.getId());
            newPenalty.setTotal(penalty.getTotal());
            newPenalty.setPenaltyStatus(PenaltyStatus.toEnum(penalty.getPenaltyStatus()));
            newPenalties.add(newPenalty);
        }
        newUserDetails.setPenalties(newPenalties);

        List<Privilege> newPrivilegeList = new ArrayList<>();
        newPrivilegeList.add(Privilege.RENT_VEHICLE);
        newUserDetails.setPrivilegeList(newPrivilegeList);

        newUser.setUserDetails(newUserDetails);
        newUser.setVerified(user.isVerified());
        newUser.setId(user.getId());
        newUser.setPassword(user.getPassword());
        newUser.setUsername(user.getUsername());

        return newUser;
    }
}