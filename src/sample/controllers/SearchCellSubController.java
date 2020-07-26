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
import sample.models.Post;
import sample.models.SubReddit;
import sample.models.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchCellSubController extends JFXListCell<SubReddit> {

    private User currentUser;


    public SearchCellSubController(User currentUser) {
        this.currentUser = currentUser;
    }


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label subredditName;

    @FXML
    private JFXButton show;

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


        if (empty || subReddit == null) {

            setText(null);
            setGraphic(null);

        } else {

            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass()
                        .getResource("/sample/views/SearchCellSub.fxml"));
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
