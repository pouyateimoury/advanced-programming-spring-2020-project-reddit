package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.models.*;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PostController {

    private Post currentPost;
    private User currentUser;
    private SubReddit postRelatedSubReddit;
    private User postRelatedUser;

    public void initData(User user1,Post post , User user2 , SubReddit subReddit ){

        currentPost = post;
        this.currentUser = user1;
        this.postRelatedSubReddit = subReddit;
        this.postRelatedUser = user2;
        image.setImage(currentPost.getImageUrl());
        profileButton.setText(currentUser.getUserName());
        profile.setImage(currentUser.getProfile());
        postTitle.setText(currentPost.getTitle());
        SimpleDateFormat formatter ;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = formatter.format(currentPost.getCreatedTime());
        time.setText(strDate );
        discription.setMaxWidth(300);
        discription.setWrapText(true);
        discription.setText(currentPost.getDescription());
        score.setText(Integer.toString(currentPost.getPostlikebalance()));

        if(postRelatedUser == null){

            postByUser.setText("r/" + postRelatedSubReddit.getSubRedditId());
        }
        else {
            postByUser.setText("u/" + postRelatedUser.getUserName());
        }

        ObservableList<Comment> items = FXCollections.observableArrayList();


        if(currentPost.getComments().isEmpty()){

        }
        else {


            for (Comment comment : currentPost.getComments()) {

                items.add(comment);

            }


            comments.setItems(items);
            comments.setCellFactory(CommentCellController -> {
                return new CommentCellController(currentUser);
            });
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

        high.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int status =0 ;

                for (PostLike postLike: currentPost.getPostLikes()) {

                    if(postLike.getUserLikedBy().equals(currentUser)) {

                        if (postLike.getTypeOfLike().equals(TypeOfLike.UP)){
                            currentPost.getPostLikes().remove(postLike);
                        currentPost.setPostlikebalance(currentPost.getPostlikebalance() - 1);
                        score.setText(Integer.toString(currentPost.getPostlikebalance()));
                        status = 1;
                    }
                        else {
                            currentPost.getPostLikes().remove(postLike);
                            currentPost.getPostLikes().add(new PostLike(currentUser,TypeOfLike.UP));
                            currentPost.setPostlikebalance(currentPost.getPostlikebalance() + 2);
                            score.setText(Integer.toString(currentPost.getPostlikebalance()));
                            status = 1;

                        }

                    }

                }

                if(status == 0){
                    currentPost.getPostLikes().add(new PostLike(currentUser,TypeOfLike.UP));
                    currentPost.setPostlikebalance(currentPost.getPostlikebalance()+1);
                    score.setText(Integer.toString(currentPost.getPostlikebalance()));
                }



            }
        });

        low.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                int status =0 ;

                for (PostLike postLike: currentPost.getPostLikes()) {

                    if(postLike.getUserLikedBy().equals(currentUser)) {

                        if (postLike.getTypeOfLike().equals(TypeOfLike.DOWN)){
                            currentPost.getPostLikes().remove(postLike);
                            currentPost.setPostlikebalance(currentPost.getPostlikebalance() + 1);
                            score.setText(Integer.toString(currentPost.getPostlikebalance()));
                            status = 1;
                        }
                        else {
                            currentPost.getPostLikes().remove(postLike);
                            currentPost.getPostLikes().add(new PostLike(currentUser,TypeOfLike.DOWN));
                            currentPost.setPostlikebalance(currentPost.getPostlikebalance() - 2);
                            score.setText(Integer.toString(currentPost.getPostlikebalance()));
                            status = 1;

                        }

                    }

                }

                if(status == 0){
                    currentPost.getPostLikes().add(new PostLike(currentUser,TypeOfLike.DOWN));
                    currentPost.setPostlikebalance(currentPost.getPostlikebalance()-1);
                    score.setText(Integer.toString(currentPost.getPostlikebalance()));
                }


            }
        });

        sendCommentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String comment = writecomment.getText().trim().toString();

                if(comment.isEmpty()){

                    errorlabel.setText("its empty !!!");

                }

                else {

                    currentPost.getComments().add(new Comment(comment , currentUser));

                    sendCommentButton.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader();

                    fxmlLoader.setLocation(getClass().getResource("/sample/views/PostPage.fxml"));
                    try {
                        fxmlLoader.load();
                    }
                    catch (IOException e){

                        e.printStackTrace();
                    }


                    Parent root = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    PostController postController = fxmlLoader.getController();
                    postController.initData(currentUser , currentPost , postRelatedUser , postRelatedSubReddit );



                    stage.show();

                }
            }
        });

        postByUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(postRelatedUser == null){

                    postByUser.getScene().getWindow().hide();
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
                    subRedditController.initData(currentUser , postRelatedSubReddit);


                    stage.show();



                }

                else{


                    if(currentUser.equals(postRelatedUser)){

                        postByUser.getScene().getWindow().hide();
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

                    else{

                        postByUser.getScene().getWindow().hide();
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
                        userPageController.initData(currentUser ,postRelatedUser );



                        stage.show();


                    }

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
    private ImageView image;

    @FXML
    private Label postby;

    @FXML
    private Label postTitle;

    @FXML
    private Label discription;

    @FXML
    private JFXListView<Comment> comments;

    @FXML
    private JFXTextArea writecomment;

    @FXML
    private JFXButton sendCommentButton;

    @FXML
    private Hyperlink postByUser;

    @FXML
    private JFXButton high;

    @FXML
    private Label score;

    @FXML
    private JFXButton low;


    @FXML
    private Label errorlabel;


    @FXML
    private Label time;


    @FXML
    void initialize() {

    }



}
