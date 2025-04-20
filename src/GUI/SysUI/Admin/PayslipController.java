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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
public class PayslipController implements Initializable {
    
    
    
   
    @FXML
    private TableView<Employees> EmpTable;
    @FXML
    private TableColumn<Employees, String> empPost;
    @FXML
    private TableColumn<Employees, String> empID;
    @FXML
    private TableColumn<Employees, String> empName;
    @FXML
    private TableColumn<Employees, String> empDept;
    @FXML
    private TableColumn<Employees, String> actionTable;
    private dbConnect db = new dbConnect();
     private ObservableList<Employees> empList;
    @FXML
    private CheckBox selectAll;
    ObservableList<Employees> items;
    ObservableList<Employees> searchby = FXCollections.observableArrayList();
    @FXML
    private TextField FilterField;
    @FXML
    private ComboBox<String> monthBox;
    private ObservableList<String> months = FXCollections.observableArrayList(
               "January", "February", "March", "April", "May", "June",
               "July", "August", "September", "October", "November", "December"
       );
    @FXML
    private Label durationLabel;
    
    @FXML
    private ComboBox<String> yearBox;
    @FXML
    private TextArea summaryArea;
    @FXML
    private Button viewReports;
    /**
     * Initializes the controller class.
     */
   @Override
public void initialize(URL url, ResourceBundle rb) {
    
    monthBox.setItems(months);

    
    int currentMonthIndex = LocalDate.now().getMonthValue() - 1; 
    monthBox.getSelectionModel().select(currentMonthIndex);

    
    int currentYear = LocalDate.now().getYear();
    ObservableList<String> years = FXCollections.observableArrayList();
    for (int i = currentYear; i >= currentYear - 10; i--) {
        years.add(String.valueOf(i));
    }
    yearBox.setItems(years);
    yearBox.getSelectionModel().select(String.valueOf(currentYear));  

    
    updateDurationLabel(LocalDate.now().getMonth(), currentYear);

    
    monthBox.setOnAction(event -> {
        String selectedMonth = monthBox.getValue();
        if (selectedMonth != null) {
            Month month = Month.valueOf(selectedMonth.toUpperCase());
            int selectedYear = Integer.parseInt(yearBox.getValue());  
            updateDurationLabel(month, selectedYear);
        }
    });

    
    yearBox.setOnAction(event -> {
        String selectedMonth = monthBox.getValue();
        if (selectedMonth != null) {
            Month month = Month.valueOf(selectedMonth.toUpperCase());
            int selectedYear = Integer.parseInt(yearBox.getValue());  
            updateDurationLabel(month, selectedYear);
        }
    });

    // Select All checkbox logic
    selectAll.selectedProperty().addListener((observable, oldValue, newValue) -> {
        System.out.println("Select All Selected");
        items = EmpTable.getItems();
        for (Employees item : items) {
            item.getBox().setSelected(selectAll.isSelected());
        }
    });

    empList = FXCollections.observableArrayList();

    empID.setCellValueFactory(new PropertyValueFactory<>("id"));
    empName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    empDept.setCellValueFactory(new PropertyValueFactory<>("dept"));
    empPost.setCellValueFactory(new PropertyValueFactory<>("pos"));
    actionTable.setCellValueFactory(new PropertyValueFactory<>("Box"));

    loadDataFromDatabase();
}

