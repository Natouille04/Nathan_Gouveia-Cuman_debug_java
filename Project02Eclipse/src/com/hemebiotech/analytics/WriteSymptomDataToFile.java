package com.hemebiotech.analytics;

import java.io.*;
import java.util.Map;

public class WriteSymptomDataToFile implements ISymptomWriter {

    private String filepath;
    public WriteSymptomDataToFile (String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void writeSymptoms(Map<String, Integer> symptoms) {
        if (filepath != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))) {
                for (Map.Entry<String, Integer> entry : symptoms.entrySet()) {
                    String symptom = entry.getKey();
                    Integer value = entry.getValue();

                    writer.write(symptom + "->" + value);
                    writer.newLine();
                }
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
