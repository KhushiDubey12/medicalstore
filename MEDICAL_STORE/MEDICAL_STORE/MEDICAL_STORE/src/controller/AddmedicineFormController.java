package controller;

import contracts.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.manfacturer;
import model.medicine;
import model.DataManager;
import model.supplier;

public class AddmedicineFormController {

    public TextField iddTextField;
    public TextField medicine_nameTextField;
    public ChoiceBox categoryChoice;
    public TextField yearTextField;
    public TextField priceTextField;
    public TextField availQtyTextField;
    public TextField strengthTextField;
    public Label addErrorLabel;
    public TextField manfacturersTextField;
    public TextField supplierNameTextField;
    public TextField supplierAddressTextField;
    public TextField supplierPhoneTextField;

    public void addBtnClicked(ActionEvent actionEvent) {
        addErrorLabel.setText("");

        medicine medicine = new medicine(iddTextField.getText(), medicine_nameTextField.getText(), categoryChoice.getValue().toString()
        , yearTextField.getText(), priceTextField.getText(), availQtyTextField.getText()
        , strengthTextField.getText(), supplierNameTextField.getText());

        supplier supplier = new supplier(supplierNameTextField.getText(),
                supplierAddressTextField.getText(), supplierPhoneTextField.getText());

        if(DataManager.getInstance().insertsupplier(supplier))
            System.out.println("supplier inserted!");

        if(DataManager.getInstance().insertmedicine(medicine)) {
            System.out.println("medicine Inserted!");
            String[] manfacturerNames = manfacturersTextField.getText().split(",");
            for(String manfacturerName : manfacturerNames){
                System.out.println("manfacturer: " + manfacturerName);
                manfacturer manfacturer = new manfacturer(manfacturerName);
                if(DataManager.getInstance().insertmanfacturer(manfacturer, medicine.getidd()))
                    System.out.println("manfacturer Inserted! " + manfacturerName);
            }

            addErrorLabel.setText("Success!");
            getBack();
        }
        else
            addErrorLabel.setText("medicine cannot be added!");
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
