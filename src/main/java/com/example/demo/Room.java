package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import org.springframework.data.annotation.Id;


@Entity
@Table(name = "rooms")
public class Room {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String name;

    public Room(String name) {
        this.name = name;
    }

    public Room() {

    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name=" + name + '}';
    }
}
