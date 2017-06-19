package ee.netgroup.su.diagnostic.web;

import ee.netgroup.su.diagnostic.web.Controllers.DiseaseController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analyzer {

    private DiseaseController diseaseController;

    public String analyze(final HttpServletRequest request) throws IOException {

        createDiseaseController();

        final String input = request.getParameter("input");

        if (input == null)
            return "";

        final StringBuilder result = new StringBuilder();

        final StringReader reader = new StringReader(input);
        final BufferedReader bufferedReader = new BufferedReader(reader);

        int linesCount = 0;

        while (true) {
            final String line = bufferedReader.readLine();
            if (line == null)
                break;

            result.append("<script>console.log('" + line + "')</script>");
            linesCount++;

            // TODO: line parsing logic ...
        }
        System.out.println(input);

        result.append("<br/>Input parsing result:");
        result.append("<br/>Detected " + linesCount + " line(s).");

        return result.toString();
    }

    public void createDiseaseController() {
        diseaseController = new DiseaseController();
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
        return new ArrayList<String>(Arrays.asList(data.split(", ")));
    }

    public DiseaseController getDiseaseController() {
        return diseaseController;
    }

}
