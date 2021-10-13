package com.boymask.city.core;

public class Rock extends ObjModel
{
    public Rock(float x, float y, float z, Stage3D s)
    {
        super(x,y,z,s);
        loadObjModel("edifici/obj/house_type06.obj");
        setBasePolygon();
        setScale(3,3,3);
    }
}
