package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.sql.ResultSet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import static java.sql.Date.valueOf;
import static sample.Controller.vissID;

public class ModifyVisController implements Initializable {
    @FXML
    private ImageView iv3;
    @FXML
    private TextField PR;
    @FXML
    private Label sex;
    @FXML
    private TextArea Diag;
    @FXML
    private Label addr;
    @FXML
    private TextField BP;
    @FXML
    private TextField Weight;
    @FXML
    private Label name;
    @FXML
    private TextField Temp;
    @FXML
    private Label phone;
    @FXML
    private Label DOB;
    @FXML
    private TextField Height;
    @FXML
    private TextArea Rem;
    @FXML
    private Label Bgrp;
    @FXML
    private TextField BSug;
    @FXML
    private TextArea Prog;
    @FXML
    private Label age;
    @FXML
    private DatePicker Date;
   @FXML
    private ObservableList<Meds> datax;
    @FXML
    private ComboBox<String> Reports = new ComboBox<String>();
    @FXML
    private TableView<Meds> tableMeds;
    @FXML
    private TableColumn<Meds, String> columnMed;
    @FXML
    private TableColumn<Meds, String> columnDosg;
    @FXML
    private TableColumn<Meds, String> columnDurn;
    @FXML
    private Label PIDLabel;
    Connection conn1 = SqliteConnection.connector();
    Connection conn = SqliteConnection.connector();
    Connection conn2 = SqliteConnection.connector();
    Connection conn3 = SqliteConnection.connector();
    private ObservableList<PatientDetails> data1;
    String newMed;
    String newDos;
    String newDur;
    public ObservableList<Meds> data4 = FXCollections.observableArrayList();
    ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PIDLabel.setText(String.valueOf(Controller.pattID));

        tableMeds.setEditable(true);
        columnMed.setCellValueFactory(new PropertyValueFactory<>("Mname"));
        columnMed.setCellFactory(TextFieldTableCell.<Meds>forTableColumn());
        columnDosg.setCellValueFactory(new PropertyValueFactory<>("dosg"));
        columnDosg.setCellFactory(TextFieldTableCell.<Meds>forTableColumn());
        columnDurn.setCellValueFactory(new PropertyValueFactory<>("duran"));
        columnDurn.setCellFactory(TextFieldTableCell.<Meds>forTableColumn());
        tableMeds.setItems(null);
        for (int i = 0; i < 10; i++)
            data4.add(new Meds("", "", ""));
        tableMeds.setItems(data4);
        columnMed.setOnEditCommit((CellEditEvent<Meds, String> event1) -> {
            TablePosition<Meds, String> pos = event1.getTablePosition();
            newMed = event1.getNewValue();
            int row = pos.getRow();
            Meds medicine = event1.getTableView().getItems().get(row);
            medicine.setMnameProperty(newMed);
        });

        columnDosg.setOnEditCommit((CellEditEvent<Meds, String> event1) -> {
            TablePosition<Meds, String> pos = event1.getTablePosition();
            newDos = event1.getNewValue();
            int row = pos.getRow();
            Meds medicine = event1.getTableView().getItems().get(row);
            medicine.setdosgProperty(newDos);
        });

