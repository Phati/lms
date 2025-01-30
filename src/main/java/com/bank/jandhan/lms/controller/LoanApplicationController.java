package com.bank.jandhan.lms.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
public class LoanApplicationController {

    @Autowired
    WebClient webClient;


@GetMapping("/v1/loan/application")
    public ResponseEntity<?> getLoanApplication(){
        log.info("loan application api called");
        return new ResponseEntity<>("No applications found try again", HttpStatus.OK);
    }

@GetMapping("/v1/hello")
    public ResponseEntity<?> getHello(){
        log.info("hello world ......");
        return new ResponseEntity<>("hello world ......", HttpStatus.OK);
    }


    @GetMapping("/oom")
    public void oom(){
        try {
            log.info("OOM api called");
            // Keep allocating memory until OutOfMemoryError
            for (int i = 1; ; i++) {
                System.out.println("Iteration " + i);
                int[] arr = new int[Integer.MAX_VALUE]; // Allocate large array
            }
        } catch (OutOfMemoryError e) {
            System.err.println("****************Out of Memory!***************");
            e.printStackTrace();
        }
    }

    @GetMapping("/get-credit-cards")
    public ResponseEntity<?> getCreditCards(){
        log.info("Calling Credit cards large json api");
        List list = webClient.get().uri("/credit-card/cards-data").retrieve().bodyToMono(ArrayList.class).block();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
