package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public ResponseEntity<HashMap<String, String>> index() {
		HashMap<String, String> msg = new HashMap<String, String>();
		msg.put("msg", "Hello, Springboot to Docker!!!");
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
}
