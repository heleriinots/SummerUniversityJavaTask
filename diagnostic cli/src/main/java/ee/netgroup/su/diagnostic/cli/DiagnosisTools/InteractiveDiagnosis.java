package ee.netgroup.su.diagnostic.cli.DiagnosisTools;

import ee.netgroup.su.diagnostic.cli.Controllers.DiseaseController;
import ee.netgroup.su.diagnostic.cli.Controllers.SymptomController;
import ee.netgroup.su.diagnostic.cli.Disease;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Heleriin on 18/06/2017.
 */
public class InteractiveDiagnosis {

    private ArrayList<String> symptomsToAsk;
    private List<Disease> possibleDiseases;
    private SymptomController symptomController;

    public InteractiveDiagnosis(SymptomController symptomController, DiseaseController diseaseController) {
        this.possibleDiseases = diseaseController.getDiseases();
        this.symptomController = symptomController;
        this.symptomsToAsk = new ArrayList<>(symptomController.getSymptoms().keySet());
    }

    public void composeQuestion(String symptom) {
        System.out.println("Do you experience " + symptom + "?\n");
    }


    public List<String> getSymptomsInPossibleDiseases() {
        List<String> symptoms = new ArrayList<>();
        possibleDiseases
                .stream()
                .forEach(d -> d.getSymptoms()
                        .stream()
                        .forEach(s -> symptoms.add(s)));
        return symptoms;
    }


    private void findSymptomsToAsk() {
        ArrayList<String> newSymptomsToAsk = new ArrayList<>();
        List<String> diseaseSymptoms = getSymptomsInPossibleDiseases();
        symptomsToAsk
                .stream()
                .forEach(s -> {
                    if (diseaseSymptoms.contains(s)) {
                        newSymptomsToAsk.add(s);
                    }
                });
        symptomsToAsk = newSymptomsToAsk;
    }


    public String chooseAverageSymptom() {
        ArrayList<Integer> possibleValues = findPossibleValues();
        int value = calculateIndex(possibleValues);
        return findSymptomToAskNext(value);
    }

    private String findSymptomToAskNext(int value) {
        String symptom;

        for (Map.Entry<String, Integer> element : symptomController.getSymptoms().entrySet()) {
            if (element.getValue() == value && symptomsToAsk.contains(element.getKey())) {
                symptom = element.getKey();
                symptomsToAsk.remove(symptom);
                return symptom;
            }
        }
        return null;
    }

    private ArrayList<Integer> findPossibleValues() {
        ArrayList<Integer> possibleValues = new ArrayList<>();

        for (Map.Entry<String, Integer> element : symptomController.getSymptoms().entrySet()) {
            if (symptomsToAsk.contains(element.getKey())) {
                possibleValues.add(element.getValue());
            }
        }
        return possibleValues;
    }

    private Integer calculateIndex(ArrayList<Integer> possibleValues) {
        return possibleValues.get((possibleValues.size() - 1) / 2);
    }


    public void askQuestion() {
        int count = 1;
        boolean askQuestions = true;

        while (askQuestions) {
            processQuestionAndInput(count);

            if (possibleDiseases.size() == 1) {

                printPossibleDiseaases();
                askQuestions = false;

            } else if (symptomsToAsk.size() == 0) {

                askQuestions = printNoDiseasesMessageAndExitInteractiveDiagnosis();
            } else {

                printPossibleDiseaases();
                findSymptomsToAsk();
            }
            if (possibleDiseases.size() == 0) {

                askQuestions = printNoDiseasesMessageAndExitInteractiveDiagnosis();
            }
            count++;
        }
    }

    private void processQuestionAndInput(int count) {
        String symptom = chooseAverageSymptom();
        System.out.println("Question number: " + count);
        composeQuestion(symptom);
        askInput(symptom);
    }

    private boolean printNoDiseasesMessageAndExitInteractiveDiagnosis() {
        System.out.println("No matching disease found");
        return false;
    }

    private void printPossibleDiseaases() {
        System.out.println("Possible diseases:\n");
        possibleDiseases.stream().forEach(d -> System.out.println(d.getName()));
        System.out.println("\n");
    }


    public List<Disease> findPossibleDiseases(String symptom, boolean symptomExists) {
        List<Disease> newPossibleDiseases;
        if (symptomExists) {
            newPossibleDiseases = findPossibleDiseasesSymptomExists(symptom);
        } else {
            newPossibleDiseases = findPossibleDiseasesSymptomDoesNotExist(symptom);
        }
        possibleDiseases = newPossibleDiseases;
        return possibleDiseases;
    }

    private List<Disease> findPossibleDiseasesSymptomDoesNotExist(String symptom) {
        List<Disease> newPossibleDiseases;
        newPossibleDiseases = possibleDiseases
                .stream()
                .filter(d -> !d.containsSymptom(symptom))
                .collect(Collectors.toList());
        return newPossibleDiseases;
    }

    private List<Disease> findPossibleDiseasesSymptomExists(String symptom) {
        List<Disease> newPossibleDiseases;
        newPossibleDiseases = possibleDiseases
                .stream()
                .filter(d -> d.containsSymptom(symptom))
                .collect(Collectors.toList());
        return newPossibleDiseases;
    }

    public void askInput(String symptom) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter y (yes) or n (no): ");
        String answer = reader.next();
        evaluateAnswer(symptom, answer);
    }

    private void evaluateAnswer(String symptom, String answer) {
        if (answer.equals("y") || answer.equals("yes")) {
            findPossibleDiseases(symptom, true);
        } else if (answer.equals("n") || answer.equals("no")) {
            findPossibleDiseases(symptom, false);
        } else {
            askInput(symptom);
        }
    }
}
