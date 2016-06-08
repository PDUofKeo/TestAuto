package imagys.com.TestAuto;

import cucumber.api.CucumberOptions;  
import cucumber.api.junit.Cucumber;  
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features= "src/test/resources/features/Login.feature", monochrome = true, format = {"pretty", "html:target/cucumber", "rerun:target/rerun.txt"})
public class RunCukesTest {  
}