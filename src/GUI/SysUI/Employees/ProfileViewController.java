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
        // Set up the image view properties
        profileImageView.setFitWidth(IMAGE_SIZE);
        profileImageView.setFitHeight(IMAGE_SIZE);
        profileImageView.setPreserveRatio(true);
        
        // Create a circular clip
        Circle clip = new Circle(IMAGE_SIZE/2, IMAGE_SIZE/2, IMAGE_SIZE/2);
        profileImageView.setClip(clip);
        
        // Set up the change photo button action
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
            // Load the image
            Image image = new Image(imageFile.toURI().toString());
            
            // Calculate the scaling factor to ensure the image fills the circle
            double scale = Math.max(
                IMAGE_SIZE / image.getWidth(),
                IMAGE_SIZE / image.getHeight()
            );
            
            // Set the image with proper scaling
            profileImageView.setImage(image);
            profileImageView.setFitWidth(image.getWidth() * scale);
            profileImageView.setFitHeight(image.getHeight() * scale);
            
            // Center the image
            profileImageView.setX((IMAGE_SIZE - profileImageView.getFitWidth()) / 2);
            profileImageView.setY((IMAGE_SIZE - profileImageView.getFitHeight()) / 2);
            
        } catch (Exception e) {
            e.printStackTrace();
            // Handle error (you might want to show an alert here)
        }
    }
} 