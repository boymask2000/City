package com.boymask.city.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.boymask.city.City;
import com.boymask.city.core.MoveDemo;

public class Launcher2
{
    public static void main ()
    {
        MoveDemo myProgram = new MoveDemo();
        LwjglApplication launcher = new LwjglApplication(
                myProgram, "Movement Demo", 800, 600 );
    }

}