package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AccountManager;
import model.DataManager;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import contracts.Screens;

public class OrderPlaceFormController {
    public TextField iddTextField;
    public TextField quantityTextField;
    public Label notifLabel;


    public void orderBtnClicked() {
        String username = AccountManager.getManager().getCurrentUser().getUserName();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderTime = sdf.format(new Date());

        String idd = iddTextField.getText();
        String quantity = quantityTextField.getText();

        String orderID = DataManager.getInstance().insertOrder(username, orderTime);

        if(orderID != null){
            DataManager.getInstance().insertOrderDetail(orderID, idd, quantity);
            notifLabel.setText("Success!");
           // getBack();
        }
        else{
            notifLabel.setText("Failure!");
        }
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
