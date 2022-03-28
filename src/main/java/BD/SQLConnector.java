package BD;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLConnector {
	private static SQLConnector instance = null;
	private String url;
	private String user;
	private String pwd;

	public SQLConnector(String url, String username, String pwd) {
		this.url = url;
		this.user = username;
		this.pwd = pwd;
	}

	public static SQLConnector getInstance() {
		if (instance == null) {

			try (InputStream input = SQLConnector.class.getClassLoader().getResourceAsStream("/config.properties")) {

				Properties prop = new Properties();
				prop.load(input);
				String name = prop.getProperty("db.name");
				String user = prop.getProperty("db.user");
				String pwd = prop.getProperty("db.password");

				Class.forName("org.mariadb.jdbc.Driver");
				instance = new SQLConnector("jdbc:mariadb://localhost:3306/" + name, user, pwd);
				return instance;
			} catch (ClassNotFoundException e) {
				System.out.println("Classe non trouver");
				e.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			return instance;
		}
		return null;
	}

	public Connection getConnection() {
		Connection res = null;
		try {
			res = DriverManager.getConnection(this.url, this.user, this.pwd);
		} catch (SQLException e) {
			System.out.println("création de la connexion à la base de donnée ne s'est pas faite");
			e.printStackTrace();
		}
		return res;
	}

}
