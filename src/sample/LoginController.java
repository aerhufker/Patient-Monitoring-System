package sample;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.fxml.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.image.*;
import javafx.stage.*;



public class LoginController implements Initializable {
    public final LoginModel loginModel = new LoginModel();

    @FXML
    private Label isConnected;
    @FXML
    private TextField txtusername;
    @FXML
    private TextField txtpassword;
    @FXML
    ImageView ivlogin;
    private int count=0;

    public LoginController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

        Image image = new Image("sample/doctor-icon.png");
        ivlogin.setImage(image);

        if(loginModel.isDbConnected()) {
            isConnected.setText("Connected!");
        }
        else {
            isConnected.setText("Not Connected!");
        }
    }

    public void Login(ActionEvent event) {
        try {
            System.out.println(txtpassword.getText()+" "+txtusername.getText());
            if(loginModel.isLogin(txtusername.getText(),txtpassword.getText())) {

                System.out.println(txtpassword.getText()+"  "+txtusername.getText());
                isConnected.setText("Username and Password is correct!");

                Stage primaryStage=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
                primaryStage.setTitle("PatientMonitoringSystem");
                primaryStage.setScene(new Scene(root, 300, 275));
                loginModel.connection.close();
                primaryStage.show();
            }
            else {
                if (count == 3) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    String content = "Too many errors";
                    alert.setContentText(content);
                    alert.showAndWait();
                    System.exit(0);
                }
                isConnected.setText("Username and Password is incorrect!");
                count++;
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            isConnected.setText("Username and Password is incorrect!");
            count++;
            e.printStackTrace();
        }
    }

}
