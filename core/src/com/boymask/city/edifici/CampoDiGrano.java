package com.boymask.city.edifici;

import com.boymask.city.City;
import com.boymask.city.merci.Merce_Acqua;
import com.boymask.city.merci.Merce_Grano;

public class CampoDiGrano extends EdificioProduzione {
    public CampoDiGrano(City city, int x, int y) {
        super(TipoEdificio.CAMPO_GRANO,  city,  new Merce_Grano(),x, y);
    }
}
