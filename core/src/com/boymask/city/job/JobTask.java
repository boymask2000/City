package com.boymask.city.job;

import com.badlogic.gdx.math.Vector3;
import com.boymask.city.MovingObject;
import com.boymask.city.edifici.Edificio;
import com.boymask.city.merci.TipoMerce;
import com.boymask.city.oggetti.TipoOggetto;

public class JobTask {
    private final TaskOperation op;
    private  Edificio target=null;
    private  TipoOggetto tipoOggetto=null;
    private Vector3 targetPosition;

    @Override
    public String toString() {
        return "JobTask{" +
                "op=" + op +
                ", target=" + target +
                '}';
    }

    private Object obj = null;
    private Job job;

    public JobTask(TaskOperation op, Edificio target, Object obj) {
        this.op = op;
        this.target = target;
        this.targetPosition=target.getPosition();
        this.obj = obj;
    }
    public JobTask(TaskOperation op, Vector3 targetPosition, Object obj) {
        this.op = op;
       // this.target = target;
        this.targetPosition=targetPosition;
        this.obj = obj;
    }
    public JobTask(TaskOperation op, Vector3 targetPosition, TipoOggetto tipoOggetto) {
        this.op = op;
        // this.target = target;
        this.targetPosition=targetPosition;
        this.tipoOggetto=tipoOggetto;
        this.obj = obj;
    }

    public void exec(MovingObject actor) {
        System.out.println("JobTask " + op);
        if (target == null) return;
        switch (op) {
            case CREA_OGGETTO:
                break;
            case VAI_A_POSITION:
                actor.setTarget(targetPosition);

                actor.moveTo(job);
                break;
            case VAI:
                actor.setTarget(targetPosition);

                actor.moveTo(job);
                break;
            case CARICA:
                TipoMerce tp=(TipoMerce)obj;
                boolean done=target.getFromInventario(tp);
              //  actor.notifyJob();
                job.notifyTaskCompleted();
                break;
            case SCARICA:
                TipoMerce tp1=(TipoMerce)obj;
                target.addinInventario(tp1);
            //    actor.notifyJob();
                job.notifyTaskCompleted();
                break;
        }

    }

    public void setJob(Job job) {
        this.job=job;
    }
}
