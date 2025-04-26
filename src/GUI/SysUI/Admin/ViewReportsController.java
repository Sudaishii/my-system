package GUI.SysUI.Admin;

import GUI.config.dbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import javafx.scene.shape.Line;

public class ViewReportsController implements Initializable {

    @FXML
    private AnchorPane overlayPane;
    @FXML
    private Label name;
    @FXML
    private TableColumn<Reports, String> emp_id;
    @FXML
    private Label dept;
    @FXML
    private Label position;
    @FXML
    private Label month;
    @FXML
    private Label year;
    @FXML
    private Label hours_worked;
    @FXML
    private Label ovtime;
    @FXML
    private Label g_pay;
    @FXML
    private Label contribution;
    @FXML
    private Label ovTIme;
    @FXML
    private Label net_pay;
    @FXML
    private Pane rootPane;
    @FXML
    private TableView<Reports> ReportsView;
    @FXML
    private TableColumn<Reports, String> r_id;
    @FXML
    private TableColumn<Reports, String> monthC;
    @FXML
    private TableColumn<Reports, String> yearC;
    @FXML
    private TableColumn<Reports, String> net_payC;
    @FXML
    private TextField FilterField;
    private static final DecimalFormat twoDecimalFormat = new DecimalFormat("#0.00");


    private ObservableList<Reports> reportList;
    private dbConnect db = new dbConnect();


    @FXML
    private Label emp_idL;
    @FXML
    private TableColumn<Reports, String> date_generated;
    @FXML
    private Label summary2;
    @FXML
    private Label summary21;
    @FXML
    private Label summary211;
    @FXML
    private Line line1;
    @FXML
    private Line line2;
    @FXML
    private Line line3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (overlayPane != null) {
            overlayPane.setOnMouseClicked(e -> {
                if (e.getPickResult().getIntersectedNode() == overlayPane) {
                    closeOverlay();
                }
            });
        }
        
        name.setVisible(false);
        dept.setVisible(false);
        position.setVisible(false);
        month.setVisible(false);
        year.setVisible(false);
        hours_worked.setVisible(false);
        ovtime.setVisible(false);
        g_pay.setVisible(false);
        contribution.setVisible(false);
        ovTIme.setVisible(false);
        net_pay.setVisible(false);
        emp_idL.setVisible(false);
        line1.setVisible(false);
        line2.setVisible(false);
        line3.setVisible(false);

