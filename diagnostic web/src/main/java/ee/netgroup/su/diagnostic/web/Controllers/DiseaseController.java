package ee.netgroup.su.diagnostic.web.Controllers;

import ee.netgroup.su.diagnostic.web.Disease;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Heleriin on 17/06/2017.
 */
public class DiseaseController {

    private ArrayList<Disease> diseases = new ArrayList<>();


    public void addDisease(Disease disease) {
        if (!diseases.contains(disease)) diseases.add(disease);
    }

    public ArrayList<Disease> getDiseases() {
        return diseases;
    }

    public void sortDiseasesAlphabetically() {
        Collections.sort(diseases, Comparator.comparing(Disease::getName));
    }

    private int findLargestValue() {
        return diseases
                .stream()
                .mapToInt(d -> d.getNumberOfSymptoms())
                .max()
                .getAsInt();
    }

    private int findSmallerLargestValue(int largestValue) {
        return diseases
                .stream()
                .mapToInt(d -> d.getNumberOfSymptoms())
                .filter(d -> d < largestValue)
                .max()
                .getAsInt();
    }

    private ArrayList<Disease> addMaxElements(int largestValue, ArrayList<Disease> maxValues) {
        diseases.stream().filter(d -> d.getNumberOfSymptoms() == largestValue).forEach(d -> {
            if (maxValues.size() < 3) {
                maxValues.add(d);
            }});
        return maxValues;
    }

    public ArrayList<Disease> findThreeDiseasesWithMostSymptoms() {
        ArrayList<Disease> maxValues = new ArrayList<>();
        if (diseases.isEmpty()) return maxValues;

        sortDiseasesAlphabetically();

        int largestValue = findLargestValue();
        int count = 0;

        while (maxValues.size() < 3) {
            if (count > 0) {
                largestValue = findSmallerLargestValue(largestValue);
            }

            maxValues = addMaxElements(largestValue, maxValues);

            if (maxValues.size() == diseases.size()) {
                return maxValues;
            }
            count++;
        }

        return maxValues;
    }

    public String findThreeDiseasesWithMostSymptomsString() {
        String diseasesString = "";
        ArrayList<Disease> maxSymptomDiseases = findThreeDiseasesWithMostSymptoms();
        for (int x = 0; x <  maxSymptomDiseases.size(); x++) {
            if (x > 0) {
                diseasesString += ", ";
            }
            diseasesString += maxSymptomDiseases.get(x).getName();
        }
        return diseasesString;
    }





}
