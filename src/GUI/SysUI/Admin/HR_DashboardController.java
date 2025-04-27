/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Admin;

import GUI.config.Session;
import GUI.config.dbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;


public class HR_DashboardController implements Initializable {
    
    
    @FXML
    private Label payment;
    private ObservableList<String> months = FXCollections.observableArrayList(
               "January", "February", "March", "April", "May", "June",
               "July", "August", "September", "October", "November", "December"
       );
    
//    @FXML
//    private ComboBox<String> deptcombo;
    @FXML
    private Label date;
//    @FXML
//    private ComboBox<String> monthCombo;
    @FXML
    private Label contributions;
    @FXML
    private Label npay;
    @FXML
    private ImageView tEmp;
    @FXML
    private Label TotalEmp;
    @FXML
    private Label grss;
    @FXML
    private Label greet;
    @FXML
    private StackedBarChart<String , Number> barChart;
    @FXML
    private ComboBox<String> year;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int currentYear = LocalDate.now().getYear();
        ObservableList<String> years = FXCollections.observableArrayList();
        for (int i = currentYear; i >= currentYear - 10; i--) {
            years.add(String.valueOf(i));
        }
        year.setItems(years);
        year.getSelectionModel().select(String.valueOf(currentYear));  

   
        year.setOnAction(e -> {
            String selectedYear = year.getSelectionModel().getSelectedItem();
            if (selectedYear != null) {
                loadPaymentData(selectedYear);
                loadTotals(selectedYear);
            }
        });

        loadChartData();
        
        String currentSelectedYear = year.getSelectionModel().getSelectedItem();
        if (currentSelectedYear != null) {
            loadPaymentData(currentSelectedYear);
            loadTotals(currentSelectedYear);
        }

        greet.setVisible(false);


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Manila")); 
        int yearNow = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; 

        LocalDate firstDay = LocalDate.of(yearNow, month, 1);
        int lastDayOfMonth = YearMonth.of(yearNow, month).lengthOfMonth();
        LocalDate lastDay = LocalDate.of(yearNow, month, lastDayOfMonth);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.US);
        String formattedFirstDay = firstDay.format(formatter);
        String formattedLastDay = lastDay.format(formatter);

        if (date != null) {
            date.setText(formattedFirstDay + " - " + formattedLastDay);
        }

       
        Session ses = Session.getInstance();
        String uname = ses.getUsername();
        greet.setText("Welcome, " + uname + "!");
    }

    
    @FXML
    private void loadPaymentData(String selectedYear) {
        String query = "SELECT SUM(net_pay) AS totalNetPay FROM reports WHERE year = ?";

        try (Connection conn = new dbConnect().getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, selectedYear);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    double totalNetPay = rs.getDouble("totalNetPay");

                 
                    payment.setText(String.format("₱ %.2f", totalNetPay));
                } else {
                  
                    payment.setText("0.00");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void loadTotals(String selectedYear) {
    String queryNetPay = "SELECT SUM(net_pay) AS totalNetPay FROM reports WHERE year = ?";
    String queryGrossSalary = "SELECT SUM(gross_salary) AS totalGrossSalary FROM reports WHERE year = ?";
    String queryContributions = "SELECT SUM(t_deductions) AS totalContributions FROM reports WHERE year = ?";
    String queryEmployees = "SELECT COUNT(*) AS totalEmployees FROM employee";

    try (Connection conn = new dbConnect().getConnection()) {
        // Net Pay
        try (PreparedStatement pst = conn.prepareStatement(queryNetPay)) {
            pst.setString(1, selectedYear);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    double netPay = rs.getDouble("totalNetPay");
                    npay.setText(String.format("₱ %.2f", netPay));
                }
            }
        }


        try (PreparedStatement pst = conn.prepareStatement(queryGrossSalary)) {
            pst.setString(1, selectedYear);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    double grossSalary = rs.getDouble("totalGrossSalary");
                    grss.setText(String.format("₱ %.2f", grossSalary));
                }
            }
        }


        try (PreparedStatement pst = conn.prepareStatement(queryContributions)) {
            pst.setString(1, selectedYear);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    double contrib = rs.getDouble("totalContributions");
                    contributions.setText(String.format("₱ %.2f", contrib));
                }
            }
        }


        try (PreparedStatement pst = conn.prepareStatement(queryEmployees);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                int totalEmp = rs.getInt("totalEmployees");
                TotalEmp.setText(String.valueOf(totalEmp));
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    private void loadChartData() {
    XYChart.Series<String, Number> employeeSeries = new XYChart.Series<>();
    employeeSeries.setName("Employees"); 

    String query = "SELECT emp_dept, COUNT(*) AS count FROM employee GROUP BY emp_dept";

    try (Connection conn = new dbConnect().getConnection();
         PreparedStatement pst = conn.prepareStatement(query);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            String department = rs.getString("emp_dept");
            int count = rs.getInt("count");

            XYChart.Data<String, Number> data = new XYChart.Data<>(department, count);
            employeeSeries.getData().add(data);

          
            Tooltip tooltip = new Tooltip("Employees: " + count);
            Tooltip.install(data.getNode(), tooltip);
        }

        barChart.getData().clear();
        barChart.getData().add(employeeSeries);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    private Map<String, String> getUserDetails(String username) throws SQLException {
    Map<String, String> userDetails = new HashMap<>();

    
    String query = "SELECT role, status, email FROM users WHERE username = ?";
    

    try (Connection connect = new dbConnect().getConnection();
         PreparedStatement pst = connect.prepareStatement(query)) {
        
        pst.setString(1, username); 
        
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                
                userDetails.put("role", rs.getString("role"));
                userDetails.put("status", rs.getString("status"));
                userDetails.put("email", rs.getString("email"));
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        throw ex; 
    }
    
    return userDetails;
}
}
