package org.example.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
		System.out.println("       /\\_/\\  ");
		System.out.println("      ( o.o )  ");
		System.out.println("       > ^ <  ");
		System.out.println("      /\\_^_/\\ ");
		System.out.println("SERVER START...");
	}
}
