package com.boymask.city;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class MovingObject {
    private ModelInstance modelInstance;
    private Vector3 movement;
    private Vector3 position;

    private static final float DELTA = 0.1f;
    private Vector3 target;

    public MovingObject(ModelInstance modelInstance) {
        this.modelInstance = modelInstance;


        position = modelInstance.transform.getTranslation(new Vector3());

    }

   private float precDist = 1000000;

    public void move() {
        if (movement == null) return;
        modelInstance.transform.translate(Terrain.moveOnTerrain(movement));

        position = modelInstance.transform.getTranslation(new Vector3());
        float dist = Terrain.distance(target, position);

        if (dist < precDist) precDist = dist;
        if (dist > precDist) {
            movement = null;
            target = null;
        }
    }

    public void moveTo(Vector3 target) {
        position = modelInstance.transform.getTranslation(new Vector3());
        float dx = target.x - position.x;
        float dy = target.y - position.y;
        float dz = target.z - position.z;

        float max = 0;
        if (dx > max) max = dx;
        if (dy > max) max = dy;
        if (dz > max) max = dz;

        //  max/DELTA=dx/deltax;
        float deltax = dx * DELTA / max;
        float deltay = dy * DELTA / max;
        float deltaz = dz * DELTA / max;
        movement = new Vector3(deltax, deltay, deltaz);

        this.target = target;

    }

    public void setMovement(Vector3 movement) {
        this.movement = movement;
    }

    public ModelInstance getModelInstance() {
        return modelInstance;
    }
}
