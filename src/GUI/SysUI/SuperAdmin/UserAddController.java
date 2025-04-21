/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.SuperAdmin;

import GUI.config.config;
import GUI.config.dbConnect;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class UserAddController implements Initializable {

    @FXML
    private TextField unF;
    @FXML
    private TextField emF;
    @FXML
    private PasswordField passF;
    @FXML
    private AnchorPane overlayPane;
    @FXML
    private Pane rootPane;
     
    dbConnect db = new dbConnect();
    config con = new config();
    @FXML
    private ImageView closeBtn;
    @FXML
    private ComboBox<String> roles;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            if (overlayPane != null) {
                   overlayPane.setOnMouseClicked(e -> {
                       if (e.getPickResult().getIntersectedNode() == overlayPane) {
                           closeOverlay();
                       }
                   });
               }
            
            roles.getItems().addAll(
                    "SuperAdmin",
                    "HR_Admin",
                    "Employee"
                );
            
    }    
    
       


    
      private boolean validateInputs(String email, String username, String password) {
        String errorMessage = null;

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            errorMessage = "All fields are required.";
        } else if (password.length() < 8) {
            errorMessage = "Password must be at least 8 characters long.";
            
        } 
        
        else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) { 
            System.out.println("Email being checked: " + email);
            errorMessage = "Please enter a valid email address.";
        }

        if (errorMessage != null) {
            con.showAlert(Alert.AlertType.ERROR, "Validation Error", errorMessage); 
            return false;
        }

        return true; 
}
    private boolean isDuplicate(String column, String value) throws SQLException {
      
        
        String sql = "SELECT 1 FROM users WHERE " + column + " = ?";
        try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
            pst.setString(1, value);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        }
    }
    
 
   

   
   

    private void clearFields() {
     
        unF.clear();
        passF.clear();
        emF.clear();
        roles.getSelectionModel().clearSelection();
        
    }

    @FXML
    private void createBtn(MouseEvent event) {
        
        String username = unF.getText().trim();
        String email = emF.getText().trim();
        String password = passF.getText().trim();
        String selectedRole = roles.getSelectionModel().getSelectedItem();
        Integer roleId = null;
        
        if (!validateInputs(email, username, password)) {
            return;
        }

            try {
      
        List<String> errors = new ArrayList<>();

       
        if (isDuplicate("user_email", email)) {
            errors.add("This email is already registered.");
        }

     
        if (isDuplicate("user_name", username)) {
            errors.add("This username is already taken.");
        }
         
        if (selectedRole == null || selectedRole.isEmpty()) {
            con.showAlert(Alert.AlertType.ERROR, "Validation Error", "Please select a role.");
            return;
        }
  
        if (!errors.isEmpty()) {
            String errorMessage = String.join("\n", errors); // Combine errors into a single message
            con.showAlert(Alert.AlertType.ERROR, "Registration Error", errorMessage);
            return; 
        }
        
        switch (selectedRole) {
            case "SuperAdmin":
                roleId = 1;
                break;
            case "HR_Admin":
                roleId = 2;
                break;
            case "Employee":
                roleId = 3;
                break;
            default:
                con.showAlert(Alert.AlertType.ERROR, "Validation Error", "Invalid role selected.");
                return;
        }
       
       String sql = "INSERT INTO users (user_email, user_name, user_pass, status, role_id) VALUES (?, ?, ?, ?, ?)";



        try (PreparedStatement pst = db.getConnection().prepareStatement(sql)) {
            pst.setString(1, email);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setString(4, "Active");
            pst.setInt(5, roleId);  
            pst.executeUpdate();
            con.showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "You have successfully registered!");
            clearFields();
        }
    } catch (SQLException ex) {
        con.showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred: " + ex.getMessage());
    }
        
        
    }
    
    public void setOverlayPane(AnchorPane overlayPane) {
        this.overlayPane = overlayPane;
    }

    public void setRootPane(Pane rootPane) {
        this.rootPane = rootPane;
    }

    @FXML
    private void closeBtn(MouseEvent event) {
        closeOverlay();
    }
    

        
     private void closeOverlay() {
         
        System.out.println("overlayPane: " + overlayPane);
        System.out.println("rootPane: " + rootPane);
           if (overlayPane != null && rootPane != null) {
                    FadeTransition fadeOut = new FadeTransition(Duration.millis(300), overlayPane); 
                    fadeOut.setFromValue(1);
                    fadeOut.setToValue(0);
                    fadeOut.setOnFinished(ev -> rootPane.getChildren().remove(overlayPane));
                    fadeOut.play();
                }
        }
}
