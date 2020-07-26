package sample.controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.models.Post;
import sample.models.SubReddit;
import sample.models.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;


import java.io.IOException;
import java.util.ArrayList;

public class HomePageController {

    @FXML
    private JFXTextField searchpan;

    @FXML
    private Label redditLabal;

    @FXML
    private JFXButton search;

    @FXML
    private ImageView profile;


    @FXML
    private JFXButton profileButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXListView<Post> listViews;





    private User currentUser;

    public void initData(User user){

        currentUser = user;
        profileButton.setText(currentUser.getUserName());
        profile.setImage(currentUser.getProfile());


        ObservableList<Post> items = FXCollections.observableArrayList();

        if(currentUser.getPostsForUser().isEmpty()){

        }
        else {


            for (Post post : currentUser.getPostsForUser()) {

                items.add(post);

            }


            listViews.setItems(items);
            listViews.setCellFactory(CellController -> new CellController(currentUser));
        }




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
                primaryStage.setTitle("Loge in");
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
    void initialize() {


    }




}
