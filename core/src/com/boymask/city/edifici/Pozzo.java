package com.boymask.city.edifici;

import com.boymask.city.City;
import com.boymask.city.core.LevelScreen;
import com.boymask.city.core.Stage3D;
import com.boymask.city.merci.Merce_Acqua;
import com.boymask.city.merci.Merce_Pane;

public class Pozzo extends EdificioProduzione{
    public Pozzo(int x, int y, int z, Stage3D st, LevelScreen lvl) { //
        super(TipoEdificio.POZZO,  lvl,  new Merce_Acqua(),x, y,z);

    }
}

