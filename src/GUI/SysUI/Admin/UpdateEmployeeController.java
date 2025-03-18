/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Admin;

import GUI.config.config;
import GUI.config.dbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class UpdateEmployeeController implements Initializable {

    @FXML
    private AnchorPane overlayPane;
    @FXML
    private Pane rootPane;
    @FXML
    private TextField fname;
    @FXML
    private TextField addF;
    @FXML
    private TextField emailF;
    @FXML
    private TextField contactF;
    @FXML
    private ComboBox<?> deptF;
    @FXML
    private ComboBox<?> posF;
    @FXML
    private Button submit;
    private String employeeId;
    @FXML
    private ComboBox<?> empBox;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 
               
    }  
    
    config con = new config();
    dbConnect db = new dbConnect();
    
      
    
    public void setOverlayPane(AnchorPane overlayPane) {
        this.overlayPane = overlayPane;
    }

    public void setRootPane(Pane rootPane) {
        this.rootPane = rootPane;
    }
    
         private void closeOverlay() {
         
        System.out.println("overlayPane: " + overlayPane);
        System.out.println("rootPane: " + rootPane);
           if (overlayPane != null && rootPane != null) {
                    FadeTransition fadeOut = new FadeTransition(Duration.millis(300), overlayPane); // Target overlayPane directly
                    fadeOut.setFromValue(1);
                    fadeOut.setToValue(0);
                    fadeOut.setOnFinished(ev -> rootPane.getChildren().remove(overlayPane));
                    fadeOut.play();
                }
        }
    
     
         
     public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
       
        System.out.println("Employee ID received in UpdateEmployeeController: " + employeeId);
       
    }    
         
         
    @FXML
    private void closeBtn(MouseEvent event) {
        closeOverlay();
    }

    

    @FXML
    private void AddEmployee(MouseEvent event) {
    }

  
   
}
