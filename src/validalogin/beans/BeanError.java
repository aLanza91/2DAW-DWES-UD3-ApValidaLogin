package validalogin.beans;

import java.io.Serializable;

/**
 * Encapsula el concepto de error en la aplicación
 */
@SuppressWarnings("serial")
public class BeanError extends Exception implements Serializable{
	
	/**
	 * Información del código del error
	 */
	private int codigoError;
	
	/**
	 * Información del mensaje del error
	 */
	private String mensajeError;
	
	/**
	 * Constructor, recibe el código y el mensaje del error
	 * @param codigoError
	 * @param mensajeError
	 */
	public BeanError(int codigoError, String mensajeError) {
	    super();
	    this.codigoError=codigoError;
	    this.mensajeError=mensajeError;
	}
	
	/**
	 * Método que imprime la información del error
	 */
	public String toString(){
		return "Código error: "+this.codigoError+", "+this.mensajeError;
	}
}
