package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BD.UserSQL;
import Bean.User;

/**
 * Servlet implementation class Connection
 */
@WebServlet("/Connection")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		User user = (User) session.getAttribute("user");
		if (user == null) {
			UserSQL uSQL = new UserSQL();
			user = new User(uSQL);
		}
		user.setName(name);
		user.setPassword(pwd);
		UserSQL uSQL = new UserSQL();
		User u = uSQL.connexion(name, pwd);
		if (u != null) {
			user.setConnect(true);
			user.setWrongConnect(false);
			user.setNbParty(u.getNbParty());
			user.setNbWin(u.getNbWin());

		}
		System.out.println(user.isConnect());
		session.setAttribute("user", user);
		if (user.isConnect()) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		}
	}

}
