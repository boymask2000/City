package com.boymask.city.edifici;

import com.boymask.city.City;
import com.boymask.city.merci.Merce_Pane;

public class Fornaio extends EdificioProduzione{
    public Fornaio( City city,int x, int y) { //
        super(TipoEdificio.FORNAIO, city, new Merce_Pane(),x, y);
    }

}

