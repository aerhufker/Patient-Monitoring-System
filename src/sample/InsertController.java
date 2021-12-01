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

public class InsertController implements Initializable {
    String relativex;
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
    private ComboBox<String> Reports = new ComboBox<String>();
    @FXML
    private TableView<Meds> tableMeds;
    @FXML
    private TableColumn<Meds, String> columnMed;
    @FXML
    private TableColumn<Meds, String> columnDosg;
    @FXML
    private TableColumn<Meds, String> columnDurn;
    Connection conn1 = SqliteConnection.connector();
    Connection conn= SqliteConnection.connector();
    Connection conn2 = SqliteConnection.connector();
    private ObservableList<PatientDetails> data1;
    String newMed;
    String newDos;
    String newDur;
    public ObservableList<Meds> data4 = FXCollections.observableArrayList();
    ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       /* FileChooser f = new FileChooser();
        File file = f.showOpenDialog(null);
        String pa = file.getAbsolutePath();
        System.out.println(pa);
        Image images = new Image("sample/Test.jpg");
        iv3.setImage(images);*/
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
            String sql2 = "INSERT INTO Meds(MedName, Dosage, Duration, VisitID, MedVisID) VALUES (?, ?, ?, ?, ?)";
            try ( PreparedStatement pstmt2 = conn.prepareStatement(sql2) )
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
        Reports.setEditable(true);
    }

    public void insert(ActionEvent event) {
        String sql = "INSERT INTO PatientDetails(Pid,Name,BloodGroup,Age,Sex,Address,PhoneNumber, DOB,Photo) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?)";
        String sql1 = "INSERT INTO Visits(Pidfk,VisitDate,VisitID,Height,Prognosis,Diagnosis,Weight,BP,Sugar,Temp,Pulse,Remarks) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (   PreparedStatement pstmt = conn1.prepareStatement(sql);
                PreparedStatement pstmt1 = conn1.prepareStatement(sql1);)
        {
            if(Name.getText().isEmpty()||BGrp.getText().isEmpty()||Age.getText().isEmpty()||Sex.getText().isEmpty()||Addr.getText().isEmpty()||Phone.getText().isEmpty()||DOB.getText().isEmpty()||Date.getValue().toString().isEmpty()||Height.getText().isEmpty()||Prog.getText().isEmpty()||Diag.getText().isEmpty()||Weight.getText().isEmpty()||BP.getText().isEmpty()||BSug.getText().isEmpty()||Temp.getText().isEmpty()||PR.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                String content = "Please fill all requested fields";
                alert.setContentText(content);
                alert.showAndWait();
            }
            else{
                pstmt.setInt(1, Controller.patID);
                pstmt.setString(2, Name.getText());
                pstmt.setString(3, BGrp.getText());
                pstmt.setInt(4, Integer.parseInt(Age.getText()));
                pstmt.setString(5, Sex.getText());
                pstmt.setString(6, Addr.getText());
                pstmt.setLong(7, Long.parseLong(Phone.getText()));
                pstmt.setString(8, DOB.getText());
                pstmt.setString(9,relativex);
                pstmt.executeUpdate();
                pstmt1.setInt(1, Controller.patID);
                Controller.patID++;
                LocalDate localDate = Date.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
                pstmt1.setString(2,localDate.format(formatter));
                pstmt1.setInt(3, Controller.vissID);
                pstmt1.setInt(4, Integer.parseInt(Height.getText()));
                pstmt1.setString(5, Prog.getText());
                pstmt1.setString(6, Diag.getText());
                pstmt1.setInt(7, Integer.parseInt(Weight.getText()));
                pstmt1.setString(8, BP.getText());
                pstmt1.setInt(9, Integer.parseInt(BSug.getText()));
                pstmt1.setInt(10, Integer.parseInt(Temp.getText()));
                pstmt1.setInt(11, Integer.parseInt(PR.getText()));
                pstmt1.setString(12, Rem.getText());
                pstmt1.executeUpdate();
                Controller.vissID++;}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

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
         relativex = new File(base).toURI().relativize(new File(pa).toURI()).getPath();
        Image image2 = new Image(relativex);
        iv3.setImage(image2);
    }

    public void Reportsins(ActionEvent event)
    {
        FileChooser f = new FileChooser();
        File file = f.showOpenDialog(null);
        String pa = file.getPath();
        String base = "/Users/shubhankitsingh/IdeaProjects/PatientMonitoringSystemBeta/src";
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

    }

}
