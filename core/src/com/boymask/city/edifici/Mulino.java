package com.boymask.city.edifici;

import com.boymask.city.City;
import com.boymask.city.core.LevelScreen;
import com.boymask.city.core.Stage3D;
import com.boymask.city.merci.Merce_Acqua;
import com.boymask.city.merci.Merce_Farina;

public class Mulino extends EdificioProduzione{
    public Mulino(int x, int y, int z, Stage3D st, LevelScreen lvl) { //
        super(TipoEdificio.MULINO,  lvl,  new Merce_Farina(),x, y,z);
    }
}

