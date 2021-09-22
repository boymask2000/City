package com.boymask.city.infrastructure;

import com.boymask.city.merci.TipoMerce;
import com.boymask.city.merci.VoceInventario;

import java.util.ArrayList;
import java.util.List;

public class InventarioGlobale {
    private List<MerceDisponibile> merci = new ArrayList<>();

    public void addMerce(MerceDisponibile m) {
        merci.add(m);
    }
}
