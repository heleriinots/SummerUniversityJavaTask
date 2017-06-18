package ee.netgroup.su.diagnostic.web.Controllers;

import ee.netgroup.su.diagnostic.web.Disease;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Heleriin on 17/06/2017.
 */
public class DiseaseController {

    private ArrayList<Disease> diseases = new ArrayList<>();


    public void addDisease(Disease disease) {
        if (!diseases.contains(disease)) diseases.add(disease);
    }

    public ArrayList<Disease> getDiseases() {
        return diseases;
    }

    public String getThreeDiseasesWithMostSymptoms() {
        diseases
                .stream()
                .mapToInt(d -> d.getNumberOfSymptoms())
                .sorted(Comparator.reverseOrder())




        /*
        wines.stream().mapToDouble(wine -> wine.getFlavonoideContent())
                .sorted()
                .limit(5)
                .forEach(w -> System.out.println("Väike flavonoidide sisaldus: " + w));
        */
        return "yo";
    }

    public void sortDiseasesAlphabetically() {
        Collections.sort(diseases, Comparator.comparing(Disease::getName));
    }



}
