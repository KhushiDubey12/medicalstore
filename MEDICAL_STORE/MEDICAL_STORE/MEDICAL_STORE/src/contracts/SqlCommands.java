package contracts;

public class SqlCommands {

    public static final String SALES = "total_sales";
    public static final String TOTAL_medicineS = "medicines";
    public static final String SEARCH_USERNAME = "SELECT " + DBContract.User.USER_NAME_COLUMN +
            " FROM " + DBContract.USER_TABLE +" WHERE " +  DBContract.User.USER_NAME_COLUMN  + " = ?;";
    public static final String GET_PASSWORD = "SELECT " + DBContract.User.PASSWORD_COLUMN +
            " FROM " + DBContract.USER_TABLE +" WHERE " +  DBContract.User.USER_NAME_COLUMN  + " = ?;";
    public static final String INSERT_USER = "INSERT INTO " + DBContract.USER_TABLE + " VALUES(?, ?, ?, ?, ?, ?, ?, ?);" ;
    public static final String GET_USER = "SELECT * FROM " + DBContract.USER_TABLE + " WHERE " + DBContract.User.USER_NAME_COLUMN + " = ?;";
    public static final String Search_For_medicine = "select b.idd, medicine_name, category, manfacture_date, selling_price, available_quantity, strength, supplier_name from medicine b where "+
            "( ? = '' OR b.idd = ?)" +
            " and ( ? = '' OR b.medicine_name = ?)" +
            " and ( ? = '' OR b.category = ?)" +
            " and ( ? = '' OR b.manfacture_date = ?)" +
            " and ( ? = '' OR b.selling_price = ?)" +
            " and ( ? = '' OR b.available_quantity = ?)" +
            " and ( ? = '' OR b.strength = ?)" +
            " and ( ? = '' OR b.supplier_name = ?)" +
            " and ( ? = '' OR EXISTS (select * from manfacturer  where manfacturer.idd = b.idd and manfacturer.manfacturer = ?) );";
    public static final String INSERT_medicine = "INSERT INTO " + DBContract.medicine_TABLE + " VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String INSERT_manfacturer = "INSERT INTO " + DBContract.manfacturer_TABLE + " VALUES(?, ?);";
    public static final String INSERT_supplier = "INSERT INTO " + DBContract.supplier_TABLE + " VALUES(?, ?, ?);";
    public static final String GET_medicine = "SELECT * FROM " + DBContract.medicine_TABLE + " WHERE " + DBContract.medicine.idd_COLUMN + " = ?;";
    public static final String GET_manfacturerS = "SELECT * FROM " + DBContract.manfacturer_TABLE + " WHERE " + DBContract.manfacturer.idd_COLUMN + " = ?;";
    public static final String GET_supplier = "SELECT * FROM " + DBContract.supplier_TABLE + " WHERE " + DBContract.supplier.NAME_COLUMN + " = ?;";
    public static final String DELETE_manfacturerS = "DELETE FROM " + DBContract.manfacturer_TABLE + " WHERE " + DBContract.manfacturer.idd_COLUMN + " = ?;";
    public static final String DELETE_medicine = "DELETE FROM " + DBContract.medicine_TABLE + " WHERE " + DBContract.medicine.idd_COLUMN + " = ?;";
    public static final String UPDATE_medicine_idd = "UPDATE " + DBContract.medicine_TABLE + " SET " +
            DBContract.medicine.idd_COLUMN + " = ? WHERE " + DBContract.medicine.idd_COLUMN + " = ?;";
    public static final String UPDATE_medicine_medicine_name = "UPDATE " + DBContract.medicine_TABLE + " SET " +
            DBContract.medicine.medicine_name_COLUMN + " = ? WHERE " + DBContract.medicine.idd_COLUMN + " = ?;";
    public static final String UPDATE_medicine_CATEGORY = "UPDATE " + DBContract.medicine_TABLE + " SET " +
            DBContract.medicine.CATEGORY_COLUMN + " = ? WHERE " + DBContract.medicine.idd_COLUMN + " = ?;";
    public static final String UPDATE_medicine_YEAR = "UPDATE " + DBContract.medicine_TABLE + " SET " +
            DBContract.medicine.manfacture_date_COLUMN + " = ? WHERE " + DBContract.medicine.idd_COLUMN + " = ?;";
    public static final String UPDATE_medicine_PRICE = "UPDATE " + DBContract.medicine_TABLE + " SET " +
            DBContract.medicine.SELLING_PRICE_COLUMN + " = ? WHERE " + DBContract.medicine.idd_COLUMN + " = ?;";
    public static final String UPDATE_medicine_AVAILABLE = "UPDATE " + DBContract.medicine_TABLE + " SET " +
            DBContract.medicine.AVAILABLE_COLUMN + " = ? WHERE " + DBContract.medicine.idd_COLUMN + " = ?;";
    public static final String UPDATE_medicine_strength = "UPDATE " + DBContract.medicine_TABLE + " SET " +
            DBContract.medicine.strength_COLUMN + " = ? WHERE " + DBContract.medicine.idd_COLUMN + " = ?;";
    public static final String UPDATE_medicine_supplier = "UPDATE " + DBContract.medicine_TABLE + " SET " +
            DBContract.medicine.supplier_NAME_COLUMN + " = ? WHERE " + DBContract.medicine.idd_COLUMN + " = ?;";
    
