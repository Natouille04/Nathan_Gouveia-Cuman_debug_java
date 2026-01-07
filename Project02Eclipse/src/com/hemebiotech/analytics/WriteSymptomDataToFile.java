package com.hemebiotech.analytics;

import java.io.*;
import java.util.Map;

/**
 * Implementation of ISymptomWriter that writes symptom data to a file.
 */
public class WriteSymptomDataToFile implements ISymptomWriter {

    private String filepath;

    /**
     * Constructor to set the output file path.
     *
     * @param filepath the path of the file to write the symptom data
     */
    public WriteSymptomDataToFile(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Writes the symptoms and their occurrence counts to the file.
     * Each line is formatted as "symptom->count".
     *
     * @param symptoms a Map of symptoms and their counts
     */
    @Override
    public void writeSymptoms(Map<String, Integer> symptoms) {
        if (filepath != null) {
            // Open / Create file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))) {
                // Writing each data line by line
                for (Map.Entry<String, Integer> entry : symptoms.entrySet()) {
                    String symptom = entry.getKey();
                    Integer value = entry.getValue();

                    writer.write(symptom + "->" + value);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
