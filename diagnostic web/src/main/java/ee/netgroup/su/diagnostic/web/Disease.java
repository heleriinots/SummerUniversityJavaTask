package ee.netgroup.su.diagnostic.web;

import java.util.TreeSet;

/**
 * Created by Heleriin on 17/06/2017.
 */
public class Disease {

    private String name;

    private TreeSet<String> symptoms = new TreeSet<>();

    public Disease(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addSymptom(String symptom) {
        symptoms.add(symptom);
    }

    public int getNumberOfSymptoms() {
        return symptoms.size();
    }

    public boolean containsSymptom(String symptom) {
        if (symptoms.contains(symptom)) return true;
        return false;
    }

    public TreeSet<String> getSymptoms() {
        return symptoms;
    }
}
