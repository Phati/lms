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

    @GetMapping("/oom")
    public void oom(){
        try {
            // Keep allocating memory until OutOfMemoryError
            for (int i = 1; ; i++) {
                System.out.println("Iteration " + i);
                int[] arr = new int[Integer.MAX_VALUE]; // Allocate large array
            }
        } catch (OutOfMemoryError e) {
            System.err.println("Out of Memory!");
            e.printStackTrace();
        }
    }
}
