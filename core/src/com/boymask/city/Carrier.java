package com.boymask.city;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.boymask.city.edifici.Edificio;
import com.boymask.city.infrastructure.AllEdifici;
import com.boymask.city.infrastructure.InventarioGlobale;
import com.boymask.city.infrastructure.MerceDisponibile;
import com.boymask.city.infrastructure.Order;
import com.boymask.city.job.Job;
import com.boymask.city.job.JobTask;
import com.boymask.city.job.TaskOperation;
import com.boymask.city.merci.TipoMerce;

import java.util.ArrayList;
import java.util.List;

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
        //setTarget(srcEdificio.getPosition());
        createJob(srcEdificio,trgEdificio,m);
        working = true;
        return;
    }
    private void createJob( Edificio srcEdificio,  Edificio trgEdificio ,MerceDisponibile m){
        TaskOperation op1=TaskOperation.VAI;
        JobTask jt1 = new JobTask(op1, srcEdificio,null);

        TaskOperation op2=TaskOperation.CARICA;
        JobTask jt2 = new JobTask(op2, srcEdificio,m.getTipoMerce());

        TaskOperation op3=TaskOperation.VAI;
        JobTask jt3 = new JobTask(op3, trgEdificio,null);

        TaskOperation op4=TaskOperation.SCARICA;
        JobTask jt4 = new JobTask(op4, srcEdificio,m.getTipoMerce());

        List<JobTask> tasks=new ArrayList<>();
        tasks.add(jt1);
        tasks.add(jt2);
        tasks.add(jt3);
        tasks.add(jt4);

        Job job = new Job(this, tasks, true);

        setJob(job);


    }
    public TipoMerce getCarico() {
        return carico;
    }

    public void setCarico(TipoMerce carico) {
        this.carico = carico;
    }


    private Edificio searchEdificioTarget(TipoMerce tipoMerce) {
        Order ord = getCity().getOrderManager().getNextOrder(tipoMerce);
        if(ord!=null){
            Edificio ed= Edificio.getEdificioById(ord.getIdEdificio());
            return ed;
        }
        return null;
    }


}
