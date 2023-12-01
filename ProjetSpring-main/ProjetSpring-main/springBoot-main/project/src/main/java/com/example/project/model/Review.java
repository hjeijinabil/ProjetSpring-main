package com.example.project.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;
    @Column(name= "note")
    private  int note;
    @Column(name="comment")
    private  String email;

    @ManyToOne
    @JoinColumn(name="freelancer_id")
    private freelancer freelancer;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;


    public abstract void calculReview();



}
