/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.SuperAdmin;

import GUI.SysUI.Admin.Employees;
import GUI.config.config;
import GUI.config.dbConnect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class SU_UserManagementController implements Initializable {
    
    private ObservableList<User> usersUpdate;
    
    private dbConnect db = new dbConnect();
    
    
    @FXML
    private TableView<User> UserView1;
    @FXML
    private TableColumn<User, String> idC1;
    @FXML
    private TableColumn<User, String> usernC1;
    @FXML
    private TableColumn<User, String> emailC1;
    @FXML
    private TableColumn<User, String> statusC1;
    @FXML
    private ComboBox<User> filterView1;
    @FXML
    private Button assignBtn;
    @FXML
    private Button addBtn;
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        usersUpdate = FXCollections.observableArrayList();
        
      
        
        idC1.setCellValueFactory(new PropertyValueFactory<>("id"));       
        usernC1.setCellValueFactory(new PropertyValueFactory<>("username"));  
        emailC1.setCellValueFactory(new PropertyValueFactory<>("email"));   
        statusC1.setCellValueFactory(new PropertyValueFactory<>("status")); 
        
        
       
        updateUsers();
        
    }    

  
    
    
    
    
  private void updateUsers() {
         if (db == null) {
        System.out.println("Database connection is NULL");
        return;
    }
         
         usersUpdate.clear();
         
        String query = "SELECT user_id, user_name, user_email, status FROM users";
        try {
            
            System.out.println("Executing Query: " + query);
            ResultSet rs = db.getData(query);
            
            if (rs == null) {
                System.out.println("ResultSet is null");
                return;
            }

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String username = rs.getString("user_name");
                String email = rs.getString("user_email");
                String status = rs.getString("status");
              
                

                usersUpdate.add(new User(id, username, email, status));
            }
            

            UserView1.setItems(usersUpdate);
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
       
        
    }

    @FXML
    private void refreshButton(MouseEvent event) {
        
    
    updateUsers();
        
    }
    
    
    
    config configUtil = new config(); 
    
    @FXML
private void activateBtn(MouseEvent event) {
    User selectedUser = UserView1.getSelectionModel().getSelectedItem();

    if (selectedUser != null) {
        String currentStatus = selectedUser.getStatus();

        if ("Active".equalsIgnoreCase(currentStatus)) {
            configUtil.showAlert(Alert.AlertType.WARNING, "Already Active", "Selected user is already active.");
            return; // Exit here to prevent further execution
        }

        if ("Newly Registered".equalsIgnoreCase(currentStatus)) {
            try (Connection conn = db.getConnection()) {
                if (conn == null) {
                    configUtil.showAlert(Alert.AlertType.ERROR, "Database Error", "Database connection failed.");
                    return;
                }

                String sql = "UPDATE users SET status = 'Active' WHERE user_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, selectedUser.getId());
                pstmt.executeUpdate();

                updateUsers(); // ✅ Ensure this method correctly refreshes the table

                configUtil.showAlert(Alert.AlertType.INFORMATION, "Success", "User activated successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
                configUtil.showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to update user status.");
            }
        } else {
            configUtil.showAlert(Alert.AlertType.WARNING, "Invalid Status", "Selected user is not in 'Newly Registered' status.");
        }
    } else {
        configUtil.showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a user from the table.");
    }
}


    @FXML
    private void addUser(MouseEvent event) throws IOException {
        
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SysUI/SuperAdmin/UserAdd.fxml"));
            Parent addUserContent = loader.load(); 

            
            UserAddController controller = loader.getController();

            
            Scene scene = addBtn.getScene();
            Pane rootPane = (Pane) scene.getRoot();

            AnchorPane overlayPane = new AnchorPane(addUserContent); 
            overlayPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

      
            overlayPane.prefWidthProperty().bind(scene.widthProperty());
            overlayPane.prefHeightProperty().bind(scene.heightProperty());


            controller.setOverlayPane(overlayPane); 
            controller.setRootPane(rootPane);

   
            rootPane.getChildren().add(overlayPane);

     
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), overlayPane); 
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();

            
            overlayPane.setOnMouseClicked(e -> {
                if (e.getTarget() == overlayPane) {
                    FadeTransition fadeOut = new FadeTransition(Duration.millis(300), overlayPane);
                    fadeOut.setFromValue(1);
                    fadeOut.setToValue(0);
                    fadeOut.setOnFinished(ev -> rootPane.getChildren().remove(overlayPane));
                    fadeOut.play();
                }
            });
    }
            
    
    
}