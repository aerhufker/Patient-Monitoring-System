package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainInsert extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("insert.fxml"));
        primaryStage.setTitle("Add new patient");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        primaryStage.show();
        //////////////System.out.println(d);//////////////
    }

    public void star(Stage primaryStage) {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
