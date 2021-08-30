package com.example.springboot.models;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tbl_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="name", nullable = false, length = 250)
    private String name;
    @Column(name="password", nullable = false, length = 250)
    private String password;
    @Column(name="icon", nullable = false, length = 1024)
    private String icon;

    @Column(name="phone", nullable = false, length = 1024)
    private String phone;
    @Column(name="birthday", nullable = false)
    private Date birthday;
    @Column(name="gender", nullable = false, length = 1024)
    private String gender;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="tbl_channel",referencedColumnName = "id")
    private Channel channel;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="tblUserRoles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="id")})
    private List<Role> roles;

    public User() {
        roles=new ArrayList<Role>();
    }

    public User(String name, String password, String icon, String phone, Date birthday, String gender,Role role) {
        this.name = name;
        this.password = password;
        this.icon = icon;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        roles=new ArrayList<Role>();
        roles.add(role);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public long getId() {
        return id;
    }
    public void setId(long id) {
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
