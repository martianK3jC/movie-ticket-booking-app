package com.application.loginapp;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;

public class LoginController {

    @FXML
    private ImageView checkImage;

    @FXML
    private Text dontHaveAnAccountText;

    @FXML
    private Text emailText;

    @FXML
    private TextField emailTextField;

    @FXML
    private Text errorText;

    @FXML
    private ImageView eyeStatus;

    @FXML
    private PasswordField hiddenPassTField;

    @FXML
    private AnchorPane loginAPaneSmall;

    @FXML
    private AnchorPane loginAnchorPage;

    @FXML
    private Button loginButton;

    @FXML
    private BorderPane loginSuccessfullyBPane;

    @FXML
    private Text loginSuccessfullyText;

    @FXML
    private Text loginToYourAccountText;

    @FXML
    private Text passwordText;

    @FXML
    private Hyperlink registerLink;

    @FXML
    private ToggleButton showPasswordBtn;

    @FXML
    private TextField shownPasswordTextField;
    // Directory and file paths
    File directory = new File("userData");
    File file = new File(directory, "accountData.txt");

    // Constructor to create folder and file if they do not exist
    public LoginController() {
        if (!directory.exists()) {
            directory.mkdir();  // Create the folder if it doesn't exist
        }
        if (!file.exists()) {
            try {
                file.createNewFile();  // Create the file if it doesn't exist
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void changeVisibility(ActionEvent event) {
        boolean isPasswordHidden = hiddenPassTField.isVisible();
        hiddenPassTField.setVisible(!isPasswordHidden);
        shownPasswordTextField.setVisible(isPasswordHidden);

        if (isPasswordHidden) {
            shownPasswordTextField.setText(hiddenPassTField.getText());
            eyeStatus.setImage(new Image(getClass().getResourceAsStream("eye_close.png")));
        } else {
            hiddenPassTField.setText(shownPasswordTextField.getText());
            eyeStatus.setImage(new Image(getClass().getResourceAsStream("eye_open.png")));
        }
    }

    @FXML
    void loginHandler() throws IOException {
        String email = emailTextField.getText();
        String password = getPassword();

        if (email.isEmpty() || password.isEmpty()) {
            errorText.setText("Field/s must not be empty.");
            errorText.setVisible(true);
        } else if (validateLogin(email, password)) {
            System.out.println("Successfully logged in");
            showLoginSuccess();
            errorText.setVisible(false);
        } else {
            errorText.setText("ⓘ Invalid email or password");
            errorText.setVisible(true);
        }
    }

    private String getPassword() {
        if (shownPasswordTextField.isVisible()) {
            return hiddenPassTField.getText();
        } else {
            return hiddenPassTField.getText();
        }
    }

    // Handle account registration (switch to registration scene)
    @FXML
    void registerAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registerSample.fxml"));
        Scene registerScene = new Scene(loader.load());
        Stage stage = (Stage) registerLink.getScene().getWindow();
        stage.setScene(registerScene);
    }




    private boolean validateLogin(String email, String password) {
        if (!file.exists()) {
            errorText.setText("Account data file not found.");
            errorText.setVisible(true);
            System.out.println("File does not exist: " + file.getAbsolutePath()); // Debugging line
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitInfo = line.split(",");
                if (splitInfo.length == 2 && splitInfo[0].equals(email) && splitInfo[1].equals(password)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            errorText.setText("Account data file not found.");
            errorText.setVisible(true);
            e.printStackTrace();
        } catch (IOException e) {
            errorText.setText("Error reading the account data file.");
            errorText.setVisible(true);
            e.printStackTrace();
        }
        return false;
    }


    // Load the next scene (after successful login)
    private void loadHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sampleHomePage.fxml"));
            Scene nextScene = new Scene(loader.load());
            Stage stage = (Stage) loginSuccessfullyBPane.getScene().getWindow();
            stage.setScene(nextScene);
        } catch (IOException e) {
            errorText.setText("Failed to load the next scene.");
            errorText.setVisible(true);
            e.printStackTrace();
        }
    }

    // Show login success message
    private void showLoginSuccess() {
        // Make the success popup elements visible
        loginSuccessfullyBPane.setVisible(true);

        // Add a pause transition of 1.5 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));

        // After the delay, load the homepage
        delay.setOnFinished(event -> loadHomePage());
        delay.play();
    }

}
