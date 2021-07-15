package com.example.springboot.models;

import javax.persistence.*;

@Entity
@Table(name="tbl_subscribers")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="tbl_users",referencedColumnName = "id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="tbl_channel",referencedColumnName = "id")
    private Channel channel;

    public Subscriber() {
    }

    public Subscriber(User user, Channel channel) {
        this.user = user;
        this.channel = channel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
