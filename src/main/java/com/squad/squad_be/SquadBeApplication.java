package com.squad.squad_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SquadBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SquadBeApplication.class, args);
    }

    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }

}
