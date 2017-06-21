package ee.netgroup.su.diagnostic.cli;

import ee.netgroup.su.diagnostic.cli.Controllers.DiseaseControllerTest;
import ee.netgroup.su.diagnostic.cli.Controllers.SymptomControllerTest;
import ee.netgroup.su.diagnostic.cli.DiagnosisTools.DiagnosisTest;
import ee.netgroup.su.diagnostic.cli.DiagnosisTools.InteractiveDiagnosisTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        DieaseTest.class,
        MainTest.class,
        DiseaseControllerTest.class,
        SymptomControllerTest.class,
        DiagnosisTest.class,
        InteractiveDiagnosisTest.class
})

public class AllTests {
}
