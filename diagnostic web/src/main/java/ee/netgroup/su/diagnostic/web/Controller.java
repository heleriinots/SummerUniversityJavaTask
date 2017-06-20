package ee.netgroup.su.diagnostic.web;

import ee.netgroup.su.diagnostic.web.Controllers.DiseaseController;
import ee.netgroup.su.diagnostic.web.Controllers.SymptomController;
import ee.netgroup.su.diagnostic.web.DiagnosisTools.InteractiveDiagnosis;

/**
 * Created by Heleriin on 18/06/2017.
 */
public class Controller {

    public static void main(String[] args) {
        DiseaseController diseaseController = new DiseaseController();

        Disease a = new Disease("Invisibility");
        a.addSymptom("blindness near mirrors");
        a.addSymptom("obsessive compulsive behavior");

        Disease b = new Disease("Exploding Head Syndrome");
        b.addSymptom("hardening of the brain tissue");
        b.addSymptom("obsessive compulsive behavior");

        Disease c = new Disease("T-Virus");
        c.addSymptom("zombie-like behavior");
        c.addSymptom("impaired judgment");
        c.addSymptom("accelerated mutation");

        Disease e = new Disease("Phazon Madness");
        e.addSymptom("impaired judgment");
        e.addSymptom("accelerated mutation");
        e.addSymptom("glows in the dark");

        Disease f = new Disease("Biscuit Poisoning");
        f.addSymptom("zombie-like behavior");
        f.addSymptom("hallucinations");

        Disease g = new Disease("Random Pavarotti Disease");
        g.addSymptom("sg of operatic tunes");

        Disease h = new Disease("Mad Zombie Disease");
        h.addSymptom("zombie-like behavior");
        h.addSymptom("impaired judgment");
        h.addSymptom("accelerated mutation");
        h.addSymptom("bite wounds");
        h.addSymptom("gunshot wounds");
        h.addSymptom("hallucinations");
        h.addSymptom("hardening of the brain tissue");
        h.addSymptom("photosensitivity");
        h.addSymptom("obsessive compulsive behavior");


        Disease i = new Disease("Ghost Sickness");
        i.addSymptom("hallucinations");

        Disease j = new Disease("Cyberbrain Sclerosis");
        j.addSymptom("impaired judgment");
        j.addSymptom("hardening of the brain tissue");

        Disease k = new Disease("Electric flu");
        k.addSymptom("glows in the dark");
        k.addSymptom("uncontrolled bursts of electricity");

        Disease l = new Disease("Deep Space Disorder");
        l.addSymptom("accelerated mutation");
        l.addSymptom("hallucinations");

        Disease m = new Disease("Neural Overstimulation Syndrome");
        m.addSymptom("impaired judgment");
        m.addSymptom("spontaneous singing of operatic tunes");
        m.addSymptom("hallucinations");
        m.addSymptom("obsessive compulsive behavior");

        Disease n = new Disease("Say the Opposite of What You Mean Disease");
        n.addSymptom("impaired judgment");

        Disease o = new Disease("Reaper");
        o.addSymptom("zombie-like behavior");
        o.addSymptom("bite wounds");
        o.addSymptom("photosensitivity");
        o.addSymptom("obsessive compulsive behavior");

        Disease p = new Disease("Vampiris");
        p.addSymptom("blindness near mirrors");
        p.addSymptom("zombie-like behavior");
        p.addSymptom("bite wounds");
        p.addSymptom("photosensitivity");
        p.addSymptom("obsessive compulsive behavior");

        Disease q = new Disease("Spattergroit");
        q.addSymptom("obsessive compulsive behavior");

        Disease r = new Disease("Acute Lead Poisoning");
        r.addSymptom("gunshot wounds");

        diseaseController.addDisease(a);
        diseaseController.addDisease(b);
        diseaseController.addDisease(c);
        diseaseController.addDisease(e);
        diseaseController.addDisease(f);
        diseaseController.addDisease(g);
        diseaseController.addDisease(h);
        diseaseController.addDisease(i);
        diseaseController.addDisease(j);
        diseaseController.addDisease(k);
        diseaseController.addDisease(l);
        diseaseController.addDisease(m);
        diseaseController.addDisease(n);
        diseaseController.addDisease(o);
        diseaseController.addDisease(p);
        diseaseController.addDisease(q);
        diseaseController.addDisease(r);

        SymptomController symptomController = new SymptomController(diseaseController);
        InteractiveDiagnosis interactiveDiagnosis = new InteractiveDiagnosis(symptomController, diseaseController);

        interactiveDiagnosis.askQuestion();

    }
}
