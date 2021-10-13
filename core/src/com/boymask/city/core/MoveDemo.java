package com.boymask.city.core;

public class MoveDemo extends BaseGame
{
    public void create()
    {
        super.create();
        setActiveScreen( new DemoScreen() );
    }
}
