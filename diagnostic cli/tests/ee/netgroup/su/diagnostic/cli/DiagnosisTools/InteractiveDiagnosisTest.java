package ee.netgroup.su.diagnostic.cli.DiagnosisTools;

import ee.netgroup.su.diagnostic.cli.Controllers.DiseaseController;
import ee.netgroup.su.diagnostic.cli.Controllers.SymptomController;
import ee.netgroup.su.diagnostic.cli.Disease;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Heleriin on 19/06/2017.
 */
public class InteractiveDiagnosisTest {

    private InteractiveDiagnosis interactiveDiagnosis;


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

        SymptomController symptomController = new SymptomController(diseaseController);
        interactiveDiagnosis = new InteractiveDiagnosis(symptomController, diseaseController);
    }


    @Test
    public void testGetSymptomsInPossibleDiseases() {
        List<String> symptoms = interactiveDiagnosis.getSymptomsInPossibleDiseases();

        assertTrue(symptoms.contains("a"));
        assertTrue(symptoms.contains("b"));
        assertTrue(symptoms.contains("c"));
    }
}
