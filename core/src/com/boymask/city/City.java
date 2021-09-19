package com.boymask.city;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.boymask.city.street.ReteStradale;
import com.boymask.city.street.StreetBlock;

import java.util.ArrayList;
import java.util.List;

public class City extends ApplicationAdapter implements InputProcessor {

    private CameraPosition cameraPosition;
    private ModelBatch modelBatch;
    private ModelBuilder modelBuilder;
    private Model box;

    private ModelInstance modelInstance;
    private ModelInstance modelInstance2;
    private Environment environment;

    private List<MovingObject> objs = new ArrayList<>();
    private UIManager uiManager;
    private InputMultiplexer multiplexer = new InputMultiplexer();


    private ReteStradale reteStradale;


    @Override
    public void create() {
        cameraPosition = new CameraPosition();
        uiManager = new UIManager(this);
        Stage input = uiManager.createAll();
        multiplexer.addProcessor(input);

        modelBatch = new ModelBatch();
        modelBuilder = new ModelBuilder();
        box = modelBuilder.createBox(2f, 2f, 2f,
                new Material(ColorAttribute.createDiffuse(Color.BLUE)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);


        modelInstance = new ModelInstance(box, 0, 0, 0);
        modelInstance2 = new ModelInstance(box, 3, 0, 0);
        MovingObject mo = new MovingObject(modelInstance);
        MovingObject mo2 = new MovingObject(modelInstance2);


        objs.add(mo);
        objs.add(mo2);

        mo.moveTo(new Vector3(5, 5, 3));

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1f));

        multiplexer.addProcessor(this);

        Gdx.input.setInputProcessor(multiplexer);
        reteStradale = new ReteStradale(this, modelBuilder);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        Camera camera = cameraPosition.getCamera();
        camera.update();
        modelBatch.begin(camera);

        for (MovingObject i : objs) {
            modelBatch.render(i.getModelInstance(), environment);
            i.move();
        }
        //     StreetBuilder.grid(400,100);
        line();
        uiManager.render();
        modelBatch.end();
    }

    private void line() {
        ShapeRenderer shapeDebugger = new ShapeRenderer();
        Gdx.gl.glLineWidth(2);
        shapeDebugger.setProjectionMatrix(cameraPosition.getCamera().combined);
        shapeDebugger.begin(ShapeRenderer.ShapeType.Line);
        shapeDebugger.setColor(Color.RED);
        shapeDebugger.line(Gdx.graphics.getWidth() / 2, 0, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
        shapeDebugger.end();
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean keyDown(int keycode) {
        Camera camera = cameraPosition.getCamera();
        if (keycode == Input.Keys.LEFT)
            camera.rotateAround(new Vector3(0f, 0f, 0f),
                    new Vector3(0f, 1f, 0f), 1f);
        if (keycode == Input.Keys.RIGHT)
            camera.rotateAround(new Vector3(0f, 0f, 0f),
                    new Vector3(0f, 1f, 0f), -1f);

        if (keycode == Input.Keys.UP)
            camera.translate(-0.1f, 0, 0);
        if (keycode == Input.Keys.DOWN)
            camera.translate(0.1f, 0, 0);

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    public MovingObject addActor(Model mod, int screenX, int screenY) {
        Vector3 tmpVector = new Vector3();
        Ray ray = cameraPosition.getCamera().getPickRay(screenX, screenY);
        final float distance = -ray.origin.y / ray.direction.y;
        tmpVector.set(ray.direction).scl(distance).add(ray.origin);
        modelInstance.transform.setTranslation(tmpVector);

        int DELTA=2;
        tmpVector.x = (int) (tmpVector.x / DELTA);
        tmpVector.x *= DELTA;
        tmpVector.y = (int) (tmpVector.y / DELTA);
        tmpVector.y *= DELTA;
        tmpVector.z = (int) (tmpVector.z / DELTA);
        tmpVector.z *= DELTA;

        ModelInstance m = new ModelInstance(mod, tmpVector.x, tmpVector.y, tmpVector.z);

        MovingObject mo = new MovingObject(m);


        objs.add(mo);
        return mo;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

    //    StreetBlock block = reteStradale.add(screenX, screenY);

        return false;
    }

    private MovingObject prev = null;

    @Override
    public boolean mouseMoved(int x, int y) {

        MovingObject mo = addActor(reteStradale.getStreetElementModel(), x, y);
        if (prev != null)
            objs.remove(prev);
        prev = mo;
        return true;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (reteStradale.isRoadBuilding()) {
            reteStradale.setRoadPoint(screenX, screenY);
        } else
            addActor(box, screenX, screenY);

        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public ReteStradale getReteStradale() {
        return reteStradale;
    }
    public CameraPosition getCameraPosition() {
        return cameraPosition;
    }

}
