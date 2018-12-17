package com.tribesbackend.tribes.tribeskingdom.controllers;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/mock")
public class MockKingdomController {
        TribesUser mockUser = new TribesUser("mockUser", "strongOne");
        Kingdom mockKingdom = new Kingdom("mockdom", mockUser);

        @GetMapping(value = "/kingdom")
        public ResponseEntity<Kingdom> getMockKingdom() {
            return ResponseEntity.ok(mockKingdom);
        }
}
