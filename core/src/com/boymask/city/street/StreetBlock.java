package com.boymask.city.street;

public class StreetBlock {

    private  int z=0;
    private  int x=0;
    private  int y=0;

    @Override
    public String toString() {
        return "StreetBlock{" +
                "z=" + z +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public StreetBlock(int x, int y) {
        this.x =x;
        this.y =y;
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

}
