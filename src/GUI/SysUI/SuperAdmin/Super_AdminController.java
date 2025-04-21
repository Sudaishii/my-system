/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.SuperAdmin;

import GUI.SysUI.Admin.HR_AdminController;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Super_AdminController implements Initializable {
    
    
    private static Super_AdminController instance;
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
                            loadPageUser("/GUI/SysUI/SuperAdmin/SU_Dashboard.fxml");
                            break;
                        case "Manage Users":
                            loadPageUser("/GUI/SysUI/SuperAdmin/SU_UserManagement.fxml");
                            break;
                        case "EventLogs":
                            loadPageUser("/GUI/SysUI/SuperAdmin/System.fxml");
                            break;
                        case "Scalability":
                            loadPageUser("/GUI/SysUI/SuperAdmin/Scalability.fxml");
                            break;
                        case "Profile":
                            loadPageUser("/GUI/SysUI/univ/Profile.fxml");
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
                    
                    
                    drawer.setOnDrawerClosed(event -> {
                        drawer.setVisible(false);
                        drawer.setManaged(false);
                    });
                    
                } else {
                    
                    drawer.setVisible(true);
                    drawer.setManaged(true);
                    drawer.open();
                }
            });
            
            
            
            
            loadPageUser("/GUI/SysUI/SuperAdmin/SU_Dashboard.fxml");
            instance = this;
        } catch (IOException ex) {
            Logger.getLogger(Super_AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
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


    private void dashboard(MouseEvent event) {
       loadPageUser("/GUI/SysUI/SuperAdmin/SU_Dashboard.fxml");
    }

    private void manageusers(MouseEvent event) {
         loadPageUser("/GUI/SysUI/SuperAdmin/SU_UserManagement.fxml");
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

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
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

    private void manageLogs(MouseEvent event) {
         loadPageUser("/GUI/SysUI/SuperAdmin/System.fxml");
    }
    
    
}
