package ee.netgroup.su.diagnostic.web.DiagnosisTools;

import ee.netgroup.su.diagnostic.web.Controllers.DiseaseController;
import ee.netgroup.su.diagnostic.web.Controllers.SymptomController;
import ee.netgroup.su.diagnostic.web.Disease;

import java.util.*;
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

    public String composeQuestion(String symptom) {
        return "Do you experience " + symptom + "?";
    }


//    public String chooseRandomSymptom() {
//        int randomIndex = 0 + (int)(Math.random() * symptomsToAsk.size() - 1);
//        String symptom = symptomsToAsk.get(randomIndex);
//        removeFromSymptomsToAsk(symptom);
//        return symptom;
//    }

    public List<String> symptomsInPossibleDiseases() {
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
        List<String> diseaseSymptoms = symptomsInPossibleDiseases();
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
        ArrayList<Integer> possibleValues = new ArrayList<>();
        String symptom;

        for (Map.Entry<String, Integer> element : symptomController.getSymptoms().entrySet()) {
            if (symptomsToAsk.contains(element.getKey())) {
                possibleValues.add(element.getValue());
            }
        }

        int value = possibleValues.get((possibleValues.size() - 1) / 2);

        for (Map.Entry<String, Integer> element : symptomController.getSymptoms().entrySet()) {
            if (element.getValue() == value && symptomsToAsk.contains(element.getKey())) {
                symptom = element.getKey();
                symptomsToAsk.remove(symptom);
                return symptom;
            }
        }
        return null;
    }



//    public String chooseLeastCommonSymptom() {
//        int minValue = Collections.max(symptomController.getSymptoms().values());
//        String symptom;
//
//        for (Map.Entry<String, Integer> element : symptomController.getSymptoms().entrySet()) {
//            if (symptomsToAsk.contains(element.getKey()) && element.getValue() < minValue) {
//                minValue = element.getValue();
//            }
//        }
//
//        for (Map.Entry<String, Integer> element : symptomController.getSymptoms().entrySet()) {
//            if (symptomsToAsk.contains(element.getKey()) && element.getValue() == minValue) {
//                symptom = element.getKey();
//                removeFromSymptomsToAsk(symptom);
//                return symptom;
//            }
//        }
//        return null;
//    }

    public void askQuestion() {
        int count = 1;
        while (possibleDiseases.size() >= 1) {
            if (symptomsToAsk.size() == 0) {
                System.out.println("No matching disease found");
                break;
            }
            String symptom = chooseAverageSymptom();
            System.out.println(composeQuestion(symptom));
            askInput(symptom);
            if (possibleDiseases.size() == 0 || symptomsToAsk.size() == 0) {
                System.out.println("No matching disease found");
                break;
            } else {
                System.out.println("Q: " + count +", Possible diseases:\n");
                possibleDiseases.stream().forEach(d -> System.out.println(d.getName()));
                System.out.println("\n");
            }
            findSymptomsToAsk();
            System.out.println(symptomsToAsk.size());
            System.out.println(possibleDiseases.size());
            count++;
        }
    }


    public List<Disease> findPossibleDiseases(String symptom, boolean symptomExists) {
        List<Disease> newPossibleDiseases;
        if (symptomExists) {
            newPossibleDiseases = possibleDiseases
                    .stream()
                    .filter(d -> d.containsSymptom(symptom))
                    .collect(Collectors.toList());
        } else {
            newPossibleDiseases = possibleDiseases
                    .stream()
                    .filter(d -> !d.containsSymptom(symptom))
                    .collect(Collectors.toList());
        }
        possibleDiseases = newPossibleDiseases;
        return possibleDiseases;
    }


    public void askInput(String symptom) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter y or n: ");
        String answer = reader.next();
        if (answer.equals("y")) {
            findPossibleDiseases(symptom, true);
        } else {
            findPossibleDiseases(symptom, false);
        }
    }





}
