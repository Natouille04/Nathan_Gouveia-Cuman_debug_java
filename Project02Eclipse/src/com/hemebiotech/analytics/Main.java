package com.hemebiotech.analytics;

import java.util.List;
import java.util.Map;

public class Main {
    /**
     * Main method to run the analytics workflow:
     * reading symptoms, counting, sorting, and writing the results.
     *
     * @param args command-line arguments (not used)
     * @throws Exception if reading or writing fails
     */
    public static void main(String[] args) throws Exception {
        ISymptomReader entryFile = new ReadSymptomDataFromFile("Project02Eclipse/src/symptoms.txt");
        ISymptomWriter outFile = new WriteSymptomDataToFile("result.out");

        AnalyticsCounter counter = new AnalyticsCounter(entryFile, outFile);

        List<String> symptoms = counter.getSymptoms();
        Map<String, Integer> sortedSymptoms = counter.sortSymptoms(counter.countSymptoms(symptoms));

        counter.writeSymptoms(sortedSymptoms);
    }
}
