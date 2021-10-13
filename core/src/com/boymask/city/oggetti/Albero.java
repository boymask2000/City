package com.boymask.city.oggetti;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.boymask.city.City;
import com.boymask.city.MovingObject;
import com.boymask.city.core.LevelScreen;

public class Albero extends MovingObject {
    public Albero(int x, int y, int z, LevelScreen city, ModelInstance modelInstance) {
        super(10,10,10,city, modelInstance);
    }

    public static Albero create(int x, int y, int z,LevelScreen city){
        ModelBuilder modelBuilder = new ModelBuilder();
        Model box = modelBuilder.createBox(2f, 2f, 2f,
                new Material(ColorAttribute.createDiffuse(Color.BLUE)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        ModelInstance mmm = new ModelInstance(box, x,y,z);
        Albero r = new Albero(x,y,z, city,mmm );
        City.theCity.addMovingObject(r);
        return r;
    }

}
