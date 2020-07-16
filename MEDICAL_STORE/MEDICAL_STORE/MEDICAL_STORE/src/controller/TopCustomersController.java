package controller;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;
import model.DataManager;

import java.util.ArrayList;

public class TopCustomersController {
    @FXML
    TableView<User> usersTable;
    @FXML
    TableColumn<User, String> userColumn;
    
    @FXML
    private void initialize() {
        userColumn.setCellValueFactory(new PropertyValueFactory("userName"));
        DataManager manager = DataManager.getInstance();
        ArrayList<Pair<String, Integer>> users = manager.getTopFiveCustomers();
        for(Pair<String, Integer> user: users){
            User usr = new User();
            usr.setUserName(user.getKey());
            usersTable.getItems().addAll(usr);
        }
    }


    public class User{
        private StringProperty userName;
        public void setUserName(String value) { firstNameProperty().set(value); }
        public String getUserName() { return firstNameProperty().get(); }
        public StringProperty firstNameProperty() {
            if (userName == null) userName = new SimpleStringProperty(this, "userName");
            return userName;
        }
    }
}
