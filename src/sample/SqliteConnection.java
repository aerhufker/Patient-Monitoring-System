package sample;

import java.sql.*;

public class SqliteConnection {
    public static Connection connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:PatientMonitor.db");
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
