package model;

import java.util.ArrayList;

public class medicine {
    private String idd;
    private String medicine_name;
    private String sellingPrice;
    private String category;
    private String year;
    private String availQuantity;
    private String supplierName;
    private String strength;
    public String idddd;


    public void setidd(final String idd) {
        this.idd = idd;
    }

    public void setmedicine_name(final String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public void setSellingPrice(final String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getidd() {

        return idd;
    }

    public String getmedicine_name() {
        return medicine_name;
    }
    public String getSellingPrice() {
        return sellingPrice;
    }

    public medicine(ArrayList<String> selectedRow) {
        this.idd = selectedRow.get(0);
        this.medicine_name = selectedRow.get(1);
       this.sellingPrice = selectedRow.get(4);
       idddd=selectedRow.get(0);
       System.out.println(idddd);
    }
     
    

    public medicine(String idd, String medicine_name, String category, String year, String sellingPrice, String availQuantity, String strength, String supplierName) {
        this.idd = idd;
        this.medicine_name = medicine_name;
        this.sellingPrice = sellingPrice;
        this.category = category;
        this.year = year;
        this.availQuantity = availQuantity;
        this.supplierName = supplierName;
        this.strength = strength;
    }

    public String getCategory() {
        return category;
    }

    public String getYear() {
        return year;
    }

    public String getAvailQuantity() {
        return availQuantity;
    }


    public String getstrength() {
        return strength;
    }

    public String getsupplierName() {
        return supplierName;
    }

    @Override
    public String toString() {
        return "medicine{" +
                "idd='" + idd + '\'' +
                ", medicine_name='" + medicine_name + '\'' +
                ", sellingPrice='" + sellingPrice + '\'' +
                ", category='" + category + '\'' +
                ", year='" + year + '\'' +
                ", availQuantity='" + availQuantity + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", strength='" + strength + '\'' +
                '}';
    }
}
