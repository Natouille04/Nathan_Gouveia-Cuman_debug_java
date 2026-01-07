package com.hemebiotech.analytics;

import java.util.Map;

/**
 * Interface defining a symptom writer.
 */
public interface ISymptomWriter {

    /**
     * Writes the given symptoms and their counts.
     *
     * @param symptoms a Map where the key is the symptom and the value is the count
     */
    void writeSymptoms(Map<String, Integer> symptoms);
}
