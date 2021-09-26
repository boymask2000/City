package com.boymask.city.infrastructure;

import com.badlogic.gdx.math.Vector3;
import com.boymask.city.edifici.Edificio;

import java.util.HashMap;
import java.util.Map;

public class AllEdifici {
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

    private int getDist(Edificio value, Vector3 v) {

        return (int) value.getPosition().dst(v);
    }
}
