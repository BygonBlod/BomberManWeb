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

import Bean.Form;
import dao.factory.DBDaoFactory;
import dao.factory.DaoFactory;
import dao.user.UserDao;

/**
 * Servlet implementation class EndParty
 */
@WebServlet("/EndPartyApi")
public class EndPartyApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EndPartyApi() {
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
		String token2 = request.getHeader("Accept");
		try (InputStream input = EndPartyApi.class.getClassLoader().getResourceAsStream("/config.properties")) {
			Properties prop = new Properties();
			prop.load(input);
			String token = prop.getProperty("web.change.token");
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
		String name = request.getParameter("name");
		int win = Integer.parseInt(request.getParameter("win"));

		PrintWriter output = new PrintWriter(response.getOutputStream(), true);
		if (Form.isCorrectFormNa(name)) {
			if (userDao.changeNbParty(name, win)) {
				output.println("success");
			}
		}
	}

}
