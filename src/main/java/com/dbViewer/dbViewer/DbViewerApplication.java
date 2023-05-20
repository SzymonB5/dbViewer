package com.dbViewer.dbViewer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DbViewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbViewerApplication.class, args);
	}

	@GetMapping("/test")
	public String test() {
		return "Hello world!123";
	}

}
