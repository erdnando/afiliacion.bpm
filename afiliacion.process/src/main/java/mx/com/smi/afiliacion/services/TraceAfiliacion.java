package mx.com.smi.afiliacion.services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class TraceAfiliacion implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		 String currentStep= execution.getCurrentActivityName();
		 String trace = execution.getVariable("trace").toString();
	       
	     execution.setVariable("trace",trace+">"+currentStep);
	}
}
