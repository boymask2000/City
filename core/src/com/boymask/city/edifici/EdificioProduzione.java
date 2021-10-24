package com.boymask.city.edifici;

import com.badlogic.gdx.graphics.g3d.Model;
import com.boymask.city.City;
import com.boymask.city.core.LevelScreen;
import com.boymask.city.infrastructure.MerceDisponibile;
import com.boymask.city.infrastructure.Order;
import com.boymask.city.merci.Inventario;
import com.boymask.city.merci.Merce;
import com.boymask.city.merci.TipoMerce;
import com.boymask.city.merci.VoceInventario;

import java.util.ArrayList;
import java.util.List;

public class EdificioProduzione extends Edificio {

    private Inventario ordiniFatti = new Inventario();

    private Merce tipoMerceProdotte = null;

    public EdificioProduzione(TipoEdificio tipo, City city, Merce tipoMerceProdotte, int x, int y, int z) {
        super(tipo, city, x, y, z);
        this.tipoMerceProdotte = tipoMerceProdotte;


    }


    @Override
    public void produci() {
        Thread t = new Thread() {
            public void run() {
                while (true) {
                    try {
                        if (tipoMerceProdotte == null) {
                            System.out.println(tipoEdificio + " tipo merce non spacificata");
                            sleep(5000);
                            continue;
                        } // Non è stata specificata la merce da produrre

                        if ( inventario.getGiacenza(tipoMerceProdotte.getTipo()) >= maxInventario) {
                            System.out.println(tipoEdificio + " max inventario");
                            sleep(5000);
                            continue;
                        }

                        if (!checkMateriePrime()) {
                            System.out.println(tipoEdificio + " no materie prime");
                            creaOrdini();
                            sleep(5000);
                            continue;
                        }

                        //--- Ora posso produrre
                        final int nsecs = tipoMerceProdotte.getTempoProduzione();

                        sleep(1000 * nsecs);
                        decurtaMateriePrime();
                        incrementaMerciInInventario();
                        aggiornaInventarioGlobale(tipoMerceProdotte.getTipo());

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();


    }

    private void incrementaMerciInInventario() {
        inventario.addMerce(tipoMerceProdotte.getTipo(), 1);
    }

    private void aggiornaInventarioGlobale(TipoMerce tipoMerce) {
        MerceDisponibile md = new MerceDisponibile(getIdEdificio(), tipoMerce);
        city.getInventarioGlobale().addMerce(md);
    }

    private void creaOrdini() {
        Inventario matPrime = tipoMerceProdotte.getMateriePrime();
        for (VoceInventario v : matPrime.getMerci()) {
            int need = inventario.getGiacenza(v.getTipo()) + //
                    ordiniFatti.getGiacenza(v.getTipo())
                    - 5;
            while (need < 0) {
                Order order = new Order(getIdEdificio(), v.getTipo());
                city.getOrderManager().putOrder(order);
                ordiniFatti.addMerce(v.getTipo(), 1);
                need++;
            }
        }

    }

    @Override
    public void addinInventario(TipoMerce t) {
        super.addinInventario(t);
        VoceInventario v = new VoceInventario(t, 1);
        ordiniFatti.decurta(v);
    }


    private void decurtaMateriePrime() {
        Inventario matPrime = tipoMerceProdotte.getMateriePrime();
        for (VoceInventario v : matPrime.getMerci()) {
            inventario.decurta(v);
        }
    }

    private boolean checkMateriePrime() {
        boolean ok = true;
        Inventario matPrime = tipoMerceProdotte.getMateriePrime();
        for (VoceInventario v : matPrime.getMerci()) {
            if (!inventario.in(v)) return false;
        }
        return true;
    }
}
