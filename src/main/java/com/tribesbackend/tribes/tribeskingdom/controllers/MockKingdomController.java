package com.tribesbackend.tribes.tribeskingdom.controllers;

import com.tribesbackend.tribes.tribeskingdom.model.Kingdom;
import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/mock")
public class MockKingdomController {

        TribesUser mockUser = new TribesUser("mockUser", "strongOne");
        Kingdom mockKingdom = new Kingdom("mockdom", mockUser);


        @GetMapping(value = "/kingdom")
        public ResponseEntity getMockKingdom() {
            return ResponseEntity.ok(mockKingdom);
        }
}
