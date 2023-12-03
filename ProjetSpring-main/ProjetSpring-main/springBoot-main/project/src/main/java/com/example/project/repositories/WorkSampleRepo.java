package com.example.project.repositories;

import com.example.project.model.Project;
import com.example.project.model.WorkSample;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkSampleRepo extends JpaRepository<WorkSample,Long> {

}
