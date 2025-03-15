/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    
    
}
