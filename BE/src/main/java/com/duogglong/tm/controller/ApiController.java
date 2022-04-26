package com.duogglong.tm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//@RestController
public class ApiController<T> {

    @PostMapping
    public ResponseEntity<T> create() {
        return new ResponseEntity<T>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable("id") UUID id) {
        return new ResponseEntity<T>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        return new ResponseEntity<List<T>>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<T> update() {
        return new ResponseEntity<T>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete() {
        return new ResponseEntity<Boolean>(HttpStatus.OK);
    }

}
