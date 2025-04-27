package GUI.SysUI.Employees;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ProfileViewController {
    @FXML private ImageView profileImageView;
    @FXML private Button changePhotoButton;
    
    private static final double IMAGE_SIZE = 150.0;
    @FXML
    private Label nameLabel;
    @FXML
    private Label employeeIdLabel;
    @FXML
    private Label departmentLabel;
    @FXML
    private Label positionLabel;
    @FXML
    private Button editButton;
    @FXML
    private Button saveButton;
    
    public void initialize() {
      
        profileImageView.setFitWidth(IMAGE_SIZE);
        profileImageView.setFitHeight(IMAGE_SIZE);
        profileImageView.setPreserveRatio(true);
        
 
        Circle clip = new Circle(IMAGE_SIZE/2, IMAGE_SIZE/2, IMAGE_SIZE/2);
        profileImageView.setClip(clip);
        
        changePhotoButton.setOnAction(event -> handleChangePhoto());
    }
    
    private void handleChangePhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        
        File selectedFile = fileChooser.showOpenDialog(changePhotoButton.getScene().getWindow());
        if (selectedFile != null) {
            loadImage(selectedFile);
        }
    }
    
    private void loadImage(File imageFile) {
        try {
           
            Image image = new Image(imageFile.toURI().toString());
            
         
            double scale = Math.max(
                IMAGE_SIZE / image.getWidth(),
                IMAGE_SIZE / image.getHeight()
            );
            
          
            profileImageView.setImage(image);
            profileImageView.setFitWidth(image.getWidth() * scale);
            profileImageView.setFitHeight(image.getHeight() * scale);
            

            profileImageView.setX((IMAGE_SIZE - profileImageView.getFitWidth()) / 2);
            profileImageView.setY((IMAGE_SIZE - profileImageView.getFitHeight()) / 2);
            
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
} 