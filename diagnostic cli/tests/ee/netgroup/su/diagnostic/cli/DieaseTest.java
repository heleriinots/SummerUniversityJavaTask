package ee.netgroup.su.diagnostic.cli;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by Heleriin on 18/06/2017.
 */
public class DieaseTest {

    private Disease disease;


    @Before
    public void runBeforeEachTest() {
        this.disease = new Disease("Depression");
    }


    @Test
    public void testGetName() {
        assertEquals("Depression", disease.getName());
    }


    @Test
    public void testAddSyptom() {
        addSymptoms();

        assertEquals(2, disease.getNumberOfSymptoms());
    }


    @Test
    public void testGetSymptoms() {
        addSymptoms();

        assertTrue(disease.getSymptoms().contains("sadness"));
        assertTrue(disease.getSymptoms().contains("insomnia"));
        assertEquals(2, disease.getSymptoms().size());
    }

    private void addSymptoms() {
        disease.addSymptom("sadness");
        disease.addSymptom("insomnia");
    }


    @Test
    public void testGetNumberOfSymptoms() {
        addSymptoms();

        disease.addSymptom("insomnia");
        disease.addSymptom("feelings of hopelessness");

        assertEquals(3, disease.getSymptoms().size());
    }


    @Test
    public void testContainsSymptom() {
        addSymptoms();

        assertTrue(disease.containsSymptom("sadness"));
        assertTrue(disease.containsSymptom("insomnia"));
        assertFalse(disease.containsSymptom("feelings of hope"));

    }


}
