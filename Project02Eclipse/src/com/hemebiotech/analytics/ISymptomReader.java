package com.hemebiotech.analytics;

import java.util.List;

/**
 * Interface defining a symptom reader.
 */
public interface ISymptomReader {

	/**
	 * Retrieves a list of symptoms.
	 *
	 * @return a List of symptom strings
	 */
	List<String> GetSymptoms();
}
