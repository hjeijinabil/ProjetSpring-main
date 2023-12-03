package com.example.project.controller;

import com.example.project.model.User;
import com.example.project.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class ForgotPasswordController {
    @Autowired
    private PasswordResetService passwordResetService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }
    @PostMapping("/forgot-password")
    public String processForgotPasswordForm(@RequestParam String email, Model model) {
        User user = passwordResetService.findUserByEmail(email);

        if (user != null) {
            String resetToken = UUID.randomUUID().toString();
            passwordResetService.createPasswordResetTokenForUser(user, resetToken);

            // Implement email sending logic here

            model.addAttribute("message", "If an account with that email exists, an email has been sent with instructions.");
        } else {
            model.addAttribute("error", "No account found with that email.");
        }

        return "reset-password";
    }
}
