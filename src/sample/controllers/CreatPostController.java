package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.models.Post;
import sample.models.SubReddit;
import sample.models.User;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreatPostController {

    private User currentUser;

    private SubReddit currentSubReddit;

    private Image tempPost;

    private boolean postForSub;

    public void initData(User user , SubReddit subReddit){

        currentUser = user ;
        currentSubReddit = subReddit;
        profileButton.setText(currentUser.getUserName());
        profile.setImage(currentUser.getProfile());


        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                homeButton.getScene().getWindow().hide();
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
                homePageController.initData(currentUser);



                stage.show();
            }
        });

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logoutButton.getScene().getWindow().hide();
                Stage primaryStage = new Stage() ;
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/sample/views/LoginPage.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                primaryStage.setTitle("Login");
                primaryStage.setScene(new Scene(root, 1080, 720));
                primaryStage.show();
            }
        });

        profileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                profileButton.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("/sample/views/ProfilePage.fxml"));
                try {
                    fxmlLoader.load();
                }
                catch (IOException e){

                    e.printStackTrace();
                }


                Parent root = fxmlLoader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                ProfilePageController profilePageController = fxmlLoader.getController();
                profilePageController.initData(currentUser);



                stage.show();

            }
        });

        postButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String title = postTitle.getText().trim().toString();
                String text = textFiled.getText().trim().toString();

                if (title.isEmpty()) {

                    error.setText("post not valid ");


                } else {

                    if (currentSubReddit == null){

                        Post post = Post.creatPost(title, text, tempPost);
                    currentUser.getPosts().add(post);
                    error.setText("");
                    postButton.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader();

                    fxmlLoader.setLocation(getClass().getResource("/sample/views/ProfilePage.fxml"));
                    try {
                        fxmlLoader.load();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }


                    Parent root = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    ProfilePageController profilePageController = fxmlLoader.getController();
                    profilePageController.initData(currentUser);


                    stage.show();

                }

                    else{

                        Post post = Post.creatPost(title, text, tempPost);
                        currentSubReddit.getPosts().add(post);
                        error.setText("");
                        postButton.getScene().getWindow().hide();
                        FXMLLoader fxmlLoader = new FXMLLoader();

                        fxmlLoader.setLocation(getClass().getResource("/sample/views/SubRedditPage.fxml"));
                        try {
                            fxmlLoader.load();
                        } catch (IOException e) {

                            e.printStackTrace();
                        }


                        Parent root = fxmlLoader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        SubRedditController subRedditController = fxmlLoader.getController();
                        subRedditController.initData(currentUser , currentSubReddit);


                        stage.show();


                    }

            }

            }
        });

        uploadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Stage primaryStage = new Stage() ;
                final FileChooser f = new FileChooser();
                File file = f.showOpenDialog(primaryStage);
                if (file != null) { // only proceed, if file was chosen
                    Image img = new Image(file.toURI().toString());
                   tempPost = img;
                   photoName.setText("picture attached");


                }



            }
        });

        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String search1 = searchpan.getText().trim().toString();

                ArrayList<User> users = User.searchUser(search1);

                ArrayList<Post> posts = Post.searchPost(search1);

                ArrayList<SubReddit> subreddit = SubReddit.searchSubReddit(search1);

                search.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("/sample/views/SearchResult.fxml"));
                try {
                    fxmlLoader.load();
                }
                catch (IOException e){

                    e.printStackTrace();
                }


                Parent root = fxmlLoader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                SearchResultController searchResultController = fxmlLoader.getController();
                searchResultController.initData(currentUser ,users , posts , subreddit);



                stage.show();





            }
        });




    }


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label redditLabal;

    @FXML
    private JFXTextField searchpan;

    @FXML
    private ImageView profile;

    @FXML
    private JFXButton search;

    @FXML
    private JFXButton profileButton;

    @FXML
    private JFXButton logoutButton;



    @FXML
    private JFXButton homeButton;

    @FXML
    private JFXTextField postTitle;

    @FXML
    private JFXButton uploadButton;

    @FXML
    private JFXTextArea textFiled;

    @FXML
    private JFXButton postButton;

    @FXML
    private Label photoName;

    @FXML
    private Label error;



    @FXML
    void initialize() {


    }



}
