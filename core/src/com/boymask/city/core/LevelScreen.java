package com.boymask.city.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.boymask.city.edifici.Pozzo;

public class LevelScreen extends BaseScreen {
    Turtle turtle;
    Label starfishLabel;
    Label messageLabel;

    public void initialize() {
        Box floor = new Box(0, 0, 0, mainStage3D);
        floor.loadTexture("simple/terrain.jpg");
        floor.setScale(500, 0.1f, 500);
        Sphere skydome = new Sphere(0, 0, 0, mainStage3D);
        skydome.loadTexture("simple/sky.jpg");
// when scaling, the negative z-value inverts the sphere
// so that the texture is rendered on the inside
        skydome.setScale(500, 500, -500);
        turtle = new Turtle(0, 0, 15, mainStage3D);
        turtle.setTurnAngle(90);
   /*     Pozzo p1 = new Pozzo(15, 1, 0, mainStage3D, this);
        Pozzo p2 = new Pozzo(0, 1, 0, mainStage3D, this);
*/
 /*
        new Rock(-15, 1, 0, mainStage3D);

        new Rock(-15, 1, 0, mainStage3D);
        new Rock(-15, 1, 15, mainStage3D);
        new Rock(-15, 1, 30, mainStage3D);

        new Rock(0, 1, 0, mainStage3D);
        new Rock(0, 1, 30, mainStage3D);
        new Rock(15, 1, 0, mainStage3D);
        new Rock(15, 1, 15, mainStage3D);
        new Rock(15, 1, 30, mainStage3D);
        new Starfish(10, 0, 10, mainStage3D);
        new Starfish(10, 0, 20, mainStage3D);
        new Starfish(-10, 0, 10, mainStage3D);
        new Starfish(-10, 0, 20, mainStage3D);

         */
        mainStage3D.setCameraPosition(0, 10, 0);
        mainStage3D.setCameraDirection(new Vector3(0, 0, 0));
        starfishLabel = new Label("Starfish left: 4", BaseGame.labelStyle);
        starfishLabel.setColor(Color.CYAN);
        messageLabel = new Label("You Win!", BaseGame.labelStyle);
        messageLabel.setColor(Color.LIME);
        messageLabel.setFontScale(2);
        messageLabel.setVisible(false);
        uiTable.pad(20);
        uiTable.add(starfishLabel);
        uiTable.row();
        uiTable.add(messageLabel).expandY();
    }

    public void update(float dt) {
        float speed = 3.0f;
        float rotateSpeed = 45.0f;
        if (Gdx.input.isKeyPressed(Keys.UP))
            turtle.moveForward(speed * dt);
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            turtle.turn(-rotateSpeed * dt);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            turtle.turn(rotateSpeed * dt);
        mainStage3D.setCameraDirection(turtle.getPosition());
        for (BaseActor3D rock : BaseActor3D.getList(mainStage3D, "com.boymask.city.core.Rock"))
            turtle.preventOverlap(rock);
        for (BaseActor3D starfish : BaseActor3D.getList(mainStage3D, "com.boymask.city.core.Starfish"))
            if (turtle.overlaps(starfish))
                starfish.remove();
        int starfishCount = BaseActor3D.count(mainStage3D, "com.boymask.city.core.Starfish");
        starfishLabel.setText("Starfish left: " + starfishCount);

        if (starfishCount == 0)
            messageLabel.setVisible(true);
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
