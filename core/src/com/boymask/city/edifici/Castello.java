package com.boymask.city.edifici;

import com.badlogic.gdx.graphics.g3d.Model;
import com.boymask.city.City;

public class Castello extends Deposito{
    public Castello( City city, int x, int y, int z) {

        super(TipoEdificio.CASTELLO, city, x, y, z);
    }
}
