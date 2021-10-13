package com.boymask.city.core;

public class Turtle extends ObjModel
{
    public Turtle(float x, float y, float z, Stage3D s)
    {
        super(x,y,z,s);
        loadObjModel("edifici/ship.obj");
        setBasePolygon();
    }
}
