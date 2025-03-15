package GUI.SysUI.Admin;

import GUI.SysUI.SuperAdmin.users;
import GUI.config.config;
import GUI.config.dbConnect;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class HR_EmployeeManagementController implements Initializable {

    @FXML
    private ComboBox<String> deptcombo1;

    private HashMap<String, List<String>> departmentPositions = new HashMap<>();

    @FXML
    private ComboBox<String> sexcombo1;
    @FXML
    private ComboBox<String> poscombo1;
    @FXML
    private TextField Tfname;
    @FXML
    private TextField Tmname;
    @FXML
    private TextField Tlname;
    @FXML
    private TextField Tadd;
    @FXML
    private TextField Temail;
    @FXML
    private TextField Tage;
    @FXML
    private TextField Tcontact;
    @FXML
    private DatePicker Tdate;
    @FXML
    private Button submit;
    private ObservableList<Employees> empList;
    @FXML
    private TableView<Employees> EmployeeView;
    
    
    private dbConnect db = new dbConnect();
    
    
    
    @FXML
    private TableColumn<Employees, String> idC;
    @FXML
    private TableColumn<Employees, String> fnameC;
    @FXML
    private TableColumn<Employees, String> lnameC;
    @FXML
    private TableColumn<Employees, String> emailC;
    @FXML
    private TableColumn<Employees, Date> hdateC;
    @FXML
    private TableColumn<Employees, String> contactC;
    @FXML
    private TableColumn<Employees, String> deptC;
    @FXML
    private TableColumn<Employees, String> postC;
    @FXML
    private ComboBox<String> filterView1;
    @FXML
    private ImageView refreshButton;
    @FXML
    private Button update;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        empList = FXCollections.observableArrayList();
        
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));       
        fnameC.setCellValueFactory(new PropertyValueFactory<>("fname"));  
        lnameC.setCellValueFactory(new PropertyValueFactory<>("lname"));   
        emailC.setCellValueFactory(new PropertyValueFactory<>("email")); 
        hdateC.setCellValueFactory(new PropertyValueFactory<>("hdate")); 
        contactC.setCellValueFactory(new PropertyValueFactory<>("contact")); 
        deptC.setCellValueFactory(new PropertyValueFactory<>("dept")); 
        postC.setCellValueFactory(new PropertyValueFactory<>("pos")); 
        
        departmentPositions.put("Human Resources", Arrays.asList("General Manager", "HR Manager", "Finance Manager", "Finance Clerk"));
        departmentPositions.put("Front Office", Arrays.asList("Front Office Manager",  "Receptionist", "Porter", "Reservation Clerk"));
        departmentPositions.put("House Keeping", Arrays.asList("Executive Housekeeper", "Housekeeping Supervisor", "Room Attendant", "Public Area Cleaner"));
        departmentPositions.put("Maintenance", Arrays.asList("Chief Engineer", "Maintenance Supervisor", "Maintenance Technician", "Groundskeeper"));
        departmentPositions.put("IT", Arrays.asList("IT Manager", "IT Support Specialist", "Network Administrator", "System Administrator"));

        ObservableList<String> departments = FXCollections.observableArrayList();
        departments.add("Choose Department");
        departments.addAll(departmentPositions.keySet());
        deptcombo1.setItems(departments);
        deptcombo1.setValue("Choose Department");

        
        ObservableList<String> sexOptions = FXCollections.observableArrayList();
        sexOptions.add("Choose Sex");
        sexOptions.addAll(Arrays.asList("Male", "Female"));
        sexcombo1.setItems(sexOptions);
        sexcombo1.setValue("Choose Sex");

      
        ObservableList<String> positionOptions = FXCollections.observableArrayList();
        positionOptions.add("Choose Position");
        poscombo1.setItems(positionOptions);
        poscombo1.setValue("Choose Position");

      
        deptcombo1.setOnAction(event -> {
            String selectedDepartment = deptcombo1.getValue();
            if (!"Choose Department".equals(selectedDepartment)) {
                List<String> positions = departmentPositions.getOrDefault(selectedDepartment, Arrays.asList());

                ObservableList<String> positionOptionsWithPrompt = FXCollections.observableArrayList();
                positionOptionsWithPrompt.add("Choose Position");
                positionOptionsWithPrompt.addAll(positions);

                poscombo1.setItems(positionOptionsWithPrompt);
                poscombo1.setValue("Choose Position");
            } else {
                ObservableList<String> positionOptionsWithPrompt = FXCollections.observableArrayList();
                positionOptionsWithPrompt.add("Choose Position");
                poscombo1.setItems(positionOptionsWithPrompt);
                poscombo1.setValue("Choose Position");
            }
        });
           
        loadDataFromDatabase();

    }

    @FXML
    private void AddEmployee(MouseEvent event) {
        
        config conf = new config();
        
            String fname = Tfname.getText().trim();
            String mname = Tmname.getText().trim();
            String lname = Tlname.getText().trim();
            String address = Tadd.getText().trim();
            String email = Temail.getText().trim();
            String age = Tage.getText().trim();
            String sex = sexcombo1.getValue().trim();
            String dept = deptcombo1.getValue().trim();
            String pos = poscombo1.getValue().trim();
            String contact = Tcontact.getText().trim();
            String date = Tdate.getValue().toString();
        
        String sql = "INSERT INTO employee (emp_fname, emp_middle, emp_lname, emp_age, emp_sex, emp_add, emp_email, emp_contact, emp_hdate, emp_dept, emp_position) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        conf.addRecord(sql, fname, mname, lname, age, sex, address, email, contact, date, dept, pos);
        conf.showAlert(Alert.AlertType.INFORMATION, "Successful", "Employee added successfully!");
        
        clearFields();
        
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
            

            EmployeeView.setItems(empList);
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        
        
        
        
    }
     
     
     private void clearFields() {
         
            Tfname.clear();
            Tmname.clear();
            Tlname.clear();
            Tadd.clear();
            Temail.clear();
            Tage.clear();
            Tcontact.clear();
            Tdate.setValue(null);

            // Reset combo boxes to default values
            sexcombo1.setValue("Choose Sex");
            deptcombo1.setValue("Choose Department");
            poscombo1.setValue("Choose Position");
}

    @FXML
    private void refreshButton(MouseEvent event) {
    }

    @FXML
    private void updateBtn(MouseEvent event) {
    }
    
}