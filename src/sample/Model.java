package sample;

import java.sql.*;

public class Model {
    final Connection connection;

    public Model() {
        super();
        connection = SqliteConnection.connector();
        if (connection == null)
            System.exit(1);
    }

    public boolean isDBconnected() {
        try {
            return (!connection.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
