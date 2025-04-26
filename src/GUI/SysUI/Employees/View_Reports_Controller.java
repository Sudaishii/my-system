/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Employees;

import GUI.SysUI.Admin.DTRModel;
import GUI.SysUI.Admin.Reports;
import GUI.config.Session;
import GUI.config.dbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class View_Reports_Controller implements Initializable {
    @FXML
    private Label employeeNameLabel;
    @FXML
    private Label employeeDepartmentLabel;
    @FXML
    private Label employeePositionLabel;
    @FXML
    private Label employeeRateLabel;
    @FXML
    private TableView<Reports> reportsTable;
    @FXML
    private TableColumn<Reports, String> monthColumn;
    @FXML
    private TableColumn<Reports, String> yearColumn;
    @FXML
    private TableColumn<Reports, String> totalHoursColumn;
    @FXML
    private TableColumn<Reports, String> netPayColumn;
    @FXML
    private Label summaryMonthYear;
    @FXML
    private Label summaryTotalHours;
    @FXML
    private Label summaryGrossSalary;
    @FXML
    private Label summaryNetPay;
    private Label summaryStatus;
     private ObservableList<Reports> reportList;
     private dbConnect db = new dbConnect();
    @FXML
    private TableColumn<Reports, String> r_id;
    @FXML
    private Label summarySSSContribution;
    @FXML
    private Label summaryPagibigContribution;
    @FXML
    private Label summaryPhilhealthContribution;
    @FXML
    private Label summaryTotalContribution;
    @FXML
    private TextField FilterField;
      private static final DecimalFormat noDecimalFormat = new DecimalFormat("#");
    @FXML
    private TableColumn<Reports, String> date_generated;
    @FXML
    private TableColumn<Reports, String> netPay;
    private int reportId;
    @FXML
    private TableColumn<?, ?> emp_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       String username = Session.getInstance().getUsername();
    
        r_id.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getReportId())));
        monthColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMonth()));
        yearColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getYear())));
        netPay.setCellValueFactory(cellData -> new SimpleStringProperty(
        noDecimalFormat.format(cellData.getValue().getNetPay())
        ));
        totalHoursColumn.setCellValueFactory(cellData -> 
         new SimpleStringProperty(String.valueOf(cellData.getValue().getTotalHours()))
            );
        date_generated.setCellValueFactory(cellData -> {
            LocalDate date = cellData.getValue().getDateGenerated();
            if (date != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
                return new SimpleStringProperty(date.format(formatter));
            } else {
                return new SimpleStringProperty("N/A");
            }
        });
        
      
        
        
        reportsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            
            Reports selectedDTR = newValue;

            
            int selectedRecordId = selectedDTR.getReportId();

            
             loadFullReportDetails(selectedRecordId);;
        }
    });

        
        loadReportsFromDatabase();
        loadEmployeeInfo();
        
    }


    private void loadEmployeeInfo() {
        
    String username = Session.getInstance().getUsername();
    if (username == null || username.isEmpty()) {
        System.out.println("No active user session");
        return;
    }
        
    if (db == null) {
        System.out.println("Database connection is NULL");
        return;
    }

    String query = "SELECT e.emp_fname, e.emp_lname, e.emp_dept, r.position, r.hourly_rate " +
                   "FROM employee e " +
                   "JOIN users u ON e.emp_id = u.emp_id " +
                   "JOIN rates r ON e.rates_id = r.rates_id " +
                   "WHERE u.user_name = ?";

    try {
        PreparedStatement stmt = db.getConnection().prepareStatement(query);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs != null && rs.next()) {
            String fname = rs.getString("emp_fname");
            String lname = rs.getString("emp_lname");
            String department = rs.getString("emp_dept");
            String position = rs.getString("position");
            double hourlyRate = rs.getDouble("hourly_rate");

           
            String fullName = (fname != null ? fname : "") + " " + (lname != null ? lname : "");

     
            employeeNameLabel.setText(fullName.trim());
            employeeDepartmentLabel.setText(department != null ? department : "N/A");
            employeePositionLabel.setText(position != null ? position : "N/A");
            employeeRateLabel.setText(String.format("₱%.2f", hourlyRate));

        } else {
            employeeNameLabel.setText("Employee Not Found");
            employeeDepartmentLabel.setText("N/A");
            employeePositionLabel.setText("N/A");
            employeeRateLabel.setText("N/A");
        }

        rs.close();
        stmt.close();

    } catch (SQLException e) {
        e.printStackTrace();
        employeeNameLabel.setText("Error");
        employeeDepartmentLabel.setText("Error");
        employeePositionLabel.setText("Error");
        employeeRateLabel.setText("Error");
    }
}



    
    private void loadReportsFromDatabase() {
    if (db == null) {
        System.out.println("Database connection is NULL");
        return;
    }
    
    

    String username = Session.getInstance().getUsername();
    int empId = -1;

    try {
        ResultSet userRs = db.getData("SELECT emp_id FROM users WHERE user_name = '" + username + "'");
        if (userRs != null && userRs.next()) {
            empId = userRs.getInt("emp_id");
        } else {
            System.out.println("No emp_id found for username: " + username);
            return;
        }

        reportList = FXCollections.observableArrayList();

        String query = "SELECT report_id, emp_id, month, year, total_hours, total_overtime, " +
                       "gross_salary, sss, phil_health, pag_ibig, t_deductions, " +
                       "overtime_pay, net_pay, status, date_generated FROM reports WHERE emp_id = " + empId;

        System.out.println("Executing Query: " + query);
        ResultSet rs = db.getData(query);

        if (rs == null) {
            System.out.println("ResultSet is null");
            return;
        }

        while (rs.next()) {
            int reportId = rs.getInt("report_id");
            String month = rs.getString("month");
            int year = rs.getInt("year");
            int totalHours = rs.getInt("total_hours");
            int totalOvertime = rs.getInt("total_overtime");
            double grossSalary = rs.getDouble("gross_salary");
            double sss = rs.getDouble("sss");
            double philHealth = rs.getDouble("phil_health");
            double pagIbig = rs.getDouble("pag_ibig");
            double totalDeductions = rs.getDouble("t_deductions");
            double overtimePay = rs.getDouble("overtime_pay");
            double netPay = rs.getDouble("net_pay");
            String status = rs.getString("status");

            LocalDate dateGenerated = null;
            String dateGeneratedStr = rs.getString("date_generated");
            if (dateGeneratedStr != null && !dateGeneratedStr.equals("0000-00-00 00:00:00")) {
                try {
                    dateGenerated = Timestamp.valueOf(dateGeneratedStr).toLocalDateTime().toLocalDate();
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid date format for report_id " + reportId + ": " + dateGeneratedStr);
                }
            }

            reportList.add(new Reports(
                reportId, empId, month, year, totalHours, totalOvertime,
                grossSalary, sss, philHealth, pagIbig, totalDeductions,
                overtimePay, netPay, status, dateGenerated
            ));
        }

        reportsTable.setItems(reportList);

        FilteredList<Reports> filteredData = new FilteredList<>(reportList, b -> true);

        FilterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(report -> {
                if (newValue == null || newValue.trim().isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
                String monthStr = report.getDateGenerated().getMonth().toString().toLowerCase(); 

                return String.valueOf(report.getReportId()).contains(lowerCaseFilter) ||
                       report.getMonth().toLowerCase().contains(lowerCaseFilter) ||
                       String.valueOf(report.getYear()).contains(lowerCaseFilter) ||
                       noDecimalFormat.format(report.getNetPay()).contains(lowerCaseFilter) ||
                       report.getDateGenerated().format(formatter).toLowerCase().contains(lowerCaseFilter) ||  
                       monthStr.contains(lowerCaseFilter); 
            });
        });

        reportsTable.setItems(filteredData);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    private void loadFullReportDetails(int reportId) {
    String sql = "SELECT r.*, e.emp_dept, e.emp_position, e.emp_fname, e.emp_lname, r.sss, r.phil_health, r.pag_ibig, r.t_deductions, r.overtime_pay " +
                 "FROM reports r " +
                 "LEFT JOIN employee e ON r.emp_id = e.emp_id " +
                 "WHERE r.report_id = ?";
    
    try (Connection conn = db.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, reportId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                // Extracting report data
                String fname = rs.getString("emp_fname");
                String lname = rs.getString("emp_lname");
                int empId = rs.getInt("emp_id");
                String department = rs.getString("emp_dept");
                String positionStr = rs.getString("emp_position");

                double grossSalary = rs.getDouble("gross_salary");
                double sss = rs.getDouble("sss");
                double philHealth = rs.getDouble("phil_health");
                double pagIbig = rs.getDouble("pag_ibig");
                double totalDeductions = rs.getDouble("t_deductions");
                double overtimePay = rs.getDouble("overtime_pay");
                double netPay = rs.getDouble("net_pay");
                String status = rs.getString("status");
                String month = rs.getString("month");
                int year = rs.getInt("year");
                int totalHours = rs.getInt("total_hours");

                // Updating summary labels
                summaryMonthYear.setText(month + " " + year);
                summaryTotalHours.setText(String.valueOf(totalHours));
                summaryGrossSalary.setText(String.format("₱%.2f", grossSalary));
                summaryNetPay.setText(String.format("₱%.2f", netPay));

                summarySSSContribution.setText(String.format("₱%.2f", sss));
                summaryPagibigContribution.setText(String.format("₱%.2f", pagIbig));
                summaryPhilhealthContribution.setText(String.format("₱%.2f", philHealth));
                summaryTotalContribution.setText(String.format("₱%.2f", sss + pagIbig + philHealth));

                


            } else {
                System.out.println("No report found with report_id: " + reportId);
                clearSummaryLabels();  
            }
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
        clearSummaryLabels();  
    }
}


private void clearSummaryLabels() {
    // Reset the summary labels to default values
    summaryMonthYear.setText("");
    summaryTotalHours.setText("");
    summaryGrossSalary.setText("");
    summaryNetPay.setText("");
    summarySSSContribution.setText("");
    summaryPagibigContribution.setText("");
    summaryPhilhealthContribution.setText("");
    summaryTotalContribution.setText("");
   
    

}

    @FXML
    private void refreshIcon(MouseEvent event) {
        loadReportsFromDatabase();
        clearSummaryLabels();
    }

    

  
  
  

    
    
}
