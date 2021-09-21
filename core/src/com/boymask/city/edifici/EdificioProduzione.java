package com.boymask.city.edifici;

import com.badlogic.gdx.graphics.g3d.Model;
import com.boymask.city.merci.Inventario;

public class EdificioProduzione extends Edificio{
    private Inventario inventario = new Inventario();

    public EdificioProduzione(Model model, int x, int y) {
        super(model, x, y);
    }
}
