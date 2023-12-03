package com.example.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
public class PasswordResetTokenC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public PasswordResetTokenC(String token) {
        this.token = token;
    }

    private String token;

    public PasswordResetTokenC(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public PasswordResetTokenC(Long id) {
        this.id = id;
    }

    public PasswordResetTokenC(User user) {
        this.user = user;
    }

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;







    public PasswordResetTokenC() {

    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private LocalDateTime expiryDate;
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }
}
