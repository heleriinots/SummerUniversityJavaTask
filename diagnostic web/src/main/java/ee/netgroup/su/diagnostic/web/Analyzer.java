package ee.netgroup.su.diagnostic.web;

import ee.netgroup.su.diagnostic.web.Controllers.DiseaseController;
import ee.netgroup.su.diagnostic.web.Controllers.SymptomController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analyzer {

    private DiseaseController diseaseController;
    private SymptomController symptomController;

    public DiseaseController getDiseaseController() {
        return diseaseController;
    }

    public SymptomController getSymptomController() {
        return symptomController;
    }

    public String analyze(final HttpServletRequest request) throws IOException {

        final String input = request.getParameter("input");

        if (input == null)
            return "";

        final StringBuilder result = new StringBuilder();
        final StringReader reader = new StringReader(input);
        final BufferedReader bufferedReader = new BufferedReader(reader);
        createDiseaseController();

        while (true) {
            final String line = bufferedReader.readLine();

            if (line == null) {
                break;
            }

            parseALineOfDiseaseData(line);
        }

        createSymptomController();

        String diseasesWithMostSymptoms = diseaseController.findThreeDiseasesWithMostSymptomsString();
        int noOfUniqueSymptoms = symptomController.getNumberOfUniqueSymptoms();
        String mostCommonSymptoms = symptomController.findThreeMostCommonSymptomsString();

        displayStatistics(result, diseasesWithMostSymptoms, noOfUniqueSymptoms, mostCommonSymptoms);

        return result.toString();
    }

    private void displayStatistics(StringBuilder result, String diseasesWithMostSymptoms, int noOfUniqueSymptoms,
                                   String mostCommonSymptoms) {
        result.append("<br/><b>Input parsing result:</b>");
        addBreak(result);
        result.append("<br/><b>Diseases with most symptoms: </b>" + diseasesWithMostSymptoms);
        addBreak(result);
        result.append("<br/><b>Number of unique symptoms: </b>" + noOfUniqueSymptoms);
        addBreak(result);
        result.append("<br/><b>Most common symptoms: </b>" + mostCommonSymptoms);
        addBreak(result);
    }

    private void addBreak(StringBuilder result) {
        result.append("<br/>");
    }

    public void createDiseaseController() {
        diseaseController = new DiseaseController();
    }

    public void createSymptomController() {
        symptomController = new SymptomController(diseaseController);
    }

    public List<String> parseSymptomsForDiagnosis(String data) {
        return splitData(data);
    }

    public void parseALineOfDiseaseData(String line) {
        List<String> elements = splitData(line);
        if (elements.size() > 1) {
            createDisease(elements);
        }
    }

    public void createDisease(List<String> elements) {
        Disease disease = new Disease(elements.get(0));
        elements.remove(0);
        elements.stream().forEach(s -> disease.addSymptom(s));
        diseaseController.addDisease(disease);
    }

    public List<String> splitData(String data) {
        return new ArrayList<>(Arrays.asList(data.split(", ")));
    }

}
