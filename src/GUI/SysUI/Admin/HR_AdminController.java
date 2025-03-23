/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class HR_AdminController implements Initializable {

    private static HR_AdminController instance;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private BorderPane rootPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           loadPage("/GUI/SysUI/Admin/HR_Dashboard.fxml");
           instance = this;
    }    
    
        public void loadPage(String targetFXML) {
            try {
                rootPane.setCenter(null); 
                Parent root = FXMLLoader.load(getClass().getResource(targetFXML));
                rootPane.setCenter(root);
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        }

    public void loadPageMini (String targetFXML) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(targetFXML));
        rootPane.setCenter(root);
        
    }
   
   @FXML
   private void employee(MouseEvent event) {
        
        loadPage("/GUI/SysUI/Admin/HR_EmployeeManagement.fxml");
        
    }

    @FXML
    private void dahboard(MouseEvent event) {
        
        loadPage("/GUI/SysUI/Admin/HR_Dashboard.fxml");
        
    }

    @FXML
    private void time(MouseEvent event) {
    }

    @FXML
    private void payslip(MouseEvent event) {
    }

    

    @FXML
    private void profile(ActionEvent event) {
         loadPage("/GUI/SysUI/univ/Profile.fxml");
    }

    @FXML
    private void logout(ActionEvent event) {
        
         
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout Confirmation");
            alert.setHeaderText("Are you sure you want to logout?");
            alert.setContentText("Unsaved progress may be lost.");

            if (alert.showAndWait().get() == ButtonType.OK) {
                try {
                    Parent loginRoot = FXMLLoader.load(getClass().getResource("/GUI/SysUI/Login/login.fxml"));
                    Scene loginScene = new Scene(loginRoot);

                    Stage stage = (Stage) rootPane.getScene().getWindow();
                    stage.setScene(loginScene);
                    stage.setTitle("Login - PayFuse");
                    stage.setResizable(false);
                    stage.sizeToScene();

                    // Center the stage
                    stage.centerOnScreen();

                    stage.show();
                } catch (IOException e) {
                    System.err.println("Error loading login page.");
                    e.printStackTrace();
                }
            }
        
    }
    
    
    
    
    
    
}
