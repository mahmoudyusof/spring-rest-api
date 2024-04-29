package com.maidtask.trial.patrons;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    
    private final PatronRepository patronRepository;

    public PatronController(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @GetMapping("")
    public List<Patron> getPatrons() {
        return patronRepository.findAll();
    }

    @GetMapping("/{id}")
    public Patron getPatron(@PathVariable Integer id) {
        return patronRepository.findById(id).orElse(null);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPatron(@RequestBody Patron patron) {
        patronRepository.save(patron);
    }

    @DeleteMapping("/{id}")
    public void deletePatron(@PathVariable Integer id) {
        patronRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updatePatron(@PathVariable Integer id, @RequestBody Patron patron) {
        patronRepository.save(patron);
    }
}
