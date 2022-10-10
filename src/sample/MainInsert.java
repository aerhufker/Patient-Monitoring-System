package sample;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.*;
import javafx.stage.*;


public class MainInsert extends Application {

    public MainInsert() {
    }

    @Override
    public void start(Stage primaryStage) {
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
