package com.websystique.springboot.controller;

import com.websystique.springboot.exception.UserDuplicatedException;
import com.websystique.springboot.exception.UserNotFoundException;
import com.websystique.springboot.model.User;
import com.websystique.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    UserService userService; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Users---------------------------------------------
    @GetMapping(value = "/user/")
    public ResponseEntity<List<User>> listAllUsers() {
        logger.info("Fetching all Users");

        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // -------------------Retrieve Single User------------------------------------------
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        logger.info("Fetching User with id {}", id);

        User currentUser = userService.findById(id);
        if (currentUser == null) {
            throw new UserNotFoundException(id);
        }

        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    // -------------------Create a User-------------------------------------------
    @PostMapping(value = "/user/")
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", user);

        if (userService.isUserExist(user)) {
            throw new UserDuplicatedException(user);
        }

        User resultUser = userService.saveUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(resultUser.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a User ------------------------------------------------
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info("Updating User with id {}", id);

        User currentUser = userService.findById(id);
        if (currentUser == null) {
            throw new UserNotFoundException(id);
        }

        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());

        User resultUser = userService.updateUser(currentUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(resultUser.getId()).toUri());
        return new ResponseEntity<User>(resultUser, HttpStatus.OK);
    }

    // ------------------- Delete a User-----------------------------------------
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting User with id {}", id);

        User currentUser = userService.findById(id);
        if (currentUser == null) {
            throw new UserNotFoundException(id);
        }

        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Users-----------------------------
    @DeleteMapping(value = "/user/")
    public ResponseEntity<User> deleteAllUsers() {
        logger.info("Deleting All Users");

        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}