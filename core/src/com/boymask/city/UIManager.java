package com.boymask.city;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
import com.boymask.city.edifici.TipoEdificio;

public class UIManager {

    private final City city;
    protected Stage stage;
    protected Label label;
    protected BitmapFont font;
    protected StringBuilder stringBuilder;

    public UIManager(City city) {
        this.city = city;
    }


    public Stage createAll() {
        stage = new Stage();
        font = new BitmapFont();
        label = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
        stage.addActor(label);
        stringBuilder = new StringBuilder();
        buttons();
        return stage;
    }

    public void render() {
        stringBuilder.setLength(0);
        stringBuilder.append(" FPS: ").append(Gdx.graphics.getFramesPerSecond());

        label.setText(stringBuilder);

        stage.draw();
    }

    public void buttons() {
        Table table = new Table();
        //  table.setFillParent(true);
        table.setPosition(100, Gdx.graphics.getHeight() - 100);


        ImageButton castle = buildImageButton("bottoni/castle100.png");
   //     ImageButton mulino0 = buildImageButton("bottoni/windmill80.png");
        ImageButton road = buildImageButton("bottoni/horizon-road80.png");
        ImageButton left_arrow = buildImageButton("bottoni/left-arrow50.png");
        ImageButton up_arrow = buildImageButton("bottoni/up-arrow50.png");
        ImageButton right_arrow = buildImageButton("bottoni/right-arrow50.png");
        ImageButton hades = buildImageButton("bottoni/hades-symbol50.png");
        TextButton pozzo = createTextButton("Pozzo");
        TextButton castello = createTextButton("Castello");
        TextButton fornaio = createTextButton("Fornaio");
        TextButton mulino = createTextButton("Mulino");
        TextButton campo_grano = createTextButton("Campo di Grano");
        TextButton deposito = createTextButton("Deposito");

        table.add(castle);

        table.row();

        table.add(left_arrow);
        table.add(up_arrow);
        table.add(right_arrow);
        table.row();
        table.add(pozzo); table.row();
        table.add(fornaio); table.row();
        table.add(mulino); table.row();
        table.add(campo_grano); table.row();
        table.add(deposito); table.row();

        setButtonListener(campo_grano,TipoEdificio.CAMPO_GRANO);
        setButtonListener(mulino,TipoEdificio.MULINO);
        setButtonListener(fornaio,TipoEdificio.FORNAIO);
        setButtonListener(pozzo,TipoEdificio.POZZO);




        castle.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (!(event instanceof InputEvent) ||
                        !((InputEvent) event).getType().equals(InputEvent.Type.touchDown))
                    return false;

                return true;
            }
        });


     /*   mulino.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (!(event instanceof InputEvent) ||
                        !((InputEvent) event).getType().equals(InputEvent.Type.touchDown))
                    return false;

                return true;
            }
        });*/


        road.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (!(event instanceof InputEvent) ||
                        !((InputEvent) event).getType().equals(InputEvent.Type.touchDown))
                    return false;

                city.getReteStradale().startRoad();
                return true;
            }
        });


        left_arrow.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (!(event instanceof InputEvent) ||
                        !((InputEvent) event).getType().equals(InputEvent.Type.touchDown))
                    return false;
                city.getCameraPosition().getCamera().rotateAround(new Vector3(0f, 0f, 0f),
                        new Vector3(0f, 1f, 0f), 1f);
                return true;
            }
        });

        right_arrow.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (!(event instanceof InputEvent) ||
                        !((InputEvent) event).getType().equals(InputEvent.Type.touchDown))
                    return false;
                city.getCameraPosition().getCamera().rotateAround(new Vector3(0f, 0f, 0f),
                        new Vector3(0f, 1f, 0f), -1f);
                return true;
            }
        });

        up_arrow.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (!(event instanceof InputEvent) ||
                        !((InputEvent) event).getType().equals(InputEvent.Type.touchDown))
                    return false;
                city.getCameraPosition().getCamera().translate(-10f, 0, 0);
                return true;
            }
        });


        stage.addActor(table);


    }

    private void setButtonListener(TextButton campo_grano, final TipoEdificio te) {
        campo_grano.addListener(new ChangeListener() {
            @Override
            public boolean handle(Event event) {
                if (!(event instanceof InputEvent) ||
                        !((InputEvent) event).getType().equals(InputEvent.Type.touchDown))
                    return false;

                city.setEdificioInCostruzione(te);
                return true;
            }
            public void changed(ChangeEvent event, Actor actor) {
            }
        });
    }

    private ImageButton buildImageButton(String fileName) {
        Texture text = new Texture(fileName);
        TextureRegion reg = new TextureRegion((text));
        TextureRegionDrawable draw = new TextureRegionDrawable(reg);
        ImageButton ib = new ImageButton(draw);
        return ib;
    }

    private TextButton createTextButton(String label) {
        Skin mySkin = new Skin(Gdx.files.internal("skins/glassy/glassy-ui.json"));
        TextButton button2 = new TextButton(label, mySkin, "small");

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();

        textButtonStyle.fontColor = Color.WHITE;

        return button2;
    }
}
