package com.usecases.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class BatchJobScheduler {

    private static final Logger LOGGER = Logger.getLogger(BatchJobScheduler.class.getName());

    @Scheduled(fixedRate =  60 * 1000) // Run every 1 minute
    public void runBatchJob() {
        try {
            processCSV();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error processing CSV", e);
        }
    }


    private void processCSV() throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            // Reading data from CSV file
            reader = new BufferedReader(new FileReader("src/main/resources/input.csv"));

            // copying data from input to output file
            writer = new BufferedWriter(new FileWriter("src/main/resources/output.csv"));

            String line;
            while ((line = reader.readLine()) != null) {
               //  copy data to output file
                writer.write(line);
                writer.newLine();
            }

            LOGGER.info("CSV processing completed successfully.");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error closing reader", e);
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error closing writer", e);
                }
            }
        }
    }
}
