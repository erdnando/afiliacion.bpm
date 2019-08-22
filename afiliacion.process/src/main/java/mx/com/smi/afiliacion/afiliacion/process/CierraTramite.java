package mx.com.smi.afiliacion.afiliacion.process;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


public class CierraTramite implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		mx.com.smi.afiliacion.modelos.Tramite t= new mx.com.smi.afiliacion.modelos.Tramite();
		
		try {
		
				t.promotorId = execution.getVariable("promotorId").toString();
				t.tiendaId = execution.getVariable("tiendaId").toString();
				t.fechaIni = execution.getVariable("fechaIni").toString();
				t.idTramite = execution.getVariable("idTramite").toString();
				
				t.nombre = execution.getVariable("Nombre").toString();
				t.paterno = execution.getVariable("Paterno").toString();
				t.materno = execution.getVariable("Materno").toString();
				t.fechaNac = execution.getVariable("FechaNac").toString();
				t.idDocumento = execution.getVariable("IdDocumento").toString();
				t.sexo = execution.getVariable("Sexo").toString();
				t.nacionalidad = execution.getVariable("Nacionalidad").toString();
				
				t.calle = execution.getVariable("Calle").toString();
				t.numExt = execution.getVariable("NumExt").toString();
				t.cp = execution.getVariable("CP").toString();
				t.colonia = execution.getVariable("Colonia").toString();
				t.municipio = execution.getVariable("Municipio").toString();
				t.ciudad = execution.getVariable("Ciudad").toString();
				t.estado = execution.getVariable("Estado").toString();
				
				t.rfc = execution.getVariable("RFC").toString();
				t.email = execution.getVariable("Email").toString();
				
				t.buro = execution.getVariable("Buro").toString();
				t.score = execution.getVariable("Score").toString();
				
				t.isApproved = (boolean) execution.getVariable("IsApproved");
				
				t.telefono1 = execution.getVariable("Telefono1").toString();
				t.telefono2 = execution.getVariable("Telefono2").toString();
				
				t.documentos = execution.getVariable("documentos").toString();
				t.folioExpediente = execution.getVariable("FolioExpediente").toString();
				
				t.idProducto = execution.getVariable("IdProducto").toString();
				t.idTipoProducto = execution.getVariable("IdTipoProducto").toString();
				
				t.idCliente = execution.getVariable("CoreId").toString();
				t.cuenta = execution.getVariable("Cuenta").toString();
				t.password = execution.getVariable("Password").toString();
				
				t.etapa = "APPROVED";
				t.step = execution.getCurrentActivityName();
				
				t.numCaso = execution.getVariable("Cuenta").toString();
		        
				t.processInstanceId= execution.getProcessInstanceId();
				
				String currentStep=execution.getCurrentActivityName();
				String trace=execution.getVariable("trace").toString();
				t.trace= trace +">"+ currentStep;
						
						
		        execution.setVariable("trace",t.trace);
		        //execution.setVariable("processInstanceId",t.processInstanceId);
        
		        mx.com.smi.afiliacion.services.Tramite tramite = new mx.com.smi.afiliacion.services.Tramite();
		        
		        try {
		        	tramite.CierraTramite(t);
		        	execution.setVariable("error","");
		        }
		        catch(Exception ex) {
		
		        	execution.setVariable("error",ex.getMessage());
		        }
		
		}catch(Exception exx) {
			
			log(" err:" + exx.getMessage(), "log1.txt");

		 }
	}
	
	void log(String msg,String fileLog) {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	            new FileOutputStream(fileLog), "utf-8"))){
				   writer.write(msg);
		}catch(IOException ex2) {
			
		}
	}

}
