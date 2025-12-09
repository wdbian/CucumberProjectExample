package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = {"src/test/java/feature/"},
		glue = {"StepDefinition", "hook"},
		plugin = {
					"pretty", 
					"json:target/cucumber-reports/CucumberReport.json", 
					"html:target/cucumber-reports/CucumberReport.html",
					"junit:target/cucumber-reports/CucumberReport.xml", 
					"listener.StepListener"
				 },
		monochrome = true,
		dryRun = true,
		tags = "@regression"
)
public class TestRunner extends AbstractTestNGCucumberTests{
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
