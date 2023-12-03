package com.example.project.model;

import jakarta.persistence.*;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;
    @Column(name= "Title")
    private  String Title;



    @Column(name="description")
    private  String description;


    @Column(name="budget")
    private  String budget;


    @Column(name="status")
    private  String status;

    @ManyToOne
    @JoinColumn(name="freelancer_id")
    private freelancer freelancers;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="pr_id")
    private Project pr;

    public void setClient(Client client) {
        this.client=client;
    }

    public String getTitle() {
        return Title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
