package sample;

import java.sql.*;

public class SqliteConnection {
    public SqliteConnection() {
    }

    public static Connection connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:PatientMonitor.db");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
