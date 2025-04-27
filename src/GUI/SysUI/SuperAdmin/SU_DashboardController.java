package GUI.SysUI.SuperAdmin;

import GUI.SysUI.Admin.Employees;
import GUI.config.Session;
import GUI.config.dbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SU_DashboardController implements Initializable {

    @FXML
    private ImageView tEmp;
    @FXML
    private Label TotalEmp;
    @FXML
    private Label TActive;
    @FXML
    private Label TNewly;
    @FXML
    private Label numTUsers;

    private Connection conn;

    private dbConnect con;

    @FXML
    private BarChart<String, Number> EmployeeBarChart;
    private PieChart UserStatusPieChart;
    @FXML
    private PieChart UserStatusPieChar;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con = new dbConnect();
            Session ses = Session.getInstance();
            String uname = ses.getUsername();
            conn = con.getConnection();


            if (EmployeeBarChart != null) {
                loadEmployeeBarChart();
            }
            
            if (UserStatusPieChar != null) {
                loadUserStatusPieChart();
            }


            loadDashboardData();
        } catch (SQLException ex) {
            Logger.getLogger(SU_DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  

    private void loadDashboardData() {
        setTotalEmployees();
        setTotalActiveUsers();
        setTotalNewlyRegistered();
        setTotalUsers();
    }

    private void setTotalEmployees() {
        String sql = "SELECT COUNT(*) FROM employee";
        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                TotalEmp.setText(String.valueOf(count));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTotalActiveUsers() {
        String sql = "SELECT COUNT(*) FROM users WHERE status = 'Active'";
        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                TActive.setText(String.valueOf(count));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTotalNewlyRegistered() {
        String sql = "SELECT COUNT(*) FROM users WHERE status = 'Newly Registered'";
        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                TNewly.setText(String.valueOf(count));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTotalUsers() {
        String sql = "SELECT COUNT(*) FROM users";
        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                numTUsers.setText(String.valueOf(count));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEmployeeBarChart() {
        String sql = "SELECT DATE_FORMAT(emp_hdate, '%Y-%m') AS month, COUNT(*) AS total " +
                     "FROM employee " +
                     "GROUP BY month " +
                     "ORDER BY month";

        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("New Employees");

            while (rs.next()) {
                String month = rs.getString("month");
                int count = rs.getInt("total");
                series.getData().add(new XYChart.Data<>(month, count));
            }

            EmployeeBarChart.getData().add(series);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadUserStatusPieChart() {
        if (UserStatusPieChar == null) {
            System.out.println("Warning: UserStatusPieChar is null");
            return;
        }

        String sql = "SELECT status, COUNT(*) AS total FROM users GROUP BY status";
        try (PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {


            UserStatusPieChar.getData().clear();


            int totalUsers = 0;
            while (rs.next()) {
                totalUsers += rs.getInt("total");
            }


            rs.beforeFirst();


            String[] pieColors = {
                "#3498db",  
                "#2ecc71",  
                "#e74c3c",  
                "#f1c40f",  
                "#9b59b6"  
            };


            int colorIndex = 0;
            while (rs.next()) {
                String status = rs.getString("status");
                int count = rs.getInt("total");
                double percentage = (count * 100.0) / totalUsers;
                

                String label = String.format("%s (%.1f%%)", status, percentage);
                
                PieChart.Data slice = new PieChart.Data(label, count);
                UserStatusPieChar.getData().add(slice);
                

                slice.getNode().getStyleClass().add("data" + colorIndex);
                
                colorIndex++;
            }


            UserStatusPieChar.setTitle("User Status Distribution");
            UserStatusPieChar.setLegendVisible(true);
            UserStatusPieChar.setLabelsVisible(true);
            
   
            UserStatusPieChar.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-pie-label-visible: true;" +
                "-fx-pie-label-font-size: 12px;" +
                "-fx-pie-label-font-weight: bold;" +
                "-fx-pie-label-fill: white;"
            );


            UserStatusPieChar.getStyleClass().add("pie-chart-legend");
            if (UserStatusPieChar.lookup(".chart-legend") != null) {
                UserStatusPieChar.lookup(".chart-legend").setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-padding: 10px;" +
                    "-fx-font-size: 12px;"
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
