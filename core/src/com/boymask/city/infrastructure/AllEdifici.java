package com.boymask.city.infrastructure;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;
import com.boymask.city.edifici.Edificio;
import com.boymask.city.edifici.TipoEdificio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllEdifici {
    private static Map<TipoEdificio, Model> modelliEdifici = new HashMap<>();

    private Map<Integer, Edificio> map = new HashMap<>();

    public void addEdificio(int idEdificio, Edificio edificio) {
        map.put(idEdificio, edificio);
    }

    public Edificio getNearest(Vector3 pos) {

        int minDist = -1;
        Edificio best = null;
        for (Map.Entry<Integer, Edificio> entry : map.entrySet()) {

            int dist = getDist(entry.getValue(), pos);
            if (minDist < 0) minDist = dist;
            if (dist < minDist) {
                minDist = dist;
                best = entry.getValue();
            }
        }
        return best;
    }

    public List<Edificio> getListaEdifici() {
        List<Edificio> lista = new ArrayList<>();
        for (Map.Entry<Integer, Edificio> entry : map.entrySet()) {
            lista.add(entry.getValue());
        }
        return lista;
    }

    private int getDist(Edificio value, Vector3 v) {

        return (int) value.getPosition().dst(v);
    }

    public static void setModelloEdificio(TipoEdificio tipo, Model model) {
        modelliEdifici.put(tipo, model);
    }

    public static Model getModelloEdificio(TipoEdificio tipo) {
        return modelliEdifici.get(tipo);
    }
}
