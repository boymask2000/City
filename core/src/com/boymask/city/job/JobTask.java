package com.boymask.city.job;

import com.boymask.city.MovingObject;
import com.boymask.city.edifici.Edificio;
import com.boymask.city.merci.TipoMerce;

public class JobTask {
    private final TaskOperation op;
    private final Edificio target;
    private Object obj = null;

    public JobTask(TaskOperation op, Edificio target, Object obj) {
        this.op = op;
        this.target = target;
        this.obj = obj;
    }

    public void exec(MovingObject actor) {
        System.out.println("JobTask " + op);
        if (target == null) return;
        switch (op) {
            case VAI:
                actor.setTarget(target.getPosition());
                System.out.println(actor.getPosition() + " -> " + target.getPosition());
                actor.moveTo();
                break;
            case CARICA:
                TipoMerce tp=(TipoMerce)obj;
                boolean done=target.getFromInventario(tp);
                actor.notifyJob();
                break;
            case SCARICA:
                TipoMerce tp1=(TipoMerce)obj;
                target.addinInventario(tp1);
                actor.notifyJob();
                break;
        }

    }
}
