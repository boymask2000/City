package com.boymask.city;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

public class CameraPosition {

    private PerspectiveCamera camera;
    private Vector3 cameraPosition=new Vector3(30,30,30);
    private Vector3 cameraLookAt=new Vector3(0,0,0);

    public CameraPosition(){
        camera = new PerspectiveCamera(75, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        update();
    }
    public void update(){
        camera.position.set(cameraPosition);
        camera.lookAt(cameraLookAt);
        camera.near = 0.1f;
        camera.far = 600f;
    }



    public void setCameraLookAt(Vector3 cameraLookAt) {

        this.cameraLookAt = cameraLookAt;
        update();
    }
    public void setCameraPosition(Vector3 cameraPosition) {

        this.cameraPosition = cameraPosition;
        update();
    }
    public Vector3 getCameraPosition() {
        return cameraPosition;
    }
    public Vector3 getCameraLookAt() {
        return cameraLookAt;
    }


    public PerspectiveCamera getCamera() {
        return camera;
    }



}
