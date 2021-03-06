package ee.netgroup.su.diagnostic.cli.Controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Heleriin on 18/06/2017.
 */
public class SymptomController {

    private TreeMap<String, Integer> symptoms = new TreeMap<>();
    private DiseaseController diseaseController;

    public SymptomController(DiseaseController diseaseController) {
        this.diseaseController = diseaseController;
        addSymptoms();
    }


    private void addSymptoms() {
        diseaseController
                .getDiseases()
                .stream()
                .forEach(d -> d
                        .getSymptoms()
                        .stream()
                        .forEach(s -> addSymptom(s)));
    }


    private void addSymptom(String symptom) {
        if (symptoms.containsKey(symptom)) {
            symptoms.put(symptom, symptoms.get(symptom) + 1);
        } else {
            symptoms.put(symptom, 1);
        }
    }


    public int getNumberOfUniqueSymptoms() {
        return symptoms.size();
    }


    private int findLargestValue() {
        Map.Entry<String, Integer> maxElement = null;

        for (Map.Entry<String, Integer> entry : symptoms.entrySet()) {
            if (maxElement == null || entry.getValue().compareTo(maxElement.getValue()) > 0) {
                maxElement = entry;
            }
        }
        return maxElement.getValue();
    }


    private int findSmallerLargestValue(int largestValue) {
        Map.Entry<String, Integer> maxElement = null;

        for (Map.Entry<String, Integer> element : symptoms.entrySet()) {
            if (maxElement == null || element.getValue().compareTo(maxElement.getValue()) > 0) {
                if (element.getValue() < largestValue) {
                    maxElement = element;
                }
            }
        }
        return maxElement.getValue();
    }


    private ArrayList<String> getListOfMostCommonSymptoms(int largestValue, ArrayList<String> maxValues) {
        for (Map.Entry<String, Integer> element : symptoms.entrySet()) {
            if (element.getValue() == largestValue && maxValues.size() < 3) {
                maxValues.add(element.getKey());
            }
        }
        return maxValues;
    }


    public ArrayList<String> findThreeMostCommonSymptoms() {
        ArrayList<String> maxValues = new ArrayList<>();
        if (symptoms.isEmpty()) return maxValues;

        int largestValue = findLargestValue();
        int count = 0;

        while (maxValues.size() < 3) {
            if (count > 0) {
                largestValue = findSmallerLargestValue(largestValue);
            }

            maxValues = getListOfMostCommonSymptoms(largestValue, maxValues);

            if (maxValues.size() == symptoms.size()) {
                return maxValues;
            }
            count++;
        }
        return maxValues;
    }


    public TreeMap<String, Integer> getSymptoms() {
        return symptoms;
    }


    public void printThreeMostCommonSymptoms() {
        ArrayList<String> commonSymptoms = findThreeMostCommonSymptoms();
        System.out.println("Three most common symptoms: " + "\n");
        commonSymptoms
                .stream()
                .forEach(s -> System.out.println(s));
        System.out.println();
    }

    public void printNumberOfUniqueSymptoms() {
        System.out.println("Number of unique symptoms: " + getNumberOfUniqueSymptoms() + "\n");
    }


}
