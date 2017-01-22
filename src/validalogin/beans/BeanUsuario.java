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
	private String login;
	
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
		this.login = "";
		this.clave = "";
		this.nombre = "";
	}

	/**
	 * Devuelve el login
	 * @return String login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Establece el login
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
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
