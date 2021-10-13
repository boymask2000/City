package com.boymask.city.core;

public class Starfish extends ObjModel
{
    public Starfish(float x, float y, float z, Stage3D s)
    {
        super(x,y,z,s);
        loadObjModel("edifici/obj/fence_small.obj");
        setScale(3,1,3);
        setBasePolygon();
    }
    public void act(float dt)
    {
        super.act(dt);
        turn( 90 * dt );
    }
}