    private void updateDurationLabel(Month month, int year) {
        
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String startDate = firstDayOfMonth.format(dateFormatter);
        String endDate = lastDayOfMonth.format(dateFormatter);

        
        durationLabel.setText(startDate + " to " + endDate);
    }

    
    
    
    private void loadDataFromDatabase() {
         if (db == null) {
        System.out.println("Database connection is NULL");
        return;
    }
         
         empList.clear();
         
        String query = "SELECT emp_id, emp_fname, emp_lname, emp_email, emp_hdate, emp_contact, emp_dept, emp_position FROM employee";
        try {
            
            System.out.println("Executing Query: " + query);
            ResultSet rs = db.getData(query);
            
            if (rs == null) {
                System.out.println("ResultSet is null");
                return;
            }

            while (rs.next()) {
                int id = rs.getInt("emp_id");
                String fname = rs.getString("emp_fname");
                String lname = rs.getString("emp_lname");
                String email = rs.getString("emp_email");
                Date hdate = rs.getDate("emp_hdate");
                String contact = rs.getString("emp_contact");
                String dept = rs.getString("emp_dept");
                String position = rs.getString("emp_position");
                

                empList.add(new Employees(id, fname, lname, email, hdate, contact, dept, position));
            }
            
            EmpTable.setItems(empList);
            
           FilteredList<Employees> filteredData = new FilteredList<>(empList, b -> true);

            // Bind the filter field to the search functionality
            FilterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(employee -> {
                    // If filter text is empty, display all employees
                    if (newValue == null || newValue.trim().isEmpty()) {
                        return true;
                    }

                    // Convert to lowercase for case-insensitive search
                    String lowerCaseFilter = newValue.toLowerCase();

                    // Check if any field contains the search string
                    if (String.valueOf(employee.getId()).contains(lowerCaseFilter) ||  // Search by ID
                        employee.getFullName().toLowerCase().contains(lowerCaseFilter) || // Search by Name
                        employee.getDept().toLowerCase().contains(lowerCaseFilter) || // Search by Department
                        employee.getPos().toLowerCase().contains(lowerCaseFilter)) { // Search by Position
                        return true; // Match found
                    }
                    return false; // No match
                });
            });
            
