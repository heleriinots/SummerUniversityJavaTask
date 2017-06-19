package ee.netgroup.su.diagnostic.web.Controllers;

import ee.netgroup.su.diagnostic.web.Disease;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Heleriin on 18/06/2017.
 */
public class DiseaseControllerTest {

    private DiseaseController diseaseController;

    @Before
    public void runBeforeEachTest() {
        diseaseController = new DiseaseController();
    }

    @Test
    public void testAddDisease() {
        diseaseController.addDisease(new Disease("a"));
        diseaseController.addDisease(new Disease("b"));

        assertEquals(2, diseaseController.getDiseases().size());
        assertEquals("a", diseaseController.getDiseases().get(0).getName());
        assertEquals("b", diseaseController.getDiseases().get(1).getName());
    }

    @Test
    public void testSortAlphabetically() {
        diseaseController.addDisease(new Disease("c"));
        diseaseController.addDisease(new Disease("a"));
        diseaseController.addDisease(new Disease("b"));
        diseaseController.addDisease(new Disease("aa"));
        diseaseController.sortDiseasesAlphabetically();

        assertEquals("a", diseaseController.getDiseases().get(0).getName());
        assertEquals("aa", diseaseController.getDiseases().get(1).getName());
        assertEquals("b", diseaseController.getDiseases().get(2).getName());
        assertEquals("c", diseaseController.getDiseases().get(3).getName());
    }

    @Test
    public void testGetThreeDiseasesWithMaxSymptoms() {
        Disease c = new Disease("c");
        c.addSymptom("a");
        c.addSymptom("b");

        Disease a = new Disease("a");
        a.addSymptom("a");

        Disease ab = new Disease("ab");
        ab.addSymptom("a");

        Disease b = new Disease("b");
        ab.addSymptom("a");

        diseaseController.addDisease(c);
        diseaseController.addDisease(b);
        diseaseController.addDisease(a);
        diseaseController.addDisease(ab);

        assertEquals(3, diseaseController.getThreeDiseasesWithMostSymptoms().size());
        assertEquals("c", diseaseController.getThreeDiseasesWithMostSymptoms().get(0).getName());
        assertEquals("a", diseaseController.getThreeDiseasesWithMostSymptoms().get(1).getName());
        assertEquals("ab", diseaseController.getThreeDiseasesWithMostSymptoms().get(2).getName());
    }

    @Test
    public void testGetThreeDiseasesWithMaxSymptomsTwoDiseases() {
        Disease a = new Disease("a");
        a.addSymptom("a");

        Disease ab = new Disease("ab");
        ab.addSymptom("a");

        diseaseController.addDisease(a);
        diseaseController.addDisease(ab);

        assertEquals(2, diseaseController.getThreeDiseasesWithMostSymptoms().size());
        assertEquals("a", diseaseController.getThreeDiseasesWithMostSymptoms().get(0).getName());
        assertEquals("ab", diseaseController.getThreeDiseasesWithMostSymptoms().get(1).getName());
    }

    @Test
    public void testGetThreeDiseasesWithMaxSymptomsOneDisease() {
        Disease a = new Disease("a");
        a.addSymptom("a");

        diseaseController.addDisease(a);

        assertEquals(1, diseaseController.getThreeDiseasesWithMostSymptoms().size());
        assertEquals("a", diseaseController.getThreeDiseasesWithMostSymptoms().get(0).getName());
    }

    @Test
    public void testGetThreeDiseasesWithMaxSymptomsNoDiseases() {
        assertEquals(0, diseaseController.getThreeDiseasesWithMostSymptoms().size());
    }

    @Test
    public void testGetThreeDiseasesWithDifferentValues() {
        Disease c = new Disease("c");
        c.addSymptom("a");
        c.addSymptom("b");
        c.addSymptom("c");
        c.addSymptom("d");

        Disease a = new Disease("a");
        a.addSymptom("a");
        a.addSymptom("b");
        a.addSymptom("c");

        Disease b = new Disease("b");
        b.addSymptom("a");
        b.addSymptom("b");

        Disease d = new Disease("d");
        d.addSymptom("a");

        diseaseController.addDisease(c);
        diseaseController.addDisease(b);
        diseaseController.addDisease(a);
        diseaseController.addDisease(d);

        assertEquals(3, diseaseController.getThreeDiseasesWithMostSymptoms().size());
        assertEquals("c", diseaseController.getThreeDiseasesWithMostSymptoms().get(0).getName());
        assertEquals("a", diseaseController.getThreeDiseasesWithMostSymptoms().get(1).getName());
        assertEquals("b", diseaseController.getThreeDiseasesWithMostSymptoms().get(2).getName());
    }


}
