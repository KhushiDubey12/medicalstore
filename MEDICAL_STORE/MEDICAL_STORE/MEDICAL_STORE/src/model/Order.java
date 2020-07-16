package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    HashMap<medicine, Integer> orders = new HashMap<>();

    public void setOrders(final HashMap<medicine, Integer> orders) {
        this.orders = orders;
    }

    public HashMap<medicine, Integer> getOrders() {

        return orders;
    }
}
