package com.boymask.city.edifici;

import com.badlogic.gdx.graphics.g3d.Model;
import com.boymask.city.City;
import com.boymask.city.infrastructure.MerceDisponibile;
import com.boymask.city.infrastructure.Order;
import com.boymask.city.merci.Inventario;
import com.boymask.city.merci.Merce;
import com.boymask.city.merci.TipoMerce;
import com.boymask.city.merci.VoceInventario;

import java.util.ArrayList;
import java.util.List;

public class EdificioProduzione extends Edificio {
    private int maxInventario = 5;

    private Inventario inventario = new Inventario();
    private Inventario ordiniFatti = new Inventario();
    private VoceInventario merciInUscita;

    private Merce tipoMerceProdotte = null;

    public EdificioProduzione(Model model, City city, Merce tipoMerceProdotte, int x, int y) {
        super(model, city, x, y);
        this.tipoMerceProdotte = tipoMerceProdotte;
        merciInUscita = new VoceInventario(tipoMerceProdotte.getTipo());
    }

    Thread t;

    public void produci() {
        t = new Thread() {
            public void run() {
                while (true) {
                    try {
                        if (tipoMerceProdotte == null) {
                            sleep(1000);
                            return;
                        } // Non è stata specificata la merce da produrre

                        if (merciInUscita.getGiacenza() >= maxInventario) {
                            sleep(1000);
                            return;
                        }

                        if (!checkMateriePrime()) {
                            creaOrdini();
                            sleep(1000);
                            return;
                        }

                        //--- Ora posso produrre
                        final int nsecs = tipoMerceProdotte.getTempoProduzione();

                        sleep(1000 * nsecs);
                        decurtaMateriePrime();
                        incrementaMerciInUscita();
                        aggiornaInventarioGlobale();

                        System.out.println(merciInUscita.toString());

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();


    }

    private void aggiornaInventarioGlobale() {
        MerceDisponibile md = new MerceDisponibile(getIdEdificio(), tipoMerceProdotte.getTipo());
        getCity().getInventarioGlobale().addMerce(md);
    }

    private void creaOrdini() {
        Inventario matPrime = tipoMerceProdotte.getMateriePrime();
        for (VoceInventario v : matPrime.getMerci()) {
            int need = inventario.getGiacenza(v.getTipo()) + //
                    ordiniFatti.getGiacenza(v.getTipo())
                    - v.getGiacenza();
            while (need < 0) {
                Order order = new Order(getIdEdificio(), v.getTipo());
                getCity().getOrderManager().putOrder(order);
                ordiniFatti.addMerce(v.getTipo(),1);
                need++;
            }
        }

    }

    public void produci2() {
        if (tipoMerceProdotte == null) return; // Non è stata specificata la merce da produrre

        if (merciInUscita.getGiacenza() >= maxInventario) return;
        if (!checkMateriePrime()) return;

        //--- Ora posso produrre
        final int nsecs = tipoMerceProdotte.getTempoProduzione();

        if (t != null && t.isAlive()) {
            System.out.println("VIVO");
            return;
        }
        System.out.println("ok");

        t = new Thread() {
            public void run() {
                try {
                    sleep(1000 * nsecs);
                    decurtaMateriePrime();
                    incrementaMerciInUscita();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        t.start();


    }

    private void incrementaMerciInUscita() {
        merciInUscita.increase(1);
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

    public void addinInventario(TipoMerce t) {
        inventario.addMerce(t, 1);
    }
}
