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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class SubRedditController {

    private User currentUser;
    private SubReddit currentSubReddit;

    public void initData(User user , SubReddit subReddit){

        this.currentUser = user;
        this.currentSubReddit = subReddit;
        subRedditProfile.setImage(currentSubReddit.getProfile());
        name.setText(currentSubReddit.getName());
        profileButton.setText(currentUser.getUserName());
        username.setText("r/" + currentSubReddit.getSubRedditId());
        about.setMaxWidth(175);
        about.setWrapText(true);
        about.setText(currentSubReddit.getAbout());
        members.setText(Integer.toString(User.getSubRedditMembers(currentSubReddit)));
        profile.setImage(currentUser.getProfile());

        int a = User.getUserRelation(currentUser , currentSubReddit);

        if (a == 1) {

            joinOrLeave.setText("leave");

        }

        if(a==2){

            joinOrLeave.setText("REMOVE(Fake)");
        }





        ObservableList<Post> items = FXCollections.observableArrayList();

        if(currentSubReddit.getPosts().isEmpty()){

        }
        else {


            for (Post post : currentSubReddit.getPosts()) {

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


            posts.setItems(items);
            posts.setCellFactory(CellController -> new CellController(currentUser));
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

        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                home.getScene().getWindow().hide();
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

        newPost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int a = User.getUserRelation(currentUser, currentSubReddit);

                if (a == 0) {

                    error.setText("you have to join first");


                } else {

                    newPost.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader();

                    fxmlLoader.setLocation(getClass().getResource("/sample/views/CreatPostPage.fxml"));
                    try {
                        fxmlLoader.load();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }


                    Parent root = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    CreatPostController creatPostController = fxmlLoader.getController();
                    creatPostController.initData(currentUser, currentSubReddit);


                    stage.show();


                }
            }
        });

        joinOrLeave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int a = User.getUserRelation(currentUser , currentSubReddit);

                if(a== 0) {

                    currentUser.getSubReddits().add(currentSubReddit);

                }

                else if(a== 1){

                    currentUser.getSubReddits().remove(currentSubReddit);

                }

                else{


                }


                    joinOrLeave.getScene().getWindow().hide();
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
                    subRedditController.initData(currentUser, currentSubReddit);


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
    private ImageView subRedditProfile;

    @FXML
    private JFXButton newPost;

    @FXML
    private Label username;

    @FXML
    private Label members;

    @FXML
    private JFXButton joinOrLeave;

    @FXML
    private Label about;

    @FXML
    private Label name;

    @FXML
    private JFXButton home;

    @FXML
    private JFXListView<Post> posts;


    @FXML
    private Label error;

    @FXML
    void initialize() {


    }



}
