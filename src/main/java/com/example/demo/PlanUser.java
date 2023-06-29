package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;


@Entity
@Table(name = "users")
public class PlanUser {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //@JsonProperty("_name") then mapping does not work at all even for get requests..
    private String name;
    private String email;

    public PlanUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public PlanUser() {
        this.email ="";
        this.name ="";
    }



    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
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

