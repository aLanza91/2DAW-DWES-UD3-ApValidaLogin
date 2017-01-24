package validalogin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import validalogin.beans.*;
import validalogin.bbdd.*;

/**
 * Servlet que encapsula el proceso de login.
 */
@SuppressWarnings("serial")
public class ValidaLogin extends HttpServlet{
	
	private String urlDataSource;
	/**
	 * Información de la Base de datos
	 */
	private DataSource dsBdValidaLogin;
	
	private boolean appOperativa = true;
	
	private Dao dao;
	
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
    	ServletContext application = config.getServletContext();
    	urlDataSource = (String) application.getInitParameter("URLDataSource");
    	InitialContext initCtx = null;
		try {
			initCtx = new InitialContext();
			this.dsBdValidaLogin = (DataSource) initCtx.lookup(urlDataSource);
		} catch (NamingException e) {
			appOperativa = false;
    	}
		dao = new Dao(dsBdValidaLogin);
		
	}
	
	/**
	 * Recibe las peticiones GET y las pasa al método doPost
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	/**
	 * Recibe las peticiones POST, realiza la consulta a la bb.dd. y devuelve una respuesta
	 * @throws IOException 
	 * @throws ServletException 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BeanUsuario usuario = null;
		BeanError error = null;
		if (!appOperativa){
			error = new BeanError(0,"La aplicación no se encuentra operativa en este momento, intentelo más tarde.");
			request.setAttribute("error", error);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/gesError.jsp");
		    rd.forward(request,response);
		} else{
			try {
				dao.getConexion();
				usuario=dao.getUsuario(request);
				dao.close();
			} catch (SQLException e) {
				error = new BeanError(1,"Error en conexión a base de datos");
			} catch (BeanError e){
				error = e;
			}
		}
		//Se comprueba si se ha producido algún error para devolver la imforción pertinente
		if (error==null){
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
