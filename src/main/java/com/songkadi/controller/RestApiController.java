package com.songkadi.controller;

import com.songkadi.exception.UserDuplicatedException;
import com.songkadi.exception.UserNotFoundException;
import com.songkadi.model.AppUser;
import com.songkadi.service.AppUserService;
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
    AppUserService appUserService; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Users---------------------------------------------
    @GetMapping(value = "/user/")
    public ResponseEntity<List<AppUser>> listAllUsers() {
        logger.info("Fetching all Users");

        List<AppUser> appUsers = appUserService.findAllUsers();
        if (appUsers.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<List<AppUser>>(appUsers, HttpStatus.OK);
    }

    // -------------------Retrieve Single AppUser------------------------------------------
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        logger.info("Fetching AppUser with id {}", id);

        AppUser currentAppUser = appUserService.findById(id);
        if (currentAppUser == null) {
            throw new UserNotFoundException(id);
        }

        return new ResponseEntity<AppUser>(currentAppUser, HttpStatus.OK);
    }

    // -------------------Create a AppUser-------------------------------------------
    @PostMapping(value = "/appUser/")
    public ResponseEntity<?> createUser(@RequestBody AppUser appUser, UriComponentsBuilder ucBuilder) {
        logger.info("Creating AppUser : {}", appUser);

        if (appUserService.isUserExist(appUser)) {
            throw new UserDuplicatedException(appUser);
        }

        AppUser resultAppUser = appUserService.saveUser(appUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/appUser/{id}").buildAndExpand(resultAppUser.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a AppUser ------------------------------------------------
    @PutMapping(value = "/appUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody AppUser appUser, UriComponentsBuilder ucBuilder) {
        logger.info("Updating AppUser with id {}", id);

        AppUser currentAppUser = appUserService.findById(id);
        if (currentAppUser == null) {
            throw new UserNotFoundException(id);
        }

        currentAppUser.setName(appUser.getName());
        currentAppUser.setAge(appUser.getAge());
        currentAppUser.setSalary(appUser.getSalary());

        AppUser resultAppUser = appUserService.updateUser(currentAppUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/appUser/{id}").buildAndExpand(resultAppUser.getId()).toUri());
        return new ResponseEntity<AppUser>(resultAppUser, HttpStatus.OK);
    }

    // ------------------- Delete a AppUser-----------------------------------------
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting AppUser with id {}", id);

        AppUser currentAppUser = appUserService.findById(id);
        if (currentAppUser == null) {
            throw new UserNotFoundException(id);
        }

        appUserService.deleteUserById(id);
        return new ResponseEntity<AppUser>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Users-----------------------------
    @DeleteMapping(value = "/user/")
    public ResponseEntity<AppUser> deleteAllUsers() {
        logger.info("Deleting All Users");

        appUserService.deleteAllUsers();
        return new ResponseEntity<AppUser>(HttpStatus.NO_CONTENT);
    }
}