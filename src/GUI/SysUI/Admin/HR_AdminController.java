/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Admin;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
    @FXML
    private JFXHamburger burger;
    @FXML
    private JFXDrawer drawer;
    
    @Override
public void initialize(URL url, ResourceBundle rb) {
    try {
        VBox box = FXMLLoader.load(getClass().getResource("slidetab.fxml"));
        
        for (Node node : box.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    switch (node.getAccessibleText()) {
                        case "Dashboard":
                            loadPage("/GUI/SysUI/Admin/HR_Dashboard.fxml");
                            break;
                        case "Manage Employees":
                            loadPage("/GUI/SysUI/Admin/HR_EmployeeManagement.fxml");
                            break;
                        case "Time":
                            loadPage("/GUI/SysUI/Admin/HR_TimeManagement.fxml");
                            break;
                        case "Payslip":
                            loadPage("/GUI/SysUI/Admin/HR_Payslip.fxml");
                            break;
                        case "Profile":
                            loadPage("/GUI/SysUI/univ/Profile.fxml");
                            break;
                        case "Logout":
                            logout(new ActionEvent());
                            break;
                        default:
                            System.out.println("Unknown action: " + node.getAccessibleText());
                    }
                });
            }
        }

        drawer.setSidePane(box);

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(burger);
        transition.setRate(-1);
        burger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isOpened()) {
            drawer.close();

            // Delay hiding to allow animation to finish
            drawer.setOnDrawerClosed(event -> {
                drawer.setVisible(false);
                drawer.setManaged(false);
            });

        } else {
            // Ensure the drawer is visible before opening
            drawer.setVisible(true);
            drawer.setManaged(true);
            drawer.open();
        }
        });

        loadPage("/GUI/SysUI/Admin/HR_Dashboard.fxml"); // Default page load
        instance = this;
    } catch (IOException ex) {
        Logger.getLogger(HR_AdminController.class.getName()).log(Level.SEVERE, null, ex);
    }
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
   
   private void employee(MouseEvent event) {
        
        loadPage("/GUI/SysUI/Admin/HR_EmployeeManagement.fxml");
        
    }

    private void dahboard(MouseEvent event) {
        
        loadPage("/GUI/SysUI/Admin/HR_Dashboard.fxml");
        
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

                    
                    stage.centerOnScreen();

                    stage.show();
                } catch (IOException e) {
                    System.err.println("Error loading login page.");
                    e.printStackTrace();
                }
            }
        
    }
    
    
    
    
    
    
}
