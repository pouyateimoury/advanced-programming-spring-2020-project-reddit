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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.controllers.CellController;
import sample.controllers.CreatPostController;
import sample.controllers.HomePageController;
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
import java.util.Comparator;
import java.util.ResourceBundle;

public class ProfilePageController {

    private User currentUser;

    public void initData(User user){


        currentUser = user;
        ObservableList<Post> items = FXCollections.observableArrayList();

        if(currentUser.getPosts().isEmpty()){

        }
        else {


            for (Post post : currentUser.getPosts()) {

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



        profileButton.setText(currentUser.getUserName());
        profileImage.setImage(currentUser.getProfile());
        profile.setImage(currentUser.getProfile());
        background.setImage(currentUser.getBackground());
        username.setText("u/" + currentUser.getUserName());
        karma.setText(Integer.toString(currentUser.getKarma()));
        SimpleDateFormat formatter ;
        formatter = new SimpleDateFormat("dd MMMM yyyy");
        String strDate = formatter.format(currentUser.getJoinedDate());
        cakeDay.setText(strDate );

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

        editProfile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Stage primaryStage = new Stage() ;
                final FileChooser f = new FileChooser();
                File file = f.showOpenDialog(primaryStage);
                if (file != null) { // only proceed, if file was chosen
                    Image img = new Image(file.toURI().toString());
                    currentUser.setProfile(img);
                    profile.setImage(img);
                    profileImage.setImage(img);
            }


            }
        });

        editBackground.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Stage primaryStage = new Stage() ;
                final FileChooser f = new FileChooser();
                File file = f.showOpenDialog(primaryStage);
                if (file != null) { // only proceed, if file was chosen
                    Image img = new Image(file.toURI().toString());
                    currentUser.setBackground(img);
                    background.setImage(img);

            }



            }
        });

        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(Desktop.isDesktopSupported()){

                    try {
                        Desktop.getDesktop().browse(new URI("https://github.com/pouyateimoury"));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

            }
        });

        newPost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                newPost.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("/sample/views/CreatPostPage.fxml"));
                try {
                    fxmlLoader.load();
                }
                catch (IOException e){

                    e.printStackTrace();
                }


                Parent root = fxmlLoader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                CreatPostController creatPostController = fxmlLoader.getController();
                creatPostController.initData(currentUser , null);



                stage.show();




            }
        });

        creatSubRedditButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                creatSubRedditButton.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("/sample/views/CreatSubRedditPage.fxml"));
                try {
                    fxmlLoader.load();
                }
                catch (IOException e){

                    e.printStackTrace();
                }


                Parent root = fxmlLoader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                CreatSubRedditController CreatSubRedditController = fxmlLoader.getController();
                CreatSubRedditController.initData(currentUser);



                stage.show();


            }
        });

        mySubRedditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                mySubRedditsButton.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("/sample/views/List.fxml"));
                try {
                    fxmlLoader.load();
                }
                catch (IOException e){

                    e.printStackTrace();
                }


                Parent root = fxmlLoader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                ListController listController = fxmlLoader.getController();
                listController.initData(currentUser);



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
    private Label username;


    @FXML
    private Label karma;

    @FXML
    private Label cakeDay;


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
    private JFXListView<Post> listViews;


    @FXML
    private JFXButton search;

    @FXML
    private JFXButton profileButton;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private ImageView profileImage;

    @FXML
    private JFXButton editProfile;

    @FXML
    private JFXButton newPost;

    @FXML
    private JFXButton editBackground;

    @FXML
    private Hyperlink about;

    @FXML
    private JFXButton homeButton;

    @FXML
    private ImageView background;

    @FXML
    private JFXButton mySubRedditsButton;


    @FXML
    private JFXButton creatSubRedditButton;

    @FXML
    void initialize() {


    }


}
