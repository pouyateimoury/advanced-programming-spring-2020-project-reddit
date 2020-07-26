package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.models.Post;
import sample.models.SubReddit;
import sample.models.User;

import java.io.IOException;
import java.util.ArrayList;

public class SearchResultController {

    private User currentUser;
    private ArrayList<User> searchUsers;
    private ArrayList<Post> searchPosts;
    private ArrayList<SubReddit> searchSubReddit;

    public void initData(User user , ArrayList<User> users , ArrayList<Post> posts , ArrayList<SubReddit> subreddit){

        currentUser = user;
        searchUsers = users;
        searchPosts = posts;
        searchSubReddit = subreddit;
        profileButton.setText(currentUser.getUserName());
        profile.setImage(currentUser.getProfile());

        ObservableList<User> items = FXCollections.observableArrayList();
        if(searchUsers.isEmpty()){

        }
        else {


            for (User user1 : searchUsers) {

                items.add(user1);

            }


            listViewUser.setItems(items);
            listViewUser.setCellFactory(SearchCellUserController -> new SearchCellUserController(currentUser));
        }



        ObservableList<Post> items1 = FXCollections.observableArrayList();

        if(searchPosts.isEmpty()){

        }
        else {


            for (Post post : searchPosts) {

                items1.add(post);

            }


            listViewsPost.setItems(items1);
            listViewsPost.setCellFactory(SearchCellPostController -> new SearchCellPostController(currentUser));
        }


        ObservableList<SubReddit> items2 = FXCollections.observableArrayList();

        if(searchSubReddit.isEmpty()){

        }
        else {


            for (SubReddit subReddit : searchSubReddit) {

                items2.add(subReddit);

            }


            listViewsCommunitie.setItems(items2);
            listViewsCommunitie.setCellFactory(SearchCellSubController -> new SearchCellSubController(currentUser));
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
    private JFXButton homeButton;

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
    private JFXListView<User> listViewUser;

    @FXML
    private JFXListView<SubReddit> listViewsCommunitie;

    @FXML
    private JFXListView<Post> listViewsPost;
}
