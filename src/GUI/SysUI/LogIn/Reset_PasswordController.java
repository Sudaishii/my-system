/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.LogIn;

import GUI.config.ResetSession;
import GUI.config.Session;
import GUI.config.config;
import GUI.config.dbConnect;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class Reset_PasswordController implements Initializable {

    @FXML
    private Rectangle sendOTP;
    @FXML
    private TextField empField;
    @FXML
    private TextField newPassField;
    @FXML
    private TextField confPass;
    @FXML
    private Label Username;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ResetSession rses = new ResetSession();
        String email = ResetSession.getInstance().getEmail();
        empField.setText(email);
        String username = getUsernameByEmail(email); 
        if (username != null) {
            Username.setText(username); 
        } else {
            Username.setText("Unknown User");
        }

        }    

        config con = new config();

    @FXML
    private void cancelBtn(MouseEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = (Stage) empField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen(); 
        } catch (IOException e) {
            con.showAlert(Alert.AlertType.ERROR, "Failed to load login screen:" , e.getMessage());
           
        }
        
    }
    
   private String getUsernameByEmail(String email) {
    String username = null;
    String query = "SELECT user_name FROM users WHERE user_email = ?";
    try (Connection connection = db.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery(); 

        if (resultSet.next()) {
            username = resultSet.getString("user_name");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        con.showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to fetch username.");
    }
    return username;
}


    @FXML
    private void confirmBtn(MouseEvent event) {
        
    String newPassword = newPassField.getText().trim();
    String confirmPassword = confPass.getText().trim();
    String email = ResetSession.getInstance().getEmail();

    if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
        con.showAlert(Alert.AlertType.WARNING, "Warning", "All fields are required.");
        return;
    }

    if (!newPassword.equals(confirmPassword)) {
        con.showAlert(Alert.AlertType.WARNING, "Warning", "New passwords do not match.");
        return;
    }

    if (newPassword.length() < 8) {
        con.showAlert(Alert.AlertType.WARNING, "Weak Password", "New password must be at least 8 characters long.");
        return;
    }

    try {

        updatePassword(email, newPassword); 
        con.showAlert(Alert.AlertType.INFORMATION, "Success", "Password has been reset successfully.");
        newPassField.clear();
        confPass.clear();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = (Stage) empField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen(); 
        } catch (IOException e) {
            con.showAlert(Alert.AlertType.ERROR, "Failed to load login screen:" , e.getMessage());
           
        }
        
   
    } catch (Exception e) {
        e.printStackTrace();
        con.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while resetting the password.");
    }
        
    }
    
    dbConnect db = new dbConnect();
    private void updatePassword(String email, String newPassword) throws NoSuchAlgorithmException {
    String query = "UPDATE users SET user_pass = ? WHERE user_email = ?";

    try (Connection connection = db.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        preparedStatement.setString(1, con.hashPassword(newPassword)); 
        preparedStatement.setString(2, email);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
           
            newPassField.clear();
            confPass.clear();
        } else {
            con.showAlert(Alert.AlertType.ERROR, "Error", "Failed to reset password.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        con.showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to reset password.");
    }
}

    
}
