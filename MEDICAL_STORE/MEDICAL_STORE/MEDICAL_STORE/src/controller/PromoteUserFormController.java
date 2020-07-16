package controller;

import contracts.DBContract;
import contracts.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DataManager;

import java.util.HashMap;
import java.util.Map;

public class PromoteUserFormController {

    public TextField userNameTextField;
    public Label firstNameLabel;
    public Label lastNameLabel;


    public void promoteBtnClicked() {
        DataManager.getInstance().promoteUser(userNameTextField.getText());
        getBack();
    }

    public void searchForUser(ActionEvent actionEvent) {
        Map<String, String> user = DataManager.getInstance().getUser(userNameTextField.getText());
        firstNameLabel.setText(user.get(DBContract.User.FIRST_NAME_COLUMN));
        lastNameLabel.setText(user.get(DBContract.User.LAST_NAME_COLUMN));
    }
    private void getBack(){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("views/customerScreen.fxml"));
            Stage stage = new Stage();
            stage.setTitle(Screens.CUSTOMER_SCREEN);
            stage.setScene(new Scene(root));
            stage.show(); 
        }catch (java.io.IOException exception){
            System.out.println("Couldn't return from the login window.");
        }
    }
}
