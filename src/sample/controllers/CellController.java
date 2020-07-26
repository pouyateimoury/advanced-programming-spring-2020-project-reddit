package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.models.Post;
import sample.models.SubReddit;
import sample.models.User;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class CellController extends JFXListCell<Post> {

    private User currentUser;

    public CellController(User currentUser) {
        this.currentUser = currentUser;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane roorAnchorPane;


    @FXML
    private URL location;

    @FXML
    private Label title;

    @FXML
    private ImageView image;

    @FXML
    private JFXButton showPost;

    @FXML
    private Label time;


    private FXMLLoader fxmlLoader;

    @FXML
    void initialize() {


        showPost.setOnAction(event -> {

            showPost.getScene().getWindow().hide();
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
            int a = getListView().getItems().indexOf(getItem());
            User user =  User.findUserByPost(getListView().getItems().get(a));
            SubReddit subReddit = SubReddit.getSubRedditByPost(getListView().getItems().get(a));
            postController.initData(currentUser,getListView().getItems().get(a) , user , subReddit);

            stage.show();


        });


    }

    @Override
    protected void updateItem(Post myPost, boolean empty) {
        super.updateItem(myPost, empty);

        if(empty || myPost == null){

            setText(null);
            setGraphic(null);

        }
        else {

            if (fxmlLoader == null ) {
                fxmlLoader = new FXMLLoader(getClass()
                        .getResource("/sample/views/Cell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            title.setText(myPost.getTitle());
            image.setImage(myPost.getImageUrl());
            SimpleDateFormat formatter ;
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String strDate = formatter.format(myPost.getCreatedTime());
            time.setText(strDate );
            setText(null);
            setGraphic(roorAnchorPane);


        }

    }


}
