package validalogin.bbdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import validalogin.beans.*;

/**
 * Encapsula la comunicación con la base de datos
 */
public class BeanDao {

	/**
	 * Información de la base de datos
	 */
	private DataSource dsBdValidaLogin;

	/**
	 * Representa la conexión con la base de datos
	 */
	private Connection conexion = null;

	/**
	 * Constructor que recibe el DataSource
	 * @param dsBdValidaLogin
	 */
	public BeanDao(DataSource dsBdValidaLogin) {
		this.dsBdValidaLogin = dsBdValidaLogin;
	}

	/**
	 * Proceso de conexión con la base de datos
	 * @return
	 * @throws SQLException
	 */
	public Connection getConexion() throws SQLException {
		conexion = dsBdValidaLogin.getConnection();
		return conexion;
	}

	/**
	 * Proceso que cierra la conexión con la base de datos
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if (conexion != null) {
			conexion.close();
		}
		conexion = null;
	}

	/**
	 * Método que devuelve un usuario de la base de datos si este existe, si no lanza una excepción
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws BeanError
	 */
	public BeanUsuario getUsuario(HttpServletRequest request) throws SQLException, BeanError {
		BeanUsuario usuario = null;
		String login = request.getParameter("usuario");
		String clave = request.getParameter("clave");
		Statement st = conexion.createStatement();
		ResultSet rs = st.executeQuery("select usuario,clave,nombre from login where usuario = '" + login + "'");
		// Se comprueba si existe el login
		if (rs.next()) {
			// Se comprueba si no coincide la clave y si coincide se obtiene el nombre
			if (!rs.getString("clave").equals(clave)) {
				throw new BeanError(2, "La clave no coincide.");
			} else {
				usuario = new BeanUsuario(login, clave, rs.getString("nombre"));
			}
		} else {
			throw new BeanError(3, "El login no existe.");
		}
		if (st != null) {
			close();
		}
		return usuario;
	}

	/**
	 * Método que inserta un usuario en la base de datos, si no existe previamente el login del usuario
	 * @param request
	 * @throws SQLException
	 * @throws BeanError
	 */
	public void setUsuario(HttpServletRequest request) throws SQLException, BeanError {
		BeanUsuario usuario = new BeanUsuario(request.getParameter("usuario"), request.getParameter("clave"),
				request.getParameter("nombre"));
		request.setAttribute("usuario", usuario);
		Statement st = conexion.createStatement();
		String insert = "INSERT INTO login (usuario,clave,nombre) VALUES ('" + usuario.getUsuario() + "','"
				+ usuario.getClave() + "','" + usuario.getNombre() + "')";
		ResultSet rs = st.executeQuery("select usuario from login where usuario = '" + usuario.getUsuario() + "'");
		//Se comprueba si existe el usuario, si existe se lanza una excepción, si no se inserta en la bb.dd.
		if (rs.next()) {
			throw new BeanError(4, "El Usuario ya existe, pruebe con otro");
		} else {
			st.executeUpdate(insert);
		}
		if (st != null) {
			close();
		}
	}

}
