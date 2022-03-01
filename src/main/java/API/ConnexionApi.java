package API;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BD.UserSQL;
import Bean.User;

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
		UserSQL uSQL = new UserSQL();
		User user = new User(uSQL);
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		user.setName(name);
		user.setPassword(pwd);
		user.connection();
		PrintWriter output = new PrintWriter(response.getOutputStream(), true);
		if (user.isConnect()) {
			output.println("success");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
