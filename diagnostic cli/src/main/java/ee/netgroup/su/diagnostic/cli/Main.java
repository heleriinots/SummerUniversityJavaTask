package ee.netgroup.su.diagnostic.cli;

import ee.netgroup.su.diagnostic.cli.Controllers.DiseaseController;
import ee.netgroup.su.diagnostic.cli.Controllers.SymptomController;
import ee.netgroup.su.diagnostic.cli.DiagnosisTools.Diagnosis;
import ee.netgroup.su.diagnostic.cli.DiagnosisTools.InteractiveDiagnosis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static DiseaseController diseaseController;
    private static SymptomController symptomController;
    private static InteractiveDiagnosis interactiveDiagnosis;
    private static Diagnosis diagnosis;
    private static boolean runApplication = true;

    public static DiseaseController getDiseaseController() {
        return diseaseController;
    }

    public static void createDiseaseController() {
        diseaseController = new DiseaseController();
    }

    public static void createSymptomController() {
        symptomController = new SymptomController(diseaseController);
    }

    public static void createDiagnosis() {
        diagnosis = new Diagnosis(diseaseController);
    }

    public static void createInteractiveDiagnosis() {
        interactiveDiagnosis = new InteractiveDiagnosis(symptomController, diseaseController);
    }

    private static File getInputFile(final String[] arguments) {
        if (arguments.length < 1)
            throw new IllegalArgumentException("No input file given at commandline.");

        final File inputFile = new File(arguments[0]);

        if (!inputFile.exists())
            throw new IllegalArgumentException("Specified input file does not exist: " + inputFile);

        if (!inputFile.canRead())
            throw new IllegalArgumentException("Specified input file is not readable: " + inputFile);

        return inputFile;
    }

    /**
     * Starting point for our application.
     */
    public static void main(final String[] arguments) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getInputFile(arguments)))) {

            createDiseaseController();

            while (true) {
                final String textLine = bufferedReader.readLine();
                if (textLine == null)
                    break;
                parseALineOfDiseaseData(textLine);
            }

            createSymptomController();
            printStatistics();

            while (runApplication) {
                askForAction();
            }
        }
    }

    private static void printStatistics() {
        diseaseController.printThreeDiseasesWithMostSymptoms();
        symptomController.printNumberOfUniqueSymptoms();
        symptomController.printThreeMostCommonSymptoms();
    }

    public static void askForAction() {
        System.out.println("Enter 1 to get diagnosis by symptoms");
        System.out.println("Enter 2 to get interactive diagnosis");
        System.out.println("Enter 3 to exit");

        readAnswer();
    }

    private static void readAnswer() {
        Scanner reader = new Scanner(System.in);
        String answer = reader.next();
        evaluateAnswer(answer);
    }

    public static void evaluateAnswer(String answer) {
        if (answer.equals("1")) {
            createDiagnosis();
            diagnosis.askForSymptoms();

        } else if (answer.equals("2")) {
            createInteractiveDiagnosis();
            interactiveDiagnosis.askQuestion();

        } else if (answer.equals("3")) {
            runApplication = false;

        } else {
            askForAction();
        }
    }


    public static void parseALineOfDiseaseData(String line) {
        List<String> elements = splitData(line);
        if (elements.size() > 1) {
            createDisease(elements);
        }
    }

    public static void createDisease(List<String> elements) {
        Disease disease = new Disease(elements.get(0));
        elements.remove(0);
        elements.stream().forEach(s -> disease.addSymptom(s));
        diseaseController.addDisease(disease);
    }

    public static List<String> splitData(String data) {
        return new ArrayList<>(Arrays.asList(data.split(", ")));
    }

}
