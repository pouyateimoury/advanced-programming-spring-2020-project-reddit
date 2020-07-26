package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.controllers.HomePageController;
import sample.models.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton signupButton;

    @FXML
    private Hyperlink loginLink;

    @FXML
    private Label errorLabal;

    @FXML
    void initialize() {

        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                User user = null;
                try {
                    user = User.signUp(username.getText().trim().toString() , email.getText().trim().toString() , password.getText().trim().toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                if(user != null){

                     signupButton.getScene().getWindow().hide();

                     FXMLLoader fxmlLoader = new FXMLLoader();

                     fxmlLoader.setLocation(getClass().getResource("/sample/views/HomePage.fxml"));
                     try {
                         fxmlLoader.load();
                     }
                     catch (IOException e){

                         e.printStackTrace();
                     }


                     Parent root = fxmlLoader.getRoot();
                     Stage stage = new Stage();
                     stage.setScene(new Scene(root));
                     HomePageController homePageController = fxmlLoader.getController();
                     homePageController.initData(user);



                     stage.show();



                 }
                 else{

                     errorLabal.setText("this account already exist try Login");

                 }


            }
        });

        loginLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                loginLink.getScene().getWindow().hide();
                Stage primaryStage = new Stage() ;
                errorLabal.setText("");
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/sample/views/LoginPage.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                primaryStage.setTitle("Loge in");
                primaryStage.setScene(new Scene(root, 1080, 720));
                primaryStage.show();

            }
        });

    }



}
