package com.boymask.city.infrastructure;

import com.boymask.city.merci.TipoMerce;
import com.boymask.city.merci.VoceInventario;

import java.util.ArrayList;
import java.util.List;

public class InventarioGlobale {
    private List<MerceDisponibile> merci = new ArrayList<>();

    public synchronized void addMerce(MerceDisponibile m) {

        merci.add(m);
        System.out.println("InventarioGlobale "+merci.size());
    }

    public synchronized MerceDisponibile getMerce() {
        if (merci.size() == 0) return null;
        MerceDisponibile m = merci.get(0);
        merci.remove(0);
        System.out.println("InventarioGlobale trovata merce"+m);
        return m;
    }
}
