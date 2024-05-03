package com.example.DataConsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class DataConsumerApplication {

	public static void main(String[] args) {

		SpringApplication.run(DataConsumerApplication.class, args);
	}

}
