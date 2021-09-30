package com.boymask.city.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.boymask.city.edifici.Edificio;
import com.boymask.city.edifici.EdificioProduzione;
import com.boymask.city.merci.VoceInventario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableDescrEdificio extends Table {
    List<Label> tm = new ArrayList<>();
    BitmapFont font = new BitmapFont();
    Label nomeEdificio=new Label("", new Label.LabelStyle(font, Color.WHITE));

    public TableDescrEdificio(){
        add(nomeEdificio);
        row();
    }

    public void show(Edificio ed) {
        nomeEdificio.setText(ed.getTipoEdificio().name());
        Map<String, Integer> vals = new HashMap<>();

        if( ed instanceof EdificioProduzione){
            EdificioProduzione prd = (EdificioProduzione) ed;
            VoceInventario ll = prd.getMerciInUscita();
            String name = ll.getTipo().name();
            Integer num = ll.getGiacenza();
            vals.put(name, num);
        }




        for (int i = 0; i < vals.size() - tm.size(); i++) {
            BitmapFont font = new BitmapFont();
            Label ll = new Label("", new Label.LabelStyle(font, Color.WHITE));
            add(ll);
            row();
            tm.add(ll);
        }

        int i=0;
        for(Map.Entry<String, Integer> en: vals.entrySet())
        {

            tm.get(i).setText(en.getKey()+" "+en.getValue());

            i++;
        }
    }

}
