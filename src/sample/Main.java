package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
    static int d;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root =FXMLLoader.load(getClass().getResource("/sample/Login.fxml"));
        primaryStage.setTitle("PatientMonitoringSystem");

        Scene scene=new Scene(root, 300, 320);

        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println(d);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
