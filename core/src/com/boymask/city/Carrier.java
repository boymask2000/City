package com.boymask.city;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.boymask.city.edifici.Edificio;
import com.boymask.city.infrastructure.InventarioGlobale;
import com.boymask.city.infrastructure.MerceDisponibile;

public class Carrier extends MovingObject {
    private final InventarioGlobale inventarioGlobale;

    public Carrier(City city, ModelInstance modelInstance) {
        super(city, modelInstance);
        this.inventarioGlobale = city.getInventarioGlobale();

    }

    boolean working = false;

    public void workCycle() {
        new Thread() {
            @Override
            public void run() {
                while(true){
                work();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }}
            }
        }.start();

    }

    public void work() {
        if(working)return;
        System.out.println("WORK ");
        MerceDisponibile m = inventarioGlobale.getMerce();
        if (m == null) {
            System.out.println("non trovato ");
            return;
        }
        System.out.println(" trovato !");
        Edificio srcEdificio = Edificio.getEdificioById(m.getIdEdificio());
        moveTo(srcEdificio.getPosition());
        working = true;
        return;
    }
}
