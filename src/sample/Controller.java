package sample;
//

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import org.sqlite.SQLiteConnection;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Model model = new Model();
    @FXML
    private ImageView iv;
    @FXML
    //private MediaView iv1;
    private ImageView iv1;
    private MediaPlayer mediaPlayer;
    private Media media;
    @FXML
    private Label name;
    @FXML
    private Label sex;
    @FXML
    private Label phno;
    @FXML
    private Label dateOB;
    @FXML
    private Label PatientID;
    @FXML
    private Label age;
    @FXML
    private Label BloodGrp;
    @FXML
    private Label Address;
    @FXML
    private Label ht;
    @FXML
    private Label wt;
    @FXML
    private Label temp;
    @FXML
    private Label bp;
    @FXML
    private Label pulse;
    @FXML
    private Label sugar;
    @FXML
    private TextArea Diagnosis;
    @FXML
    private TextArea Prognosis;
    @FXML
    private TextArea remarks;
    @FXML
    DatePicker visitdate = new DatePicker();
    @FXML
    private TableView<Meds> tableMeds;
    @FXML
    private TableColumn<Meds, String> columnMed;
    @FXML
    private TableColumn<Meds, String> columnDosg;
    @FXML
    private TableColumn<Meds, String> columnDurn;
    @FXML
    private TableView<PatientDetails> tableUser;
    @FXML
    private TableColumn<PatientDetails, Integer> columnID;
    @FXML
    private TableColumn<PatientDetails, String> columnName;
    @FXML
    private Button search;
    @FXML
    private TextField searcher;
    @FXML
    private VBox pane;
    @FXML
    //private ComboBox Reports;
    private Menu Files;
    @FXML
    private MenuItem addPat;
    @FXML
    private DatePicker date;
    @FXML
    private ListView visitlist;
    public Connection connection1;
    public Connection connection2;
    public Connection connection3;
    public Connection connection4;
    public Connection connection5;
    public Connection connection9;
    public Connection connection8;
    public ObservableList data2 = null;// = FXCollections.observableArrayList();
    int z=0;
    static int pattID = 0;
    static int patID = 0;
    static int visID = 0;
    static int vissID = 0;
    private ObservableList<PatientDetails> data1;
    private ObservableList<Meds> data;
    MainInsert ob = new MainInsert();
    // Stage primaryStages=null;
    public static int repID;
    public static int medID;
    @FXML
    private ComboBox<String> Reports1 = new ComboBox<String>();

    public void searchData(ActionEvent event) throws SQLException {
        connection1 = SqliteConnection.connector();
        data1 = FXCollections.observableArrayList();
        String nameS = searcher.getText();
        if (searcher.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            String content = "Please fill all requested fields";
            alert.setContentText(content);
            alert.showAndWait();
        } else {
            Image image3 = new Image("sample/lmao.jpg");
            iv1.setImage(image3);
            data2 = null;
            bp.setText("");
            ht.setText("");
            sugar.setText("");
            wt.setText("");
            temp.setText("");
            pulse.setText("");
            Diagnosis.setText("");
            Prognosis.setText("");
            remarks.setText("");
            vissID = 0;
            pattID=0;
            tableMeds.setItems(null);
            name.setText("");
            PatientID.setText("");
            BloodGrp.setText("");
            age.setText("");
            sex.setText("");
            Address.setText("");
            phno.setText("");
            dateOB.setText("");
            date.getEditor().setText("DD-MM-YYYY");
            visitlist.setItems(null);
            Image image = new Image("sample/ghanta.jpeg");
            iv.setImage(image);
            Reports1.setPromptText("Report type");
            int pidi = (int) (nameS.charAt(0));
            String query;
            if (pidi > 48 && pidi < 57)
                query = "select * from PatientDetails where (Pid =" + (pidi - 48) + ");";
            else
                query = "select * from PatientDetails where (name  LIKE '" + nameS + "');";
            System.out.print(nameS + "  " + pidi);
            ResultSet resultSet = null;
            try {

                resultSet = connection1.createStatement().executeQuery(query);
                query = "";
                z = Integer.parseInt(resultSet.getString(1));
                while (resultSet.next()) {
                    data1.add(new PatientDetails(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9)));

                }
//
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                String content = "No such value exists";
                alert.setContentText(content);
                alert.showAndWait();
            }
            columnID.setCellValueFactory(new PropertyValueFactory<>("PatientId"));
            columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            tableUser.setItems(null);
            tableUser.setItems(data1);
            tableUser.setOnMouseClicked((MouseEvent event1) -> {
                if (event1.getClickCount() > 0) {
                    try {
                        pattID=z;
                        detailsEdit();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public void detailsEdit() throws SQLException {
        // check the table's selected item and get selected item
        data2 = FXCollections.observableArrayList();
        if (tableUser.getSelectionModel().getSelectedItem() != null) {
            PatientDetails selectedPerson = tableUser.getSelectionModel().getSelectedItem();
            name.setText(selectedPerson.getName());
            PatientID.setText(selectedPerson.getPatientId());
            BloodGrp.setText(selectedPerson.getBloodgrp());
            age.setText(selectedPerson.getAge());
            sex.setText(selectedPerson.getSex());
            Address.setText(selectedPerson.getAddress());
            phno.setText(selectedPerson.getPhno());
            dateOB.setText(selectedPerson.getDateOB());
            String pa = selectedPerson.getPhoto();
            String base = "/Users/shubhankitsingh/IdeaProjects/PatientMonitoringSystemBeta/src";
            String relative = new File(base).toURI().relativize(new File(pa).toURI()).getPath();
            Image image = new Image(relative);
            iv.setImage(image);
            String query3 = "select * from Visits where Pidfk=" + pattID + ";";
            ResultSet resultSet3 = null;
            connection2 = SqliteConnection.connector();
            resultSet3 = connection2.createStatement().executeQuery(query3);
            while (resultSet3.next()) {
                data2.add(resultSet3.getString("VisitDate"));

            }
            visitlist.setItems(null);
            visitlist.setItems(data2);
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (model.isDBconnected()) {
            System.out.print("DatabaseConnected");
            Image image = new Image("sample/ghanta.jpeg");
            iv.setImage(image);
            Image image1 = new Image("sample/lmao.jpg");
            iv1.setImage(image1);
            Reports1.getItems().addAll(
                    "ECG",
                    "Angiogram",
                    "CT",
                    "Blood Report"
            );
            Reports1.setPromptText("Report Type");
            date.getEditor().setText("DD-MM-YYYY");
            //String path = new File("src/media/The.Girl.Next.Door.UNRATED.2004.1080p.BrRip.x264.BOKUTOX.YIFY.mp4").getAbsolutePath();
           /*media = new Media(new File("/Users/shubhankitsingh/Desktop/41.mp4").toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            iv1.setMediaPlayer(mediaPlayer);
            mediaPlayer.setAutoPlay(true);*/


            // Create the view and add it to the Scene.
            //  MediaView mediaView = new MediaView(iv1);
           // Image image1 = new Image("sample/ghanta.jpeg");
            //iv1.setImage(image1);

        } else {
            System.out.print("Database_Not_Connected");
        }

        try {
            String query1 = "select count(*) from Visits";
            String query2 = "select count(*) from PatientDetails";
            String query3 = "select count(*) from Meds";
            String query4 = "select count(*) from Reports";
            ResultSet resultSet1 = null;
            connection9 = SqliteConnection.connector();
            resultSet1 = connection9.createStatement().executeQuery(query1);
            while (resultSet1.next()) {
                visID = resultSet1.getInt(1) + 1;
            }
            ResultSet resultSet2 = null;
            resultSet2 = connection9.createStatement().executeQuery(query2);
            while (resultSet2.next()) {
                patID = resultSet2.getInt(1) + 1;
            }
            ResultSet resultSet3 = null;
            resultSet3 = connection9.createStatement().executeQuery(query3);
            while (resultSet3.next()) {
                medID = resultSet3.getInt(1) + 1;
            }
            ResultSet resultSet4 = null;
            resultSet4 = connection9.createStatement().executeQuery(query4);
            while (resultSet4.next()) {
                repID = resultSet4.getInt(1) + 1;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectDate(ActionEvent event) throws SQLException {
        //  mediaPlayer.setAutoPlay(false);
        LocalDate localDate = date.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM uuuu");
        localDate.format(formatter);

        String query1 = "select * from Visits where VisitDate='" + localDate.format(formatter) + "'and Pidfk=" + pattID + ";";
        System.out.print(query1);
        ResultSet resultSet1 = null;
        try {
            connection3 = SqliteConnection.connector();
            resultSet1 = connection3.createStatement().executeQuery(query1);

            if (!resultSet1.next()) {
                bp.setText("");
                ht.setText("");
                sugar.setText("");
                wt.setText("");
                temp.setText("");
                pulse.setText("");
                Diagnosis.setText("");
                Prognosis.setText("");
                remarks.setText("");
                vissID = 0;
                tableMeds.setItems(null);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                String content = "Please choose the correct visit date";
                alert.setContentText(content);
                alert.showAndWait();


            } else {
                bp.setText(resultSet1.getString("bp"));
                ht.setText(resultSet1.getString("height"));
                sugar.setText(resultSet1.getString("sugar"));
                wt.setText(resultSet1.getString("weight"));
                temp.setText(resultSet1.getString("temp"));
                pulse.setText(resultSet1.getString("pulse"));
                Diagnosis.setText(resultSet1.getString("diagnosis"));
                Prognosis.setText(resultSet1.getString("prognosis"));
                remarks.setText(resultSet1.getString("Remarks"));
                vissID = resultSet1.getInt("VisitID");

                System.out.println(vissID);
            }
        } catch (SQLException e) {

        }
        String query2 = "select MedName,Dosage,Duration from Meds where VisitID=" + vissID + ";";
        data = FXCollections.observableArrayList();
        try {
            ResultSet resultSet2 = null;
            resultSet2 = connection3.createStatement().executeQuery(query2);
            //adding elements to an observable array list
            while (resultSet2.next()) {
                System.out.println(resultSet2.getString("MedName"));
                data.add(new Meds(resultSet2.getString(1), resultSet2.getString(2), resultSet2.getString(3)));
            }
        } catch (SQLException e) {

        }

        columnMed.setCellValueFactory(new PropertyValueFactory<>("Mname"));
        columnDosg.setCellValueFactory(new PropertyValueFactory<>("dosg"));
        columnDurn.setCellValueFactory(new PropertyValueFactory<>("duran"));
        //populating the table meds
        tableMeds.setItems(null);
        tableMeds.setItems(data);


    }

    public void addPatient(ActionEvent event) throws NullPointerException {
        try {
           /* VBox rootpane = FXMLLoader.load(getClass().getResource("insert.fxml"));
            pane.getChildren().setAll(rootpane);*/
            Stage primaryStages = new Stage();
            Parent roots = FXMLLoader.load(getClass().getResource("insert.fxml"));
            primaryStages.setTitle("PatientMonitoringSystem");
            primaryStages.setScene(new Scene(roots, 300, 275));
            primaryStages.show();
            try {
                connection1.close();
                connection2.close();
                connection3.close();
                connection4.close();
                connection5.close();
                connection9.close();
                connection8.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**/

    }

    public void addVisit(ActionEvent event) throws NullPointerException {
        try {
           /* VBox rootpane = FXMLLoader.load(getClass().getResource("insert.fxml"));
            pane.getChildren().setAll(rootpane);*/
            Stage primaryStages = new Stage();
            Parent roots = FXMLLoader.load(getClass().getResource("insertvis.fxml"));
            primaryStages.setTitle("PatientMonitoringSystem");
            primaryStages.setScene(new Scene(roots, 300, 275));
            primaryStages.show();
            try {
                connection1.close();
                connection2.close();
                connection3.close();
                connection4.close();
                connection5.close();
                connection9.close();
                connection8.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deletePatient(ActionEvent e) throws NullPointerException {
        String query5;
        query5 = "delete from PatientDetails where Pid=" + pattID + ";";
        String query6 = "delete from Visits where Pidfk=" + pattID + ";";
        ResultSet resultSet5 = null;
        tableUser.setItems(null);
        tableMeds.setItems(null);
        connection4 = SqliteConnection.connector();
        try {
            resultSet5 = connection4.createStatement().executeQuery(query6);
            resultSet5 = null;
            resultSet5 = connection4.createStatement().executeQuery(query5);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void deleteVisit(ActionEvent e) {
        String query6;
        query6 = "delete from Visits where  VisitID=" + vissID + ";";
        ResultSet resultSet6 = null;
        connection5 = SqliteConnection.connector();
        try {

            ResultSet resultSet = resultSet6 = connection5.createStatement().executeQuery(query6);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }


    public void modifyPatient(ActionEvent event) throws IOException {
        Stage primaryStages = new Stage();
        Parent roots = FXMLLoader.load(getClass().getResource("modify.fxml"));
        primaryStages.setTitle("PatientMonitoringSystem");
        primaryStages.setScene(new Scene(roots, 300, 275));
        primaryStages.show();
        try {
            connection1.close();
            connection2.close();
            connection3.close();
            connection4.close();
            connection5.close();
            connection9.close();
            connection8.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyVisit(ActionEvent event) throws IOException {
        Stage primaryStages = new Stage();
        Parent roots = FXMLLoader.load(getClass().getResource("modifyvis.fxml"));
        primaryStages.setTitle("PatientMonitoringSystem");
        primaryStages.setScene(new Scene(roots, 300, 275));
        primaryStages.show();
            try {
            connection1.close();
            connection2.close();
            connection3.close();
            connection4.close();
            connection5.close();
            connection9.close();
            connection8.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reports(ActionEvent event) throws IOException {
        String query6;
        System.out.println("kj" + (Reports1.getValue()).toString() + "vissID" + vissID);
        query6 = "select URL from Reports where  VisitID=" + vissID + " and Type= '" + (Reports1.getValue()).toString() + "';";
        ResultSet resultSet61 = null;
        connection8 = SqliteConnection.connector();
        try {
            resultSet61 = connection8.createStatement().executeQuery(query6);
            if (resultSet61.next()) {
                System.out.println(resultSet61.getString("URL"));
            /*media = new Media(new File(resultSet61.getString(1)).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            iv1.setMediaPlayer(mediaPlayer);
            mediaPlayer.setAutoPlay(true);*/
                String base = "/Users/shubhankitsingh/IdeaProjects/PatientMonitoringSystemBeta/src";
                String relative = new File(base).toURI().relativize(new File(resultSet61.getString(1)).toURI()).getPath();
                Image image = new Image(relative);
                iv1.setImage(image);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                String content = "Report doesn't exist";
                alert.setContentText(content);
                alert.showAndWait();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    public void manual(ActionEvent event) throws IOException {
        File pdff=new File("/Users/shubhankitsingh/Downloads/Overview_history-1/hllo.pdf");
        if(pdff.exists())
        {
            if(Desktop.isDesktopSupported())
            {
                Desktop.getDesktop().open(pdff);
            }
        }
    }
}