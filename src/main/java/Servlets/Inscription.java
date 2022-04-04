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
import dao.factory.DBDaoFactory;
import dao.factory.DaoFactory;
import dao.user.UserDao;
import dao.user.UserDb;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Inscription() {
		super();
		DaoFactory daoFactory = new DBDaoFactory();
		userDao = daoFactory.getUserDao();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
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

		if (userDao.exist(name)){
			user.setWrongConnect(true);
		} else {
			userDao.inscrire(name, pwd);
			user.setConnect(true);
			user.setWrongConnect(false);
		}

		session.setAttribute("user", user);
		if (user.isConnect()) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
		}

	}

}
