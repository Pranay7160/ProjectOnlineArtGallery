package com.app.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Arts;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.ArtsRepository;

@RestController
@RequestMapping("/arts")
public class ArtsController {

    @Autowired
    private ArtsRepository artsRepository; // Assuming you have a repository for Arts

    @GetMapping
    public ResponseEntity<List<Arts>> getAllArts() {
        List<Arts> artsList = artsRepository.findAll();
        return ResponseEntity.ok(artsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arts> getArtsById(@PathVariable Long id) throws ResourceNotFoundException {
        Arts arts = artsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arts not found with id: " + id));
        return ResponseEntity.ok(arts);
    }

    @PostMapping
    public ResponseEntity<Arts> createArts(@RequestBody Arts arts) {
        Arts createdArts = artsRepository.save(arts);
        return ResponseEntity.created(null).body(createdArts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Arts> updateArts(@PathVariable Long id, @RequestBody Arts arts) throws ResourceNotFoundException {
        Arts existingArts = artsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arts not found with id: " + id));

        // Update fields of the existingArts with values from arts

        Arts updatedArts = artsRepository.save(existingArts);
        return ResponseEntity.ok(updatedArts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArts(@PathVariable Long id) throws ResourceNotFoundException {
        Arts arts = artsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arts not found with id: " + id));

        artsRepository.delete(arts);
        return ResponseEntity.noContent().build();
    }
}

