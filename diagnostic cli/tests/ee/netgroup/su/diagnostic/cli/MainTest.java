package ee.netgroup.su.diagnostic.cli;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Heleriin on 21/06/2017.
 */
public class MainTest {

    private Main main;


    @Before
    public void runBeforeEachTest() {
        this.main = new Main();
    }


    @Test
    public void testSplitData() {
        List<String> symptoms = main.splitData("a, b, c, d");
        assertEquals("a", symptoms.get(0));
        assertEquals("b", symptoms.get(1));
        assertEquals("c", symptoms.get(2));
        assertEquals("d", symptoms.get(3));
    }


    @Test
    public void testDiseaseCreation() {
        main.createDiseaseController();
        main.parseALineOfDiseaseData("Vampiris, blindness near mirrors, zombie-like behavior, bite wounds");
        assertEquals("Vampiris", main.getDiseaseController().getDiseases().get(0).getName());
        assertEquals(3, main.getDiseaseController().getDiseases().get(0).getNumberOfSymptoms());
    }


    @Test
    public void testDiseaseCreationMultipleDiseases() {
        main.createDiseaseController();
        main.parseALineOfDiseaseData("Vampiris, blindness near mirrors, zombie-like behavior, bite wounds");
        main.parseALineOfDiseaseData("Reaper, zombie-like behavior, bite wounds, photosensitivity");
        assertEquals(2, main.getDiseaseController().getDiseases().size());
    }
}