        columnDurn.setOnEditCommit((CellEditEvent<Meds, String> event1) -> {
            TablePosition<Meds, String> pos = event1.getTablePosition();
            newDur = event1.getNewValue();
            int row = pos.getRow();
            Meds medicine = event1.getTableView().getItems().get(row);
            medicine.setduranProperty(newDur);
            String sql2 = "update Meds set MedName=?, Dosage=?, Duration=? where VisitID='" + Controller.vissID + "' ";
            try (PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
                pstmt2.setString(1, newMed);
                pstmt2.setString(2, newDos);
                pstmt2.setString(3, newDur);
                //pstmt2.setInt(4, Controller.vissID);
                //pstmt2.setInt(5, Controller.medID);
                //Controller.medID++;
                pstmt2.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
        Reports.getItems().addAll(
                "ECG",
                "Angiogram",
                "CT",
                "Blood Report"
        );
        Reports.setPromptText("Report Type");
        Reports.setEditable(true);

        String query1 = "select * from PatientDetails where Pid=" + Controller.pattID + "" + ";";
        ResultSet resultSet1 = null;
        try {
            resultSet1 = conn1.createStatement().executeQuery(query1);
            name.setText(resultSet1.getString("Name"));
            Bgrp.setText(resultSet1.getString("BloodGroup"));
            sex.setText(resultSet1.getString("Sex"));
            phone.setText(resultSet1.getString("PhoneNumber"));
            DOB.setText(resultSet1.getString("DOB"));
            age.setText(resultSet1.getString("Age"));
            addr.setText(resultSet1.getString("Address"));
            String base = "/Users/shubhankitsingh/IdeaProjects/PatientMonitoringSystemBeta/src";
            String relative = new File(base).toURI().relativize(new File(resultSet1.getString("Photo")).toURI()).getPath();
            Image image2 = new Image(relative);
            iv3.setImage(image2);
            String query12 = "select * from Visits where VisitID=" + vissID + ";";
            System.out.print(query12);
            ResultSet resultSet2 = null;
            try {

                resultSet2 = conn3.createStatement().executeQuery(query12);
                BP.setText(resultSet2.getString("bp"));
                Height.setText(resultSet2.getString("height"));
                BSug.setText(resultSet2.getString("sugar"));
                Weight.setText(resultSet2.getString("weight"));
                Temp.setText(resultSet2.getString("temp"));
                PR.setText(resultSet2.getString("pulse"));
                Diag.setText(resultSet2.getString("diagnosis"));
                Prog.setText(resultSet2.getString("prognosis"));
                Rem.setText(resultSet2.getString("Remarks"));
                Date.getEditor().setText(resultSet2.getString("VisitDate"));

            } catch (SQLException e) {

            }
            String query2 = "select MedName,Dosage,Duration from Meds where VisitID=" + vissID + ";";
            datax = FXCollections.observableArrayList();
            try {
                ResultSet resultSet23 = null;
                resultSet23 = conn3.createStatement().executeQuery(query2);
                //adding elements to an observable array list
                while (resultSet23.next()) {
                    System.out.println(resultSet23.getString("MedName"));
                    datax.add(new Meds(resultSet23.getString(1), resultSet23.getString(2), resultSet23.getString(3)));
                }
            } catch (SQLException e) {

            }

            columnMed.setCellValueFactory(new PropertyValueFactory<>("Mname"));
            columnDosg.setCellValueFactory(new PropertyValueFactory<>("dosg"));
            columnDurn.setCellValueFactory(new PropertyValueFactory<>("duran"));
            //populating the table meds
            tableMeds.setItems(null);
            tableMeds.setItems(datax);


        } catch (SQLException e) {
        }
    }

    public void update(ActionEvent event) {
        String sql1 = "update Visits set VisitDate=?, Height=?, Prognosis=?, Diagnosis=?, Weight=?, BP=?, Sugar=?, Temp=?, Pulse=?, Remarks=? where Pidfk='" + Controller.pattID + "' ";
        String sql2 = "update Meds set MedName=?, Dosage=?, Duration=? where VisitID='" + Controller.vissID + "' ";
        try (PreparedStatement pstmt1 = conn1.prepareStatement(sql1);
             PreparedStatement pstmt2 = conn1.prepareStatement(sql2)) {
            //pstmt1.setInt(1, Controller.patID);
            if (Date.getValue().toString().isEmpty() || Height.getText().isEmpty() || Prog.getText().isEmpty() || Diag.getText().isEmpty() || Weight.getText().isEmpty() || BP.getText().isEmpty() || BSug.getText().isEmpty() || Temp.getText().isEmpty() || PR.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                String content = "Please fill all requested fields";
                alert.setContentText(content);
                alert.showAndWait();
            } else {
                LocalDate localDate = Date.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
                pstmt1.setString(1, localDate.format(formatter));
                //pstmt1.setInt(2, Controller.vissID);
                pstmt1.setInt(2, Integer.parseInt(Height.getText()));
                pstmt1.setString(3, Prog.getText());
                pstmt1.setString(4, Diag.getText());
                pstmt1.setInt(5, Integer.parseInt(Weight.getText()));
                pstmt1.setString(6, BP.getText());
                pstmt1.setInt(7, Integer.parseInt(BSug.getText()));
                pstmt1.setInt(8, Integer.parseInt(Temp.getText()));
                pstmt1.setInt(9, Integer.parseInt(PR.getText()));
                pstmt1.setString(10, Rem.getText());
                pstmt1.executeUpdate();
                pstmt2.setString(1, newMed);
                pstmt2.setString(2, newDos);
                pstmt2.setString(3, newDur);
                //pstmt2.setInt(4, Controller.vissID);
                //Controller.vissID++;
                pstmt2.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String query2 = "select MedName,Dosage,Duration from Meds ;";

        try {
            conn1.close();
            conn.close();
            conn2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void upImage(ActionEvent event) throws IOException {
        FileChooser f = new FileChooser();
        File file = f.showOpenDialog(null);
        String pa = file.getPath();
        String base = "/Users/shubhankitsingh/IdeaProjects/PatientMonitoringSystemBeta/src";
        String relative = new File(base).toURI().relativize(new File(pa).toURI()).getPath();
        Image image2 = new Image(relative);
        iv3.setImage(image2);
    }

    public void Reportsmod(ActionEvent event) {
        FileChooser f = new FileChooser();
        File file = f.showOpenDialog(null);
        String pa = file.getPath();
        String base = "/";
        String relative = new File(base).toURI().relativize(new File(pa).toURI()).getPath();
        String sql = "update Reports set Type=?, URL=? where VisitID='" + Controller.vissID + "' ";
        try (PreparedStatement pstmt = conn2.prepareStatement(sql);) {
            //pstmt.setInt(1, Controller.repID);
            //Controller.repID++;
            // pstmt.setInt(2, Controller.patID);
            //pstmt.setInt(3, Controller.vissID);
            pstmt.setString(1, Reports.getEditor().getText());
            pstmt.setString(2, pa);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /*public void deleteReport(ActionEvent e) {
        String query="delete from Reports where VisitID="+Controller.vissID+"AND Rid="+Controller.repID+";";
        Resultset resultset1=null;
        try{
            resultset1=conn1.executeQuery(query);
        } catch (SQLException e){
            System.out.println(e.getMessage());
            }
        }*/

}
