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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class PayslipController implements Initializable {
    
    
    
   
    @FXML
    private TableView<Employees> EmpTable;
    @FXML
    private TableColumn<Employees, String> empPost;
    @FXML
    private TableColumn<Employees, String> empID;
    @FXML
    private TableColumn<Employees, String> empName;
    @FXML
    private TableColumn<Employees, String> empDept;
    @FXML
    private TableColumn<Employees, String> actionTable;
    private dbConnect db = new dbConnect();
     private ObservableList<Employees> empList;
    @FXML
    private CheckBox selectAll;
    ObservableList<Employees> items;
    ObservableList<Employees> searchby = FXCollections.observableArrayList();
    @FXML
    private TextField FilterField;
    @FXML
    private ComboBox<String> monthBox;
    private ObservableList<String> months = FXCollections.observableArrayList(
               "January", "February", "March", "April", "May", "June",
               "July", "August", "September", "October", "November", "December"
       );
    @FXML
    private Label durationLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         monthBox.setItems(months);

        int currentMonthIndex = LocalDate.now().getMonthValue() - 1; // Month is 1-based
        monthBox.getSelectionModel().select(currentMonthIndex);

        // Initialize the duration label with the current month
        updateDurationLabel(LocalDate.now().getMonth());

        // Add a listener to the ComboBox to update the label when the selection changes
        monthBox.setOnAction(event -> {
            String selectedMonth = monthBox.getValue();
            if (selectedMonth != null) {
                Month month = Month.valueOf(selectedMonth.toUpperCase());
                updateDurationLabel(month);
            }
        });
        
        
     
        
        selectAll.selectedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            System.out.println("Select All Selected");

            items = EmpTable.getItems();

            for (Employees item : items) {
                if (selectAll.isSelected()) {
                    item.getBox().setSelected(true);
                } else {
                    item.getBox().setSelected(false); 
                }
            }
        }
    });
        
        
        empList = FXCollections.observableArrayList();
        
        empID.setCellValueFactory(new PropertyValueFactory<>("id"));
        empName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        empDept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        empPost.setCellValueFactory(new PropertyValueFactory<>("pos")); 
        actionTable.setCellValueFactory(new PropertyValueFactory<>("Box"));
        
        
   

       
        monthBox.setItems(months);

       
        
        
       loadDataFromDatabase();
    }    
    
    private void updateDurationLabel(Month month) {
        LocalDate now = LocalDate.now();
        int year = now.getYear(); // Use the current year for simplicity
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String startDate = firstDayOfMonth.format(dateFormatter);
        String endDate = lastDayOfMonth.format(dateFormatter);

        durationLabel.setText(startDate + " to " + endDate);
    }
    
    
    
    private void loadDataFromDatabase() {
         if (db == null) {
        System.out.println("Database connection is NULL");
        return;
    }
         
         empList.clear();
         
        String query = "SELECT emp_id, emp_fname, emp_lname, emp_email, emp_hdate, emp_contact, emp_dept, emp_position FROM employee";
        try {
            
            System.out.println("Executing Query: " + query);
            ResultSet rs = db.getData(query);
            
            if (rs == null) {
                System.out.println("ResultSet is null");
                return;
            }

            while (rs.next()) {
                int id = rs.getInt("emp_id");
                String fname = rs.getString("emp_fname");
                String lname = rs.getString("emp_lname");
                String email = rs.getString("emp_email");
                Date hdate = rs.getDate("emp_hdate");
                String contact = rs.getString("emp_contact");
                String dept = rs.getString("emp_dept");
                String position = rs.getString("emp_position");
                

                empList.add(new Employees(id, fname, lname, email, hdate, contact, dept, position));
            }
            
            EmpTable.setItems(empList);
            
           FilteredList<Employees> filteredData = new FilteredList<>(empList, b -> true);

            // Bind the filter field to the search functionality
            FilterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(employee -> {
                    // If filter text is empty, display all employees
                    if (newValue == null || newValue.trim().isEmpty()) {
                        return true;
                    }

                    // Convert to lowercase for case-insensitive search
                    String lowerCaseFilter = newValue.toLowerCase();

                    // Check if any field contains the search string
                    if (String.valueOf(employee.getId()).contains(lowerCaseFilter) ||  // Search by ID
                        employee.getFullName().toLowerCase().contains(lowerCaseFilter) || // Search by Name
                        employee.getDept().toLowerCase().contains(lowerCaseFilter) || // Search by Department
                        employee.getPos().toLowerCase().contains(lowerCaseFilter)) { // Search by Position
                        return true; // Match found
                    }
                    return false; // No match
                });
            });
            
            EmpTable.setItems(filteredData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        
        
        
        
    }

   @FXML
private void GenerateSlips(MouseEvent event) {
    config conf = new config();
    
    ObservableList<Employees> selectedEmployees = FXCollections.observableArrayList();
    ObservableList<Employees> items = EmpTable.getItems();
    
    // Store selected employee IDs
    StringBuilder selectedIDs = new StringBuilder();
    
    for (Employees emp : items) {
        if (emp.getBox().isSelected()) { 
            selectedEmployees.add(emp);
            selectedIDs.append(emp.getId()).append(", "); 
            System.out.println(selectedIDs);
        }
    }

   
    if (selectedEmployees.isEmpty()) {
        conf.showAlert(Alert.AlertType.WARNING, "Warning", "No employees selected. Please select at least one.");
        return;
    }

   
    if (selectedIDs.length() > 0) {
        selectedIDs.setLength(selectedIDs.length() - 2);
    }

    
    conf.showAlert(Alert.AlertType.INFORMATION, "Selected Employees", "Employee IDs: " + selectedIDs.toString());

   
}
}
