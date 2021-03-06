package com.boymask.city.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseScreen implements Screen, InputProcessor {
    public Stage3D getMainStage3D() {
        return mainStage3D;
    }

    private List<Stage> moreStages = new ArrayList<>();

    protected Stage3D mainStage3D;
    protected Stage uiStage;
    protected Table uiTable;

    public BaseScreen() {
        mainStage3D = new Stage3D();
        uiStage = new Stage();
        uiTable = new Table();
        uiTable.setFillParent(true);
        uiStage.addActor(uiTable);
        initialize();
    }

    public abstract void initialize();

    public abstract void update(float dt);

    // gameloop method
    public void render(float dt) {
// limit amount of time that can pass while window is being dragged
        dt = Math.min(dt, 1 / 30f);
// act methods
        uiStage.act(dt);
        for (Stage st : moreStages) st.act(dt);
        mainStage3D.act(dt);
        update(dt);
// render
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT + GL20.GL_DEPTH_BUFFER_BIT);
// draw the graphics
        mainStage3D.draw();
        for (Stage st : moreStages) st.draw();
        uiStage.draw();
    }

    public void addStage(Stage st) {
        moreStages.add(st);
    }

    // methods required by Screen interface
    public void resize(int width, int height) {
        uiStage.getViewport().update(width, height, true);
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }

    public void show() {
        InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
        im.addProcessor(this);
        im.addProcessor(uiStage);
    }

    public void hide() {
        InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
        im.removeProcessor(this);
        im.removeProcessor(uiStage);
    }

    // methods required by InputProcessor interface
    public boolean keyDown(int keycode) {
        return false;
    }

    public boolean keyUp(int keycode) {
        return false;
    }

    public boolean keyTyped(char c) {
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    public boolean scrolled(int amount) {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
}
