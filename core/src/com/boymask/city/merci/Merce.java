package com.boymask.city.merci;

public class Merce {

    private final TipoMerce tipo;
    private Inventario materiePrime = new Inventario();

    private int tempoProduzione=5; //Secondi

    public Merce(TipoMerce tipo){
        this.tipo=tipo;
    }

    public TipoMerce getTipo() {
        return tipo;
    }
    public void addMateriaPrima( VoceInventario v){
        materiePrime.addVoce(v);
    }
    public int getTempoProduzione() {
        return tempoProduzione;
    }

    public void setTempoProduzione(int tempoProduzione) {
        this.tempoProduzione = tempoProduzione;
    }
}
