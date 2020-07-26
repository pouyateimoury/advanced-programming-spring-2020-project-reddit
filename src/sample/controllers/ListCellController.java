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
import sample.models.SubReddit;
import sample.models.User;

import java.io.IOException;

public class ListCellController extends JFXListCell<SubReddit> {

    private User currentUser ;

    @FXML
    private Label subredditName;

    @FXML
    private JFXButton show;

    @FXML
    private AnchorPane anchorPane;

    public ListCellController(User currentUser) {

        this.currentUser = currentUser;

    }

    private FXMLLoader fxmlLoader;

    @FXML
    void initialize() {

        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                show.getScene().getWindow().hide();

                int a = getListView().getItems().indexOf(getItem());
                SubReddit subReddit = getListView().getItems().get(a);


                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("/sample/views/SubRedditPage.fxml"));
                try {
                    fxmlLoader.load();
                }
                catch (IOException e){

                    e.printStackTrace();
                }


                Parent root = fxmlLoader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                SubRedditController subRedditController = fxmlLoader.getController();
                subRedditController.initData(currentUser , subReddit);
                stage.show();


            }
        });

    }

    @Override
    protected void updateItem(SubReddit subReddit, boolean empty) {
        super.updateItem(subReddit, empty);

        if(empty || subReddit == null){

            setText(null);
            setGraphic(null);

        }
        else {

            if (fxmlLoader == null ) {
                fxmlLoader = new FXMLLoader(getClass()
                        .getResource("/sample/views/ListCell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            subredditName.setText("r/" + subReddit.getSubRedditId());


            setText(null);
            setGraphic(anchorPane);


        }
    }
}
