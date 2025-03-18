/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.SysUI.univ;

import GUI.config.Session;
import GUI.config.config;
import GUI.config.dbConnect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class ProfileController implements Initializable {

    @FXML
    private Label ProfName;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField eField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Session ses = Session.getInstance();
       String uname = Session.getInstance().getUname();
        uname = ses.getUname();
        ProfName.setText(uname);
        
        fetchUserDetails(uname);
        
    }    
    
    config con = new config();
    dbConnect db = new dbConnect();
    
    private void fetchUserDetails(String username) {
    String query = "SELECT user_email FROM users WHERE user_name = ?"; 
    dbConnect dbConnection = new dbConnect(); 

    try (Connection connection = dbConnection.getConnection(); 
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String email = resultSet.getString("user_email"); 
            eField.setText(email); 
            emailLabel.setText(email);
        } else {
            
            con.showAlert(Alert.AlertType.ERROR, "Error", "Could not retrieve user details.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        con.showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to fetch user details.");
    }
}

    
    
}
