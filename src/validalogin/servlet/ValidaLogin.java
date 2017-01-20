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

@WebServlet(name="ValidaLogin",urlPatterns="/validar")
@SuppressWarnings("serial")
public class ValidaLogin extends HttpServlet{
	
	private DataSource dsBdValidaLogin;
	private BeanError error;
	
	public ValidaLogin() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	InitialContext initCtx = null;
		try {
			initCtx = new InitialContext();
			this.dsBdValidaLogin = (DataSource) initCtx.lookup("java:jboss/datasources/dsbdvalidalogin");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String clave = request.getParameter("clave");
		String nombre="";
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conexion = dsBdValidaLogin.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery("select login,clave,nombre from login where login = '"+login+"'");
			if (rs.next()) {
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
		if (error==null){
			PrintWriter out = response.getWriter();
			out.write("<html>");
			out.write("<head><title>resultado</title></head>");
			out.write("<body>");
			out.write("<br>");
			out.write(nombre);
			out.write("</body>");
			out.write("</html>");
			out.close();
		
		} else {
			request.setAttribute("error", error);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/gesError.jsp");
		    rd.forward(request,response);
		    destroy();
		}
		if (st != null){
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void destroy() {
		this.error=null;
	}
}
