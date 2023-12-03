package com.example.project.service;

import com.example.project.model.PasswordResetTokenC;
import com.example.project.model.User;
import com.example.project.repositories.PasswordResetTokenRepository;
import com.example.project.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PasswordResetService {
@Autowired
    private UserRepository userRepository;
@Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
@Autowired
    private PasswordEncoder passwordEncoder;
    @Transactional
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetTokenC resetToken = new PasswordResetTokenC();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); // Set expiration time (1 hour in this example)
        passwordResetTokenRepository.save(resetToken);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByResetToken(String token) {
        return userRepository.findByResetToken(token);
    }
    public PasswordResetTokenC findTokenByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Transactional
    public void clearPasswordResetToken(PasswordResetTokenC token) {
        passwordResetTokenRepository.delete(token);
    }

    @Transactional
    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
