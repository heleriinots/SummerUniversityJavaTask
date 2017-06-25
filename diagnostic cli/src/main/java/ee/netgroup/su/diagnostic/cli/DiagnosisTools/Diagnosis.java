package ee.netgroup.su.diagnostic.cli.DiagnosisTools;

import ee.netgroup.su.diagnostic.cli.Controllers.DiseaseController;
import ee.netgroup.su.diagnostic.cli.Disease;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Heleriin on 18/06/2017.
 */
public class Diagnosis {

    ArrayList<Disease> possibleDiseases;
    DiseaseController diseaseController;


    public Diagnosis(DiseaseController diseaseController) {
        this.diseaseController = diseaseController;
    }


    public ArrayList<Disease> findPossibleDiseases(List<String> patientsSymptoms) {
        possibleDiseases = new ArrayList<>();

        for (Disease disease : diseaseController.getDiseases()) {
            int symptomCount = 0;
            for (String symptom : patientsSymptoms) {
                if (disease.containsSymptom(symptom)) symptomCount++;
            }
            if (symptomCount == patientsSymptoms.size()) possibleDiseases.add(disease);
        }
        return possibleDiseases;
    }


    public void askForSymptoms() {
        printInputInfo();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String answer;

        try {
            answer = br.readLine();
            evaluateSymptoms(answer);

        } catch (IOException e) {
            askForSymptoms();
        }
    }

    private void printInputInfo() {
        System.out.println("Please enter a comma separated list of symptoms");
        System.out.println("e.g. fever, hallucinations, nausea\n");
    }

    public void evaluateSymptoms(String answer) {
        if (answer == null || answer.equals("")) {
            askForSymptoms();
        } else {
            findPossibleDiseases(splitData(answer));
            printNamesOfPossibleDiseases();
        }

    }

    public List<String> splitData(String data) {
        return new ArrayList<>(Arrays.asList(data.split(", ")));
    }


    public void printNamesOfPossibleDiseases() {
        System.out.println("\nPossible diseases:\n");
        if (possibleDiseases.isEmpty()) {
            System.out.println("No matching diseases\n");
        } else {
            possibleDiseases
                    .stream()
                    .forEach(d -> System.out.println(d.getName()));
            System.out.println();
        }
    }

}
