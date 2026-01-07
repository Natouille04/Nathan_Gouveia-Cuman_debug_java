package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple brute-force implementation of ISymptomReader.
 * Reads symptoms line by line from a text file.
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;

	/**
	 * Constructor to set the input file path.
	 *
	 * @param filepath a full or relative path to the file containing symptom strings, one per line
	 */
	public ReadSymptomDataFromFile(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * Reads all symptoms from the file and returns them as a list.
	 *
	 * @return a List of symptom strings
	 */
	@Override
	public List<String> GetSymptoms() {
		ArrayList<String> result = new ArrayList<>();

		if (filepath != null) {
			try {
				// Open the symptoms file
				BufferedReader reader = new BufferedReader(new FileReader(filepath));
				String line = reader.readLine();

				// Read each line and add it to the list
				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}

				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}

