package com.example.testapi.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controllers {

    @GetMapping("/hello")
    @RequestMapping("/hello")
    public String sayhello()
    {
        return "Hello";
    }

    @GetMapping("/hi")
    @RequestMapping("/hi")
    public String sayHi()
    {
        return "Hi";
    }

}
