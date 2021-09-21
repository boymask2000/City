package com.boymask.city.street;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.boymask.city.City;
import com.boymask.city.MovingObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReteStradale {
    private final City city;

    private Model streetElementModel;
    private Set<StreetBlock> blocks = new HashSet<>();
    private Set<String> sblocks = new HashSet<>();
    private boolean roadBuilding = false;
    private StreetBlock startBlock;
    private StreetBlock last = null;

    private Map<String, StreetBlock> allNet = new HashMap<>();


    public ReteStradale(City city, ModelBuilder modelBuilder) {
        this.city = city;

        streetElementModel = modelBuilder.createBox(2f, 0.1f, 2f,
                new Material(ColorAttribute.createDiffuse(Color.GRAY)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }

    public StreetBlock addElement(int x, int y) {
        city.addActor(getStreetElementModel(), x, y);
        return add(x, y);
    }

    private StreetBlock add(int x, int y) {

        StreetBlock block = new StreetBlock(x, y);
        String id = block.getId();
        StreetBlock prev = allNet.get(id);
        if (prev == null) {
            allNet.put(id, block);
            prev = block;
        }
        setNear(prev.getX() - 2, prev.getY() - 2, prev);
        setNear(prev.getX() - 2, prev.getY(), prev);
        setNear(prev.getX() - 2, prev.getY() + 2, prev);
        setNear(prev.getX(), prev.getY() - 2, prev);
        setNear(prev.getX(), prev.getY() + 2, prev);
        setNear(prev.getX() + 2, prev.getY() - 2, prev);
        setNear(prev.getX() + 2, prev.getY(), prev);
        setNear(prev.getX() + 2, prev.getY() + 2, prev);


        return block;
    }

    private void setNear(int x, int y, StreetBlock prev) {
        StreetBlock near = allNet.get(StreetBlock.buildId(x, y));
        if (near == null) return;
        near.addNear(prev);
        prev.addNear(near);
    }


    public boolean isRoadBuilding() {
        return roadBuilding;
    }

    public void startRoad() {
        roadBuilding = !roadBuilding;

    }

    public Model getStreetElementModel() {
        return streetElementModel;
    }

    public StreetBlock walk(List<StreetBlock> goodGath, List<StreetBlock> currPath, StreetBlock start, StreetBlock end) {

        for (StreetBlock near : start.getNears()) {
            if( currPath.contains(near))continue;
            if (near.equals(end)) {
                currPath.add(near);
                if (goodGath == null || goodGath.size() > currPath.size()) {
                    if (goodGath != null) goodGath.clear();
                    for (StreetBlock s : currPath)
                        goodGath.add(s);
                }
                return near;
            }
            if (goodGath != null &&  currPath.size() < goodGath.size())
                return walk(goodGath, currPath, near, end);

        }
        return null;
    }
}
