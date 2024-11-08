package pl.hospital;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class OdwolanieCallback implements JavaDelegate {
    public void execute(DelegateExecution execution) throws Exception {
        RuntimeService runtimeService =
                execution.getProcessEngineServices().getRuntimeService();
        runtimeService.createMessageCorrelation("wynikNowaWizytaMsg")
                .processInstanceId(execution.getVariable("parentBussinesKey").toString())
                .setVariable("lekarzForm", execution.getVariable("lekarzForm"))
                .correlateWithResult();}
}

