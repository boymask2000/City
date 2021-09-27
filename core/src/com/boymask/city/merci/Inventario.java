package com.boymask.city.merci;

import java.util.ArrayList;
import java.util.List;

public class Inventario {

    private List<VoceInventario> merci = new ArrayList<>();

    public void addMerce(TipoMerce tipo, int n) {
        VoceInventario v = null;
        for (VoceInventario voce : merci)
            if (voce.getTipo() == tipo) {
                v = voce;
                break;
            }
        if (v == null) {
            v = new VoceInventario(tipo, n);
            merci.add(v);
        }
        v.increase(n);

    }

    public void dump() {

        for (VoceInventario m : merci)
            System.out.println(m.toString());
    }


    public boolean getMerce(TipoMerce t) {
        for (VoceInventario m : merci)
            if (m.getTipo() == t && m.getGiacenza() > 0) {
                m.setGiacenza(m.getGiacenza() - 1);
                return true;
            }
        return false;
    }

    public int getGiacenza(TipoMerce t) {
        for (VoceInventario m : merci)
            if (m.getTipo() == t) return m.getGiacenza();
        return 0;
    }

    public boolean in(VoceInventario v) {

        for (VoceInventario m : merci)
            if (v.getTipo() == m.getTipo()) {
                if (v.getGiacenza() <= m.getGiacenza()) return true;
                return false;
            }
        return false;
    }

    public void decurta(VoceInventario v) {
        for (VoceInventario m : merci)
            if (v.getTipo() == m.getTipo()) {
                m.setGiacenza(m.getGiacenza() - v.getGiacenza());
            }
    }

    public void addVoce(VoceInventario v) {
        merci.add(v);
    }

    public List<VoceInventario> getMerci() {
        return merci;
    }


}
