package com.boymask.city.infrastructure;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.boymask.city.merci.TipoMerce;
import com.boymask.city.merci.VoceInventario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventarioGlobale {
    private List<MerceDisponibile> merci = new ArrayList<>();
    private Table table;

    public synchronized void addMerce(MerceDisponibile m) {

        merci.add(m);
        System.out.println("InventarioGlobale " + merci.size());
    }

    public synchronized MerceDisponibile getMerce() {
        if (merci.size() == 0) return null;
        MerceDisponibile m = merci.get(0);
        merci.remove(0);
        System.out.println("InventarioGlobale trovata merce" + m);
        return m;
    }

    public synchronized MerceDisponibile getMerce(TipoMerce tm) {
        if (merci.size() == 0) return null;
        for (MerceDisponibile md : merci)
            if (md.getTipoMerce() == tm) {
                merci.remove(md);
                return md;
            }
        return null;
    }

    List<Label> tm = new ArrayList<>();

    public void show() {
        Map<String, Integer> vals = new HashMap<>();
        for (int i = 0; i < merci.size(); i++) {
            String name = merci.get(i).getTipoMerce().name();
            Integer num = vals.get(name);
            if (num == null || num == 0)
                vals.put(name, 1);
            else vals.put(name, num + 1);
        }
        int num = merci.size();
        for (int i = 0; i < vals.size() - tm.size(); i++) {
            BitmapFont font = new BitmapFont();
            Label ll = new Label("", new Label.LabelStyle(font, Color.WHITE));
            table.add(ll);
            table.row();
            tm.add(ll);
        }

        int i = 0;
        for (Map.Entry<String, Integer> en : vals.entrySet()) {

            tm.get(i).setText(en.getKey() + " " + en.getValue());

            i++;
        }
    }

    public void setShow(Table table) {
        this.table = table;


    }


}
