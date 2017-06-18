package ee.netgroup.su.diagnostic.web.Controllers;

import ee.netgroup.su.diagnostic.web.Disease;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Heleriin on 18/06/2017.
 */
public class DataController {

    DiseaseController diseaseController;

    public DataController(DiseaseController diseaseController) {
        this.diseaseController = diseaseController;
    }

    public List<String> parseSymptomsForDiagnosis(String data) {
        List<String> symtoms = splitData(data);
        return trimElements(symtoms);
    }

    public void parseALineOfDiseaseData(String line) {
        List<String> elements = splitData(line);
        elements = trimElements(elements);
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
        return new ArrayList<String>(Arrays.asList(data.split(",")));
    }

    public List<String> trimElements(List<String> elements) {
        return elements
                .stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
