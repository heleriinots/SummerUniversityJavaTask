package ee.netgroup.su.diagnostic.web;

import ee.netgroup.su.diagnostic.web.Controllers.DataController;
import ee.netgroup.su.diagnostic.web.Controllers.DiseaseController;
import ee.netgroup.su.diagnostic.web.Controllers.SymptomController;
import ee.netgroup.su.diagnostic.web.DiagnosisTools.Diagnosis;

import java.util.ArrayList;

/**
 * Created by Heleriin on 18/06/2017.
 */
public class Controller {

    public static void main(String[] args) {
        DiseaseController diseaseController =  new DiseaseController();

        Disease b = new Disease("b");
        diseaseController.addDisease(b);
        b.addSymptom("a");
        b.addSymptom("c");
        b.addSymptom("d");
        b.addSymptom("x");
        Disease bd = new Disease("bd");
        diseaseController.addDisease(bd);
        bd.addSymptom("a");
        bd.addSymptom("c");
        bd.addSymptom("d");
        bd.addSymptom("b");
        Disease a = new Disease("a");
        diseaseController.addDisease(a);
        a.addSymptom("a");
        a.addSymptom("a");
        a.addSymptom("b");
        Disease aa = new Disease("a");
        diseaseController.addDisease(aa);
        aa.addSymptom("a");
        aa.addSymptom("a");
        aa.addSymptom("b");

        SymptomController symptomController = new SymptomController(diseaseController);

        //symptomController.getSymptoms().forEach((k,v)->System.out.println("Key : " + k + " Value : " + v));
        //System.out.println(symptomController.getNumberOfUniqueSymptoms());

        Diagnosis diagnosis = new Diagnosis(diseaseController);

        ArrayList<String> s = new ArrayList<>();
        s.add("a");
        s.add("b");

        //System.out.println(diagnosis.analyseSymptoms(s));

        DataController dataController = new DataController(diseaseController);

        //dataController.parseSymptomsForDiagnosis("a h h, b, c, d").stream().forEach(st -> System.out.println(st));

        //dataController.parseALineOfDiseaseData("Neural Overstimulation Syndrome, impaired judgment, spontaneous " +
        //        "singing of operatic tunes, hallucinations");

        //diseaseController.getDiseases().stream().forEach(d -> System.out.println(d.getName() + " " + d
        //        .getNumberOfSymptoms()));

        diseaseController.getThreeDiseasesWithMostSymptoms();

        symptomController.findThreeMostCommonSymptoms();
    }
}
