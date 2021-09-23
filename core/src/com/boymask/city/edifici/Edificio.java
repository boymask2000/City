package com.boymask.city.edifici;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.boymask.city.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Edificio {
    private static int CURRID = 0;

    private final City city;


    private final Vector3 position;

    private int idEdificio;
    private Map<Integer, Edificio> estates = new HashMap<>();


    private final static List<Edificio> elencoEdifici = new ArrayList<>();
    private final static List<ModelInstance> istanceEdifici = new ArrayList<>();


    private final ModelInstance modelInstance;
    private ModelBuilder modelBuilder = new ModelBuilder();
    private Model model;
    private static AssetManager am = new AssetManager();

    public static Model modelFornaio;
    public static Model modelPozzo;



    private static final String[] filenames = {
            "edifici/obj/house_type01.obj", //
            "edifici/obj/house_type02.obj", //
            "edifici/obj/house_type03.obj", //
            "edifici/obj/house_type04.obj", //
            "edifici/obj/house_type05.obj", //
            "edifici/obj/house_type06.obj", //
            "edifici/obj/house_type07.obj", //
            "edifici/obj/house_type08.obj", //
            "edifici/obj/house_type09.obj", //
            "edifici/obj/house_type10.obj", //
            "edifici/obj/house_type11.obj", //
            "edifici/obj/house_type12.obj", //
            "edifici/obj/house_type13.obj", //
            "edifici/obj/house_type14.obj", //
            "edifici/obj/house_type15.obj", //
            "edifici/obj/house_type16.obj", //
            "edifici/obj/house_type17.obj", //
            "edifici/obj/house_type18.obj", //
            "edifici/obj/house_type19.obj", //
            "edifici/obj/house_type20.obj", //
            "edifici/obj/house_type21.obj" //
    };

    static {
        loadAllModels();
    }

    private synchronized int createId() {
        CURRID++;
        return CURRID;
    }

    public Edificio(Model model, City city, int x, int y) {
        this.city=city;
        this.idEdificio = createId();
        this.model = model;
        modelInstance = new ModelInstance(model, x, y, 0);
        position=new Vector3(x,y,0);

        elencoEdifici.add(this);
        istanceEdifici.add(modelInstance);

        estates.put(getIdEdificio(), this);
    }

    private static void loadAllModels() {

        for (String f : filenames) {
            System.out.println("Loading " + f);
            am.load(f, Model.class);
        }
        am.finishLoading();
//        Model model = am.get(fileName, Model.class);
//        return model;

        modelFornaio = getModel("edifici/obj/house_type21.obj");
        modelPozzo = getModel("edifici/obj/house_type20.obj");
    }

    public static final List<ModelInstance> getIstanceEdifici() {
        return istanceEdifici;
    }

    public static Model getModel(String fileName) {
        return am.get(fileName, Model.class);
    }


    public int getIdEdificio() {
        return idEdificio;
    }

    public City getCity() {
        return city;
    }
    public Vector3 getPosition() {
        return position;
    }
    public static Edificio getEdificioById(int id){
        for(Edificio e: elencoEdifici)
            if( e.getIdEdificio()==id) return e;
        return null;
    }
}
