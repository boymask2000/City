package com.boymask.city;

import static com.boymask.city.edifici.Edificio.*;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.boymask.city.edifici.Edificio;
import com.boymask.city.job.Job;

public class MovingObject {


    private final City city;
    private ModelInstance modelInstance;
    private Vector3 movement;
    private Vector3 position;

    private static final float DELTA = 0.03f;


    private Vector3 target;
    private Job job;


    public MovingObject(City city, ModelInstance modelInstance) {
        this.modelInstance = modelInstance;
        this.city = city;


        position = modelInstance.transform.getTranslation(new Vector3());

    }

    private float precDist = 1000000;


    public void move() {
        movement=moveTo(job);
        if (movement == null) return;
        modelInstance.transform.translate(Terrain.moveOnTerrain(movement));

        position = modelInstance.transform.getTranslation(new Vector3());
        float dist = target.dst( position);

        //   if (dist < precDist) precDist = dist;
        if (dist < 0.1) {
            movement = null;
            target = null;
            System.out.println("hit");
            Edificio hit = getAllEdifici().getNearest(position);
            if(job!=null)
job.notifyTaskCompleted();
            notifyJob();
        }
    }
    public void setJob(Job job) {
        System.out.println(job);
        this.job = job;
        notifyJob();
    }

    public void notifyJob() {
        System.out.println("***************get notify !");
        System.out.println(getPosition());
        if (job == null) return;

       boolean completed= job.execTask();
       if(completed)notifyJobCompleted();
    }
    public void notifyJobCompleted() {

    }
 /*   public Vector3 moveTo1() {
        if(target==null)return null;
        position = modelInstance.transform.getTranslation(new Vector3());

        float minDist = 1000000;
        Vector3 bestMove=null;
        for (float dx = -DELTA; dx <= +DELTA; dx += DELTA)
            for (float dy = -DELTA; dy <= +DELTA; dy += DELTA)
                for (float dz = -DELTA; dz <= +DELTA; dz += DELTA) {
                    Vector3 newPos = new Vector3(position.x + dx, position.y + dy, position.z + dz);
                    float dist = newPos.dst(target);
                    if (dist < minDist) {
                        minDist = dist;
                        bestMove = newPos;
                    }
                }
System.out.println(bestMove);
        return bestMove;
    }*/

    public Vector3 moveTo(Job job) {
    //    this.job=job;
        if(target==null)return null;
        position = modelInstance.transform.getTranslation(new Vector3());
        float dx = target.x - position.x;
        float dy = target.y - position.y;
        float dz = target.z - position.z;

        int factX = dx > 0 ? 1 : -1;
        int factY = dy > 0 ? 1 : -1;
        int factZ = dz > 0 ? 1 : -1;
        if (dx < 0) dx = -dx;
        if (dy < 0) dy = -dy;
        if (dz < 0) dz = -dz;

        float max = 0;
        if (dx > max) max = dx;
        if (dy > max) max = dy;
        if (dz > max) max = dz;

        //  max/DELTA=dx/deltax;
        float deltax = factX * dx * DELTA / max;
        float deltay = factY * dy * DELTA / max;
        float deltaz = factZ * dz * DELTA / max;
        movement = new Vector3(deltax, deltay, deltaz);
       // System.out.println("Movement: " + movement);
        this.target = target;
return movement;
    }

    public Vector3 getPosition() {
        position = modelInstance.transform.getTranslation(new Vector3());
        return position;
    }

    public void setMovement(Vector3 movement) {
        this.movement = movement;
    }

    public ModelInstance getModelInstance() {
        return modelInstance;
    }

    public Vector3 getTarget() {
        return target;
    }

    public void setTarget(Vector3 target) {
        this.target = target;
    }
    public City getCity() {
        return city;
    }
}
