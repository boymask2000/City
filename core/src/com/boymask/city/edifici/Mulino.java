package com.boymask.city.edifici;

import com.boymask.city.City;
import com.boymask.city.merci.Merce_Acqua;
import com.boymask.city.merci.Merce_Farina;

public class Mulino extends EdificioProduzione{
    public Mulino(City city, int x, int y) { //
        super(TipoEdificio.MULINO,  city,  new Merce_Farina(),x, y);
    }
}

