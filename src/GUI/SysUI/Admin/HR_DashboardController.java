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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class HR_DashboardController implements Initializable {

    @FXML
    private Label payment;
    @FXML
    private ComboBox<String> deptcombo;
    @FXML
    private Label date;
    @FXML
    private ComboBox<String> monthCombo;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ObservableList<String> months = FXCollections.observableArrayList(
            "    Overall", "    January", "    February", "    March", "    April", "    May", "    June",
            "    July", "    August", "    September", "    October", "    November", "    December"
        );
       
       ObservableList<String> departments = FXCollections.observableArrayList(
               "    Overall", "    Human Resources", "    Front Office", "    House Keeping", "    Maintenance", "    IT"
       );
       
        deptcombo.setItems(departments);
        deptcombo.setPromptText("    Select Department");
        
        monthCombo.setItems(months);
        monthCombo.setPromptText("    Select Month");
        
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Manila")); 

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

      
        LocalDate firstDay = LocalDate.of(year, month, 1);
        int lastDayOfMonth = YearMonth.of(year, month).lengthOfMonth();
        LocalDate lastDay = LocalDate.of(year, month, lastDayOfMonth);

       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.US);
        String formattedFirstDay = firstDay.format(formatter);
        String formattedLastDay = lastDay.format(formatter);

     

        
        if (date != null) {
            date.setText(formattedFirstDay + " - " + formattedLastDay);
        }
        
       Session ses = Session.getInstance();
        
        String uname = Session.getInstance().getUname();
        uname = ses.getUname();
        greet.setText(uname);
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
