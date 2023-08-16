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

import com.app.entity.Login;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.LoginRepository;

@RestController
@RequestMapping("/logins")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository; // Assuming you have a repository for Login

    @GetMapping
    public ResponseEntity<List<Login>> getAllLogins() {
        List<Login> logins = loginRepository.findAll();
        return ResponseEntity.ok(logins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Login> getLoginById(@PathVariable Long id) throws ResourceNotFoundException {
        Login login = loginRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Login not found with id: " + id));
        return ResponseEntity.ok(login);
    }

    @PostMapping
    public ResponseEntity<Login> createLogin(@RequestBody Login login) {
        Login createdLogin = loginRepository.save(login);
        return ResponseEntity.created(null).body(createdLogin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Login> updateLogin(@PathVariable Long id, @RequestBody Login login) throws ResourceNotFoundException {
        Login existingLogin = loginRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Login not found with id: " + id));

        // Update fields of the existingLogin with values from login

        Login updatedLogin = loginRepository.save(existingLogin);
        return ResponseEntity.ok(updatedLogin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogin(@PathVariable Long id) throws ResourceNotFoundException {
        Login login = loginRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Login not found with id: " + id));

        loginRepository.delete(login);
        return ResponseEntity.noContent().build();
    }
}
