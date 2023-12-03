package com.example.project.repositories;

import com.example.project.model.PasswordResetTokenC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenC,Long> {
    PasswordResetTokenC findByToken(String token);

}
