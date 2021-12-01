package sample;
import sample.SqliteConnection;

import java.sql.*;
public class LoginModel {
    Connection connection;
    public LoginModel () {
        connection = SqliteConnection.connector();
        if(connection == null) {
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
            if (resultSet.next()) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        } finally {
            System.out.println("bull");
            preparedStatement.close();
            resultSet.close();
        }
    }
}
