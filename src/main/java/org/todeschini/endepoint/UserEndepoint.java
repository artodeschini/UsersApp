package org.todeschini.endepoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.todeschini.model.User;
import org.todeschini.repository.UserRepository;

import javax.validation.Valid;

/**
 * Created by Artur on 14/05/18.
 */
@RestController("users")
public class UserEndepoint {

    private UserRepository repository;

    @Autowired
    public UserEndepoint(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        return new ResponseEntity<>(repository.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody User user) {
        repository.save(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}