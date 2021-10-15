package com.boymask.city.infrastructure;

import com.boymask.city.merci.TipoMerce;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> ordini = new ArrayList<>();

    public synchronized void putOrder(Order order) {
        ordini.add(order);
        System.out.println("Ricevuto ordine "+order.toString());

    }

    public synchronized Order getNextOrder() {
        for( Order ord: ordini)
            System.out.println("Ordini: "+ord);

        if (ordini.size() == 0) return null;
        Order ord = ordini.get(0);
        ordini.remove(0);
        return ord;
    }
    public synchronized Order getNextOrder(TipoMerce tm) {
        if (ordini.size() == 0) return null;
        for( Order ord: ordini)
            if( ord.getTipoMerce()==tm) {
                ordini.remove(ord);
                return ord;
            }
        return null;
    }

}
