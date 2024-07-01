package com.bank.jandhan.lms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanApplicationController {

    @GetMapping("/loan/application")
    public ResponseEntity<?> getLoanApplication(){
        return new ResponseEntity<>("No applications found", HttpStatus.OK);
    }

}
