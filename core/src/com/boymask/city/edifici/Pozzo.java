package com.boymask.city.edifici;

import com.boymask.city.City;
import com.boymask.city.merci.Merce_Acqua;
import com.boymask.city.merci.Merce_Pane;

public class Pozzo extends EdificioProduzione{
    public Pozzo(City city, int x, int y) { //
        super(TipoEdificio.POZZO,  city,  new Merce_Acqua(),x, y);
    }


   /* public static void main(String s[]){
        Fornaio f = new Fornaio(10,10);
        f.produci();
    }*/

}

