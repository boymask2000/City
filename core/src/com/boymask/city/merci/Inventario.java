package com.boymask.city.merci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventario {

    private Map<TipoMerce, VoceInventario> map = new HashMap<>();

    public void addMerce(TipoMerce tipo, int n) {
        VoceInventario v = map.get(tipo);
        if (v == null) {
            v = new VoceInventario(tipo, 0);
            map.put(tipo, v);
        }
        v.increase(n);

    }
    public VoceInventario getVoce( TipoMerce tipo){
        return map.get(tipo);
    }
    public void dump() {

        for (VoceInventario m :  getMerci() )
            System.out.println(m.toString());
    }

    public boolean getMerce(TipoMerce tipo) {
        VoceInventario v = map.get(tipo);
        if (v == null) {
            return false;
        }
        v.increase(-1);
        return true;

    }

    public int getGiacenza(TipoMerce tipo) {
        VoceInventario v = map.get(tipo);
        if (v == null) {
            return 0;
        }
        return v.getGiacenza();
    }

    public boolean in(VoceInventario v) {
        VoceInventario m = map.get(v.getTipo());
        if (m == null) {
            return false;
        }
        return v.getGiacenza() <= m.getGiacenza();
    }

    public void decurta(VoceInventario v) {
        VoceInventario m = map.get(v.getTipo());
        if (m == null) {
            return;
        }
        int diff = m.getGiacenza() - v.getGiacenza();
        if (diff < 0) diff = 0;
        m.setGiacenza(diff);


    }

    public void addVoce(VoceInventario v) {
        VoceInventario m = map.get(v.getTipo());
        if (m == null) {
            map.put(v.getTipo(), v);
            return;
        }
        m.setGiacenza(m.getGiacenza() + v.getGiacenza());

    }

    public List<VoceInventario> getMerci() {
        return new ArrayList<VoceInventario>(map.values());

    }
}
