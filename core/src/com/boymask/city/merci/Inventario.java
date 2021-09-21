package com.boymask.city.merci;

import java.util.ArrayList;
import java.util.List;

public class Inventario {

    private List<VoceInventario> merci = new ArrayList<>();

    public void addMerce(TipoMerce tipo,int n) {
        VoceInventario v = null;
        for (VoceInventario voce : merci)
            if (voce.getTipo() == tipo) {
                v = voce;
                break;
            }
        if (v == null) {
            v = new VoceInventario(tipo,n);
            merci.add(v);
        }
        v.increase(n);

    }
    public void addVoce(VoceInventario v ){
        merci.add(v);
    }

    public List<VoceInventario> getMerci() {
        return merci;
    }
}
