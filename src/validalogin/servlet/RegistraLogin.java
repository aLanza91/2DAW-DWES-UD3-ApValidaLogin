package validalogin.servlet;

import java.io.IOException;
import java.sql.Connection;
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

import validalogin.beans.BeanError;
import validalogin.beans.BeanUsuario;

/**
 * Servlet que encapsula el proceso de registro.
 */
@WebServlet(name="RegistraLogin",urlPatterns="/registrar")
@SuppressWarnings("serial")
public class RegistraLogin extends HttpServlet{
	
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
	public RegistraLogin() {
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
		Connection conexion = null;
		Statement st = null;
		String login = request.getParameter("login");
		String clave = request.getParameter("clave");
		String nombre = request.getParameter("nombre");
		error = null;
		usuario = new BeanUsuario();
		usuario.setClave(clave);
		usuario.setLogin(login);
		usuario.setNombre(nombre);
		request.setAttribute("usuario", usuario);
		try {
			conexion = dsBdValidaLogin.getConnection();
			st = conexion.createStatement();
			String insert = "INSERT INTO login (login,clave,nombre) VALUES ('" + usuario.getLogin() + "','" +
	                usuario.getClave() + "','" + usuario.getNombre() + "')";
	        st.executeUpdate(insert);
		} catch (SQLException e) {
			error = new BeanError(e.getErrorCode(),e.getMessage());
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
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/verRegistro.jsp");
			rd.forward(request,response);
		} else {
			request.setAttribute("error", error);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/gesError.jsp");
		    rd.forward(request,response);
		}
	
	}
}
