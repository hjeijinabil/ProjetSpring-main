package com.example.project.repositories;

import com.example.project.model.Review;
import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

//    Review getReviewByUserID

    @Query("SELECT u FROM Review u WHERE u.freelancer.id = :id")
    Review getReviewByFreelancerId(@Param("id") long id);
}