        r_id.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getReportId())));
        emp_id.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEmpId())));
        monthC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMonth()));
        yearC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getYear())));
        net_payC.setCellValueFactory(cellData -> {
            double netPay = cellData.getValue().getNetPay();
            String formattedNetPay = "₱" + twoDecimalFormat.format(netPay);
            return new SimpleStringProperty(formattedNetPay);
        });
        date_generated.setCellValueFactory(cellData -> {
            LocalDate date = cellData.getValue().getDateGenerated();
            if (date != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
                return new SimpleStringProperty(date.format(formatter));
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        loadReportsFromDatabase();
        
        
        ReportsView.setOnMouseClicked(event -> {
        Reports selectedReport = ReportsView.getSelectionModel().getSelectedItem();
        if (selectedReport != null) {
            int reportId = selectedReport.getReportId();
            loadFullReportDetails(reportId);
        }
    });
    }

    private void closeOverlay() {
        if (overlayPane != null && rootPane != null) {
            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), overlayPane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);

            fadeOut.setOnFinished(ev -> {
                rootPane.getChildren().remove(overlayPane);
                rootPane.setMouseTransparent(false);
            });

            fadeOut.play();
        }
    }
    
   private void loadFullReportDetails(int reportId) {
    String sql = "SELECT r.*, e.emp_dept, e.emp_position, e.emp_fname, e.emp_lname " +
             "FROM employee e " +
             "LEFT JOIN reports r ON e.emp_id = r.emp_id " +
             "WHERE r.report_id = ?";
    
    try (Connection conn = db.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, reportId);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            String fname = rs.getString("emp_fname");
            String lname = rs.getString("emp_lname");
            int empId = rs.getInt("emp_id");
            String department = rs.getString("emp_dept");
            String positionStr = rs.getString("emp_position");

            emp_idL.setText("Employee ID: " + empId);
            name.setText(fname + " " + lname);
            dept.setText("Department: " + (department != null ? department : "N/A"));
            position.setText("Position: " + (positionStr != null ? positionStr : "N/A"));
            month.setText("Month: " + rs.getString("month"));
            year.setText("Year: " + rs.getInt("year"));
            hours_worked.setText("Total Hours: " + rs.getInt("total_hours"));
            ovtime.setText("Overtime Hours: " + rs.getInt("total_overtime"));
            g_pay.setText("Gross Pay: ₱" + twoDecimalFormat.format(rs.getDouble("gross_salary")));
            contribution.setText("Total Deductions: ₱" + twoDecimalFormat.format(rs.getDouble("t_deductions")));
            ovTIme.setText("Overtime Pay: ₱" + twoDecimalFormat.format(rs.getDouble("overtime_pay")));
            net_pay.setText("Net Pay: ₱" + twoDecimalFormat.format(rs.getDouble("net_pay")));

            summary2.setVisible(false);
            summary21.setVisible(false);
            summary211.setVisible(false);
            name.setVisible(true);
            dept.setVisible(true);
            position.setVisible(true);
            month.setVisible(true);
            year.setVisible(true);
            hours_worked.setVisible(true);
            ovtime.setVisible(true);
            g_pay.setVisible(true);
            contribution.setVisible(true);
            ovTIme.setVisible(true);
            net_pay.setVisible(true);
            emp_idL.setVisible(true);
            line1.setVisible(true);
            line2.setVisible(true);
            line3.setVisible(true);

        } else {
            System.out.println("No report found with report_id: " + reportId);
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    
    public void setOverlayPane(AnchorPane overlayPane) {
        this.overlayPane = overlayPane;
    }

    public void setRootPane(Pane rootPane) {
        this.rootPane = rootPane;
    }

    @FXML
    private void goBack(MouseEvent event) {
        closeOverlay();
    }

    private void loadReportsFromDatabase() {
        
       
        if (db == null) {
            System.out.println("Database connection is NULL");
            return;
        }

        reportList = FXCollections.observableArrayList();

        String query = "SELECT report_id, emp_id, month, year, total_hours, total_overtime, " +
                       "gross_salary, sss, phil_health, pag_ibig, t_deductions, " +
                       "overtime_pay, net_pay, status, date_generated FROM reports";
        try {
            System.out.println("Executing Query: " + query);
            ResultSet rs = db.getData(query);

            if (rs == null) {
                System.out.println("ResultSet is null");
                return;
            }

            while (rs.next()) {
                int reportId = rs.getInt("report_id");
                int empId = rs.getInt("emp_id");
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

                // Handle date_generated
                LocalDate dateGenerated = null;
                String dateGeneratedStr = rs.getString("date_generated");
                if (dateGeneratedStr != null && !dateGeneratedStr.equals("0000-00-00 00:00:00")) {
                    try {
                        dateGenerated = Timestamp.valueOf(dateGeneratedStr).toLocalDateTime().toLocalDate();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid date format for report_id " + reportId + ": " + dateGeneratedStr);
                    }
                } else {
                    System.out.println("Invalid date for report_id " + reportId + ": " + dateGeneratedStr);
                }

                reportList.add(new Reports(
                    reportId, empId, month, year, totalHours, totalOvertime,
                    grossSalary, sss, philHealth, pagIbig, totalDeductions,
                    overtimePay, netPay, status, dateGenerated
                ));
            }

            ReportsView.setItems(reportList);

            FilteredList<Reports> filteredData = new FilteredList<>(reportList, b -> true);

            FilterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(report -> {
                    if (newValue == null || newValue.trim().isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    return String.valueOf(report.getReportId()).contains(lowerCaseFilter) ||
                           String.valueOf(report.getEmpId()).contains(lowerCaseFilter) ||
                           report.getMonth().toLowerCase().contains(lowerCaseFilter) ||
                           String.valueOf(report.getYear()).contains(lowerCaseFilter) ||
                           twoDecimalFormat.format(report.getNetPay()).contains(lowerCaseFilter);
                });
            });

            ReportsView.setItems(filteredData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void RefreshSummary(MouseEvent event) {
        
        summary2.setVisible(true);
        summary21.setVisible(true);
        summary211.setVisible(true);
        name.setVisible(false);
        dept.setVisible(false);
        position.setVisible(false);
        month.setVisible(false);
        year.setVisible(false);
        hours_worked.setVisible(false);
        ovtime.setVisible(false);
        g_pay.setVisible(false);
        contribution.setVisible(false);
        ovTIme.setVisible(false);
        net_pay.setVisible(false);
        emp_idL.setVisible(false);
        line1.setVisible(false);
        line2.setVisible(false);
        line3.setVisible(false);

        
    }
}
