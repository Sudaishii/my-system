/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Staff;

import GUI.SysUI.Admin.DetailedReport;
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
public class PDFGENController implements Initializable {
    
    private ObservableList<Reports> reportList;
    @FXML
    private TableView<Reports> reportsTable;
    @FXML
    private TableColumn<Reports, String> r_id;
    @FXML
    private TableColumn<Reports, String> emp_id;
    @FXML
    private TableColumn<Reports, String> monthColumn;
    @FXML
    private TableColumn<Reports, String> yearColumn;
    @FXML
    private TableColumn<Reports, String> totalHoursColumn;
    private TableColumn<Reports, String> netPay;
    @FXML
    private TableColumn<Reports, String> netPayColumn;
    @FXML
    private TableColumn<Reports, String> date_generated;
    @FXML
    private Label summaryMonthYear;
    @FXML
    private Label summaryTotalHours;
    @FXML
    private Label summaryGrossSalary;
    @FXML
    private Label summarySSSContribution;
    @FXML
    private Label summaryPagibigContribution;
    @FXML
    private Label summaryPhilhealthContribution;
    @FXML
    private Label summaryTotalContribution;
    @FXML
    private Label summaryNetPay;
    @FXML
    private TextField FilterField;
    private static final DecimalFormat noDecimalFormat = new DecimalFormat("#");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        r_id.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getReportId())));
        emp_id.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEmpId())));
        monthColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMonth()));
        yearColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getYear())));
       netPayColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
            "₱" + noDecimalFormat.format(cellData.getValue().getNetPay())
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
    
    } 
    
    dbConnect db = new dbConnect();
    
    private void loadReportsFromDatabase() {
    if (db == null) {
        System.out.println("Database connection is NULL");
        return;
    }
    
    

   

    try {
       

        reportList = FXCollections.observableArrayList();

        String query = "SELECT report_id, emp_id, month, year, total_hours, total_overtime, " +
                       "gross_salary, sss, phil_health, pag_ibig, t_deductions, " +
                       "overtime_pay, net_pay, status, date_generated FROM reports";

        System.out.println("Executing Query: " + query);
        ResultSet rs = db.getData(query);

        if (rs == null) {
            System.out.println("ResultSet is null");
            return;
        }

        while (rs.next()) {
            int reportId = rs.getInt("report_id");
            int empId;
            empId = rs.getInt("emp_id");
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
    
    private DetailedReport loadFullReportDetails(int reportId) {
    String sql = "SELECT r.*, e.emp_dept, e.emp_position, e.emp_fname, e.emp_lname " +
                 "FROM reports r " +
                 "LEFT JOIN employee e ON r.emp_id = e.emp_id " +
                 "WHERE r.report_id = ?";

    try (Connection conn = db.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, reportId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                // Extracting report data
                int empId = rs.getInt("emp_id");
                String fname = rs.getString("emp_fname");
                String lname = rs.getString("emp_lname");
                String department = rs.getString("emp_dept");
                String positionStr = rs.getString("emp_position");
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
                LocalDate dateGenerated = rs.getDate("date_generated").toLocalDate();

                // Update summary UI (optional)
                summaryMonthYear.setText(month + " " + year);
                summaryTotalHours.setText(String.valueOf(totalHours));
                summaryGrossSalary.setText(String.format("₱%.2f", grossSalary));
                summaryNetPay.setText(String.format("₱%.2f", netPay));
                summarySSSContribution.setText(String.format("₱%.2f", sss));
                summaryPagibigContribution.setText(String.format("₱%.2f", pagIbig));
                summaryPhilhealthContribution.setText(String.format("₱%.2f", philHealth));
                summaryTotalContribution.setText(String.format("₱%.2f", sss + pagIbig + philHealth));

                return new DetailedReport(reportId, empId, month, year, totalHours, totalOvertime,
                        grossSalary, sss, philHealth, pagIbig, totalDeductions,
                        overtimePay, netPay, status, dateGenerated,
                        fname, lname, department, positionStr);
            } else {
                System.out.println("No report found with report_id: " + reportId);
                clearSummaryLabels();
                return null;
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        clearSummaryLabels();
        return null;
    }
}

     
     private void clearSummaryLabels() {

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

@FXML
private void ExportButton(MouseEvent event) {
    Reports selectedReport = reportsTable.getSelectionModel().getSelectedItem();
    if (selectedReport == null) {
        System.out.println("No report selected for export.");
        return; 
    }

    int empId = selectedReport.getEmpId();
    String month = selectedReport.getMonth();
    int year = selectedReport.getYear();

    boolean success = ThisIsForPDF.generateEmployeeReport(empId, month, year);

    if (success) {
        System.out.println("PDF generated successfully.");
    } else {
        System.out.println("Failed to generate PDF.");
    }
}
    
    
 

    
}
