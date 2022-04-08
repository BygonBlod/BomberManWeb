package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Form;
import Bean.User;
import dao.factory.DBDaoFactory;
import dao.factory.DaoFactory;
import dao.user.UserDao;

/**
 * Servlet implementation class Connection
 */
@WebServlet("/Connection")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connexion() {
		super();
		DaoFactory daoFactory = new DBDaoFactory();
		userDao = daoFactory.getUserDao();
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
			user = new User();
		}
		if (Form.isCorrectForm(name, pwd)) {
			user.setName(name);
			user.setPassword(pwd);
			if (userDao.login(name, pwd)) {
				User u = userDao.get(name);
				user.setConnect(true);
				user.setWrongConnect(false);
				user.setNbParty(u.getNbParty());
				user.setNbWin(u.getNbWin());

			}
		}
		System.out.println(user.isConnect());
		session.setAttribute("user", user);
		if (user.isConnect()) {
			response.sendRedirect("/BomberManWeb/");
		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		}
	}

}
