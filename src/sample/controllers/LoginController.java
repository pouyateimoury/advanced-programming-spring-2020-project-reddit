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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton loginButton;

    @FXML
    private Label errorLabal;

    @FXML
    private Hyperlink signupLink;

    @FXML
    void initialize() {

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                User user = User.logIn(username.getText().trim().toString() , password.getText().trim().toString());

                if(user == null ){
                    errorLabal.setText("Wrong username or password ");

                }

                else{

                    loginButton.getScene().getWindow().hide();

                    errorLabal.setText("");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/views/HomePage.fxml"));

                    try{
                        loader.load();
                    }
                    catch (IOException e ){

                        e.printStackTrace();

                    }

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setTitle("Home Page");
                    stage.setScene(new Scene(root));
                    HomePageController homePageController = loader.getController();
                    homePageController.initData(user);


                    stage.show();


                }
            }
        });

        signupLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                signupLink.getScene().getWindow().hide();

                errorLabal.setText("");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/views/SignupPage.fxml"));

                try{
                    loader.load();
                }
                catch (IOException e ){

                    e.printStackTrace();

                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Sign Up");
                stage.setScene(new Scene(root));


                stage.show();

            }
        });



    }


}
