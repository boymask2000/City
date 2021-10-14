package com.boymask.city.edifici;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.boymask.city.City;
import com.boymask.city.core.LevelScreen;
import com.boymask.city.core.ObjModel;
import com.boymask.city.infrastructure.AllEdifici;
import com.boymask.city.merci.Inventario;
import com.boymask.city.merci.TipoMerce;
import com.boymask.city.merci.VoceInventario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Edificio extends ObjModel {

    private static int CURRID = 0;
    protected final TipoEdificio tipoEdificio;
    private int idEdificio;
    public static AllEdifici allEdifici = new AllEdifici();
    private final static List<Edificio> elencoEdifici = new ArrayList<>();
    private final static List<ModelInstance> istanceEdifici = new ArrayList<>();
    private static AssetManager am = new AssetManager();
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
            "edifici/obj/house_type21.obj", //

            "edifici/ship.obj",
            "edifici/house_type18.g3db" //
    };

    static {
        loadAllModels();
    }

    @Override
    public void act(float dt) {
        super.act(dt);

    }

    //***************************************************************************************************
    public abstract void produci();

    //***************************************************************************************************

    private synchronized int createId() {
        CURRID++;
        return CURRID;
    }

    public Edificio(TipoEdificio tipo, LevelScreen city, int x, int y, int z) {
        super(x, y, z, city.getMainStage3D());
        Model model = AllEdifici.getModelloEdificio(tipo);
        ModelInstance modelInstance = new ModelInstance(model, x, y, z);

        loadObjModel(modelInstance);
        setBasePolygon();
        //   setScale(3,3,3);


        this.tipoEdificio = tipo;

        this.idEdificio = createId();

        elencoEdifici.add(this);
        istanceEdifici.add(modelInstance);


        allEdifici.addEdificio(getIdEdificio(), this);

        produci();
    }


    public int getIdEdificio() {
        return idEdificio;
    }


    public void addinInventario(TipoMerce t) {

        inventario.addMerce(t, 1);
        inventario.dump();
    }

    public boolean getFromInventario(TipoMerce t) {

        boolean b = inventario.getMerce(t);
        inventario.dump();
        return b;
    }

    public int getGiacenza() {
        return inventario.getMerci().size();
    }


    @Override
    public String toString() {
        return "Edificio{" +
                "tipoEdificio=" + tipoEdificio +
                ", idEdificio=" + idEdificio +
                '}';
    }

    //***********  STATICS  ***************************************************
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
        //     AllEdifici.setModelloEdificio(TipoEdificio.CAMPO_GRANO, getModel("edifici/obj/house_type16.obj"));
        AllEdifici.setModelloEdificio(TipoEdificio.DEPOSITO, getModel("edifici/obj/house_type18.obj"));

        AllEdifici.setModelloEdificio(TipoEdificio.CAMPO_GRANO, getModel("edifici/ship.obj"));
    }


    public static AllEdifici getAllEdifici() {
        return allEdifici;
    }

    public static Edificio getEdificioById(int id) {
        for (Edificio e : elencoEdifici)
            if (e.getIdEdificio() == id) return e;
        return null;
    }

    public static final List<ModelInstance> getIstanceEdifici() {
        return istanceEdifici;
    }

    public static Model getModel(String fileName) {
        return am.get(fileName, Model.class);
    }

    public static Edificio createEdificio(TipoEdificio tipo, City city, int x, int y, int z) {
        Edificio ed = null;
        switch (tipo) {
            case POZZO:
                //  ed = new Pozzo(city,x,y,z);
                break;
            case MULINO:
                //     ed = new Mulino(city,x,y,z);
                break;
            case FORNAIO:
                //       ed = new Fornaio(city,x,y,z);
                break;
            case CAMPO_GRANO:
                //           ed = new CampoDiGrano(city,x,y,z);
                break;
            case CASTELLO:
                //        ed = new Castello(city,x,y,z);
                break;
            case DEPOSITO:
                //  ed = new Deposito(city,x,y);
                break;
        }
        return ed;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public TipoEdificio getTipoEdificio() {
        return tipoEdificio;
    }
}
