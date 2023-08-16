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

import com.app.entity.ArtDetails;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.ArtDetailsRepository;

@RestController
@RequestMapping("/art-details")
public class ArtDetailsController {

    @Autowired
    private ArtDetailsRepository artDetailsRepository; // Assuming you have a repository for ArtDetails

    @GetMapping
    public ResponseEntity<List<ArtDetails>> getAllArtDetails() {
        List<ArtDetails> artDetailsList = artDetailsRepository.findAll();
        return ResponseEntity.ok(artDetailsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtDetails> getArtDetailsById(@PathVariable Long id) throws ResourceNotFoundException {
        ArtDetails artDetails = artDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ArtDetails not found with id: " + id));
        return ResponseEntity.ok(artDetails);
    }

    @PostMapping
    public ResponseEntity<ArtDetails> createArtDetails(@RequestBody ArtDetails artDetails) {
        ArtDetails createdArtDetails = artDetailsRepository.save(artDetails);
        return ResponseEntity.created(null).body(createdArtDetails);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtDetails> updateArtDetails(@PathVariable Long id, @RequestBody ArtDetails artDetails) throws ResourceNotFoundException {
        ArtDetails existingArtDetails = artDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ArtDetails not found with id: " + id));

        // Update fields of the existingArtDetails with values from artDetails

        ArtDetails updatedArtDetails = artDetailsRepository.save(existingArtDetails);
        return ResponseEntity.ok(updatedArtDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtDetails(@PathVariable Long id) throws ResourceNotFoundException {
        ArtDetails artDetails = artDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ArtDetails not found with id: " + id));

        artDetailsRepository.delete(artDetails);
        return ResponseEntity.noContent().build();
    }
}
