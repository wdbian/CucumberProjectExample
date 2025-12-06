package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/java/feature"},
		glue = {"StepDefinitions", "hook"},
		plugin = {"pretty", "json:target/cucumber-reports/CucumberReport.json", "html:target/cucumber-reports/CucumberReport.html",
				"junit:target/cucumber-reports/CucumberReport.xml", "listener.StepListener"},
		monochrome = true
)
public class TestRunner {

}
