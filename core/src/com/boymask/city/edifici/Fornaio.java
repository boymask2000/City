package com.boymask.city.edifici;

import com.boymask.city.City;
import com.boymask.city.merci.Merce_Pane;

public class Fornaio extends EdificioProduzione{
    public Fornaio( City city,int x, int y) { //
        super(modelFornaio, city, new Merce_Pane(),x, y);
    }


   /* public static void main(String s[]){
        Fornaio f = new Fornaio(10,10);
        f.produci();
    }*/

}
