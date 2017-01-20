package validalogin.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BeanError extends Exception implements Serializable{
	
	private int codigoError;
	private String mensajeError;
	
	public BeanError(int codigoError, String mensajeError) {
	    super();
	    this.codigoError=codigoError;
	    this.mensajeError=mensajeError;
	}
	
	public String toString(){
		return this.codigoError+", "+this.mensajeError;
	}
}
