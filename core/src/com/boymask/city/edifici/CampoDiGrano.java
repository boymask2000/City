package com.boymask.city.edifici;

import com.boymask.city.City;
import com.boymask.city.core.LevelScreen;
import com.boymask.city.core.Stage3D;
import com.boymask.city.merci.Merce_Acqua;
import com.boymask.city.merci.Merce_Grano;

public class CampoDiGrano extends EdificioProduzione {
    public CampoDiGrano(int x, int y, int z, Stage3D st, LevelScreen lvl) {
        super(TipoEdificio.CAMPO_GRANO, lvl,  new Merce_Acqua(),x, y,z);
    }

}
