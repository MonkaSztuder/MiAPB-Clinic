package pl.hospital;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.dmn.engine.DmnDecisionRuleResult;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.StringValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class Application {

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }
  @PostDeploy
  public void evaluateDecisionTable(ProcessEngine processEngine) {
    // Define the decision service
    DecisionService decisionService = processEngine.getDecisionService();

    // Define input variables
    VariableMap variables = Variables.createVariables()
            .putValue("oplata_kwota", 101)
            .putValue("oplata_czyZgoda", true);

    // Evaluate decision table by key
    DmnDecisionTableResult decisionTableResult = decisionService.evaluateDecisionTableByKey("OcenaWniosku", variables);
    DmnDecisionRuleResult singleResult = decisionTableResult.getSingleResult();

    // Print the result
    if (singleResult.containsKey("decyzja_czyPozytywna")) {
      boolean isPositive = singleResult.getEntry("decyzja_czyPozytywna");
      System.out.println("Czy pozytywna: " + isPositive);
    }

    StringValue uzasadnienie = singleResult.getEntryTyped("decyzja_uzasadnienie");
    System.out.println("Uzasadnienie: " + uzasadnienie.getValue());

    for (Map<String, Object> result : decisionTableResult.getResultList()) {
      for (Map.Entry<String, Object> entry : result.entrySet()) {
        System.out.println("KEY: " + entry.getKey() + ", VALUE: " + entry.getValue());
      }
    }
  }
}