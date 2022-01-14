package Theater.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Theater";
    private static final String USER = "kolupanov_a_v";
    private static final String PASS = "admin";

    private static DBConnection INSTANCE;

    public static DBConnection getINSTANCE() {
        if (INSTANCE==null)
            INSTANCE = new DBConnection();
        return INSTANCE;
    }

    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to find db driver: " + e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
//        DriverManager.getDrivers().asIterator().forEachRemaining(System.out::println);
//        DriverManager.setLoginTimeout(10);
        return DriverManager.getConnection(URL, USER, PASS);

    }
}
