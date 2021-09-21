package com.boymask.city.edifici;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import java.util.ArrayList;
import java.util.List;

public class Edificio {
    private static List<Edificio> elencoEdifici = new ArrayList<>();
    private static List<ModelInstance> istanceEdifici = new ArrayList<>();

    private final ModelInstance modelInstance;
    private ModelBuilder modelBuilder = new ModelBuilder();
    private Model model;

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

    public Edificio(Model model, int x, int y) {

        this.model = model;
        modelInstance = new ModelInstance(model, x, y, 0);

        elencoEdifici.add(this);
        istanceEdifici.add(modelInstance);
    }

    private static void loadAllModels() {
        AssetManager am = new AssetManager();
        for (String f : filenames){
            System.out.println("Loading "+f);
            am.load(f, Model.class);}
        am.finishLoading();
//        Model model = am.get(fileName, Model.class);
//        return model;
    }

    public static final List<ModelInstance> getIstanceEdifici() {
        return istanceEdifici;
    }

}
