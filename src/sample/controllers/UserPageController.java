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
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class UserPageController {

    private User currentUser;
    private User pageOfUser;

    public void initData(User user1 , User user2){

        currentUser = user1;
        pageOfUser = user2;
        profile.setImage(currentUser.getProfile());
        profileImage.setImage(pageOfUser.getProfile());
        background.setImage(pageOfUser.getBackground());
        username.setText("u/" + pageOfUser.getUserName());
        karma.setText(Integer.toString(pageOfUser.getKarma()));
        SimpleDateFormat formatter ;
        formatter = new SimpleDateFormat("dd MMMM yyyy");
        String strDate = formatter.format(currentUser.getJoinedDate());
        cakeDay.setText(strDate );
        profileButton.setText(currentUser.getUserName());

        ObservableList<Post> items = FXCollections.observableArrayList();

        if(pageOfUser.getPosts().isEmpty()){

        }
        else {


            for (Post post : pageOfUser.getPosts()) {

                items.add(post);

            }

            items.sort(new Comparator<Post>() {
                @Override
                public int compare(Post o1, Post o2) {
                    int a;
                    a = o1.getCreatedTime().compareTo(o2.getCreatedTime());
                    if(a>0){
                        return -1;
                    }
                    if(a<0){
                        return 1;
                    }
                    else{
                        return 0;
                    }
                }
            });


            listViews.setItems(items);
            listViews.setCellFactory(CellController -> new CellController(currentUser));
        }


        if(currentUser.isFollowed(pageOfUser)){

            follow.setText("UNFOLLOW");

        }

        else {

            follow.setText("FOLLOW");
        }

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

        follow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(currentUser.isFollowed(pageOfUser)){

                    currentUser.getFollowing().remove(pageOfUser);

                    follow.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader();

                    fxmlLoader.setLocation(getClass().getResource("/sample/views/UserPage.fxml"));
                    try {
                        fxmlLoader.load();
                    }
                    catch (IOException e){

                        e.printStackTrace();
                    }


                    Parent root = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    UserPageController userPageController = fxmlLoader.getController();
                    userPageController.initData(currentUser , pageOfUser);

                    stage.show();

                }

                else{

                    currentUser.getFollowing().add(pageOfUser);

                    follow.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader();

                    fxmlLoader.setLocation(getClass().getResource("/sample/views/UserPage.fxml"));
                    try {
                        fxmlLoader.load();
                    }
                    catch (IOException e){

                        e.printStackTrace();
                    }


                    Parent root = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    UserPageController userPageController = fxmlLoader.getController();
                    userPageController.initData(currentUser , pageOfUser);

                    stage.show();

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
    private ImageView background;

    @FXML
    private ImageView profileImage;

    @FXML
    private JFXButton follow;

    @FXML
    private Label username;

    @FXML
    private Label karma;

    @FXML
    private Label cakeDay;

    @FXML
    private JFXButton homeButton;

    @FXML
    private JFXListView<Post> listViews;

    @FXML
    void initialize() {


    }
}
