package testauto;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features" }, monochrome = true, format = { "pretty", "html:target/cucumber",
		"rerun:target/rerun.txt", "json:target/report/cucumber.json" , "junit:target_json/Cucumber_junit.xml"}, 
tags = {"@IM-332"}
//{"@MAT1,@MAT3,@MAT5,@MAT6,@MAT7,@MAT8,@After" }
// +
// ",@SupervisionSearch,@SupervisionResult,@checkQCisDeleted,@UserLogout,@SupervisorLogin1"}
)

public class RunTest  {
	
	
	

}