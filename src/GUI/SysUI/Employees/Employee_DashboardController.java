package GUI.SysUI.Employees;

import GUI.config.Session;
import GUI.config.config;
import GUI.config.dbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Employee_DashboardController implements Initializable {

    @FXML
    private TextField empIdField;
    @FXML
    private Button bindIdButton;
    private config conf;
    private dbConnect db;
    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conf = new config();  
        db = new dbConnect();
        Session ses = new Session();
        String username = Session.getInstance().getUsername();

    }    

    @FXML
    private void BindButton(MouseEvent event) {
        String empId = empIdField.getText().trim();

        if (empId.isEmpty()) {
            conf.showAlert(Alert.AlertType.WARNING, "Access Denied", "Please bind your account first.");
            return;
        }

        if (empId.matches("[A-Za-z]+")) {
            conf.showAlert(Alert.AlertType.WARNING, "Invalid ID", "The ID should not contain letters.");
            return;
        }

        if (empId.contains(" ") || !empId.matches("\\d+")) {
            conf.showAlert(Alert.AlertType.WARNING, "Invalid ID", "The ID should not contain spaces or non-numeric");
            return;
        }

        int dbEmpId = getEmpIdByUsername(empId);  

        if (dbEmpId == 0) {
            conf.showAlert(Alert.AlertType.WARNING, "Invalid ID", "The employee ID does not exist in the system.");
        } else {
            System.out.println("ID entered: " + empId);

            boolean isInserted = insertEmpIdToDatabase(empId);

            if (isInserted) {
                logoutUser();
            } else {
                conf.showAlert(Alert.AlertType.ERROR, "Insertion Failed", "Could not bind the employee ID.");
            }
        }
    }

    private boolean insertEmpIdToDatabase(String empId) {
    try (Connection conn = db.getConnection()) {
        String query = "UPDATE users SET emp_id = ? WHERE user_name = ?";  
        String username = Session.getInstance().getUsername();  
        
        System.out.println("Executing query: " + query);  
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, Integer.parseInt(empId));  
            pstmt.setString(2, username);  
            
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);  
            return rowsUpdated > 0;
        }
    } catch (SQLException | NumberFormatException e) {
        e.printStackTrace();
    }
    return false;
}



    private int getEmpIdByUsername(String empId) {
        int empIdFromDb = 0;  

        try (Connection conn = db.getConnection()) {
            String query = "SELECT emp_id FROM employee WHERE emp_id = ?";  
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, Integer.parseInt(empId));  
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        empIdFromDb = rs.getInt("emp_id");  
                    }
                }
            }
        } catch (SQLException e) {
        }
        System.out.println("ID" + empIdFromDb);
        return empIdFromDb; 
    }

   private void logoutUser() {
  
    conf.showAlert(Alert.AlertType.INFORMATION, "Account Bound", 
                   "Your account has been successfully bound. You will be logged out to load your reports.");


    try {
        Stage stage = (Stage) rootPane.getScene().getWindow(); 
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/SysUI/LogIn/login.fxml"));
        Scene scene = new Scene(root, 899, 547);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    } catch (IOException e) {
        System.err.println("Error loading login page: " + e.getMessage());
        e.printStackTrace();
    }
}


}