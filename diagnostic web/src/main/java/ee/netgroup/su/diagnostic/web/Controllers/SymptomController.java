package ee.netgroup.su.diagnostic.web.Controllers;

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

    public void addSymptoms() {
        diseaseController
                .getDiseases()
                .stream()
                .forEach(d -> d
                        .getSymptoms()
                        .stream()
                        .forEach(s -> addSymptom(s)));
    }

    public void addSymptom(String symptom) {
        if (symptoms.containsKey(symptom)) {
            symptoms.put(symptom, symptoms.get(symptom) + 1);
        } else {
            symptoms.put(symptom, 1);
        }
    }

    public int getNumberOfUniqueSymptoms() {
        return symptoms.size();
    }

    public void findThreeMostCommonSymptoms() {
        //
    }

    public TreeMap<String, Integer> getSymptoms() {
        return symptoms;
    }


}
