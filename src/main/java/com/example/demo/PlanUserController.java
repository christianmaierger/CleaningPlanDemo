package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Todo actual CORS @CrossOrigin(origins = "http://localhost:4200")
public class PlanUserController {

    // standard constructors

    private final PlanUserRepository planUserRepository;

    public PlanUserController(PlanUserRepository planUserRepository) {
        this.planUserRepository = planUserRepository;
    }

    @GetMapping("/users")
    public List<PlanUser> getUsers() {
        return (List<PlanUser>) planUserRepository.findAll();
    }

    @PostMapping("/users")
    ResponseEntity<String> addUser(@Valid @RequestBody PlanUser planUser) {
        // TODO Exception handling
        planUserRepository.save(planUser);
        return ResponseEntity.ok("User is valid");
    }
}
