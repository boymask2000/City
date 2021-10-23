package com.boymask.city;

import com.badlogic.gdx.math.Vector3;
import com.boymask.city.core.Mover;
import com.boymask.city.core.ObjModel;
import com.boymask.city.edifici.Edificio;
import com.boymask.city.infrastructure.MerceDisponibile;
import com.boymask.city.infrastructure.Order;
import com.boymask.city.infrastructure.OrderManager;
import com.boymask.city.job.Job;
import com.boymask.city.job.JobTask;
import com.boymask.city.job.TaskOperation;
import com.boymask.city.merci.TipoMerce;

import java.util.ArrayList;
import java.util.List;

public class Carrier extends Mover {

    private final OrderManager orderManager;
    private final City city;
    private TipoMerce carico = null;
    private Edificio trgEdificio;

    private enum Phase {IDLE, TAKE, DELIVER}

    ;

    private Phase currentPhase = Phase.IDLE;
    private Edificio currentTarget = null;

    public Carrier(int x, int y, int z, City city) {
        super(x, y, z, city.getMainStage3D());
        this.city = city;

        loadObjModel("edifici/obj/fence_wide.obj");
        this.orderManager = city.getOrderManager();
    }

    public static Carrier create(int x, int y, int z, City lvl) {

        Carrier r = new Carrier(x, y, z, lvl);

        r.workCycle();
        return r;
    }

    private boolean working = false;

    public void workCycle() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("loop WORK working: " + working);
                    work();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void work() {
        workByOrder();
    }

    public void workByOrder() {

        System.out.println("WORK ");
        Order order = orderManager.getNextOrder();

        if (order == null) {
            System.out.println("non trovato ");
            setWorking(false);
            return;
        }
        System.out.println("Cerrier trovato ordine : " + order);

        Edificio srcEdificio = searchFornitore(order.getTipoMerce());

        //setTarget(srcEdificio.getPosition());
        if (srcEdificio == null) {
            System.out.println("Non trovato fornitore  per " + order.getTipoMerce());
            orderManager.putOrder(order);
            setWorking(false);
            return;
        }
        System.out.println("Cerrier trovato fornitore : " + srcEdificio);
        //    setAcceleration(1);
        //  setSpeed(5);
        //     accelerateAtAngle(10);


        trgEdificio = Edificio.getEdificioById(order.getIdEdificio());

        MerceDisponibile md = new MerceDisponibile(trgEdificio.getIdEdificio(), order.getTipoMerce());

        setWorking(true);
        currentPhase = Phase.TAKE;
        currentTarget = srcEdificio;
        setTarget(srcEdificio.getPosition());
    }


    public TipoMerce getCarico() {
        return carico;
    }

    public void setCarico(TipoMerce carico) {
        this.carico = carico;
    }


    private Edificio searchFornitore(TipoMerce tipoMerce) {

        MerceDisponibile md = city.getInventarioGlobale().getMerce(tipoMerce);
        if (md != null) {
            Edificio ed = Edificio.getEdificioById(md.getIdEdificio());
            return ed;
        }
        return null;
    }

    public synchronized boolean isWorking() {
        return working;
    }

    public synchronized void setWorking(boolean working) {
        System.out.println("******  working --> " + working);
        this.working = working;
    }

    @Override
    public void targetHit() {

        switch (currentPhase) {
            case IDLE:
                break;
            case TAKE:
                currentPhase = Phase.DELIVER;
                currentTarget = trgEdificio;
                setTarget(trgEdificio.getPosition());
                break;
            case DELIVER:
                currentPhase = Phase.IDLE;
                currentTarget = null;
                break;
        }
    }
}
