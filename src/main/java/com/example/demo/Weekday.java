package com.example.demo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "weekday")
public class Weekday {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // shold be safe as Angular makes sure mo-su
    private String day;

    // one Weekday can hold many rooms
    @OneToMany()
    private List<Room> rooms;

    public Weekday() {
    }

    public Weekday(String day, List<Room> rooms) {
        this.day = day;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    public void removeRoom(Room room) {
        rooms.remove(room);
    }
}
