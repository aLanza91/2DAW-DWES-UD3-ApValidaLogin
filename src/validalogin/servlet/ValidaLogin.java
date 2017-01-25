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
	
	/**
	 * Información de la Base de datos
	 */
	private DataSource dsBdValidaLogin;
	
	/**
	 * Informa si la aplicación se encuentra operativa
	 */
	private boolean appOperativa = true;
	
	/**
	 * Objeto encargado e la comunicación con la base de datos
	 */
	
	private BeanDao beanDao;
	
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
    	String urlDataSource = (String) application.getInitParameter("URLDataSource");
    	InitialContext initCtx = null;
		try {
			initCtx = new InitialContext();
			this.dsBdValidaLogin = (DataSource) initCtx.lookup(urlDataSource);
		} catch (NamingException e) {
			appOperativa = false;
    	}
		beanDao = new BeanDao(dsBdValidaLogin);
		
	}
	
	/**
	 * Recibe las peticiones GET y las pasa al método doPost
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	/**
	 * Recibe las peticiones POST, comprueba si la bb.dd está funcionando, llama al método del Dao correspondiente
	 * y devuelve la respuesta al usuario.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BeanUsuario usuario = null;
		BeanError error = null;
		//Comprueba si la aplicación puede funcionar.
		if (!appOperativa){
			error = new BeanError(0,"La aplicación no se encuentra operativa en este momento, intentelo más tarde.");
			request.setAttribute("error", error);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/gesError.jsp");
		    rd.forward(request,response);
		} else{
			try {
				beanDao.getConexion();
				usuario=beanDao.getUsuario(request);
				beanDao.close();
			} catch (SQLException e) {
				error = new BeanError(1,"Error en conexión a base de datos");
			} catch (BeanError e){
				error = e;
			}
		}
		//Se comprueba si se ha producido algún error para devolver la información pertinente
		if (error==null){
			request.setAttribute("usuario", usuario);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/verUsuario.jsp");
		    rd.forward(request,response);
		
		} else {
			request.setAttribute("error", error);
			request.setAttribute("nombre", "");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/gesError.jsp");
		    rd.forward(request,response);
		}
	}
	
}
