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
 * Servlet implementation class EndParty
 */
@WebServlet("/EndPartyApi")
public class EndPartyApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EndPartyApi() {
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
		System.out.println("token " + token2);
		String name = request.getParameter("name");
		String win = request.getParameter("win");
		System.out.println(name + " " + win);
		if (!token2.equals("583-.mZVh7S*k(xY9wB;")) {
			response.sendRedirect(request.getContextPath() + "/Accueil");
		}

		UserSQL uSQL = new UserSQL();
		User user = new User(uSQL);
		user.setName(name);

		PrintWriter output = new PrintWriter(response.getOutputStream(), true);
		if (user.changeNBParty(win)) {
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
