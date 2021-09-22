package com.boymask.city.infrastructure;

import com.boymask.city.merci.TipoMerce;

public class Order {
    private static int CURRIDORDINE = 0;
    private int idOrdine = 0;
    private int idEdificio;

    @Override
    public String toString() {
        return "Order{" +
                "idOrdine=" + idOrdine +
                ", idEdificio=" + idEdificio +
                ", tipoMerce=" + tipoMerce +
                '}';
    }

    private TipoMerce tipoMerce;

    public Order(int idEdificio, TipoMerce tipoMerce) {
        this.idEdificio = idEdificio;
        this.tipoMerce = tipoMerce;
        this.idOrdine = createIdOrder();
    }

    private synchronized int createIdOrder() {
        CURRIDORDINE++;
        return CURRIDORDINE;
    }


    public int getIdEdificio() {
        return idEdificio;
    }

    public TipoMerce getTipoMerce() {
        return tipoMerce;
    }

}
