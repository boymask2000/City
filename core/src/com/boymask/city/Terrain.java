package com.boymask.city;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class Terrain {
    public static Vector3 moveOnTerrain( Vector3 movm){
        movm.y=0;
        return movm;
    }
    public static float distance( Vector3 v1, Vector3 v2){
       float dist=0;
        dist += (v1.x> v2.x )? (v1.x-v2.x):( v2.x-v1.x );
        dist += (v1.y> v2.y )? (v1.y-v2.y):( v2.y-v1.y );
        dist += (v1.z> v2.z )? (v1.z-v2.z):( v2.z-v1.z );

        return dist;
    }

}
