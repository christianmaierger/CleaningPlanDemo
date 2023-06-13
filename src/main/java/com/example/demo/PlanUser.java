package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

@Entity
public class PlanUser {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private final String name;
    private final String email;

    public PlanUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public PlanUser() {

        name = null;
        email = null;
    }


    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PlanUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

