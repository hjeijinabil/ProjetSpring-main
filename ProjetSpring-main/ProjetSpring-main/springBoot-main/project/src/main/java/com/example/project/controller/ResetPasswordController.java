package com.example.project.controller;

import com.example.project.model.PasswordResetTokenC;
import com.example.project.model.User;
import com.example.project.repositories.PasswordResetTokenRepository;
import com.example.project.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResetPasswordController {
@Autowired
private PasswordResetService passwordResetService;


    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        User user = passwordResetService.findUserByResetToken(token);

        if (user != null) {
            model.addAttribute("token", token);
            return "reset-password";
        } else {
            model.addAttribute("error", "Invalid or expired token. Please request a new password reset.");
            return "redirect:/forgot-password";
        }
    }
    @PostMapping("/reset-password")
    public String processResetPasswordForm(@RequestParam String token, @RequestParam String password, Model model) {
        PasswordResetTokenC resetToken = passwordResetService.findTokenByToken(token);
        if (resetToken != null ) {
            User user = resetToken.getUser();
            passwordResetService.updatePassword(user, password);
            passwordResetService.clearPasswordResetToken(resetToken);

            model.addAttribute("message", "Password reset successfully.");
        } else {
            model.addAttribute("error", "Invalid or expired token.");
        }

        return "reset-password";
    }
}
