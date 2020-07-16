package contracts;

public class DBContract {
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "123456";

    public static final String DB_NAME = "medical_store";

    public static final String USER_TABLE = "USER";
    public static final String manfacturer_TABLE = "manfacturer";
    public static final String medicine_TABLE = "medicine";
    public static final String ORDER_TABLE = "ORDERS";
    public static final String ORDER_DETAIL_TABLE = "ORDER_DETAIL";
    public static final String supplier_TABLE = "supplier";
    public static final String PURCHASE_TABLE = "PURCHASE";
    public static final String PURCHASE_DETAIL_TABLE = "PURCHASE_DETAIL";

    public static class User{
        public static final String USER_NAME_COLUMN = "user_name";
        public static final String PASSWORD_COLUMN = "password";
        public static final String FIRST_NAME_COLUMN = "first_name";
        public static final String LAST_NAME_COLUMN = "last_name";
        public static final String PHONE_COLUMN = "phone";
        public static final String ADDRESS_COLUMN = "address";
        public static final String CREDENTIAL_COLUMN = "credential";
        public static final String EMAIL_COLUMN = "email";
    }
    public static class manfacturer{
        public static final String idd_COLUMN = "idd";
        public static final String manfacturer_COLUMN = "manfacturer";
    }
    public static class medicine{
        public static final String idd_COLUMN = "idd";
        public static final String medicine_name_COLUMN = "medicine_name";
        public static final String CATEGORY_COLUMN = "category";
        public static final String manfacture_date_COLUMN = "manfacture_date";
        public static final String SELLING_PRICE_COLUMN = "selling_price";
        public static final String AVAILABLE_COLUMN = "available_quantity";
        public static final String strength_COLUMN = "strength";
        public static final String supplier_NAME_COLUMN  = "supplier_name";
    }
    public static class Order{
        public static final String ORDER_ID_COLUMN = "order_id";
        public static final String USER_NAME_COLUMN = "user_name";
        public static final String ORDER_TIME_COLUMN = "order_time";
    }
    public static class OrderDetail{
        public static final String ORDER_ID_COLUMN = "order_id";
        public static final String QUANTITY_COLUMN = "quantity";
        public static final String medicine_idd_COLUMN = "medicine_idd";
    }

    public static class Purchase{
        public static final String PURCHASE_ID_COLUMN = "purchase_id";
        public static final String USER_NAME_COLUMN = "user_name";
        public static final String PURCHASE_TIME_COLUMN = "purchase_time";
        public static final String CREDIT_CARD_NUMBER_COLUMN = "credit_card";
        public static final String CREDIT_CARD_CVV_COLUMN = "credit_card_cvv";
        public static final String CREDIT_CARD_EXPIRATION_COLUMN = "credit_card_expiration";
    }
    public static class PurchaseDetail{
        public static final String PURCHASE_ID_COLUMN = "purchase_id";
        public static final String QUANTITY_COLUMN = "quantity";
        public static final String medicine_idd_COLUMN = "medicine_idd";
    }

    public static class supplier{
        public static final String NAME_COLUMN = "name";
        public static final String ADDRESS_COLUMN = "address";
        public static final String PHONE_COLUMN = "phone";
    }
}
