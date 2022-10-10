package sample;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class Main extends Application {
    static int d;

    public Main() {
    }

    @Override
    public void start(Stage primaryStage) {
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
