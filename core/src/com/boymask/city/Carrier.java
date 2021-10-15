package com.boymask.city;

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

public class Carrier extends ObjModel {

    private final OrderManager orderManager;
    private final City city;
    private TipoMerce carico = null;

    public Carrier(int x, int y, int z, City city) {
        super(x, y, z, city.getMainStage3D());
        this.city=city;

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
            System.out.println("Non trovato fornitore  per "+order.getTipoMerce() );
            orderManager.putOrder(order);
            setWorking(false);
            return;
        }
        System.out.println("Cerrier trovato fornitore : " + srcEdificio);
        setAcceleration(0);
        setSpeed(5);



        Edificio trgEdificio = Edificio.getEdificioById(order.getIdEdificio());

        MerceDisponibile md = new MerceDisponibile(trgEdificio.getIdEdificio(), order.getTipoMerce());
        //    job = createJob(srcEdificio, trgEdificio, md);
        setWorking(true);


        return;
    }


    private Job createJob(Edificio srcEdificio, Edificio trgEdificio, MerceDisponibile m) {
        TaskOperation op1 = TaskOperation.VAI;
        JobTask jt1 = new JobTask(op1, srcEdificio, null);

        TaskOperation op2 = TaskOperation.CARICA;
        JobTask jt2 = new JobTask(op2, srcEdificio, m.getTipoMerce());

        TaskOperation op3 = TaskOperation.VAI;
        JobTask jt3 = new JobTask(op3, trgEdificio, null);

        TaskOperation op4 = TaskOperation.SCARICA;
        JobTask jt4 = new JobTask(op4, trgEdificio, m.getTipoMerce());

        List<JobTask> tasks = new ArrayList<>();
        tasks.add(jt1);
        tasks.add(jt2);
        tasks.add(jt3);
        tasks.add(jt4);

        //   Job job = new Job(this, tasks, false);


        return null;

    }

    public TipoMerce getCarico() {
        return carico;
    }

    public void setCarico(TipoMerce carico) {
        this.carico = carico;
    }


    private Edificio searchFornitore(TipoMerce tipoMerce) {

        MerceDisponibile md =city.getInventarioGlobale().getMerce(tipoMerce);
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
}
