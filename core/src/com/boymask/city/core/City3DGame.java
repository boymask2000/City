package com.boymask.city.core;

import com.boymask.city.City;

public class City3DGame extends BaseGame
{
    public void create()
    {
        super.create();
        setActiveScreen( new City() );
    }
}
