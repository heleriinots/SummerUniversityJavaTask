package ee.netgroup.su.diagnostic.web.DiagnosisTools;

import ee.netgroup.su.diagnostic.web.Controllers.DiseaseController;
import ee.netgroup.su.diagnostic.web.Disease;

import java.util.ArrayList;
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

    public ArrayList<Disease> analyseSymptoms(List<String> patientsSymptoms) {
        possibleDiseases = new ArrayList<>();

        for (Disease disease: diseaseController.getDiseases()) {
            int symptomCount = 0;
            for (String symptom: patientsSymptoms) {
                if (disease.containsSymptom(symptom)) symptomCount++;
            }
            if (symptomCount == patientsSymptoms.size()) possibleDiseases.add(disease);
        }
        return possibleDiseases;
    }


    //is this useful?

//    private String getNamesOfPossibleDiseases() {
//        String diseaseNames = "";
//        for (int x = 0; x < possibleDiseases.size(); x++) {
//            if (x > 0) diseaseNames += "\n";
//            diseaseNames += possibleDiseases.get(x).getName();
//        }
//        return diseaseNames;
//    }

}
