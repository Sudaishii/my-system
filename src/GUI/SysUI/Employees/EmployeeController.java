/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.Employees;

import GUI.SysUI.Admin.HR_AdminController;
import GUI.SysUI.SuperAdmin.User;
import GUI.config.Session;
import GUI.config.config;
import GUI.config.dbConnect;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class EmployeeController implements Initializable {
    
    private static EmployeeController instance;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private BorderPane rootPane;
    @FXML
    private JFXHamburger burger;
    @FXML
    private JFXDrawer drawer;
    private User currentUser;
    @FXML
    private VBox vboxBox;
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        config con = new config();
        
        try {

        Session session = Session.getInstance();
        String username = session.getUsername();  
        int empId = getEmpIdByUsername(username);
        
            System.out.println("Employee ID: "+ empId);
        
        VBox box;
        if (empId != 0) {
            
            box = FXMLLoader.load(getClass().getResource("slidetab.fxml"));
            }
         else {
           
            box = FXMLLoader.load(getClass().getResource("slidetab.fxml"));
        }

        for (Node node : box.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                    switch (node.getAccessibleText()) {
                        case "Dashboard":
                            if (empId == 0) {
                                vboxBox.setVisible(false);
                                loadPage("/GUI/SysUI/Employees/Employee_Dashboard.fxml");
                            } else {
                                vboxBox.setVisible(false);
                                loadPage("/GUI/SysUI/Employees/Employee_Dashboard_1.fxml");
                            }
                            break;
                        case "DTR_REPORTS":
                            if (empId != 0) {
                                vboxBox.setVisible(false);
                                loadPage("/GUI/SysUI/Employees/View_DailyTimeRecords.fxml");
                            } else {
                                con.showAlert(Alert.AlertType.WARNING, "Access Denied", "Please bind your account first.");
                            }
                            break;
                        case "SlipReports":
                            if (empId != 0) {
                                vboxBox.setVisible(false);
                                 loadPage("/GUI/SysUI/Employees/View_Reports.fxml");
                            } else {
                                con.showAlert(Alert.AlertType.WARNING, "Access Denied", "Please bind your account first.");
                            }
                            break;
                        case "Settings":
                           
                             if (empId != 0) {
                                vboxBox.setVisible(false);
                                  loadPage("/GUI/SysUI/univ/Profile.fxml");
                            } else {
                                con.showAlert(Alert.AlertType.WARNING, "Access Denied", "Please bind your account first.");
                            }
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
        
    } catch (IOException ex) {
        Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
    dbConnect con = new dbConnect();
    
    private int getEmpIdByUsername(String username) {
    int empId = 0;
    try (Connection conn = con.getConnection()) {
        String query = "SELECT emp_id FROM users WHERE user_name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    empId = rs.getInt("emp_id");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return empId;
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
    
//    private User getUserByUsername(String username) {
//        try (Connection conn = DBConnection.getConnection()) {
//            String query = "SELECT * FROM users WHERE username = ?";
//            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
//                pstmt.setString(1, username);
//                try (ResultSet rs = pstmt.executeQuery()) {
//                    if (rs.next()) {
//                        return new User(
//                            rs.getInt("user_id"),
//                            rs.getString("username"),
//                            rs.getString("password"),
//                            rs.getInt("emp_id"),
//                            rs.getString("role")
//                        );
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    
    
    
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
    private void profile(ActionEvent event) {
    }

   public void showLogoutConfirmationAlert(Node node) {
    Stage currentStage = (Stage) node.getScene().getWindow();
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setHeaderText("Logout Confirmation");
    alert.setContentText("Are you sure you want to logout?");
    alert.initOwner(currentStage);

    DialogPane dialogPane = alert.getDialogPane();
 
    
    String loginFXML = "/GUI/SysUI/LogIn/login.fxml"; 

    alert.showAndWait().ifPresent(response -> {
        if (response == ButtonType.OK) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource(loginFXML));
                Stage stage = (Stage) node.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    });
}

    
    public static EmployeeController getInstance() {
        return instance;
    }
}
