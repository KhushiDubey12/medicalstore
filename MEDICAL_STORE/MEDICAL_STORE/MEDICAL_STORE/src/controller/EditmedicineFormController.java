package controller;

import contracts.DBContract;
import contracts.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.manfacturer;
import model.medicine;
import model.DataManager;
import model.supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EditmedicineFormController {
    public TextField yearTextField;
    public TextField priceTextField;
    public TextField availQtyTextField;
    public TextField strengthTextField;
    public Label addErrorLabel; 
    public TextField manfacturersTextField;
    public TextField supplierNameTextField;
    public TextField supplierAddressTextField;
    public TextField supplierPhoneTextField;
    public TextField searchTextField;
    public TextField iddTextField;
    public TextField medicine_nameTextField;
    public ChoiceBox categoryChoice;
    public Label editErrorLabel;
    

    public void deleteBtnClicked() {
        String idd = iddTextField.getText();
        if(DataManager.getInstance().deletemedicine(idd))
            System.out.println("medicine Deleted!");
        getBack();
    }

    public void editBtnClicked() {
    	
        editErrorLabel.setText("");

        String idd = iddTextField.getText();

        DataManager.getInstance().deletemanfacturers(searchTextField.getText());

        supplier supplier = new supplier(supplierNameTextField.getText(),
                supplierAddressTextField.getText(), supplierPhoneTextField.getText());

        if(DataManager.getInstance().insertsupplier(supplier))
            System.out.println("supplier inserted!");

        medicine medicine = new medicine(iddTextField.getText(), medicine_nameTextField.getText(), categoryChoice.getValue().toString(),
                yearTextField.getText(), priceTextField.getText(), availQtyTextField.getText(),
                strengthTextField.getText(), supplierNameTextField.getText());
        if(DataManager.getInstance().updatemedicine(medicine, searchTextField.getText())){
          
            String[] manfacturerNames = manfacturersTextField.getText().split(",");
            for(String manfacturerName : manfacturerNames){
                manfacturer manfacturer = new manfacturer(manfacturerName);
                if(DataManager.getInstance().insertmanfacturer(manfacturer, idd))
                    System.out.println("manfacturer Inserted! " + manfacturerName);
            }

            editErrorLabel.setText("Success!");
            getBack();
        }
        else{
            editErrorLabel.setText("medicine cannot be edited!");
        }
    }

    public void searchBtnClicked() throws SQLException {

        iddTextField.setText("");
        medicine_nameTextField.setText("");
        yearTextField.setText("");
        categoryChoice.setValue("Science");
        priceTextField.setText("");
        availQtyTextField.setText("");
        strengthTextField.setText("");
        supplierNameTextField.setText("");
        manfacturersTextField.setText("");
        supplierNameTextField.setText("");
        supplierAddressTextField.setText("");
        supplierPhoneTextField.setText("");

        ResultSet medicineResultSet = DataManager.getInstance().getmedicine(searchTextField.getText());
        for(int i=1; i<=medicineResultSet.getMetaData().getColumnCount(); i++){
            System.out.println(medicineResultSet.getMetaData().getColumnName(i));
        }
        if(medicineResultSet.next()){
            medicine medicine = new medicine(medicineResultSet.getString(1), medicineResultSet.getString(2),
                    medicineResultSet.getString(3), medicineResultSet.getString(4),
                    medicineResultSet.getString(5), medicineResultSet.getString(6),
                    medicineResultSet.getString(7), medicineResultSet.getString(8));
            System.out.println(medicine.toString());

            iddTextField.setText(medicine.getidd());
            medicine_nameTextField.setText(medicine.getmedicine_name());
            yearTextField.setText(medicine.getYear().split("-")[0]);
            categoryChoice.setValue(medicine.getCategory());
            priceTextField.setText(medicine.getSellingPrice());
            availQtyTextField.setText(medicine.getAvailQuantity());
            strengthTextField.setText(medicine.getstrength());
            supplierNameTextField.setText(medicine.getsupplierName());

            ResultSet manfacturerResultSet = DataManager.getInstance().getmanfacturers(medicine.getidd());
            String manfacturers = "";
            if(manfacturerResultSet.next())
                manfacturers += manfacturerResultSet.getString(2);
            while(manfacturerResultSet.next()) {
                manfacturers += "," + manfacturerResultSet.getString(2);
            }
            manfacturersTextField.setText(manfacturers);

            ResultSet supplierResultSet = DataManager.getInstance().getsupplier(medicine.getsupplierName());
            if(supplierResultSet.next()) {
                supplierNameTextField.setText(supplierResultSet.getString(1));
                supplierAddressTextField.setText(supplierResultSet.getString(2));
                supplierPhoneTextField.setText(supplierResultSet.getString(3));
            }
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
