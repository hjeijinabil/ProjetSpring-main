package com.example.project.repositories;

import com.example.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project,Long> {
    List<Project> findByClientId(Long clientId);
    @Query("SELECT c FROM Project c WHERE c.client.id = :client_id")
     List<Project> findByClient(@Param("client_id") int client_id) ;



}
