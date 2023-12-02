package com.example.project.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class WorkSample {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;


    public freelancer getFreelance() {
        return freelance;
    }

    public void setFreelance(freelancer freelance) {
        this.freelance = freelance;
    }

    @Column(name= "description")
    private  String description;
    @Column(name="title")
    private  String title;
    @Column(name="price")
    private  String price;

    @ManyToOne
    @JoinColumn(name="freelancer_id")
    private freelancer freelance;

    public Long getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany (cascade = CascadeType.ALL)
    Set<Client> clients;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

