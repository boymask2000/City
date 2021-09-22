package com.boymask.city.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> ordini = new ArrayList<>();

    public synchronized void putOrder(Order order) {
        ordini.add(order);
        System.out.println("Ricevuto ordine "+order.toString());

    }

    public synchronized Order getNextOrder() {
        if (ordini.size() == 0) return null;
        Order ord = ordini.get(0);
        ordini.remove(0);
        return ord;
    }


}
