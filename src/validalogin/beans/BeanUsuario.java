package validalogin.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BeanUsuario implements Serializable{
	
	private String login;
	private String clave;
	private String nombre;
	
	public BeanUsuario() {
		this.login = "";
		this.clave = "";
		this.nombre = "";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
