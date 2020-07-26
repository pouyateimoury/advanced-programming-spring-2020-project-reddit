package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.models.Comment;
import sample.models.User;

import java.io.IOException;

public class ThreadCellController extends JFXListCell<Comment> {

    private User currentUser;

    public ThreadCellController(User currentUser) {
        this.currentUser = currentUser;
    }

    private FXMLLoader fxmlLoader;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Label commentText;

    @FXML
    private JFXButton showThread;

    @FXML
    private Label numOfThread;

    @FXML
    private Label commentOwner;



    @FXML
    void initialize() {

        showThread.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int a = getListView().getItems().indexOf(getItem());
                Comment comment = getListView().getItems().get(a);


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
                ShowThreadController showThreadController = fxmlLoader.getController();
                showThreadController.initData(comment , currentUser);
                stage.show();


            }
        });

    }


    @Override
    protected void updateItem(Comment reply, boolean empty) {
        super.updateItem(reply, empty);

        if(empty || reply == null){

            setText(null);
            setGraphic(null);

        }
        else {

            if (fxmlLoader == null ) {
                fxmlLoader = new FXMLLoader(getClass()
                        .getResource("/sample/views/ThreadCell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            commentText.setText(reply.getCommentText());
            numOfThread.setText(Integer.toString(reply.getCommentsRealatedToThisComment().size()));
            commentOwner.setText("u/" + reply.getCommentByUser().getUserName());

            setText(null);
            setGraphic(anchorpane);


        }

    }
}
