package com.boymask.city.merci;

public class Merce_Farina extends Merce {
    public Merce_Farina() {
        super(TipoMerce.FARINA);

        VoceInventario v1 = new VoceInventario(TipoMerce.GRANO, 1);

        addMateriaPrima(v1);
    }
}
