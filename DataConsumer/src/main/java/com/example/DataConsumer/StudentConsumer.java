package com.example.DataConsumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Component
public class StudentConsumer {
    private static final Logger LOGGER = Logger.getLogger(StudentConsumer.class.getName());


    @JmsListener(destination = "myqueue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
        LOGGER.info(" Received successfully.");

    }


}