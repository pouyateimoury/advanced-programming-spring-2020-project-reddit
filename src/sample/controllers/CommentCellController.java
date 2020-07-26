package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.models.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CommentCellController extends JFXListCell<Comment> {

    private User currentUser ;

    public CommentCellController(User currentUser) {
        this.currentUser = currentUser;
    }













    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private URL location;

    @FXML
    private Label commentBy;

    @FXML
    private Label commentText;

    @FXML
    private JFXButton showThread;


    @FXML
    private Label commentScore;

    @FXML
    private Label numOfThread;

    private FXMLLoader fxmlLoader;

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
                showThreadController.initData(comment, currentUser);
                stage.show();



            }
        });









    }



    @Override
    protected void updateItem(Comment myComment, boolean empty) {
        super.updateItem(myComment, empty);

        if(empty || myComment == null){

            setText(null);
            setGraphic(null);

        }
        else {

            if (fxmlLoader == null ) {
                fxmlLoader = new FXMLLoader(getClass()
                        .getResource("/sample/views/CommentCell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            commentText.setMaxWidth(280);
            commentText.setWrapText(true);
            commentText.setText(myComment.getCommentText());
            commentBy.setText("u/" + myComment.getCommentByUser().getUserName());

            setText(null);
            setGraphic(anchorpane);
            numOfThread.setText(Integer.toString(myComment.getCommentsRealatedToThisComment().size()));


        }




    }


}
