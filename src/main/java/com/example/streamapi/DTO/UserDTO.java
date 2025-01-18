package com.example.streamapi.DTO;

import java.time.LocalDateTime;

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime subscriptionStart;

    public UserDTO(Long id, String name, String email, LocalDateTime subscriptionStart) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subscriptionStart = subscriptionStart;
    }

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getSubscriptionStart() {
        return subscriptionStart;
    }

    public void setSubscriptionStart(LocalDateTime subscriptionStart) {
        this.subscriptionStart = subscriptionStart;
    }
}