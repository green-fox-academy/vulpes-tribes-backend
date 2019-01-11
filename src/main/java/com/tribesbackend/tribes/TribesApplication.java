package com.tribesbackend.tribes;

import com.tribesbackend.tribes.tribesuser.model.TribesUser;
import com.tribesbackend.tribes.tribesuser.repository.UserTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TribesApplication  {

    public static void main(String[] args) {
        SpringApplication.run(TribesApplication.class, args);
    }
}
