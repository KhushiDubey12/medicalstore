package model;

import contracts.DBContract;
import contracts.SqlCommands;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static DataManager uniqueInstance;

    private DataManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://127.0.0.1/" + DBContract.DB_NAME+"?useSSL=false", DBContract.DB_USERNAME, DBContract.DB_PASSWORD);
           connect.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getException().getMessage());
        }
    }

    public static DataManager getInstance() {
        if (uniqueInstance == null) {
            return uniqueInstance = new DataManager();
        }
        return uniqueInstance;
    }

    public boolean deleteInstance() {
        uniqueInstance = null;
        return close();
    }

    private boolean close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return false;
        }
    }

    public boolean searchForUser(String userName) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.SEARCH_USERNAME);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            ArrayList<String> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(resultSet.getString(DBContract.User.USER_NAME_COLUMN));
            }
            if (users.size() == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean insertCustomer(Customer customer) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.INSERT_USER);
            preparedStatement.setString(1, customer.getUserName());
            preparedStatement.setString(2, customer.getHashedPassword());
            preparedStatement.setString(3, customer.getFirstName());
            preparedStatement.setString(4, customer.getLastName());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getAddress());
            preparedStatement.setString(7, customer.getCredential());
            preparedStatement.setString(8, customer.getEmail());
            int cnt = preparedStatement.executeUpdate();
            if (cnt == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUserHashedPassword(String userName) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.GET_PASSWORD);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            String password = "";
            while (resultSet.next()) {
                password = resultSet.getString(DBContract.User.PASSWORD_COLUMN);
            }
            return password;
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public HashMap<String, String> getUser(String userName) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.GET_USER);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            HashMap<String, String> userData = new HashMap<>();
            while (resultSet.next()) {
                userData.put(DBContract.User.USER_NAME_COLUMN, resultSet.getString(DBContract.User.USER_NAME_COLUMN));
                userData.put(DBContract.User.PASSWORD_COLUMN, resultSet.getString(DBContract.User.PASSWORD_COLUMN));
                userData.put(DBContract.User.FIRST_NAME_COLUMN, resultSet.getString(DBContract.User.FIRST_NAME_COLUMN));
                userData.put(DBContract.User.LAST_NAME_COLUMN, resultSet.getString(DBContract.User.LAST_NAME_COLUMN));
                userData.put(DBContract.User.ADDRESS_COLUMN, resultSet.getString(DBContract.User.ADDRESS_COLUMN));
                userData.put(DBContract.User.PHONE_COLUMN, resultSet.getString(DBContract.User.PHONE_COLUMN));
                userData.put(DBContract.User.CREDENTIAL_COLUMN, resultSet.getString(DBContract.User.CREDENTIAL_COLUMN));
                userData.put(DBContract.User.EMAIL_COLUMN, resultSet.getString(DBContract.User.EMAIL_COLUMN));
            }
            return userData;
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public ResultSet getAllmedicine() {
        try {
            preparedStatement = connect.prepareStatement("select * from medicine");
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet searchFormedicine(ArrayList<String> searchParameters) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.Search_For_medicine);
            int index = 1;
            for (int i = 0; i < searchParameters.size(); i++) {
                preparedStatement.setString(index++, searchParameters.get(i));
                preparedStatement.setString(index++, searchParameters.get(i));
            }
            int x = 0;
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertmedicine(medicine medicine) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.INSERT_medicine);
            preparedStatement.setString(1, medicine.getidd());
            preparedStatement.setString(2, medicine.getmedicine_name());
            preparedStatement.setString(3, medicine.getCategory());
            preparedStatement.setString(4, medicine.getYear());
            preparedStatement.setString(5, medicine.getSellingPrice());
            preparedStatement.setString(6, medicine.getAvailQuantity());
            preparedStatement.setString(7, medicine.getstrength());
            preparedStatement.setString(8, medicine.getsupplierName());
            int cnt  = preparedStatement.executeUpdate();
            if(cnt == 1){
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertmanfacturer(manfacturer manfacturer, String idd) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.INSERT_manfacturer);
            preparedStatement.setString(1, idd);
            preparedStatement.setString(2, manfacturer.getName());
            int cnt = preparedStatement.executeUpdate();
            if (cnt == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertsupplier(supplier supplier){
        try{
            preparedStatement = connect.prepareStatement(SqlCommands.INSERT_supplier);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getAddress());
            preparedStatement.setString(3, supplier.getPhone());
            int cnt  = preparedStatement.executeUpdate();
            if(cnt == 1){
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean insertPurchase(ArrayList<String> parameters, HashMap<medicine, Integer> orders) {
        try {

            connect.setAutoCommit(false);
            PreparedStatement purchaseStatment = connect.prepareStatement(SqlCommands.INSERT_PURCHASE);
            PreparedStatement purchaseDetailsStatement = connect.prepareStatement(SqlCommands.INSERT_PURCHASE_DETAILS);
          
            for (int i = 0; i < parameters.size(); i++) {
                purchaseStatment.setString(i + 1, parameters.get(i));
            }
            purchaseStatment.executeUpdate();
            PreparedStatement lastId = connect.prepareStatement(SqlCommands.LAST_ID);
            ResultSet resultSet = lastId.executeQuery();
            resultSet.next();
            String id = resultSet.getString(1);
            System.out.println(id);

            for (Map.Entry<medicine, Integer> e : orders.entrySet()) {
                purchaseDetailsStatement.setString(1, id);
                purchaseDetailsStatement.setString(2, e.getKey().getidd());
                purchaseDetailsStatement.setString(3, String.valueOf(e.getValue()));
                purchaseDetailsStatement.executeUpdate();
                System.out.println( String.valueOf(e.getValue()));
                System.out.println(  e.getKey().getidd());
                DataManager.getInstance().updatemedquantity(e.getKey().getidd(), String.valueOf(e.getValue()));
            }
        } catch (SQLException e) {
          try {
                System.err.print("Transaction is being rolled back");
                connect.rollback();
            } catch (SQLException excep) {
                return false;
            }
            return false;
        } finally {
            try {
                connect.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        
        return true;
        
    }
   

    public ResultSet getmedicine(String idd){
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.GET_medicine);
            preparedStatement.setString(1, idd);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getmanfacturers(String idd){
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.GET_manfacturerS);
            preparedStatement.setString(1, idd);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getsupplier(String name){
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.GET_supplier);
            preparedStatement.setString(1, name);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deletemanfacturers(String idd){
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.DELETE_manfacturerS);
            preparedStatement.setString(1, idd);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletemedicine(String idd){
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.DELETE_medicine);
            preparedStatement.setString(1, idd);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatemedicine(medicine medicine, String oldidd){
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.UPDATE_medicine);
            preparedStatement.setString(1, medicine.getidd());
            preparedStatement.setString(2, medicine.getmedicine_name());
            preparedStatement.setString(3, medicine.getCategory());
            preparedStatement.setString(4, medicine.getYear());
            preparedStatement.setString(5, medicine.getSellingPrice());
            preparedStatement.setString(6, medicine.getAvailQuantity());
            preparedStatement.setString(7, medicine.getstrength());
            preparedStatement.setString(8, medicine.getsupplierName());
            preparedStatement.setString(9, oldidd);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatemedicineidd(String text, String idd) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.UPDATE_medicine_idd);
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, idd);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatemedicinemedicine_name(String text, String idd) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.UPDATE_medicine_medicine_name);
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, idd);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatemedicineYear(String text, String idd) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.UPDATE_medicine_YEAR);
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, idd);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatemedicineCategory(String text, String idd) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.UPDATE_medicine_CATEGORY);
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, idd);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatemedicinePrice(String text, String idd) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.UPDATE_medicine_PRICE);
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, idd);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatemedicineAvail(String text, String idd) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.UPDATE_medicine_AVAILABLE);
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, idd);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatemedicinestrength(String text, String idd) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.UPDATE_medicine_strength);
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, idd);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatemedicinesupplierName(String text, String idd) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.UPDATE_medicine_supplier);
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, idd);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean promoteUser(String username){
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.PROMOTE_USER);
            preparedStatement.setString(1, username);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String insertOrder(String username, String orderTime){
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, orderTime);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            String orderId = "";
            if(resultSet.next()){
                System.out.println(resultSet.getString(1));
                orderId = resultSet.getString(1);

            }
            return orderId;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertOrderDetail(String orderID, String medicineidd, String quantity){
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.INSERT_ORDER_DETAIL);
            preparedStatement.setString(1, orderID);
            preparedStatement.setString(2, medicineidd);
            preparedStatement.setString(3, quantity);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getJoinedOrders(){
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.GET_JOINED_ORDERS);
            System.out.println(preparedStatement.toString());
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteOrder(String id, String username){
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.DELETE_ORDER);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, username);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrderDetail(String id, String idd){
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.DELETE_ORDER_DETAIL);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, idd);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(Customer customer) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.UPDATE_CUSTOMER);
            preparedStatement.setString(1, customer.getUserName());
            preparedStatement.setString(2, customer.getHashedPassword());
            preparedStatement.setString(3, customer.getFirstName());
            preparedStatement.setString(4, customer.getLastName());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getAddress());
            preparedStatement.setString(7, customer.getCredential());
            preparedStatement.setString(8, customer.getEmail());
            preparedStatement.setString(9, customer.getUserName());
            int cnt = preparedStatement.executeUpdate();
            if (cnt == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addToAvailQuantity(String idd, String value){
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.INCREASE_medicine_AVAILABLE);
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, idd);
            int cnt = preparedStatement.executeUpdate();
            if (cnt == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double getLastMonthTotalSales() {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.LAST_MONTH_TOTAL_SALES);
            resultSet = preparedStatement.executeQuery();
            double totalSales = 0;
            while (resultSet.next()) {
                totalSales = resultSet.getDouble(SqlCommands.SALES);
            }
            return totalSales;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ResultSet getTopSellingmedicines(String date) {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.TOP_SELLING_medicineS);
            preparedStatement.setString(1, date);
            System.out.println(preparedStatement.toString() + "topselling");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Pair<String,Integer>> getTopFiveCustomers() {
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.TOP_FIVE_CUSTOMERS);
            resultSet = preparedStatement.executeQuery();
            ArrayList<Pair<String, Integer>> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(new Pair<>(resultSet.getString(DBContract.Purchase.USER_NAME_COLUMN), resultSet.getInt(SqlCommands.TOTAL_medicineS)));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public ResultSet getpurchase() {
        try {
            preparedStatement = connect.prepareStatement("select * from purchase");
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean updatemedquantity(String idd, String value){
        try {
            preparedStatement = connect.prepareStatement(SqlCommands.UPDATE_medicine_quantity);
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, idd);
            int cnt = preparedStatement.executeUpdate();
            if (cnt == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
