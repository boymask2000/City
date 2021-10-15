package com.boymask.city.edifici;

import com.boymask.city.City;
import com.boymask.city.core.LevelScreen;
import com.boymask.city.core.Stage3D;
import com.boymask.city.merci.Merce_Pane;

public class Fornaio extends EdificioProduzione{
    public Fornaio(int x, int y, int z, Stage3D st, City lvl) { //
        super(TipoEdificio.FORNAIO,  lvl, new Merce_Pane(),x, y,z);
    }

}

