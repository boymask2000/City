package com.boymask.city;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
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

public class City extends ApplicationAdapter implements InputProcessor {

    private CameraPosition cameraPosition;
    private ModelBatch modelBatch;
    private ModelBuilder modelBuilder;
    private Model box;

    private ModelInstance modelInstance;
    private ModelInstance house;
    private ModelInstance modelInstance2;
    private Environment environment;

    private List<MovingObject> objs = new ArrayList<>();
    private UIManager uiManager;
    private InputMultiplexer multiplexer = new InputMultiplexer();


    private ReteStradale reteStradale;
    private boolean hades;
    private ModelInstance tree;
    private Model treeModel;
    private CameraInputController cameraController;
    private PerspectiveCamera camera;
    private ModelInstance instance;


    private TipoEdificio edificioInCostruzione = null;


    private OrderManager orderManager = new OrderManager();
    private List<ModelInstance> act = new ArrayList<>();

    private InventarioGlobale inventarioGlobale = new InventarioGlobale();

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
        //       MovingObject mo = new MovingObject(this,modelInstance);
        //    MovingObject mo2 = new MovingObject(this, modelInstance2);
//mo2.setMovement(new Vector3(10,10,10));
//mo2.moveTo(new Vector3(10,10,10));
        //    objs.add(mo);
        //  objs.add(mo2);

        //mo.moveTo(new Vector3(5, 5, 3));
        environment = createEnvironment();


        float intensity = 100f;


        multiplexer.addProcessor(this);


        Gdx.input.setInputProcessor(multiplexer);
        reteStradale = new ReteStradale(this, modelBuilder);

        String fileName = "edifici/house_type10.g3db";
        UBJsonReader jsonReader = new UBJsonReader();
        G3dModelLoader loader = new G3dModelLoader(jsonReader);


  //      showTree("edifici/obj/house_type01.obj", 0, 5);

   //     house = loadModelInstance("edifici/house_type06.g3dj", 5, 5, 0);



    /*    Fornaio f = new Fornaio(this, 10, 10);

        f.produci();
        Pozzo p = new Pozzo(this, 10, 20);
        p.produci();
*/

     //   house.transform.scale(5f, 5, 5f);

        ModelInstance mmm = new ModelInstance(box, 10, 20, 30);
        Carrier r = new Carrier(this, mmm);
        objs.add(r);
        r.workCycle();

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

    private void showTree(String fileName, int x, int y) {
        //FileHandle stream = Gdx.files.getFileHandle("edifici/tree_large.obj", Files.FileType.Internal);
        FileHandle stream = Gdx.files.getFileHandle(fileName, Files.FileType.Internal);
        ObjLoader d = new ObjLoader();
        Model treeModel = d.loadModel(stream, true);

        ModelInstance mi = new ModelInstance(treeModel, x, y, 5);
        mi.transform.scale(5f, 5, 5f);
        act.add(mi);
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


    @Override
    public void render() {

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        Camera camera = cameraPosition.getCamera();
        camera.update();
        modelBatch.begin(camera);

        for (MovingObject i : objs) {
            modelBatch.render(i.getModelInstance(), environment);
            i.move();
        }

     /*   for( Edificio ed: Edificio.getAllEdifici().getListaEdifici())
        {
            ed.produci();
        }*/


        modelBatch.render(act);

        modelBatch.render(Edificio.getIstanceEdifici());

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
        //     modelInstance.transform.setTranslation(tmpVector);

        int DELTA = 2;
        tmpVector.x = (int) (tmpVector.x / DELTA);
        tmpVector.x *= DELTA;
        tmpVector.y = (int) (tmpVector.y / DELTA);
        tmpVector.y *= DELTA;
        tmpVector.z = (int) (tmpVector.z / DELTA);
        tmpVector.z *= DELTA;

        ModelInstance m = new ModelInstance(mod, tmpVector.x, tmpVector.y, tmpVector.z);

        MovingObject mo = new MovingObject(this, m);
        m.transform.scale(5f, 5, 5f);
        objs.add(mo);
        return mo;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {

        if (reteStradale.isRoadBuilding())
            reteStradale.addElement(x, y);
        return true;
    }

    private MovingObject prev = null;

    @Override
    public boolean mouseMoved(int x, int y) {

        if (prev != null)
            objs.remove(prev);
        MovingObject mo = addActor(reteStradale.getStreetElementModel(), x, y);
        prev = mo;
        return true;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (edificioInCostruzione != null) {
            System.out.println("Creazione edificio "+edificioInCostruzione);
            Model mod = AllEdifici.getModelloEdificio(edificioInCostruzione);
            addActor(mod, x, y);


           Edificio ed =  Edificio.createEdificio(edificioInCostruzione, this,x,y);
           ed.produci();

            edificioInCostruzione = null;
        }
        if (reteStradale.isRoadBuilding()) {
            addActor(reteStradale.getStreetElementModel(), x, y);
            return true;
        }

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

    public OrderManager getOrderManager() {
        return orderManager;
    }

    public InventarioGlobale getInventarioGlobale() {
        return inventarioGlobale;
    }

    public void setEdificioInCostruzione(TipoEdificio edificioInCostruzione) {
        this.edificioInCostruzione = edificioInCostruzione;
        System.out.println("Set EDIFiCIO "+edificioInCostruzione);
    }

}
