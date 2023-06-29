package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PlanUserController {

    private final String url  = "http://localhost:4200";

    // standard constructors
    @Autowired
    private final PlanUserRepository planUserRepository;

    public PlanUserController(PlanUserRepository planUserRepository) {
        this.planUserRepository = planUserRepository;
    }

    @CrossOrigin(origins = url)
    @GetMapping("/users")
    public List<PlanUser> getUsers() {
        List<PlanUser> users = (List<PlanUser>) planUserRepository.findAll();
        users.sort(Comparator.comparing(PlanUser::getId));
        for (PlanUser planUser : users) {
            System.out.println(planUser);
        }
        return users;
    }

  /*  @PostMapping("/users")
    ResponseEntity<String> addUser(@Valid @RequestBody PlanUser planUser) {
        // TODO Exception handling
        planUserRepository.save(planUser);
        return ResponseEntity.ok("ok");
    }*/
    @CrossOrigin(origins = url)
    @PostMapping("/users")
    void addUser(@RequestBody PlanUser planUser) {
        planUserRepository.save(planUser);
    }

    @CrossOrigin(origins = url)
    @PutMapping("/users/{id}")
    public ResponseEntity<String> changeUser(@PathVariable Long id, @RequestBody PlanUser updatedUser) {
        Optional<PlanUser> optionalUser = planUserRepository.findById(id);
        if (optionalUser.isPresent()) {
            PlanUser existingUser = optionalUser.get();

            // Update the properties of the existing user with the new values
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());

            // Save the updated user back to the database
            planUserRepository.save(existingUser);

            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @CrossOrigin(origins = url)
    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("This is the user id to be deleted: " + id);
        planUserRepository.deleteById(id);
    }
}
