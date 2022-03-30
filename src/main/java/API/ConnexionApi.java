package API;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BD.UserSQL;

/**
 * Servlet implementation class ConnexionApi
 */
@WebServlet("/ConnexionApi")
public class ConnexionApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnexionApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String token2 = request.getHeader("Accept");
		// System.out.println("token " + token2);
		try (InputStream input = ConnexionApi.class.getClassLoader().getResourceAsStream("/config.properties")) {
			Properties prop = new Properties();
			prop.load(input);
			String token = prop.getProperty("web.connect.token");
			if (!token2.equals(token)) {
				response.sendRedirect(request.getContextPath() + "/Accueil");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		UserSQL uSQL = new UserSQL();
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		PrintWriter output = new PrintWriter(response.getOutputStream(), true);
		if (uSQL.connexion(name, pwd) != null) {
			output.println("success");
		}
	}

}
