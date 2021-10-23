package com.boymask.city.job;

import com.badlogic.gdx.math.Vector3;
import com.boymask.city.core.Mover;
import com.boymask.city.edifici.Edificio;

public class JobTask_VAI extends JobTask{

    private Vector3 target;
    private final Mover mover;
    private Vector3 targetPosition;
    private Vector3 velocityVec;

    public JobTask_VAI(Edificio target, Mover mover) {

        this.target = target.getPosition();
        this.targetPosition = target.getPosition();
        this.mover = mover;
    }

    public void exec() {
        
    }
    public void setTarget(int x, int y, int z) {
        target = new Vector3(x, y, z);
        Vector3 pos = mover.getPosition();
        int dx = (int) (x - pos.x);
        int dy = (int) (y - pos.y);
        int dz = (int) (z - pos.z);

        velocityVec = new Vector3(dx, dy, dz);
    }

    public void setTarget(Vector3 pos) {
        int dx = (int) (pos.x);
        int dy = (int) (pos.y);
        int dz = (int) (pos.z);

        setTarget(dx, dy, dz);
    }
}
