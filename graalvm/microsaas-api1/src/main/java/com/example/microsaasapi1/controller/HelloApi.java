package com.example.microsaasapi1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
public class HelloApi {

  @GetMapping
  public String helloMessage(@RequestParam String name) {
    return "Hello " + name;
  }

}
