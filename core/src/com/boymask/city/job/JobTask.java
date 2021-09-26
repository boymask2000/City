package com.boymask.city.job;

import com.boymask.city.MovingObject;
import com.boymask.city.edifici.Edificio;

public class JobTask {
    private final TaskOperation op;
    private final Edificio target;

    public JobTask(TaskOperation op, Edificio target) {
        this.op = op;
        this.target = target;

    }

    public void exec(MovingObject actor) {
        System.out.println("JobTask " + op);

        switch (op) {
            case VAI:
                actor.setTarget(target.getPosition());
                System.out.println(actor.getPosition()+" -> "+target.getPosition());
                actor.moveTo();
                break;
            case CARICA:
                break;
            case SCARICA:
                break;
        }

    }
}
