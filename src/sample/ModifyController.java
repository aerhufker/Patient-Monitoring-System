package sample;

import javafx.collections.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.*;
import javafx.scene.control.TableColumn.*;
import javafx.scene.control.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.cell.*;
import javafx.scene.image.*;
import javafx.scene.image.*;
import javafx.stage.*;
import java.io.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.time.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.*;

public class ModifyController implements Initializable {
    @FXML
    private ImageView iv3;
    @FXML
    private TextField PR;
    @FXML
    private TextField Sex;
    @FXML
    private TextArea Diag;
    @FXML
    private TextArea Addr;
    @FXML
    private TextField BP;
    @FXML
    private TextField Weight;
    @FXML
    private TextField Name;
    @FXML
    private TextField Temp;
    @FXML
    private TextField Phone;
    @FXML
    private TextField DOB;
    @FXML
    private TextField Height;
    @FXML
    private TextArea Rem;
    @FXML
    private TextField BGrp;
    @FXML
    private TextField BSug;
    @FXML
    private TextArea Prog;
    @FXML
    private TextField Age;
    @FXML
    private DatePicker Date;
    @FXML
    private final ComboBox<String> Reports = new ComboBox<String>();
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
    final Connection conn1 = SqliteConnection.connector();
    final Connection conn= SqliteConnection.connector();
    final Connection conn2 = SqliteConnection.connector();
    private ObservableList<PatientDetails> data1;
    String newMed;
    String newDos;
    String newDur;
    public ObservableList<Meds> data4 = FXCollections.observableArrayList();

    public ModifyController() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PIDLabel.setText(String.valueOf(Controller.pattID));
       /* FileChooser f = new FileChooser();
        File file = f.showOpenDialog(null);
        String pa = file.getAbsolutePath();
        System.out.println(pa);*/

        String query1 = "select * from PatientDetails where Pid=" + Controller.pattID + "" + ";";
        ResultSet resultSet1;
        try {
            Connection conn3=SqliteConnection.connector();
            resultSet1 = Objects.requireNonNull(conn3).createStatement().executeQuery(query1);
            Name.setText(resultSet1.getString("Name"));
            BGrp.setText(resultSet1.getString("BloodGroup"));
            Sex.setText(resultSet1.getString("Sex"));
            Phone.setText(resultSet1.getString("PhoneNumber"));
            DOB.setText(resultSet1.getString("DOB"));
            Age.setText(resultSet1.getString("Age"));
            Addr.setText(resultSet1.getString("Address"));
            String base = "/Users/shubhankitsingh/IdeaProjects/PatientMonitoringSystemBeta/src";
            String relative = new File(base).toURI().relativize(new File(resultSet1.getString("Photo")).toURI()).getPath();
            Image image2 = new Image(relative);
            iv3.setImage(image2);

            conn3.close();}
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*tableMeds.setEditable(true);
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
            String sql2 = "INSERT INTO Meds(MedName, Dosage, Duration, VisitID, MedVissID) VALUES (?, ?, ?, ?, ?)";
            try ( PreparedStatement pstmt2 = conn.prepareStatement(sql2)  )
            {
                pstmt2.setString(1, newMed);
                pstmt2.setString(2, newDos);
                pstmt2.setString(3, newDur);
                pstmt2.setInt(4, Controller.vissID);
                pstmt2.setInt(5, Controller.medID);
                Controller.medID++;
                pstmt2.executeUpdate();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }});

        Reports.getItems().addAll(
                "ECG",
                "Angiogram",
                "CT",
                "Blood Report"
        );
        Reports.setPromptText("Report Type");
        Reports.setEditable(true);*/
    }

    public void update(ActionEvent event) {
        //String sql = "INSERT INTO PatientDetails(Pid,Name,BloodGroup,Age,Sex,Address,PhoneNumber, DOB) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        //String sql1 = "INSERT INTO Visits(Pidfk,VisitDate,VisitID,Height,Prognosis,Diagnosis,Weight,BP,Sugar,Temp,Pulse,Remarks) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sql = "update PatientDetails set Name=?, BloodGroup=? , Age=?, Sex=?, Address=?, PhoneNumber=?, DOB=? where Pid='" + Controller.pattID + "' ";

        try (   PreparedStatement pstmt = Objects.requireNonNull(conn1).prepareStatement(sql))
        {
            if(Name.getText().isEmpty()||BGrp.getText().isEmpty()||Age.getText().isEmpty()||Sex.getText().isEmpty()||Addr.getText().isEmpty()||Phone.getText().isEmpty()||DOB.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                String content = "Please fill all requested fields";
                alert.setContentText(content);
                alert.showAndWait();
            }
            else{

                pstmt.setString(1, Name.getText());
                pstmt.setString(2, BGrp.getText());
                pstmt.setInt(3, Integer.parseInt(Age.getText()));
                pstmt.setString(4, Sex.getText());
                pstmt.setString(5, Addr.getText());
                pstmt.setLong(6, Long.parseLong(Phone.getText()));
                pstmt.setString(7, DOB.getText());

                pstmt.executeUpdate();}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            Objects.requireNonNull(conn1).close();
            Objects.requireNonNull(conn).close();
            Objects.requireNonNull(conn2).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void upImage(ActionEvent event) {
        FileChooser f = new FileChooser();
        File file = f.showOpenDialog(null);
        String pa = file.getPath();
        String base = "/Users/shubhankitsingh/IdeaProjects/PatientMonitoringSystemBeta/src";
        String relative = new File(base).toURI().relativize(new File(pa).toURI()).getPath();
        Image image2 = new Image(relative);
        iv3.setImage(image2);
    }

    /*public void Reportsins(ActionEvent event)
    {
        FileChooser f = new FileChooser();
        File file = f.showOpenDialog(null);
        String pa = file.getPath();
        String base = "C:";
        String relative = new File(base).toURI().relativize(new File(pa).toURI()).getPath();
        String sql = "INSERT INTO Reports(Rid,Pid,VisitID,Type,URL) VALUES (?, ?, ?, ?, ?)";
        try ( PreparedStatement pstmt = conn2.prepareStatement(sql);)
        {
            pstmt.setInt(1, Controller.repID);
            Controller.repID++;
            pstmt.setInt(2, Controller.patID);
            pstmt.setInt(3, Controller.vissID);
            pstmt.setString(4, Reports.getEditor().getText());
            pstmt.setString(5, pa);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }*/

}
