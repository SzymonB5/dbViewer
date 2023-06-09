package com.dbViewer.dbViewer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DbViewerApplication {

	public static void main(String[] args) {
//		System.out.println(DatabaseConnector.getDatabaseName());
		SpringApplication.run(DbViewerApplication.class, args);
	}

	@GetMapping("/")
	public String test() {
		return "Currently using " + DatabaseConnector.getDatabaseName() + ".";
	}

	@GetMapping("/test")
	public String test2() {
		return "123";
	}

}
