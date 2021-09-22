package com.boymask.city.merci;

public class VoceInventario {


    private TipoMerce tipo;
    private int giacenza;

    public VoceInventario(TipoMerce tipo) {
        this.tipo = tipo;
    }
    public VoceInventario(TipoMerce tipo, int v) {
        this.tipo = tipo;
        this.giacenza=v;
    }
    public int getGiacenza() {
        return giacenza;
    }

    public void setGiacenza(int giacenza) {
        this.giacenza = giacenza;
    }
    public void increase(int n) {
        this.giacenza +=n;
    }
    public TipoMerce getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "VoceInventario{" +
                "tipo=" + tipo +
                ", giacenza=" + giacenza +
                '}';
    }
}
