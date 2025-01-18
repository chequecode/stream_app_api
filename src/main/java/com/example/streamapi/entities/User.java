package com.example.streamapi.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "Users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User extends BaseEntity{

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime subscriptionStart;

    public User() {}

    public User(String name, long id, String email, String password, LocalDateTime subscriptionStart) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.subscriptionStart = subscriptionStart;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getSubscriptionStart() {
        return subscriptionStart;
    }

    public void setSubscriptionStart(LocalDateTime subscriptionStart) {
        this.subscriptionStart = subscriptionStart;
    }
}
