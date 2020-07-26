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
import sample.models.SubReddit;
import sample.models.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchCellUserController extends JFXListCell<User> {

    private User currentUser;

    public SearchCellUserController(User currentUser) {
        this.currentUser = currentUser;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label userName;

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
                User user = getListView().getItems().get(a);


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
                userPageController.initData(currentUser , user);
                stage.show();


            }
        });


    }

    @Override
    protected void updateItem(User item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {

            setText(null);
            setGraphic(null);

        } else {

            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass()
                        .getResource("/sample/views/SearchCellUser.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            userName.setText("r/" + item.getUserName());


            setText(null);
            setGraphic(anchorPane);
        }
    }
    }

