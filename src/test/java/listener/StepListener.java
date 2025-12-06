package listener;

import extent.ExtentTestManager;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestStep;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.TestStepStarted;

public class StepListener {
	
	public void setEventPublisher(EventPublisher eventPublisher) {
		eventPublisher.registerHandlerFor(TestCaseStarted.class, this::onSenarioStarted);
		eventPublisher.registerHandlerFor(TestCaseFinished.class, this::onScenarioFinished);
		eventPublisher.registerHandlerFor(TestStepStarted.class, this::onStepStarted);
		eventPublisher.registerHandlerFor(TestStepFinished.class, this::onStepFinished);
	}

	private void onSenarioStarted(TestCaseStarted event) {
		String scenarioName = event.getTestCase().getName();
		ExtentTestManager.startTest(scenarioName);
		ExtentTestManager.getTest().info("<b>Scenario - " + scenarioName + ": Started</b>");
	}
	
	private void onScenarioFinished(TestCaseFinished event) {
		
	}
	
	private void onStepStarted(TestStepStarted event) {
		TestStep step = event.getTestStep();
		if (step instanceof PickleStepTestStep) {
			PickleStepTestStep pickleStep = (PickleStepTestStep) step;
			String stepName = pickleStep.getStep().getKeyword() + pickleStep.getStep().getText();		
		}
	}
	
	private void onStepFinished(TestStepFinished event) {
		TestStep step = event.getTestStep();
		if (step instanceof PickleStepTestStep) {
			PickleStepTestStep pickleStep = (PickleStepTestStep) step;
			String stepName = pickleStep.getStep().getKeyword() + pickleStep.getStep().getText();
			Status result = event.getResult().getStatus();
			switch (result) {
				case PASSED:
					ExtentTestManager.getTest().pass(stepName);
					break;
				case FAILED:
					ExtentTestManager.getTest().fail(stepName);
					break;
				default:
					ExtentTestManager.getTest().skip(stepName);
			}
		}
	}
}
