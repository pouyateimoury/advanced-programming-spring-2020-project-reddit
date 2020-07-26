package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.models.Comment;
import sample.models.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowThreadController {

    private Comment currentComment;
    private User currentUser;


    public void initData(Comment comment , User currentUser){

        this.currentUser = currentUser;
        currentComment = comment;
        commentText.setMaxWidth(560);
        commentText.setWrapText(true);
        commentText.setText(currentComment.getCommentText());
        commentOwner.setText("u/" + currentComment.getCommentByUser().getUserName());


        ObservableList<Comment> items = FXCollections.observableArrayList();


        if(currentComment.getCommentsRealatedToThisComment().isEmpty()){

        }
        else {


            for (Comment comment1 : currentComment.getCommentsRealatedToThisComment()) {

                items.add(comment1);

            }


            repliedComments.setItems(items);
            repliedComments.setCellFactory(ThreadCellController -> new ThreadCellController(currentUser));
        }


        sendReply.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String reply = replyText.getText().trim().toString();

                if(reply.isEmpty()){

                    error.setText("its empty !");


                }
                else{

                    currentComment.getCommentsRealatedToThisComment().add(new Comment(reply , currentUser));

                    sendReply.getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader();

                    fxmlLoader.setLocation(getClass().getResource("/sample/views/ShowThread.fxml"));
                    try {
                        fxmlLoader.load();
                    }
                    catch (IOException e){

                        e.printStackTrace();
                    }


                    Parent root = fxmlLoader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    ShowThreadController ShowThreadController = fxmlLoader.getController();
                    ShowThreadController.initData(currentComment , currentUser);

                    stage.show();

                }



            }
        });






    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label commentOwner;

    @FXML
    private Label commentText;

    @FXML
    private JFXListView<Comment> repliedComments;

    @FXML
    private JFXTextArea replyText;

    @FXML
    private JFXButton sendReply;


    @FXML
    private Label error;

    @FXML
    void initialize() {


    }

}
