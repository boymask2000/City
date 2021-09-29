package com.boymask.city.infrastructure;

import com.boymask.city.merci.TipoMerce;

public class MerceDisponibile {

    private int idEdificio;

    @Override
    public String toString() {
        return "MerceDisponibile{" +
                "idEdificio=" + idEdificio +
                ", tipoMerce=" + tipoMerce +
                '}';
    }

    private TipoMerce tipoMerce;

    public MerceDisponibile(int idEdificio, TipoMerce tipoMerce) {
        this.idEdificio = idEdificio;
        this.tipoMerce = tipoMerce;
    }
    public int getIdEdificio() {
        return idEdificio;
    }

    public TipoMerce getTipoMerce() {
        return tipoMerce;
    }

}
