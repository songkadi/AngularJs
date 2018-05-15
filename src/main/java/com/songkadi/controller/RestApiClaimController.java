package com.songkadi.controller;

import com.songkadi.model.Claim;
import com.songkadi.repositories.ClaimRepository;
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
public class RestApiClaimController {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClaimRepository claimRepository;

    @GetMapping(value = "/claim/")
    public ResponseEntity<List<Claim>> listAllClaims() {
        logger.info("Fetching all Claim");

        List<Claim> claims = claimRepository.findAll();

        return new ResponseEntity<>(claims, HttpStatus.OK);
    }

    @GetMapping(value = "/claim/{claimNumber}")
    public ResponseEntity<?> getClaim(@PathVariable("claimNumber") String claimNumber) {
        logger.info("Fetching Claim with claimNumber {}", claimNumber);

        Claim currentClaim = claimRepository.findOne(claimNumber);

        return new ResponseEntity<>(currentClaim, HttpStatus.OK);
    }

    @PostMapping(value = "/claim/")
    public ResponseEntity<?> createClaim(@RequestBody Claim claim, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Claim : {}", claim);

        Claim resultClaim = claimRepository.save(claim);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/claim/{id}").buildAndExpand(resultClaim.getClaimNumber()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
