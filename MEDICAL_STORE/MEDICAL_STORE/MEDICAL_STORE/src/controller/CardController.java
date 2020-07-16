package controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.medicine;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import contracts.SqlCommands;

public class CardController {
    private VBox card;
    private int quantity;
    private  ArrayList<String> selectedRow;
    private medicine medicine;
    public int medicinequantity;

    static HashMap<medicine, Integer> medicineQuantityHashMap = new HashMap<>();
    static HashMap<medicine, HBox> medicineHBoxHashMap = new HashMap<>();
    static HashMap<Button, medicine> increasemedicineHashMap = new HashMap<>();
    static HashMap<Button, medicine> decreasemedicineHashMap = new HashMap<>();
    static HashMap<Button, medicine> removemedicineHashMap = new HashMap<>();
    static HashMap<String, medicine> iddmedicineHashMap = new HashMap<>();
    static Label TotalPriceFiels;
    static double totalPruschase = 0;

    public CardController(final VBox cart, final String quantity, final ArrayList<String> selectedRow, Label prices) {
        this.card = cart;
        this.quantity = Integer.valueOf(quantity);
        this.selectedRow = selectedRow;
        medicine = new medicine(selectedRow);
        TotalPriceFiels = prices;
    }


    public void addToCard() {
        if(iddmedicineHashMap.containsKey(medicine.getidd())){
            medicine = iddmedicineHashMap.get(medicine.getidd());
            medicinequantity=medicineQuantityHashMap.get(medicine);
            totalPruschase -= Double.valueOf(medicine.getSellingPrice()) * medicineQuantityHashMap.get(medicine);
            ((Label)(medicineHBoxHashMap.get(medicine).getChildren().get(5))).setText(String.valueOf(this.quantity));
            ((Label)(medicineHBoxHashMap.get(medicine).getChildren().get(7))).setText(String.valueOf(Double.valueOf(medicine.getSellingPrice()) * this.quantity));
            medicineQuantityHashMap.put(medicine, this.quantity);
            totalPruschase += Double.valueOf(medicine.getSellingPrice()) * medicineQuantityHashMap.get(medicine);
            TotalPriceFiels.setText("Total Price:  " + totalPruschase);
            System.out.println(medicinequantity);
            if(this.quantity <= 0){
                removeOrder(medicine);
            }
            return;
        }
        medicineQuantityHashMap.put(medicine, this.quantity);
        HBox hbox = new HBox();

       Button increase = new Button();
        Button decrease = new Button();
        Button remove = new Button();
        increase.setText("Increase");
        decrease.setText("Decrease");
        remove.setText("Remove");

        increase.getStyleClass().add("hbox");
        decrease.getStyleClass().add("hbox");
        remove.getStyleClass().add("hbox");

        hbox.getChildren().add(increase);
        hbox.getChildren().add(decrease);
        hbox.getChildren().add(remove);  

        Label medicineNumber = new Label();
        Label medicinemedicine_name = new Label();
        Label medicinePirce = new Label();
        Label totalPrice = new Label();
        Label quantityString = new Label();

        medicineNumber.getStyleClass().add("hbox");
        medicinemedicine_name.getStyleClass().add("hbox");
        medicinePirce.getStyleClass().add("hbox");
        totalPrice.getStyleClass().add("hbox");
        quantityString.getStyleClass().add("hbox");


        medicineNumber.setText(medicine.getidd());
        medicinemedicine_name.setText(medicine.getmedicine_name());
        quantityString.setText(String.valueOf(this.quantity));
        medicinePirce.setText(medicine.getSellingPrice());
        totalPrice.setText(String.valueOf(Double.valueOf(medicine.getSellingPrice()) * this.quantity));
        totalPruschase += Double.valueOf(medicine.getSellingPrice()) * this.quantity;

        hbox.getChildren().add(medicineNumber);
        hbox.getChildren().add(medicinemedicine_name);
        hbox.getChildren().add(quantityString);
        hbox.getChildren().add(medicinePirce);
        hbox.getChildren().add(totalPrice);

      increasemedicineHashMap.put(increase, medicine);
        decreasemedicineHashMap.put(decrease, medicine);
        removemedicineHashMap.put(remove, medicine);
        medicineHBoxHashMap.put(medicine, hbox);
        increase.setOnAction(e -> increaseOrder(increasemedicineHashMap.get(increase)));
        decrease.setOnAction(e -> decreaseOrder(decreasemedicineHashMap.get(decrease)));
        remove.setOnAction(e -> removeOrder(removemedicineHashMap.get(remove)));
        card.getChildren().add(hbox);
        iddmedicineHashMap.put(medicine.getidd(), medicine);
        TotalPriceFiels.setText("Total Price:  " + totalPruschase);   

        if(Double.valueOf(quantityString.getText()) <= 0){
            removeOrder(medicine);
        }
       

    }


    private void removeOrder(medicine medicine) {
        for(int i = 0 ; i < card.getChildren().size(); i++){
            if(medicineHBoxHashMap.get(medicine) == card.getChildren().get(i)){
                card.getChildren().remove(medicineHBoxHashMap.get(medicine));
                break;
            }
        }
        totalPruschase -= Double.valueOf(medicine.getSellingPrice()) * medicineQuantityHashMap.get(medicine);
        TotalPriceFiels.setText("Total Price:  " + totalPruschase);
        medicineHBoxHashMap.remove(medicine);
        medicineQuantityHashMap.remove(medicine);
        iddmedicineHashMap.remove(medicine.getidd());
    }


    private void increaseOrder(medicine medicine) {
        medicineQuantityHashMap.put(medicine, medicineQuantityHashMap.get(medicine) + 1);
        HBox hBox = medicineHBoxHashMap.get(medicine);
        Label quantityString  = (Label) hBox.getChildren().get(5);
        quantityString.setText(String.valueOf(Integer.valueOf(quantityString.getText()) + 1));
        Label totalPrice  = (Label) hBox.getChildren().get(7);
        totalPruschase += Double.valueOf(medicine.getSellingPrice());
        totalPrice.setText(String.valueOf(Double.valueOf(medicine.getSellingPrice()) * medicineQuantityHashMap.get(medicine)));
        TotalPriceFiels.setText("Total Price:  " + totalPruschase);

    }


    private void decreaseOrder(medicine medicine) {
        medicineQuantityHashMap.put(medicine, medicineQuantityHashMap.get(medicine) - 1);
        HBox hBox = medicineHBoxHashMap.get(medicine);
        Label quantityString  = (Label) hBox.getChildren().get(5);
        quantityString.setText(String.valueOf(Integer.valueOf(quantityString.getText()) - 1));
        Label totalPrice  = (Label) hBox.getChildren().get(7);
        totalPrice.setText(String.valueOf(Double.valueOf(medicine.getSellingPrice()) * medicineQuantityHashMap.get(medicine)));
        totalPruschase -= Double.valueOf(medicine.getSellingPrice());
        TotalPriceFiels.setText("Total Price:  " + totalPruschase);
        if(Double.valueOf(quantityString.getText()) <= 0){
            removeOrder(medicine);
        }
    }
}
