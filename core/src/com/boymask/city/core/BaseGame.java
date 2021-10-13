package com.boymask.city.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public abstract class BaseGame extends Game {
    private static BaseGame game;public static LabelStyle labelStyle;

    public BaseGame()
    {
        game = this;
    }
    public void create()
    {
        InputMultiplexer im = new InputMultiplexer();
        Gdx.input.setInputProcessor( im );

        BitmapFont font = new BitmapFont();
        labelStyle = new LabelStyle();new Label.LabelStyle(font, Color.WHITE);
        labelStyle.font = font;
    }
    public static void setActiveScreen(BaseScreen s)
    {
        game.setScreen(s);
    }
}
