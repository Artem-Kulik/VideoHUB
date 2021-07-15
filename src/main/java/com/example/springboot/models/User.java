package com.example.springboot.models;
import javax.persistence.*;

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

    public User() {
    }

    public User(String name, String password, String icon) {
        this.name = name;
        this.password = password;
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
    public void setName(String name) {
        this.name = name;
    }
}
