package validalogin.beans;

import java.io.Serializable;

/**
 * Encapsula el concepto de usuario
 */
@SuppressWarnings("serial")
public class BeanUsuario implements Serializable{
	
	/**
	 * Información del login
	 */
	private String usuario;
	
	/**
	 * Información de la clave
	 */
	private String clave;
	
	/**
	 * Información del nombre
	 */
	private String nombre;
	
	/**
	 * Constructor por defecto
	 */
	public BeanUsuario() {
		this.usuario = "";
		this.clave = "";
		this.nombre = "";
	}
	
	/**
	 * Constructor con parámetros
	 * @param usuario
	 * @param clave
	 * @param nombre
	 */
	public BeanUsuario(String usuario,String clave, String nombre) {
		this.usuario = usuario;
		this.clave = clave;
		this.nombre = nombre;
	}

	/**
	 * Devuelve el login
	 * @return String login
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Establece el login
	 * @param login
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Devuelve la clave
	 * @return String clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Establece la clave
	 * @param clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Devuelve al nombre
	 * @return String nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}