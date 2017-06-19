package ee.netgroup.su.diagnostic.web;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Heleriin on 19/06/2017.
 */
public class AnalyzerTest {

    private Analyzer analyzer;

    @Before
    public void runBeforeEachTest() {
        this.analyzer = new Analyzer();
    }

    @Test
    public void testParseSymptomsForDiagnosis() {
        List<String> symptoms = analyzer.parseSymptomsForDiagnosis("impaired judgment, spontaneous singing of " +
                "operatic tunes, hallucinations");
        assertEquals("impaired judgment", symptoms.get(0));
        assertEquals("spontaneous singing of operatic tunes", symptoms.get(1));
        assertEquals("hallucinations", symptoms.get(2));
    }

    @Test
    public void testSplitData() {
        List<String> symptoms = analyzer.splitData("a, b, c, d");
        assertEquals("a", symptoms.get(0));
        assertEquals("b", symptoms.get(1));
        assertEquals("c", symptoms.get(2));
        assertEquals("d", symptoms.get(3));
    }

    @Test
    public void testDiseaseCreation() {
        analyzer.createDiseaseController();
        analyzer.parseALineOfDiseaseData("Vampiris, blindness near mirrors, zombie-like behavior, bite wounds");
        assertEquals("Vampiris", analyzer.getDiseaseController().getDiseases().get(0).getName());
        assertEquals(3, analyzer.getDiseaseController().getDiseases().get(0).getNumberOfSymptoms());
    }

    @Test
    public void testDiseaseCreationMultipleDiseases() {
        analyzer.createDiseaseController();
        analyzer.parseALineOfDiseaseData("Vampiris, blindness near mirrors, zombie-like behavior, bite wounds");
        analyzer.parseALineOfDiseaseData("Reaper, zombie-like behavior, bite wounds, photosensitivity");
        assertEquals(2, analyzer.getDiseaseController().getDiseases().size());
    }
}
