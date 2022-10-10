package sample;

import java.sql.*;
import java.util.Objects;

public class LoginModel {
    final Connection connection;
    public LoginModel () {
        super();
        connection = SqliteConnection.connector();
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLogin(String user, String pass) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        System.out.println(user+" "+pass);
        String query = " select * from Login where username = ? and password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
        catch (Exception e) {
            return false;
        } finally {
            System.out.println("bull");
            Objects.requireNonNull(preparedStatement).close();
            Objects.requireNonNull(resultSet).close();
        }
    }
}
