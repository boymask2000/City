package com.boymask.city.street;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StreetBlock {



    private static final int BLOCKSIZE = 2;
    private int z = 0;
    private int x = 0;
    private int y = 0;



    private List<StreetBlock> nears = new ArrayList<>();


    public String getId(){
        return buildId(getX(),getY());
    }
    public static String buildId(int x, int y ){
        return x+"-"+y;
    }

    public List<StreetBlock> getNears() {
        return nears;
    }

    @Override
    public String toString() {
        return "StreetBlock{" +
                "z=" + z +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public StreetBlock(int x, int y) {
        this.x = x / BLOCKSIZE;
        this.y = y / BLOCKSIZE;
        this.x *= BLOCKSIZE;
        this.y *= BLOCKSIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void addNear(StreetBlock near) {
        nears.add(near); //TODO verficicare se non già presente
    }

    //------------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreetBlock that = (StreetBlock) o;
        return z == that.z && x == that.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(z, x);
    }
}
