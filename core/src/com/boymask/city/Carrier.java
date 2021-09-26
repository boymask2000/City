package com.boymask.city;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.boymask.city.edifici.Edificio;
import com.boymask.city.infrastructure.InventarioGlobale;
import com.boymask.city.infrastructure.MerceDisponibile;
import com.boymask.city.merci.TipoMerce;

public class Carrier extends MovingObject {


    private TipoMerce carico=null;
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
        Edificio trgEdificio = searchEdificioTarget(m.getTipoMerce());
        setTarget(srcEdificio.getPosition());

        working = true;
        return;
    }
    public TipoMerce getCarico() {
        return carico;
    }

    public void setCarico(TipoMerce carico) {
        this.carico = carico;
    }


    private Edificio searchEdificioTarget(TipoMerce tipoMerce) {
        return null;
    }


}
