package com.boymask.city.merci;

public class Merce_Pane extends Merce {
    public Merce_Pane() {
        super(TipoMerce.PANE);

        VoceInventario v1 = new VoceInventario(TipoMerce.ACQUA, 1);
        VoceInventario v2 = new VoceInventario(TipoMerce.FARINA, 1);

        addMateriaPrima(v1);
        addMateriaPrima(v2);
    }
}
