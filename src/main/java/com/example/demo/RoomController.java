package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {

    private final String url  = "http://localhost:4200";

    // standard constructors
    @Autowired
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @CrossOrigin(origins = url)
    @GetMapping("/rooms")
    public List<Room> getRooms() {
        for (Room room : roomRepository.findAll()) {
            System.out.println(room);
        }
        return (List<Room>) roomRepository.findAll();
    }

    @CrossOrigin(origins = url)
    @PostMapping("/rooms")
    void addRoom(@RequestBody Room room) {

        try {
            roomRepository.save(room);
            // is thrown when name is already taken for a room
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            // todo better exc handling
        }
    }

    @CrossOrigin(origins = url)
    @PutMapping("/rooms/{id}")
    public ResponseEntity<String> changeRoom(@PathVariable Long id, @RequestBody Room updatedRoom) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        System.out.println("Put getriggerd, id is " + id + "and name is" + updatedRoom.getName());
        if (optionalRoom.isPresent()) {
            Room existingRoom = optionalRoom.get();
            System.out.println("Put getriggerd, id is " + id + "and name is" + updatedRoom.getName());
            // Update the properties of the existing room with the new values
            existingRoom.setName(updatedRoom.getName());

            // Save the updated room back to the database
            roomRepository.save(existingRoom);

            return ResponseEntity.ok("Room updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @CrossOrigin(origins = url)
    @DeleteMapping("rooms/{id}")
    public void deleteRoom(@PathVariable Long id) {
        System.out.println("This is the room id to be deleted: " + id);
        roomRepository.deleteById(id);
    }
}
