package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BdConnection {
    private final String url = "jdbc:mysql://localhost:3306/belairclub?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private final String user = "anesabml";
    private final String password = "anesabml";
    private Connection connection = null;
    private Statement statement;

    public BdConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
