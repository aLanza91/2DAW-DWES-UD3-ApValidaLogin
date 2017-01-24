package validalogin.bbdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import validalogin.beans.*;

public class Dao {
	
	private DataSource dsBdValidaLogin;
	
	private Connection conexion = null;
	
	public Dao(DataSource dsBdValidaLogin){
		this.dsBdValidaLogin=dsBdValidaLogin;
	}
	
	public Connection getConexion() throws SQLException {
		conexion = dsBdValidaLogin.getConnection();
        return conexion;
    }
	
	public void close() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
        conexion = null;
    }
	
	public BeanUsuario getUsuario(HttpServletRequest request) throws SQLException, BeanError{
		BeanUsuario usuario = null;
		String login = request.getParameter("login");
		String clave = request.getParameter("clave");
		Statement st = null;
		ResultSet rs = null;
		getConexion();
		st = conexion.createStatement();
		rs = st.executeQuery("select login,clave,nombre from login where login = '"+login+"'");
		//Se comprueba si existe el login
		if (rs.next()) {
			//Se comprueba si no coincide la clave y si es así se obtiene el nombre
			if (!rs.getString("clave").equals(clave)) {
				throw new BeanError(2,"La clave no coincide.");
				} else{
					usuario = new BeanUsuario(login,clave,rs.getString("nombre"));
				}
			}
			else {
				throw new BeanError(3,"El login no existe.");
			}
		if (st != null){
			close();
		}
		return usuario;
	}

}
