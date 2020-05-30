package com.example.user.model;

import com.example.user.model.enums.Privilege;

import javax.persistence.*;

@Entity
public class UserPrivilege {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "privilege", nullable = false)
    private Privilege privilege;

    public UserPrivilege() {
    }

    public UserPrivilege(User user, Privilege privilege) {
        this.user = user;
        this.privilege = privilege;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "UserPrivilege{" +
                "id=" + id +
                ", user=" + user +
                ", privilege=" + privilege +
                '}';
    }
}
