/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Employees;

import GUI.SysUI.Admin.DTRModel;
import GUI.SysUI.Admin.Employees;
import GUI.config.Session;
import GUI.config.dbConnect;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class View_DailyTimeRecordsController implements Initializable {

    @FXML
    private Label EmployeeName;
    @FXML
    private TableColumn<DTRModel, String> recordId;
    @FXML
    private TableColumn<DTRModel, String> employeeId;
    @FXML
    private TableColumn<DTRModel, Date> entry_date;
    @FXML
    private TableColumn<DTRModel, String> absnt;
    @FXML
    private TextField FilterField;
    @FXML
    private VBox date;
    @FXML
    private Label detailDate;
    @FXML
    private VBox timeIN;
    @FXML
    private Label detailTimeIn;
    @FXML
    private VBox timeOut;
    @FXML
    private Label detailTimeOut;
    @FXML
    private VBox t_hours;
    @FXML
    private Label detailTotalHours;
    @FXML
    private Label overtime_hours;
    @FXML
    private VBox absent;
    @FXML
    private Label detailStatus1;
    private ObservableList<DTRModel> DTRList;
    ObservableList<Employees> searchby = FXCollections.observableArrayList();
    @FXML
    private TableView<DTRModel> TabelView;
    private dbConnect db = new dbConnect();
    @FXML
    private TableColumn<DTRModel, String> entry_date1;
    @FXML
    private ImageView refreshBtn;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session ses = new Session();
    String username = Session.getInstance().getUsername();

        DTRList = FXCollections.observableArrayList();
        recordId.setCellValueFactory(new PropertyValueFactory<>("recordId"));
        employeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        entry_date.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
        absnt.setCellValueFactory(new PropertyValueFactory<>("absent"));
        entry_date1.setCellValueFactory(new PropertyValueFactory<>("month"));
        
        TabelView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            
            DTRModel selectedDTR = newValue;

            
            int selectedRecordId = selectedDTR.getRecordId();

            
            loadDetailsForSelectedRecord(selectedRecordId);
        }
    });
        
        loadDataFromDBRecords();
        loadEmployeeFullName(username);
    }    
    
    private void loadEmployeeFullName(String username) {

    if (db == null) {
        System.out.println("Database connection is NULL");
        return;
    }

    String query = "SELECT e.emp_fname, e.emp_lname " +
                   "FROM employee e " +
                   "JOIN users u ON e.emp_id = u.emp_id " +
                   "WHERE u.user_name = ?";

    try {
        PreparedStatement stmt = db.getConnection().prepareStatement(query);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs != null && rs.next()) {
            String fname = rs.getString("emp_fname");
            String lname = rs.getString("emp_lname");

            String fullName = (fname != null ? fname : "") + " " + (lname != null ? lname : "");
            EmployeeName.setText(fullName.trim());
        } else {
            EmployeeName.setText("Employee Not Found");
        }

        rs.close();
        stmt.close();

    } catch (SQLException e) {
        e.printStackTrace();
        EmployeeName.setText("Error Loading Name");
    }
}


    
    private void loadDetailsForSelectedRecord(int recordId) {
        
    if (db == null) {
        System.out.println("Database connection is NULL");
        return;
    }

    String query = "SELECT record_id, employee_id, entry_date, time_in, time_out, month, hours_worked, overtime_hrs, absent FROM dailytimerecords WHERE record_id = " + recordId;

    try {
        ResultSet rs = db.getData(query);

        if (rs != null && rs.next()) {

            Date entryDate = rs.getDate("entry_date");
            String timeIn = rs.getString("time_in");
            String timeOut = rs.getString("time_out");
            String hoursWorked = rs.getString("hours_worked");
            String overtimeHrs = rs.getString("overtime_hrs");
            String absent = rs.getString("absent");


            String displayTimeIn = (timeIn != null && !timeIn.trim().isEmpty()) ? timeIn : "N/A";
            String displayTimeOut = (timeOut != null && !timeOut.trim().isEmpty()) ? timeOut : "N/A";
            String displayHoursWorked = (hoursWorked != null && !hoursWorked.trim().isEmpty()) ? hoursWorked : "0";
            String displayOvertime = (overtimeHrs != null && !overtimeHrs.trim().isEmpty()) ? overtimeHrs : "0";
            String displayAbsent = (absent != null && !absent.trim().isEmpty()) ? absent : "N/A";

            detailDate.setText(entryDate != null ? entryDate.toString() : "N/A");
            detailTimeIn.setText(displayTimeIn);
            detailTimeOut.setText(displayTimeOut);
            detailTotalHours.setText(displayHoursWorked);
            overtime_hours.setText(displayOvertime);
            detailStatus1.setText(displayAbsent);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    
   public void loadDataFromDBRecords() {
    if (db == null) {
        System.out.println("Database connection is NULL");
        return;
    }

    String username = Session.getInstance().getUsername();
    if (username == null || username.isEmpty()) {
        System.out.println("No active user session");
        return;
    }

    DTRList.clear();

    try {
        String getEmpIdQuery = "SELECT emp_id FROM users WHERE user_name = ?";
        PreparedStatement empStmt = db.getConnection().prepareStatement(getEmpIdQuery);
        empStmt.setString(1, username);
        ResultSet empRs = empStmt.executeQuery();

        if (!empRs.next()) {
            System.out.println("No employee found for username: " + username);
            return;
        }

        int empId = empRs.getInt("emp_id");
        empRs.close();
        empStmt.close();

        String dtrQuery = "SELECT record_id, employee_id, entry_date, time_in, time_out, month, hours_worked, overtime_hrs, absent " +
                          "FROM dailytimerecords WHERE employee_id = ?";
        PreparedStatement dtrStmt = db.getConnection().prepareStatement(dtrQuery);
        dtrStmt.setInt(1, empId);
        ResultSet rs = dtrStmt.executeQuery();

        while (rs.next()) {
            DTRList.add(new DTRModel(
                rs.getInt("record_id"),
                empId,
                rs.getDate("entry_date"),
                rs.getString("time_in"),
                rs.getString("time_out"),
                rs.getString("month"),
                rs.getString("hours_worked"),
                rs.getString("overtime_hrs"),
                rs.getString("absent")
            ));
        }

        rs.close();
        dtrStmt.close();

        TabelView.setItems(DTRList);

        FilteredList<DTRModel> filteredData = new FilteredList<>(DTRList, b -> true);
        FilterField.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredData.setPredicate(dtr -> {
                if (newVal == null || newVal.trim().isEmpty()) return true;
                String lower = newVal.toLowerCase();
                return String.valueOf(dtr.getRecordId()).contains(lower)
                    || String.valueOf(dtr.getEmpId()).contains(lower)
                    || (dtr.getEntryDate() != null && dtr.getEntryDate().toString().toLowerCase().contains(lower))
                    || (dtr.getMonth() != null && dtr.getMonth().toLowerCase().contains(lower));
            });
        });

        TabelView.setItems(filteredData);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}



    @FXML
    private void refreshBtn(MouseEvent event) {
        loadDataFromDBRecords();
        detailDate.setText("");
        detailTimeIn.setText("");
        detailTimeOut.setText("");
        detailTotalHours.setText("");
        overtime_hours.setText("");
        detailStatus1.setText("");
    }
    
}
