/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.SuperAdmin;

import GUI.SysUI.Admin.HR_AdminController;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Super_AdminController implements Initializable {
    
    
    private static Super_AdminController instance;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private BorderPane rootPane;
    @FXML
    private Label LabelNi;
    @FXML
    private ImageView tEmp;
    @FXML
    private Label TotalEmp;
    @FXML
    private Label npay;
    @FXML
    private Label grss;
    @FXML
    private Label contributions;

   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          loadPageUser("/GUI/SysUI/SuperAdmin/SU_Dashboard.fxml");
          instance = this;
    }    
    
    
     public void loadPageUser(String targetFXML) {
            try {
                rootPane.setCenter(null); 
                Parent root = FXMLLoader.load(getClass().getResource(targetFXML));
                rootPane.setCenter(root);
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        }


    @FXML
    private void dashboard(MouseEvent event) {
       loadPageUser("/GUI/SysUI/SuperAdmin/SU_Dashboard.fxml");
    }

    @FXML
    private void manageusers(MouseEvent event) {
         loadPageUser("/GUI/SysUI/SuperAdmin/SU_UserManagement.fxml");
    }

    @FXML
    private void manageroles(MouseEvent event) {
        
    }

    @FXML
    private void profile(ActionEvent event) {
         loadPageUser("/GUI/SysUI/univ/Profile.fxml");
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

                    
                    stage.centerOnScreen();

                    stage.show();
                } catch (IOException e) {
                    System.err.println("Error loading login page.");
                    e.printStackTrace();
                }
            }
        
    }

    @FXML
    private void manageLogs(MouseEvent event) {
         loadPageUser("/GUI/SysUI/SuperAdmin/System.fxml");
    }
    
    
}
