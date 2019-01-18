package com.tribesbackend.tribes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TribesApplication  {

//    @Autowired
//    ItemPriceRepository itemPriceRepository;

    public static void main(String[] args) {
        SpringApplication.run(TribesApplication.class, args);
    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        itemPriceRepository.save(new ItemPrice("townhall", 300));
//        itemPriceRepository.save(new ItemPrice("mine", 100));
//        itemPriceRepository.save(new ItemPrice("farm", 80));
//        itemPriceRepository.save(new ItemPrice("barracks", 150));
//        itemPriceRepository.save(new ItemPrice("troop", 20));
//
//    }
}
