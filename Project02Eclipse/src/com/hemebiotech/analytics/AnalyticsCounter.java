package com.hemebiotech.analytics;

import com.hemebiotech.analytics.ISymptomReader;
import com.hemebiotech.analytics.ISymptomWriter;

import java.util.*;

public class AnalyticsCounter {
	private static ISymptomReader reader;
	private static ISymptomWriter writer;

	public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer) {
		AnalyticsCounter.reader = reader;
		AnalyticsCounter.writer = writer;
	}

	public List<String> getSymptoms() {
        return reader.GetSymptoms();
	}

	public Map<String, Integer> countSymptoms(List<String> symptoms) {
		Map<String, Integer> result = new HashMap<>();

		for(String symptom : symptoms) {
			if(result.get(symptom) == null) {
				result.put(symptom, 1);
			}

			else {
                result.compute(symptom, (k, valueIndex) -> valueIndex + 1);
			}
		}

		return result;
	}

	public Map<String, Integer> sortSymptoms(Map<String, Integer> symptoms) {
		List<String> symptomsByKey = new ArrayList<>(symptoms.keySet());
		Collections.sort(symptomsByKey);

		Map<String, Integer> response = new LinkedHashMap<>();

		for(String key : symptomsByKey) {
			response.put(key, symptoms.get(key));
		}

		return response;
	}

	public void writeSymptoms(Map<String, Integer> symptoms) {
		writer.writeSymptoms(symptoms);
	}

	public static void main(String[] args) throws Exception {}
}
