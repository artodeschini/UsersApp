package org.todeschini.endepoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.todeschini.error.ResourceNotFoundException;
import org.todeschini.model.Users;
import org.todeschini.repository.UserRepository;

import javax.validation.Valid;

/**
 * Created by Artur on 14/05/18.
 */
@RestController
@RequestMapping("users")
public class UserEndepoint {

    private UserRepository repository;

    @Autowired
    public UserEndepoint(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    @GetMapping( path = "/findByName/{name}")
    public ResponseEntity<?> findUsersByName(@PathVariable String name) {
        return new ResponseEntity<>(repository.findByNameIgnoreCaseContaining( name ), HttpStatus.OK );
    }

    @GetMapping( path = "/findByEmail/{email}")
    public ResponseEntity<?> findUsersByEmail(@PathVariable String email) {
        return new ResponseEntity<>(repository.findByEmailIgnoreCaseContaining( email ), HttpStatus.OK );
    }

    @GetMapping( path = "/findByUsername/{username}")
    public ResponseEntity<?> findUsersByUsername(@PathVariable String username) {
        return new ResponseEntity<>(repository.findByUsernameIgnoreCaseContaining( username ), HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Users user) {
        return new ResponseEntity<>(repository.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        verififyUserExist(id);
        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Users user) {
        verififyUserExist(user.getId());
        repository.save(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void verififyUserExist(Integer id) {
        if ( repository.findOne(id) == null ) {
            throw new ResourceNotFoundException("The user not found with ID " + id);
        }
    }

}