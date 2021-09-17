package com.boymask.city;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UIManager {
    protected Stage stage;
    protected Label label;
    protected BitmapFont font;
    protected StringBuilder stringBuilder;

    public Stage createAll(){
        stage = new Stage();
        font = new BitmapFont();
        label = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
        stage.addActor(label);
        stringBuilder = new StringBuilder();

        return stage;
    }

    public void render(){
        stringBuilder.setLength(0);
        stringBuilder.append(" FPS: ").append(Gdx.graphics.getFramesPerSecond());

        label.setText(stringBuilder);
        buttons();
        stage.draw();
    }
    public void buttons(){
        Table table = new Table();
        table.setPosition(300,Gdx.graphics.getHeight()-100);

Skin skin = new Skin(Gdx.files.internal("skins/comic/arcade-ui.json"));

        table.add(new Label("parallel policy:",skin));//.pad(5);
        table.add();
        table.add(new Label("vs", skin));//.padLeft(10).padRight(10);
        table.add(new Label("parallel polic" , skin));//.pad(5);

        table.row().padTop(15);

        Texture text = new Texture("skins/comic/arcade-ui.png");
        TextureRegion reg=new TextureRegion((text));
        TextureRegionDrawable draw = new TextureRegionDrawable(reg);
        ImageButton ib = new ImageButton(draw);
        ib.addListener(new EventListener() {
            @Override
            public boolean handle (Event event) {
return true;
            }
        });
        table.add(ib);


/*
        TextButton startButton = new TextButton("Start", skin);

        startButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {

            }
        });

        table.add();
        table.add(startButton);
        table.add();
*/
stage.addActor(table);


    }
}
