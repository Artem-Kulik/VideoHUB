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
    @Column(name="birthday", nullable = false, length = 250)
    private String birthday;
    @Column(name="gender", nullable = false, length = 250)
    private String gender;
    @Column(name="phone", nullable = false, length = 250)
    private String phone;

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

    public User(String name, String password, String birthday, String gender, String phone, String icon, List<Role> roles) {
        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.icon = icon;
        roles=new ArrayList<Role>();
    }



    public String getGender() {
        return gender;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
