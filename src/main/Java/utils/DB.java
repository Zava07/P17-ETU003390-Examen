package main.Java.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    public static Connection connect() throws SQLException {
        String URL = "jdbc:mysql://172.80.237.54/db_s2_ETU003390?useSSL=false&serverTimezone=UTC";
        Properties properties = new Properties();
        properties.setProperty("user", "ETU003390");
        properties.setProperty("password", "Eu1mFF9a");

        Connection connection = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, properties);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC non trouvé !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
            throw e;
        }

        return connection;
    }
}
