package mx.com.smi.afiliacion.services;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Tramite {
	
	public String CreaTramite(String fechaIni, String tiendaId, String promotorId, String etapa, String step, String instanceId) {
		long idTramite = 0;
		try
	    {
	        String userName = "usrA05007s10038";
	        String password = "A05007s10038";
	        
	        String url ="jdbc:jtds:sqlserver://74.208.98.86:1444;instance=MSSQLSERVER2016;DatabaseName=IBIS001";
	        Class.forName("net.sourceforge.jtds.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url, userName, password);
	        
	        String SQL_INSERT="INSERT INTO TRAMITESBPM (\n" + 
	        		" numeroCaso,\n" + 
	        		"  folioBuro,\n" + 
	        		"  PrimerNombre,\n" + 
	        		"  ApellidoPaterno,\n" + 
	        		"  ApellidoMaterno,\n" + 
	        		"  FechaNacimiento,\n" + 
	        		"  Sexo,\n" + 
	        		"  Nacionalidad,\n" + 
	        		"  Calle,\n" + 
	        		"  NumExterior,\n" + 
	        		"  CodigoPostal,\n" + 
	        		"  Colonia,\n" + 
	        		"  Municipio,\n" + 
	        		"  Ciudad,\n" + 
	        		"  Estado,\n" + 
	        		"  BanderaDocumentos,\n" + 
	        		"  NumeroSolicitud,\n" + 
	        		"  FechaSolicitud,\n" + 
	        		"  IdProducto,\n" + 
	        		"  Producto,\n" + 
	        		"  IdTipoProducto,\n" + 
	        		"  TipoProducto,\n" + 
	        		"  NumeroTienda,\n" + 
	        		"  IdCanalVentas,\n" + 
	        		"  NumPromotor,\n" + 
	        		"  rfc,\n" + 
	        		"  Procedure_name,\n" + 
	        		"  Status,\n" + 
	        		"  Step_name,\n" + 
	        		"  IdCliente,\n" + 
	        		"  Email,\n" + 
	        		"  Termograbado,\n" + 
	        		"  cuenta,\n" + 
	        		"  contrasenia,thread) \n" + 
	        		"  VALUES(\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  '"+fechaIni+"',\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  '"+tiendaId+"',\n" + 
	        		"  null,\n" + 
	        		"  '"+promotorId+"',\n" + 
	        		"  null,\n" + 
	        		"  '"+etapa+"',\n" + 
	        		"  'mdi-editor-insert-drive-file',\n" + 
	        		"  '"+step+"',\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null,\n" + 
	        		"  null, '"+instanceId+"'\n" + 
	        		"  )";
	        
	        PreparedStatement statement = conn.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
	        
	        int affectedRows = statement.executeUpdate();
	        
	        if (affectedRows == 0) {
	            return "0";
	        }
	        
	        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	idTramite=generatedKeys.getLong(1);
	            }
	            else {
	            	return "0";
	            }
	        }
	        statement.close();  
	
	    } catch (Exception e)
	    {
	    	return "0";
	    }
		
		return Long.toString(idTramite) ;
	}

	public int CierraTramite(mx.com.smi.afiliacion.modelos.Tramite t) {
		
		String SQL_UPDATE="";
		try
	    {
	        String userName = "usrA05007s10038";
	        String password = "A05007s10038";
	        
	        String url ="jdbc:jtds:sqlserver://74.208.98.86:1444;instance=MSSQLSERVER2016;DatabaseName=IBIS001";
	        Class.forName("net.sourceforge.jtds.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(url, userName, password);
	        
	        SQL_UPDATE="UPDATE TRAMITESBPM SET \n" + 
	        		"FOLIOBURO=?, PRIMERNOMBRE=?, APELLIDOPATERNO=?, APELLIDOMATERNO=?, FECHANACIMIENTO=? , SEXO=?, NACIONALIDAD=?,\n" + 
	        		"CALLE=?, NUMEXTERIOR=?,CODIGOPOSTAL=?,COLONIA=?,MUNICIPIO=?,CIUDAD=?,ESTADO=?, NUMEROSOLICITUD=?, IDPRODUCTO=?,\n" + 
	        		"IDTIPOPRODUCTO=?, RFC=?,STEP_NAME=?,IDCLIENTE=?,EMAIL=?,TERMOGRABADO=0,CUENTA=?,CONTRASENIA=?,NUMEROCASO=?, BANDERADOCUMENTOS=?, TRACE=?, THREAD=? \n" + 
	        		"WHERE IDTRAMITE=?";
	         
	        PreparedStatement statement = conn.prepareStatement(SQL_UPDATE);
	        statement.setString(1, t.buro==null?"":t.buro);
	        statement.setString(2, t.nombre==null?"":t.nombre);
	        statement.setString(3, t.paterno==null?"":t.paterno);
	        statement.setString(4, t.materno==null?"":t.materno);
	        statement.setString(5, t.fechaNac==null?"":t.fechaNac);
	        statement.setString(6, t.sexo==null?"":t.sexo);
	        statement.setString(7, t.nacionalidad==null?"":t.nacionalidad);
	        statement.setString(8, t.calle==null?"":t.calle);
	        statement.setString(9, t.numExt==null?"":t.numExt);
	        statement.setString(10, t.cp==null?"":t.cp);
	        statement.setString(11, t.colonia==null?"":t.colonia);
	        statement.setString(12, t.municipio==null?"":t.municipio);
	        statement.setString(13, t.ciudad==null?"":t.ciudad);
	        statement.setString(14, t.estado==null?"":t.estado);
	        statement.setString(15, t.folioExpediente==null?"":t.folioExpediente);
	        statement.setString(16, t.idProducto==null?"":t.idProducto);
	        statement.setString(17, t.idTipoProducto==null?"":t.idTipoProducto);
	        statement.setString(18, t.rfc==null?"":t.rfc);
	        statement.setString(19, t.step==null?"":t.step);
	        statement.setString(20, t.idCliente==null?"":t.idCliente);
	        statement.setString(21, t.email==null?"":t.email);
	        statement.setString(22, t.cuenta==null?"":t.cuenta);
	        statement.setString(23, t.password==null?"":t.password);
	        statement.setString(24, t.numCaso==null?"":t.numCaso);
	        statement.setString(25, t.documentos==null?"":t.documentos);
	        statement.setString(26, t.trace==null?"":t.trace);
	        statement.setString(27, t.processInstanceId==null?"":t.processInstanceId);
	        statement.setString(28, t.idTramite==null?"":t.idTramite);
	        
	        int affectedRows = statement.executeUpdate();
	        
	        statement.close(); 
	        return affectedRows;
	
	    } catch (Exception exx)
	    {
	    	log(" err:" + exx.getMessage(), "log3.txt");
	    	return -1;
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
