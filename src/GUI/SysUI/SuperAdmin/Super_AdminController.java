/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.SuperAdmin;

import GUI.SysUI.Admin.HR_AdminController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Super_AdminController implements Initializable {
    
    
    private static Super_AdminController instance;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private BorderPane rootPane;

   
    
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
    
}
