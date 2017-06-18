package ee.netgroup.su.diagnostic.web.DiagnosisTools;

import ee.netgroup.su.diagnostic.web.Controllers.DiseaseController;
import ee.netgroup.su.diagnostic.web.Disease;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Heleriin on 18/06/2017.
 */
public class InteractiveDiagnosis {

    private TreeSet<String> patientsSymptoms;
    private ArrayList<Disease> possibleDiseases;
    private DiseaseController diseaseController;

    public String composeQuestion(String symptom) {
        return "Do you experience " + symptom + "?";
    }



}
