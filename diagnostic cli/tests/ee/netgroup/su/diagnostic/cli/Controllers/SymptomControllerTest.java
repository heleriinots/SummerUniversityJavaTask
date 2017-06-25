package ee.netgroup.su.diagnostic.cli.Controllers;

import ee.netgroup.su.diagnostic.cli.Disease;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Heleriin on 18/06/2017.
 */
public class SymptomControllerTest {

    private DiseaseController diseaseController;
    private SymptomController symptomController;


    @Before
    public void runBeforeEachTest() {
        diseaseController = new DiseaseController();
    }


    @Test
    public void testAddingSymptoms() {
        Disease a = new Disease("a");
        a.addSymptom("a");
        a.addSymptom("c");

        Disease b = new Disease("b");
        a.addSymptom("b");

        diseaseController.addDisease(a);
        diseaseController.addDisease(b);

        symptomController = new SymptomController(diseaseController);

        assertEquals(3, symptomController.getSymptoms().size());
        assertTrue(symptomController.getSymptoms().containsKey("a"));
        assertTrue(symptomController.getSymptoms().containsKey("b"));
        assertTrue(symptomController.getSymptoms().containsKey("c"));
    }


    @Test
    public void testCountingSymptoms() {
        Disease a = new Disease("a");
        a.addSymptom("a");
        a.addSymptom("c");

        Disease b = new Disease("b");
        b.addSymptom("c");

        diseaseController.addDisease(a);
        diseaseController.addDisease(b);

        symptomController = new SymptomController(diseaseController);

        assertEquals(2, symptomController.getSymptoms().size());
        assertEquals(new Integer(1), symptomController.getSymptoms().get("a"));
        assertEquals(new Integer(2), symptomController.getSymptoms().get("c"));
    }


    @Test
    public void testGetNumberOfUniqueSymptoms() {
        Disease a = new Disease("a");
        a.addSymptom("a");
        a.addSymptom("c");

        Disease b = new Disease("b");
        b.addSymptom("c");

        Disease c = new Disease("c");
        c.addSymptom("b");
        c.addSymptom("c");

        diseaseController.addDisease(a);
        diseaseController.addDisease(b);
        diseaseController.addDisease(c);

        symptomController = new SymptomController(diseaseController);

        assertEquals(3, symptomController.getNumberOfUniqueSymptoms());
    }


    @Test
    public void testFindThreeMostCommonSymptoms() {
        Disease a = new Disease("a");
        a.addSymptom("a");
        a.addSymptom("c");

        Disease b = new Disease("b");
        b.addSymptom("c");

        Disease c = new Disease("c");
        c.addSymptom("b");
        c.addSymptom("c");
        c.addSymptom("d");

        Disease d = new Disease("d");
        d.addSymptom("a");

        diseaseController.addDisease(a);
        diseaseController.addDisease(b);
        diseaseController.addDisease(c);
        diseaseController.addDisease(d);

        symptomController = new SymptomController(diseaseController);

        assertEquals(3, symptomController.findThreeMostCommonSymptoms().size());
        assertEquals("c", symptomController.findThreeMostCommonSymptoms().get(0));
        assertEquals("a", symptomController.findThreeMostCommonSymptoms().get(1));
        assertEquals("b", symptomController.findThreeMostCommonSymptoms().get(2));
    }


    @Test
    public void testFindThreeMostCommonSymptomsSameValues() {
        Disease a = new Disease("a");
        a.addSymptom("a");

        Disease b = new Disease("b");
        b.addSymptom("a");
        b.addSymptom("c");

        Disease c = new Disease("c");
        c.addSymptom("b");
        c.addSymptom("c");

        Disease d = new Disease("d");
        d.addSymptom("b");

        diseaseController.addDisease(a);
        diseaseController.addDisease(b);
        diseaseController.addDisease(c);
        diseaseController.addDisease(d);

        symptomController = new SymptomController(diseaseController);

        assertEquals(3, symptomController.findThreeMostCommonSymptoms().size());
        assertEquals("a", symptomController.findThreeMostCommonSymptoms().get(0));
        assertEquals("b", symptomController.findThreeMostCommonSymptoms().get(1));
        assertEquals("c", symptomController.findThreeMostCommonSymptoms().get(2));
    }


    @Test
    public void testFindThreeMostCommonSymptomsTwoSymptoms() {
        Disease a = new Disease("a");
        a.addSymptom("a");

        Disease b = new Disease("b");
        b.addSymptom("a");
        b.addSymptom("c");

        diseaseController.addDisease(a);
        diseaseController.addDisease(b);

        symptomController = new SymptomController(diseaseController);

        assertEquals(2, symptomController.findThreeMostCommonSymptoms().size());
        assertEquals("a", symptomController.findThreeMostCommonSymptoms().get(0));
        assertEquals("c", symptomController.findThreeMostCommonSymptoms().get(1));
    }


    @Test
    public void testFindThreeMostCommonSymptomsOneSymptom() {
        Disease a = new Disease("a");
        a.addSymptom("a");

        diseaseController.addDisease(a);

        symptomController = new SymptomController(diseaseController);

        assertEquals(1, symptomController.findThreeMostCommonSymptoms().size());
        assertEquals("a", symptomController.findThreeMostCommonSymptoms().get(0));
    }


    @Test
    public void testFindThreeMostCommonSymptomsNoSymptoms() {
        Disease a = new Disease("a");

        diseaseController.addDisease(a);

        symptomController = new SymptomController(diseaseController);

        assertEquals(0, symptomController.findThreeMostCommonSymptoms().size());
    }

}
