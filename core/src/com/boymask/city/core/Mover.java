package com.boymask.city.core;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public abstract class Mover extends ObjModel {
    private Vector3 velocityVec;
    private Vector3 accelerationVec;
    private float acceleration;
    private float maxSpeed;
    private float deceleration;
    private Vector3 target;

    public Mover(int x, int y, int z, Stage3D st) {
        super(x, y, z, st);
        //   setAcceleration(400);
        //  setMaxSpeed(100);
        // setDeceleration(400);

        velocityVec = new Vector3(0, 0, 0);
        accelerationVec = new Vector3(0, 0, 0);
        acceleration = 0;
        maxSpeed = 1000;
        deceleration = 0;
    }

    public void setSpeed(float speed) {
// if length is zero, then assume motion angle is zero degrees
/*        if (velocityVec.len() == 0)
            velocityVec.set(speed, 0);
        else
            velocityVec.setLength(speed);*/

        if (velocityVec.len() != 0)
            velocityVec.setLength(speed);
    }

    public float getSpeed() {
        return velocityVec.len();
    }

    /*  public void setMotionAngle(float angle) {
          velocityVec.setAngle(angle);
      }

      public float getMotionAngle() {
          return velocityVec.angle();
      }
  */
    public boolean isMoving() {
        return (getSpeed() > 0);
    }

    public void setAcceleration(float acc) {
        acceleration = acc;
    }

  /*  public void accelerateAtAngle(float angle) {
        accelerationVec.add(new Vector2(acceleration, 0).setAngle(angle));
    }*/

    public void accelerateForward() {
        //    accelerateAtAngle( getRotation() );
    }

    public void setMaxSpeed(float ms) {
        maxSpeed = ms;
    }

    public void setDeceleration(float dec) {
        deceleration = dec;
    }

    public void applyPhysics(float dt) {

        //     velocityVec.add(accelerationVec.x * dt, accelerationVec.y * dt, accelerationVec.z * dt);
        float speed = getSpeed();

        if (accelerationVec.len() == 0)
            speed -= deceleration * dt;

        speed = MathUtils.clamp(speed, 0, maxSpeed);

        setSpeed(speed);

        moveBy(velocityVec.x * dt, velocityVec.y * dt, velocityVec.z * dt);
   /*     if (target != null) {
        float dst = getPosition().dst(target);
        System.out.println("DST: " + dst);
    }*/
        if( target!=null && getPosition().dst(target)<0.1) {
            target=null;
            velocityVec = new Vector3(0, 0, 0);

            targetHit();
        }

        //   accelerationVec.set(0, 0);
    }
    public abstract void targetHit();

    public void setTarget(int x, int y, int z) {
        target = new Vector3(x, y, z);
        Vector3 pos = getPosition();
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


    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);
    }
}
