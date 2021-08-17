package com.example.springboot.models;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tbl_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name", nullable = false, length = 250)
    private String name;
    @Column(name="password", nullable = false, length = 250)
    private String password;
    @Column(name="icon", nullable = false, length = 1024)
    private String icon;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="tblUserRoles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="id")})

    private List<Role> roles;

    public User() {
        roles=new ArrayList<Role>();
    }

    public User(String username, String password, String icon) {
        this.name = username;
        this.password = password;
        roles=new ArrayList<Role>();
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public  List<Role> getRoles() {return roles;}
    public void setName(String name) {
        this.name = name;
    }
}
