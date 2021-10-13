package com.boymask.city.core;

public class StarfishCollector3DGame extends BaseGame
{
    public void create()
    {
        super.create();
        setActiveScreen( new LevelScreen() );
    }
}
