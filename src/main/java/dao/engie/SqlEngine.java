package dao.engie;

import BD.SQLConnector;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlEngine {

    private static SqlEngine instance;

    private Connection connection;

    public static SqlEngine getInstance() {
        if (instance == null)
            try {
                instance = new SqlEngine();
            } catch (Exception e){
                e.printStackTrace();
            }
        return instance;
    }

    public SqlEngine() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");

        try (InputStream input = SQLConnector.class.getClassLoader().getResourceAsStream("/config.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            String name = prop.getProperty("db.name");
            String user = prop.getProperty("db.user");
            String pwd = prop.getProperty("db.password");

            this.connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/" + name + "?autoReconnect=true", user, pwd);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlEngine.instance = this;
    }

    public Connection getConnection() {

        try {

            if (!this.connection.isValid(5000)) {

                new SqlEngine();

                return SqlEngine.getInstance().getConnection();

            }

            if (this.connection.isClosed()) {

                new SqlEngine();

                return SqlEngine.getInstance().getConnection();

            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return this.connection;

    }

}
