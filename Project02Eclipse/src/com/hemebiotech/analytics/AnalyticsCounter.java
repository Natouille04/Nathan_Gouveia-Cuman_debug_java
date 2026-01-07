package com.hemebiotech.analytics;

import java.util.*;

public class AnalyticsCounter {
	private static ISymptomReader reader; // Interface to read symptoms from a file
	private static ISymptomWriter writer; // Interface to write results to a file

	/**
	 * Constructor that initializes the symptom reader and writer.
	 *
	 * @param reader an implementation of ISymptomReader to fetch symptoms
	 * @param writer an implementation of ISymptomWriter to save results
	 */

	public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
		AnalyticsCounter.reader = reader;
		AnalyticsCounter.writer = writer;
	}

	/**
	 * Fetches the list of symptoms from the reader.
	 *
	 * @return a List of raw symptom strings
	 */

	public List<String> getSymptoms() {
		return reader.GetSymptoms();
	}

	/**
	 * Counts the occurrences of each symptom in the list.
	 *
	 * @param symptoms the list of symptoms to count
	 * @return a Map where the key is the symptom and the value is its count
	 */

	public Map<String, Integer> countSymptoms(List<String> symptoms) {
		Map<String, Integer> result = new HashMap<>();

		for (String symptom : symptoms) {
			if (result.get(symptom) == null) {
				result.put(symptom, 1);
			} else {
				result.compute(symptom, (k, valueIndex) -> valueIndex + 1);
			}
		}

		return result;
	}

	/**
	 * Sorts the symptoms alphabetically by name.
	 *
	 * @param symptoms a Map of symptoms and their counts
	 * @return a new Map sorted by symptom name
	 */

	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
		List<String> symptomsByKey = new ArrayList<>(symptoms.keySet());
		Collections.sort(symptomsByKey);

		Map<String, Integer> response = new LinkedHashMap<>();
		for (String key : symptomsByKey) {
			response.put(key, symptoms.get(key));
		}

		return response;
	}

	/**
	 * Writes the symptoms and their counts to the output using the writer.
	 *
	 * @param symptoms a Map of symptoms and their counts to write
	 */

	public void writeSymptoms(Map<String, Integer> symptoms) {
		writer.writeSymptoms(symptoms);
	}

	/**
	 * Main method to run the analytics workflow:
	 * reading symptoms, counting, sorting, and writing the results.
	 *
	 * @param args command-line arguments (not used)
	 * @throws Exception if reading or writing fails
	 */

	public static void main(String[] args) throws Exception {
		ISymptomReader entryFile = new ReadSymptomDataFromFile("symptoms.txt");
		ISymptomWriter outFile = new WriteSymptomDataToFile("result.out");

		AnalyticsCounter counter = new AnalyticsCounter(entryFile, outFile);

		List<String> symptoms = counter.getSymptoms();
		Map<String, Integer> sortedSymptoms = counter.sortSymptoms(counter.countSymptoms(symptoms));

		counter.writeSymptoms(sortedSymptoms);
	}
}
