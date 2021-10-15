package com.boymask.city;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.boymask.city.edifici.Edificio;
import com.boymask.city.edifici.TipoEdificio;
import com.boymask.city.ui.TableDescrEdificio;

import java.awt.event.MouseEvent;

public class UIManager {

    private final City city;
    protected Stage stage;
    protected Label label;
    protected BitmapFont font;
    protected StringBuilder stringBuilder;

    private  TableDescrEdificio tableDescrEdificio=new TableDescrEdificio();

    public UIManager(City city) {
        this.city = city;
    }



    public Stage createAll() {
        tableDescrEdificio.setPosition(Gdx.graphics.getWidth()-50, Gdx.graphics.getHeight()-50);
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
        city.getInventarioGlobale().show();
        stage.draw();
    }

    public void buttons() {

        stage.addActor(tableDescrEdificio);

        Table table = new Table();
         table.setFillParent(true);
     table.top();table.left();
      //  table.setPosition(100, Gdx.graphics.getHeight() - 100);


        ImageButton castle = buildImageButton("bottoni/castle100.png");
   //     ImageButton mulino0 = buildImageButton("bottoni/windmill80.png");
        ImageButton road = buildImageButton("bottoni/horizon-road80.png");

        final ImageButton carrier = buildImageButton("bottoni/hades-symbol50.png");
        TextButton pozzo = createTextButton("Pozzo");
        TextButton castello = createTextButton("Castello");
        TextButton fornaio = createTextButton("Fornaio");
        TextButton mulino = createTextButton("Mulino");
        TextButton campo_grano = createTextButton("Campo di Grano");
        TextButton deposito = createTextButton("Deposito");

        table.add(castle);

        table.row();
        table.add(pozzo); table.row();
        table.add(fornaio); table.row();
        table.add(mulino); table.row();
        table.add(campo_grano); table.row();
        table.add(deposito); table.row();
        table.add(carrier); table.row();

   //     city.getInventarioGlobale().setShow(table);


        setButtonListener(campo_grano,TipoEdificio.CAMPO_GRANO);
        setButtonListener(mulino,TipoEdificio.MULINO);
        setButtonListener(fornaio,TipoEdificio.FORNAIO);
        setButtonListener(pozzo,TipoEdificio.POZZO);

        stage.addActor(tableDescrEdificio);

        InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
        im.addProcessor(stage);

        castle.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (!(event instanceof InputEvent) ||
                        !((InputEvent) event).getType().equals(InputEvent.Type.touchDown))
                    return false;
                return true;
            }
        });

        carrier.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (!(event instanceof InputEvent) ||
                        !((InputEvent) event).getType().equals(InputEvent.Type.touchDown))
                    return false;

                Carrier.create(1,1,15, city);
                return true;
            }
        });

        stage.addActor(table);
    }

    private void setButtonListener(TextButton b, final TipoEdificio te) {
        b.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                city.setEdificioInCostruzione(te);
                System.out.println("In costruzione: "+te);

            }
        } );
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
    void writeLabel( String text){
        Label.LabelStyle style = new Label.LabelStyle();
  //      style.background = ...; // Set the drawable you want to use

        Label label = new Label("Text here", style);
    }
    public TableDescrEdificio getTableDescrEdificio() {
        return tableDescrEdificio;
    }

}
