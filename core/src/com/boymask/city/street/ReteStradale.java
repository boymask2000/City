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

import java.util.HashSet;
import java.util.Set;

public class ReteStradale {
    private final City city;



    private Model streetElementModel;
    private Set<StreetBlock> blocks = new HashSet<>();
    private Set<String> sblocks = new HashSet<>();


    public ReteStradale(City city, ModelBuilder modelBuilder) {
        this.city = city;

        streetElementModel = modelBuilder.createBox(2f, 0.1f, 2f,
                new Material(ColorAttribute.createDiffuse(Color.GRAY)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }

    private StreetBlock last = null;

    public StreetBlock add(int x, int y) {

        StreetBlock block = new StreetBlock(x, y);
        if (blocks.contains(block)) return null;
        if (sblocks.contains(block.toString())) return null;
     //   if (last != null && last.getX() != block.getX() && last.getY() != block.getY()) return null;
/*        if (last != null)
            System.out.println (last.getX()+" "+ block.getX()+" "+last.getY()+" "+block.getY());
            */
        last = block;

        System.out.println(block.toString());
        city.addActor(streetElementModel, x, y);
        blocks.add(block);
        sblocks.add(block.toString());
        return block;
    }

    int roadBuilding = 0;
    private StreetBlock startBlock;

    public boolean isRoadBuilding() {
        return roadBuilding > 0;
    }

    public void setRoadPoint(int x, int y) {
        StreetBlock block = new StreetBlock(x, y);

        switch (roadBuilding) {

            case 2:
                drawRoad(startBlock, block);
                roadBuilding = 0;
                break;
            case 1:
                startBlock = block;
                roadBuilding = 2;
                break;
        }
    }

    private void drawRoad(StreetBlock startBlock, StreetBlock block) {
        int y = startBlock.getY();
        int minX = startBlock.getX();
        int maxX = block.getX();
        if (minX > maxX) {
            maxX = startBlock.getX();
            minX = block.getX();
        }
        for (int x = minX; x < maxX; x += 5)
            add(x, y);

        int minY = startBlock.getY();
        int maxY = block.getY();
        if (minY > maxY) {
            maxY = startBlock.getY();
            minY = block.getY();
        }
        for (y = minY; y < maxY; y += 5)
            add(maxX, y);
    }


    public void startRoad() {
        roadBuilding = 1;
        System.out.println("start");
    }
    public Model getStreetElementModel() {
        return streetElementModel;
    }
}
