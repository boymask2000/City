package com.boymask.city;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.UBJsonReader;
import com.boymask.city.core.BaseGame;
import com.boymask.city.core.Box;
import com.boymask.city.core.LevelScreen;
import com.boymask.city.core.Stage3D;
import com.boymask.city.edifici.Boscaiolo;
import com.boymask.city.edifici.Edificio;
import com.boymask.city.edifici.Fornaio;
import com.boymask.city.edifici.Pozzo;
import com.boymask.city.edifici.TipoEdificio;
import com.boymask.city.infrastructure.AllEdifici;
import com.boymask.city.infrastructure.InventarioGlobale;
import com.boymask.city.infrastructure.OrderManager;
import com.boymask.city.job.Job;
import com.boymask.city.job.JobTask;
import com.boymask.city.job.TaskOperation;
import com.boymask.city.merci.TipoMerce;
import com.boymask.city.street.ReteStradale;
import com.boymask.city.street.StreetBlock;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.SysexMessage;

public class City extends LevelScreen implements InputProcessor {
    public static City theCity;

    private Environment environment;
    private UIManager uiManager;

    private TipoEdificio edificioInCostruzione = null;

    private OrderManager orderManager = new OrderManager();

    private InventarioGlobale inventarioGlobale = new InventarioGlobale();



    @Override
    public void initialize() {
        super.initialize();
        theCity = this;

        uiManager = new UIManager(this);
        Stage input = uiManager.createAll();

        InputMultiplexer multiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
        multiplexer.addProcessor(this);
        multiplexer.addProcessor(input);
addStage(input);
      //  getMainStage3D().addActor(input);
    }

    private Environment createEnvironment() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 0.8f, 0.8f, 1f));
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        //  environment.add(new DirectionalLight().set(0.9f, 0.9f, 0.9f, 20f, 20f, 20f));

        DirectionalLight dLight = new DirectionalLight();
        Color lightColor = new Color(0.75f, 0.75f, 0.75f, 1);
        Vector3 lightVector = new Vector3(-1.0f, -0.75f, -0.25f);
        dLight.set(lightColor, lightVector);
        environment.add(dLight);
        return environment;
    }



    private Model loadModel(String fileName) {
        AssetManager am = new AssetManager();
        am.load(fileName, Model.class);
        am.finishLoading();
        Model model = am.get(fileName, Model.class);
        return model;
    }


    private ModelInstance loadModelInstance(String fileName, float x, float y, float z) {

        Model model = loadModel(fileName);
        return new ModelInstance(model, x, y, z);
    }


    public void renderold() {

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);





    }

  /*  private void line() {
        ShapeRenderer shapeDebugger = new ShapeRenderer();
        Gdx.gl.glLineWidth(2);
        shapeDebugger.setProjectionMatrix(cameraPosition.getCamera().combined);
        shapeDebugger.begin(ShapeRenderer.ShapeType.Line);
        shapeDebugger.setColor(Color.RED);
        shapeDebugger.line(Gdx.graphics.getWidth() / 2, 0, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
        shapeDebugger.end();
    }*/




    /*public Edificio getEdificioAtMouse(int screenX, int screenY) {
        Vector3 pos = fromScreenTo3d(screenX, screenY);
        float minDist = 10000;
        Edificio best = null;
        for (Edificio ed : Edificio.getAllEdifici().getListaEdifici()) {
            float dist = ed.getPosition().dst(pos);
            if (dist < minDist) {
                minDist = dist;
                best = ed;
            }
        }
        if (minDist < 0.5)
            return best;
        return null;
    }*/

 /*   public Vector3 fromScreenTo3d(int screenX, int screenY) {
        Vector3 tmpVector = new Vector3();
        Ray ray = cameraPosition.getCamera().getPickRay(screenX, screenY);
        final float distance = -ray.origin.y / ray.direction.y;
        tmpVector.set(ray.direction).scl(distance).add(ray.origin);
        //     modelInstance.transform.setTranslation(tmpVector);

        int DELTA = 2;
        tmpVector.x = (int) (tmpVector.x / DELTA);
        tmpVector.x *= DELTA;
        tmpVector.y = (int) (tmpVector.y / DELTA);
        tmpVector.y *= DELTA;
        tmpVector.z = (int) (tmpVector.z / DELTA);
        tmpVector.z *= DELTA;
        return tmpVector;
    }
*/



    private MovingObject prev = null;

/*    @Override
    public boolean mouseMoved(int x, int y) {

        Edificio ed = getEdificioAtMouse(x, y);
        if (ed != null) uiManager.getTableDescrEdificio().show(ed);

        if (prev != null)
            objs.remove(prev);

        return true;
    }*/

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (edificioInCostruzione != null) {
            System.out.println("Creazione edificio " + edificioInCostruzione);


            edificioInCostruzione = null;
        }

        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }


    public OrderManager getOrderManager() {
        return orderManager;
    }

    public InventarioGlobale getInventarioGlobale() {
        return inventarioGlobale;
    }

    public void setEdificioInCostruzione(TipoEdificio ed) {
        if (this.edificioInCostruzione != null) return;
        this.edificioInCostruzione = ed;
        System.out.println("Set EDIFiCIO " + ed);
    }

}
