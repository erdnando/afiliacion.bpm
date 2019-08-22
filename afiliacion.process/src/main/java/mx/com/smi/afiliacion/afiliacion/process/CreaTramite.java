package mx.com.smi.afiliacion.afiliacion.process;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import org.camunda.bpm.engine.delegate.JavaDelegate;
import mx.com.smi.afiliacion.services.*;  



public class CreaTramite implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
	
		
		String promotorId = execution.getVariable("promotorId").toString();
		String tiendaId = execution.getVariable("tiendaId").toString();
		String fechaIni = execution.getVariable("fechaIni").toString();
		//String folioExpediente = execution.getVariable("FolioExpediente").toString();
		
		String etapa = "CAPTURA";
		
        String step= execution.getCurrentActivityName();
        String processInstanceId= execution.getProcessInstanceId();
        execution.setVariable("trace",step);
        execution.setVariable("processInstanceId",processInstanceId);
        
		
		
		Tramite t = new Tramite();
		String idTramite=t.CreaTramite(fechaIni,tiendaId,promotorId,etapa,step,processInstanceId);
		
		if(idTramite=="0") {
			execution.setVariable("isOK",false);
			execution.setVariable("error","No se pudo crear el id del tramite. Problemas con la DB");
		}
		else execution.setVariable("isOK",true);
		
		execution.setVariable("idTramite",idTramite);

	}

}
