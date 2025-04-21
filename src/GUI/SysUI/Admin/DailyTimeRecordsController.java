/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Admin;

import GUI.config.config;
import GUI.config.dbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class DailyTimeRecordsController implements Initializable {
    ObservableList<Reports> searchby = FXCollections.observableArrayList();
    @FXML
    private Button CSV;
    @FXML
    private TableView<DTRModel> DTRView;
    @FXML
    private TableColumn<DTRModel, String> recordID;
    @FXML
    private TableColumn<DTRModel, String> employeeID;
    @FXML
    private TableColumn<DTRModel, Date> entry_date;
    @FXML
    private TableColumn<DTRModel, String> time_in; // Changed to String
    @FXML
    private TableColumn<DTRModel, String> time_out; // Changed to String
    @FXML
    private TableColumn<DTRModel, String> month;
    @FXML
    private TableColumn<DTRModel, String> hours_worked; // Changed to String
    @FXML
    private TableColumn<DTRModel, String> overtime_hours; // Changed to String
    @FXML
    private TableColumn<DTRModel, String> absent;

    private ObservableList<DTRModel> DTRList;

    private dbConnect db = new dbConnect();
    @FXML
    private Label CurrentDandT;
    @FXML
    private TextField FilterField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        updateDateTime(); 
        startClock(); 
        DTRList = FXCollections.observableArrayList();

        recordID.setCellValueFactory(new PropertyValueFactory<>("recordId"));
        employeeID.setCellValueFactory(new PropertyValueFactory<>("empId"));
        entry_date.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
        time_in.setCellValueFactory(new PropertyValueFactory<>("timeIn"));
        time_out.setCellValueFactory(new PropertyValueFactory<>("timeOut"));
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        hours_worked.setCellValueFactory(new PropertyValueFactory<>("hoursWorked"));
        overtime_hours.setCellValueFactory(new PropertyValueFactory<>("overtimeHours"));
        absent.setCellValueFactory(new PropertyValueFactory<>("absent"));

        loadDataFromDBRecords();
    }

    @FXML
    private void refreshButton(MouseEvent event) {
    }
    
    private void startClock() {
    Timeline timeline = new Timeline(
        new KeyFrame(Duration.seconds(1), e -> updateDateTime())
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
}
    
    private void updateDateTime() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a - MMM dd, yyyy");
    String formattedDateTime = now.format(formatter);
    
    CurrentDandT.setText(formattedDateTime);
        }   

    public void loadDataFromDBRecords() {
        if (db == null) {
            System.out.println("Database connection is NULL");
            return;
        }

        DTRList.clear();

        String query = "SELECT record_id, employee_id, entry_date, time_in, time_out, month, hours_worked, overtime_hrs, absent FROM dailytimerecords";
        try {

            System.out.println("Executing Query: " + query);
            ResultSet rs = db.getData(query);

            if (rs == null) {
                System.out.println("ResultSet is null");
                return;
            }

            while (rs.next()) {
                int recordId = rs.getInt("record_id");
                int employeeId = rs.getInt("employee_id");
                Date entryDate = rs.getDate("entry_date");
                String timeIn = rs.getString("time_in");
                String timeOut = rs.getString("time_out");
                String month = rs.getString("month");
                String hoursWorked = rs.getString("hours_worked");
                String overtimeHrs = rs.getString("overtime_hrs");
                String absent = rs.getString("absent");

                DTRList.add(new DTRModel(recordId, employeeId, entryDate, timeIn, timeOut, month, hoursWorked, overtimeHrs, absent));
            }

            DTRView.setItems(DTRList);
            
            FilteredList<DTRModel> filteredData = new FilteredList<>(DTRList, b -> true);

          
            FilterField.textProperty().addListener((observable, oldValue, newValue) -> {
               filteredData.setPredicate(dtr -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(dtr.getRecordId()).contains(lowerCaseFilter) || 
                    String.valueOf(dtr.getEmpId()).contains(lowerCaseFilter) ||
                    (dtr.getEntryDate() != null && dtr.getEntryDate().toString().toLowerCase().contains(lowerCaseFilter)) ||
                    (dtr.getMonth() != null && dtr.getMonth().toLowerCase().contains(lowerCaseFilter))) {
                    return true;
                }

                return false; // Does not match filter
            });
            });
            
            DTRView.setItems(filteredData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void importCSV(MouseEvent event) throws IOException {

        config conf = new config();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SysUI/Admin/ImportCSV.fxml"));
        Parent addUserContent = loader.load();


        ImportCSVController controller = loader.getController();


        Scene scene = CSV.getScene();
        Pane rootPane = (Pane) scene.getRoot();

        AnchorPane overlayPane = new AnchorPane(addUserContent);
        overlayPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");


        overlayPane.prefWidthProperty().bind(scene.widthProperty());
        overlayPane.prefHeightProperty().bind(scene.heightProperty());


        controller.setOverlayPane(overlayPane);
        controller.setRootPane(rootPane);


        rootPane.getChildren().add(overlayPane);


        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), overlayPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();


        overlayPane.setOnMouseClicked(e -> {
            if (e.getTarget() == overlayPane) {
                FadeTransition fadeOut = new FadeTransition(Duration.millis(300), overlayPane);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.setOnFinished(ev -> rootPane.getChildren().remove(overlayPane));
                fadeOut.play();
            }
        });

    }


}