package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weekdays")
@CrossOrigin(origins = "http://localhost:4200")
public class WeekdayController {

    private final WeekdayRepository weekdayRepository;

    @Autowired
    public WeekdayController(WeekdayRepository weekdayRepository) {
        this.weekdayRepository = weekdayRepository;
    }

    // I think I will not need it, as weekdays will belong to a plan/week and then only the specifik weekdays with their specific rooms will matter
   /* @GetMapping("/weekdays")
    public List<Weekday> getWeekdays() {
        return (List<Weekday>) weekdayRepository.findAll();
    }*/

    @GetMapping("/weekdays/{id}")
    public ResponseEntity<Weekday> getWeekdayById(@PathVariable Long id) {
        Optional<Weekday> optionalWeekday = weekdayRepository.findById(id);
        return optionalWeekday.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/weekdays/{id}")
    public ResponseEntity<Void> addRoomToWeekday(@PathVariable Long id, @RequestBody Room room) {
        Optional<Weekday> optionalWeekday = weekdayRepository.findById(id);
        if (optionalWeekday.isPresent()) {
            Weekday weekday = optionalWeekday.get();
            weekday.getRooms().add(room);
            weekdayRepository.save(weekday);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomFromWeekday(@PathVariable Long id,  @RequestBody Room room) {
        Optional<Weekday> optionalWeekday = weekdayRepository.findById(id);
        if (optionalWeekday.isPresent()) {
            optionalWeekday.get().removeRoom(room);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