            EmpTable.setItems(filteredData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        
        
        
        
    }



@FXML
private void GenerateSlips(MouseEvent event) {
    config conf = new config();
    dbConnect connect = new dbConnect();
    Connection con = null;
    PreparedStatement checkStmt = null;
    PreparedStatement selectStmt = null;
    PreparedStatement insertStmt = null;

    ObservableList<Employees> selectedEmployees = FXCollections.observableArrayList();
    ObservableList<Employees> items = EmpTable.getItems();

    StringBuilder summary = new StringBuilder(); 

    
    for (Employees emp : items) {
        if (emp.getBox().isSelected()) {
            selectedEmployees.add(emp);
        }
    }

    
    if (selectedEmployees.isEmpty()) {
        conf.showAlert(Alert.AlertType.WARNING, "Warning", "No employees selected. Please select at least one.");
        return;
    }

    
    String selectedMonth = monthBox.getValue();
    String selectedYearString = yearBox.getValue(); 
    if (selectedMonth == null || selectedYearString == null || selectedYearString.isEmpty()) {
        conf.showAlert(Alert.AlertType.ERROR, "Error", "All fields are required.");
        return;
    }

    int selectedYear;
    try {
        selectedYear = Integer.parseInt(selectedYearString);
    } catch (NumberFormatException e) {
        conf.showAlert(Alert.AlertType.ERROR, "Error", "Invalid year format. Please select a valid year.");
        return;
    }

    
    final double SSS_RATE = 0.045;
    final double PHILHEALTH_RATE = 0.025;
    final double PAGIBIG_FIXED = 200;
    String status = "Approved";

    try {
        con = connect.getConnection(); 

        String checkReportQuery = "SELECT * FROM reports WHERE emp_id = ? AND month = ? AND year = ?";
        String selectQuery = "SELECT dtr.employee_id, "
            + "SUM(dtr.hours_worked) AS total_hours, "
            + "SUM(dtr.overtime_hrs) AS total_overtime, "
            + "SUM(dtr.hours_worked) * r.hourly_rate AS gross_salary, "
            + "(SUM(dtr.hours_worked) * r.hourly_rate * ?) AS sss, "
            + "(SUM(dtr.hours_worked) * r.hourly_rate * ?) AS phil_health, "
            + "? AS pagibig, "
            + "SUM(dtr.overtime_hrs) * (r.hourly_rate * 1.5) AS overtime_pay, "
            + "(SUM(dtr.hours_worked) * r.hourly_rate + SUM(dtr.overtime_hrs) * (r.hourly_rate * 1.5)) - "
            + "((SUM(dtr.hours_worked) * r.hourly_rate * ?) + (SUM(dtr.hours_worked) * r.hourly_rate * ?) + ?) AS net_pay "
            + "FROM dailytimerecords dtr "
            + "JOIN employee e ON dtr.employee_id = e.emp_id "
            + "JOIN rates r ON e.rates_id = r.rates_id "
            + "WHERE dtr.employee_id = ? AND dtr.month = ? AND YEAR(dtr.entry_date) = ? "
            + "GROUP BY dtr.employee_id";

        String insertReportQuery = "INSERT INTO reports (emp_id, month, year, total_hours, total_overtime, gross_salary, sss, phil_health, pag_ibig, t_deductions, overtime_pay, net_pay, status, date_generated) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        checkStmt = con.prepareStatement(checkReportQuery);
        selectStmt = con.prepareStatement(selectQuery);
        insertStmt = con.prepareStatement(insertReportQuery);

        
for (Employees emp : selectedEmployees) {
    int empId = emp.getId();

    
    checkStmt.setInt(1, empId);
    checkStmt.setString(2, selectedMonth);
    checkStmt.setInt(3, selectedYear);

    ResultSet rs = checkStmt.executeQuery();
    if (rs.next()) {
        summary.append("Skipped (already exists): Employee ID ").append(empId).append("\n");
        continue; // Skip if report exists
    }

    
    selectStmt.setDouble(1, SSS_RATE);
    selectStmt.setDouble(2, PHILHEALTH_RATE);
    selectStmt.setDouble(3, PAGIBIG_FIXED);
    selectStmt.setDouble(4, SSS_RATE);
    selectStmt.setDouble(5, PHILHEALTH_RATE);
    selectStmt.setDouble(6, PAGIBIG_FIXED);
    selectStmt.setInt(7, empId);
    selectStmt.setString(8, selectedMonth);
    selectStmt.setInt(9, selectedYear);

    ResultSet payrollRs = selectStmt.executeQuery();

    if (payrollRs.next()) {
        double totalHours = payrollRs.getDouble("total_hours");
        double totalOvertime = payrollRs.getDouble("total_overtime");
        double grossSalary = payrollRs.getDouble("gross_salary");
        double sss = payrollRs.getDouble("sss");
        double philhealth = payrollRs.getDouble("phil_health");
        double pagibig = payrollRs.getDouble("pagibig");
        double overtimePay = payrollRs.getDouble("overtime_pay");
        double netPay = payrollRs.getDouble("net_pay");

        double totalDeductions = sss + philhealth + pagibig;

        
        insertStmt.setInt(1, empId);
        insertStmt.setString(2, selectedMonth);
        insertStmt.setInt(3, selectedYear);
        insertStmt.setDouble(4, totalHours);
        insertStmt.setDouble(5, totalOvertime);
        insertStmt.setDouble(6, grossSalary);
        insertStmt.setDouble(7, sss);
        insertStmt.setDouble(8, philhealth);
        insertStmt.setDouble(9, pagibig);
        insertStmt.setDouble(10, totalDeductions);
        insertStmt.setDouble(11, overtimePay);
        insertStmt.setDouble(12, netPay);
        insertStmt.setString(13, status);

        insertStmt.executeUpdate();

        
        summary.append("Generated: Employee ID ").append(empId)
               .append(", Month: ").append(selectedMonth)
               .append(", Year: ").append(selectedYear)
               .append(", Net Pay: ").append(String.format("%.2f", netPay))
               .append(", Timestamp: ").append(java.time.LocalDateTime.now())
               .append("\n");
    } else {
        summary.append("No DTR records found: Employee ID ").append(empId).append("\n");
    }
}

        
        conf.showAlert(Alert.AlertType.INFORMATION, "Success", "Payslips generation completed!");
        summaryArea.setText(summary.toString()); // Update summaryArea with the generated report

    } catch (SQLException e) {
        e.printStackTrace();
        conf.showAlert(Alert.AlertType.ERROR, "Database Error", "Error occurred while generating payslips.");
        summaryArea.setText("Error during payslip generation. See console for details.");
    } finally {
        try {
            if (checkStmt != null) checkStmt.close();
            if (selectStmt != null) selectStmt.close();
            if (insertStmt != null) insertStmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    @FXML
    private void viewReport(MouseEvent event) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SysUI/Admin/ViewReports.fxml"));
            Parent addUserContent = loader.load(); 

            
            ViewReportsController controller = loader.getController();

            
            Scene scene = viewReports.getScene();
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

