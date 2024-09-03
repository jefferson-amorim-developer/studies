package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sdk.spring.core.MyUtils;


@SpringBootApplication
public class ExampleApiApplication {



	public static void main(String[] args) {

    System.out.println(MyUtils.getMessage() + "AGORA VAI 22zz");
		SpringApplication.run(ExampleApiApplication.class, args);
	}

}
