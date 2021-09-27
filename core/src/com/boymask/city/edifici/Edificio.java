package com.boymask.city.edifici;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.boymask.city.City;
import com.boymask.city.infrastructure.AllEdifici;
import com.boymask.city.merci.Inventario;
import com.boymask.city.merci.TipoMerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Edificio {
    private static int CURRID = 0;

    private final City city;


    private final Vector3 position;

    private int idEdificio;


    public static AllEdifici allEdifici = new AllEdifici();


    private final static List<Edificio> elencoEdifici = new ArrayList<>();
    private final static List<ModelInstance> istanceEdifici = new ArrayList<>();


    private final ModelInstance modelInstance;
    private ModelBuilder modelBuilder = new ModelBuilder();
    private Model model;
    private static AssetManager am = new AssetManager();

    public static Model modelFornaio;
    public static Model modelPozzo;

    protected Inventario inventario = new Inventario();

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

    public Edificio(TipoEdificio tipo, City city, int x, int y) {
        this.city = city;
        this.idEdificio = createId();
        this.model = AllEdifici.getModelloEdificio(tipo);
        modelInstance = new ModelInstance(model, x, y, 0);
        position = new Vector3(x, y, 0);

        elencoEdifici.add(this);
        istanceEdifici.add(modelInstance);


        allEdifici.addEdificio(getIdEdificio(), this);
    }

    private static void loadAllModels() {

        for (String f : filenames) {
            am.load(f, Model.class);
        }
        am.finishLoading();

        AllEdifici.setModelloEdificio(TipoEdificio.FORNAIO, getModel("edifici/obj/house_type21.obj"));
        AllEdifici.setModelloEdificio(TipoEdificio.POZZO, getModel("edifici/obj/house_type20.obj"));
        AllEdifici.setModelloEdificio(TipoEdificio.CASTELLO, getModel("edifici/obj/house_type19.obj"));
        AllEdifici.setModelloEdificio(TipoEdificio.DEPOSITO, getModel("edifici/obj/house_type18.obj"));
        AllEdifici.setModelloEdificio(TipoEdificio.MULINO, getModel("edifici/obj/house_type17.obj"));
        AllEdifici.setModelloEdificio(TipoEdificio.CAMPO_GRANO, getModel("edifici/obj/house_type16.obj"));
        AllEdifici.setModelloEdificio(TipoEdificio.DEPOSITO, getModel("edifici/obj/house_type18.obj"));
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

    public static Edificio getEdificioById(int id) {
        for (Edificio e : elencoEdifici)
            if (e.getIdEdificio() == id) return e;
        return null;
    }

    public void addinInventario(TipoMerce t) {
        inventario.addMerce(t, 1);
    }

    public boolean getFromInventario(TipoMerce t) {
        return inventario.getMerce(t);
    }

    public static AllEdifici getAllEdifici() {
        return allEdifici;
    }
}
