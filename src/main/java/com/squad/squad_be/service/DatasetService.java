package com.squad.squad_be.service;

import com.squad.squad_be.dto.User;
import org.springframework.stereotype.Service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class DatasetService {

    private final String[] fields = {"email", "name", "summonerLevel"};

    public void write(User user, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);

        // Create CSVPrinter object with the specified format
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(fields));

        // Add user data to the CSV file
        csvPrinter.printRecord(user.getEmail(), user.getName(), user.getAccount().getSummonerLevel());

        // Flush the CSVPrinter and close the FileWriter
        csvPrinter.flush();
        csvPrinter.close();
        writer.close();
    }

    public static void write(User user, String filePath, String[] fields) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        File file = new File(filePath);

        // Create CSVPrinter object with the specified format
        CSVPrinter csvPrinter;
        if (file.length() == 0) { // If file is empty, add header
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(fields));
        } else { // Otherwise, header already exists
            csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        }

        // Map user fields to CSV columns
        String[] record = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            switch (fields[i]) {
                case "EMAIL":
                    record[i] = user.getEmail();
                    break;
                case "NAME":
                    record[i] = user.getName();
                    break;
                case "SUMMONER_LEVEL":
                    record[i] = user.getAccount().getSummonerLevel();
                    break;
                // Add more cases to map additional fields to CSV columns
                default:
                    record[i] = ""; // Write an empty string for fields not present in the user object
            }
        }

        // Add user data to the CSV file
        csvPrinter.printRecord((Object) record);

        // Flush the CSVPrinter and close the FileWriter
        csvPrinter.flush();
        csvPrinter.close();
        writer.close();
    }
}

