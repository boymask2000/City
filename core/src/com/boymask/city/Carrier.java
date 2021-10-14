package com.boymask.city;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.boymask.city.core.LevelScreen;
import com.boymask.city.edifici.Edificio;
import com.boymask.city.infrastructure.AllEdifici;
import com.boymask.city.infrastructure.InventarioGlobale;
import com.boymask.city.infrastructure.MerceDisponibile;
import com.boymask.city.infrastructure.Order;
import com.boymask.city.infrastructure.OrderManager;
import com.boymask.city.job.Job;
import com.boymask.city.job.JobTask;
import com.boymask.city.job.TaskOperation;
import com.boymask.city.merci.Merce;
import com.boymask.city.merci.TipoMerce;

import java.util.ArrayList;
import java.util.List;

public class Carrier extends MovingObject {


 //   private final OrderManager ordermanager;
    private TipoMerce carico = null;
 //   private final InventarioGlobale inventarioGlobale;
    private Job job;

    public Carrier(int x, int y, int z, LevelScreen city, ModelInstance modelInstance) {
        super(x,y,z, city, modelInstance);
   //     this.inventarioGlobale = city.getInventarioGlobale();
    //    this.ordermanager = city.getOrderManager();

    }

    public static Carrier create(int x, int y, int z, LevelScreen lvl){
        ModelBuilder modelBuilder = new ModelBuilder();
        Model box = modelBuilder.createBox(2f, 2f, 2f,
                new Material(ColorAttribute.createDiffuse(Color.BLUE)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        ModelInstance mmm = new ModelInstance(box, 10, 20, 30);
        Carrier r = new Carrier(x,y,z, lvl,mmm );

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
        /*
        if (isWorking()) {
            setWorking(!job.isJobCompleted());
            return;
        }
        System.out.println("WORK ");
        Order order = ordermanager.getNextOrder();
if( order!=null)
    System.out.println("");

        if (order == null) {
            System.out.println("non trovato ");
            setWorking(false);
            return;
        }
        System.out.println("Cerrier trovato ordine : "+order);

        Edificio srcEdificio = searchFornitore(order.getTipoMerce());

        //setTarget(srcEdificio.getPosition());
        if (srcEdificio == null) {
            ordermanager.putOrder(order);
            setWorking(false);
            return;
        }
        System.out.println("Cerrier trovato fornitore : "+srcEdificio);
        Edificio trgEdificio = Edificio.getEdificioById(order.getIdEdificio());
        
        MerceDisponibile md = new MerceDisponibile(trgEdificio.getIdEdificio(), order.getTipoMerce());
        job = createJob(srcEdificio, trgEdificio, md);
        setWorking(true);

         */
        return;
    }



    @Override
    public synchronized void notifyJobCompleted() {

        setWorking(false);
        System.out.println("notifyJobCompleted working: " + working);
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

        Job job = new Job(this, tasks, false);

        setJob(job);
        return job;

    }

    public TipoMerce getCarico() {
        return carico;
    }

    public void setCarico(TipoMerce carico) {
        this.carico = carico;
    }


    private Edificio searchFornitore(TipoMerce tipoMerce) {
/*
        MerceDisponibile md = inventarioGlobale.getMerce(tipoMerce);
        if (md != null) {
            Edificio ed = Edificio.getEdificioById(md.getIdEdificio());
            return ed;
        }

 */
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