    public static final String UPDATE_medicine_quantity = "UPDATE " + DBContract.medicine_TABLE + " SET " +
            DBContract.medicine.AVAILABLE_COLUMN +" = " + DBContract.medicine.AVAILABLE_COLUMN  + " - ? WHERE " + DBContract.medicine.idd_COLUMN + "= ?;";


    public static final String INSERT_PURCHASE = "INSERT INTO " + DBContract.PURCHASE_TABLE +" (user_name, purchase_time, credit_card, credit_card_cvv, credit_card_expiration) VALUES(?, ?, ?, ?, ?);";
    public static final String INSERT_PURCHASE_DETAILS = "INSERT INTO purchase_detail VALUES (?, ?, ?);";
    public static final String LAST_ID = "SELECT LAST_INSERT_ID() from " + DBContract.PURCHASE_TABLE + " ;";


    public static final String PROMOTE_USER = "UPDATE " + DBContract.USER_TABLE + " SET " +
            DBContract.User.CREDENTIAL_COLUMN + " = 'manager' WHERE " + DBContract.User.USER_NAME_COLUMN + " = ?;";
    public static final String UPDATE_medicine = "UPDATE " + DBContract.medicine_TABLE + " SET " +
            DBContract.medicine.idd_COLUMN + " = ?, " +
            DBContract.medicine.medicine_name_COLUMN + " = ?, " +
            DBContract.medicine.CATEGORY_COLUMN + " = ?, " +
            DBContract.medicine.manfacture_date_COLUMN + " = ?, " +
            DBContract.medicine.SELLING_PRICE_COLUMN + " = ?, " +
            DBContract.medicine.AVAILABLE_COLUMN + " = ?, " +
            DBContract.medicine.strength_COLUMN + " = ?, " +
            DBContract.medicine.supplier_NAME_COLUMN + " = ?" +
            " WHERE " + DBContract.medicine.idd_COLUMN + " = ?;";
    public static final String INSERT_ORDER = "INSERT INTO " + DBContract.ORDER_TABLE + "(" + DBContract.Order.USER_NAME_COLUMN + "," + DBContract.Order.ORDER_TIME_COLUMN + ")" + " VALUES(?, ?);";
    public static final String INSERT_ORDER_DETAIL = "INSERT INTO " + DBContract.ORDER_DETAIL_TABLE + " VALUES(?, ?, ?);";;
    public static final String GET_JOINED_ORDERS = "SELECT " +
            " o." + DBContract.Order.ORDER_ID_COLUMN + ", " + DBContract.Order.USER_NAME_COLUMN + ", " +
            DBContract.Order.ORDER_TIME_COLUMN + ", " + DBContract.OrderDetail.medicine_idd_COLUMN + ", " +
            DBContract.OrderDetail.QUANTITY_COLUMN
            + " FROM " + DBContract.ORDER_TABLE
            + " o JOIN " + DBContract.ORDER_DETAIL_TABLE + " d ON o." + DBContract.Order.ORDER_ID_COLUMN + " = d." + DBContract.OrderDetail.ORDER_ID_COLUMN + ";";
    public static final String DELETE_ORDER = "DELETE FROM " + DBContract.ORDER_TABLE + " WHERE " + DBContract.Order.ORDER_ID_COLUMN + " = ? AND " + DBContract.Order.USER_NAME_COLUMN + " = ?;";
    public static final String DELETE_ORDER_DETAIL = "DELETE FROM " + DBContract.ORDER_DETAIL_TABLE + " WHERE " + DBContract.OrderDetail.ORDER_ID_COLUMN + " = ? AND " + DBContract.OrderDetail.medicine_idd_COLUMN + " = ?;";
    public static final String INCREASE_medicine_AVAILABLE = "UPDATE " + DBContract.medicine_TABLE + " SET " +
            DBContract.medicine.AVAILABLE_COLUMN + " = " + DBContract.medicine.AVAILABLE_COLUMN + " + ? WHERE " + DBContract.medicine.idd_COLUMN + " = ?;";
    public static final String UPDATE_CUSTOMER = "UPDATE " + DBContract.USER_TABLE + " SET " + DBContract.User.USER_NAME_COLUMN + "=?," +
            DBContract.User.PASSWORD_COLUMN + "=?," +  DBContract.User.FIRST_NAME_COLUMN + "=?," + DBContract.User.LAST_NAME_COLUMN + "=?," +
            DBContract.User.PHONE_COLUMN + "=?," + DBContract.User.ADDRESS_COLUMN + "=?," + DBContract.User.CREDENTIAL_COLUMN + "=?," +
            DBContract.User.EMAIL_COLUMN + "=?" + " WHERE " + DBContract.User.USER_NAME_COLUMN + " = ?;";
    public static final String LAST_MONTH_ALL_SALES = "SELECT (medicine.selling_price * purchase_detail.quantity) AS sales, purchase.purchase_time "+
            "FROM medicine " +
            "JOIN purchase_detail ON medicine.idd = purchase_detail.medicine_idd " +
            "JOIN purchase ON purchase_detail.purchase_id = purchase.purchase_id " +
            "HAVING YEAR(purchase.purchase_time) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) " +
            "AND MONTH(purchase.purchase_time) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH)";
    public static final String LAST_MONTH_TOTAL_SALES = "SELECT SUM(res.sales) as " + SALES + " FROM (" + LAST_MONTH_ALL_SALES + ") res;";
    public static final String TOP_SELLING_medicineS = "SELECT " + DBContract.PurchaseDetail.medicine_idd_COLUMN + ", SUM(" + DBContract.PurchaseDetail.QUANTITY_COLUMN + ") TotalQuantity"
            + " FROM " + DBContract.PURCHASE_DETAIL_TABLE + " d"
            + " JOIN " + DBContract.PURCHASE_TABLE + " p"
            + " ON d." + DBContract.PurchaseDetail.PURCHASE_ID_COLUMN + " = p." + DBContract.Purchase.PURCHASE_ID_COLUMN
            + " WHERE " + DBContract.Purchase.PURCHASE_TIME_COLUMN + " >= ?"
            + " GROUP BY " + DBContract.PurchaseDetail.medicine_idd_COLUMN
            + " ORDER BY " + "TotalQuantity"
            + " DESC LIMIT 10;";
    public static final String TOP_FIVE_CUSTOMERS = "SELECT purchase.user_name, SUM(purchase_detail.quantity) as " + TOTAL_medicineS + " ,purchase.purchase_time "+
            "FROM purchase " +
            "JOIN purchase_detail ON purchase.purchase_id = purchase_detail.purchase_id " +
            "GROUP BY purchase.user_name " +
            "HAVING (YEAR(purchase.purchase_time) = YEAR(CURRENT_DATE - INTERVAL 2 MONTH) " +
            "AND MONTH(purchase.purchase_time) = MONTH(CURRENT_DATE - INTERVAL 2 MONTH)) " +
            "OR (YEAR(purchase.purchase_time) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) " +
            "AND MONTH(purchase.purchase_time) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH)) " +
            "OR (YEAR(purchase.purchase_time) = YEAR(CURRENT_DATE) " +
            "AND MONTH(purchase.purchase_time) = MONTH(CURRENT_DATE)) " +
            "ORDER BY SUM(purchase_detail.quantity) DESC " +
            "LIMIT 5";
}

