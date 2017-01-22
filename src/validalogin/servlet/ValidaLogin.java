package validalogin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import validalogin.beans.*;

/**
 * Servlet que encapsula el proceso de login.
 */
@WebServlet(name="ValidaLogin",urlPatterns="/validar")
@SuppressWarnings("serial")
public class ValidaLogin extends HttpServlet{
	
	/**
	 * Información de la Base de datos
	 */
	private DataSource dsBdValidaLogin;
	
	/**
	 * Información del posible error generado
	 */
	private BeanError error;
	
	/**
	 * Información del usuario
	 */
	private BeanUsuario usuario;
	
	/**
	 * Constructor por defecto
	 */
	public ValidaLogin() {
        super();
    }

	/**
	 * Inicializa el servlet y la fuente de datos 
	 */
	public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	InitialContext initCtx = null;
		try {
			initCtx = new InitialContext();
			this.dsBdValidaLogin = (DataSource) initCtx.lookup("java:jboss/datasources/dsbdvalidalogin");
		} catch (NamingException e) {
			error = new BeanError(1,"Error en conexión a base de datos");
    	}
	}
	
	/**
	 * Recibe las peticiones GET y las pasa al método doPost
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	/**
	 * Recibe las peticiones POST, realiza la consulta a la bb.dd. y devuelve una respuesta
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String clave = request.getParameter("clave");
		String nombre="";
		error = null;
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conexion = dsBdValidaLogin.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery("select login,clave,nombre from login where login = '"+login+"'");
			//Se comprueba si existe el login
			if (rs.next()) {
				//Se comprueba si no coincide la clave y si es así se obtiene el nombre
				if (!rs.getString("clave").equals(clave)) {
					error = new BeanError(2,"La clave no coincide.");
				} else{
					nombre = rs.getString("nombre");
				}
			}
			else {
				error = new BeanError(3,"El login no existe.");
			}
		} catch (SQLException e) {
			error = new BeanError(1,"Error en conexión a base de datos");
		}
		if (st != null){
			try {
				conexion.close();
			} catch (SQLException e) {
				error = new BeanError(4,"Error al cerrar conexión a base de datos");
			}
		}
		//Se comprueba si se ha producido algún error para devolver la imforción pertinente
		if (error==null){
			usuario = new BeanUsuario();
			usuario.setClave(clave);
			usuario.setLogin(login);
			usuario.setNombre(nombre);
			request.setAttribute("usuario", usuario);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/verUsuario.jsp");
		    rd.forward(request,response);
		
		} else {
			request.setAttribute("error", error);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/gesError.jsp");
		    rd.forward(request,response);
		}
	}
	
}
