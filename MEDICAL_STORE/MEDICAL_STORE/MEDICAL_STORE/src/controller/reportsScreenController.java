package controller;

import contracts.Screens;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.DataManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class reportsScreenController {
   @FXML
    Button totalSales;

    private void startReportsScreen(){
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/reportsScreen.fxml")));
            Stage stage = new Stage();
            stage.setTitle(Screens.REPORTS_SCREEN);
            stage.setScene(new Scene(root));
            stage.show();
            totalSales.getScene().getWindow().hide();
        }catch (java.io.IOException exception){
            System.out.println("Couldn't launch Reports Screen");
        }
    }

    public void topCustomers(ActionEvent event){
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/topCustomersReport.fxml")));
            Stage stage = new Stage();
            stage.setTitle(Screens.TOP_CUSTOMERS_SCREEN);
            stage.setScene(new Scene(root));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }catch (java.io.IOException exception){
            exception.printStackTrace();
            System.out.println("Couldn't launch Top Customers Screen");
        }
    }

    public void topSellingmedicines(ActionEvent event){
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/topSellingmedicineReport.fxml")));
            Stage stage = new Stage();
            stage.setTitle(Screens.TOP_SELLING_medicineS_SCREEN);
            stage.setScene(new Scene(root));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }catch (java.io.IOException exception){
            System.out.println("Couldn't launch Top Selling medicines Screen");
        }
    }
	


}
