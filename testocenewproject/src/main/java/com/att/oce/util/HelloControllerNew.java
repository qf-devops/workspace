package com.att.oce.util;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloControllerNew {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
