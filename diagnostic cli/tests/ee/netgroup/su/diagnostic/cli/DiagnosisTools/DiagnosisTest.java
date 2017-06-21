package ee.netgroup.su.diagnostic.cli.DiagnosisTools;

import ee.netgroup.su.diagnostic.cli.Controllers.DiseaseController;
import ee.netgroup.su.diagnostic.cli.Disease;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Heleriin on 19/06/2017.
 */
public class DiagnosisTest {

    private Diagnosis diagnosis;


    @Before
    public void runBeforeEachTest() {
        DiseaseController diseaseController = new DiseaseController();

        Disease a = new Disease("a");
        diseaseController.addDisease(a);
        a.addSymptom("a");
        a.addSymptom("b");

        Disease b = new Disease("b");
        diseaseController.addDisease(b);
        b.addSymptom("a");
        b.addSymptom("b");

        Disease c = new Disease("c");
        diseaseController.addDisease(c);
        c.addSymptom("a");
        c.addSymptom("c");

        diagnosis = new Diagnosis(diseaseController);
    }


    @Test
    public void testAnalyseSymptoms() {
        ArrayList<String> symptoms = new ArrayList<>();
        symptoms.add("c");
        assertEquals(1, diagnosis.findPossibleDiseases(symptoms).size());
        assertEquals("c", diagnosis.findPossibleDiseases(symptoms).get(0).getName());
    }


    @Test
    public void testAnalyseSymptomsMultipleSymptoms() {
        ArrayList<String> symptoms = new ArrayList<>();
        symptoms.add("a");
        symptoms.add("b");

        assertEquals(2, diagnosis.findPossibleDiseases(symptoms).size());
        assertEquals("a", diagnosis.findPossibleDiseases(symptoms).get(0).getName());
        assertEquals("b", diagnosis.findPossibleDiseases(symptoms).get(1).getName());
    }


    @Test
    public void testAnalyseSymptomsMultipleDiseases() {
        ArrayList<String> symptoms = new ArrayList<>();
        symptoms.add("a");

        assertEquals(3, diagnosis.findPossibleDiseases(symptoms).size());
        assertEquals("a", diagnosis.findPossibleDiseases(symptoms).get(0).getName());
        assertEquals("b", diagnosis.findPossibleDiseases(symptoms).get(1).getName());
        assertEquals("c", diagnosis.findPossibleDiseases(symptoms).get(2).getName());
    }
}
