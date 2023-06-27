package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



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
        for (PlanUser planUser : planUserRepository.findAll()) {
            System.out.println(planUser);
        }

        return (List<PlanUser>) planUserRepository.findAll();
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
    @PutMapping("/users")
    void changeUser(@RequestBody PlanUser planUser) {
        planUserRepository.save(planUser);
    }

    @CrossOrigin(origins = url)
    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("This is the user id to be deleted: " + id);
        planUserRepository.deleteById(id);
    }
}
