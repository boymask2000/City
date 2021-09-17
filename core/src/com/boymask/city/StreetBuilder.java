package com.boymask.city;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;

public class StreetBuilder {
    private static ImmediateModeRenderer20 lineRenderer = new ImmediateModeRenderer20(false, true, 0);

    // create atomic method for line
    public static void line(float x1, float y1, float z1,
                            float x2, float y2, float z2,
                            float r, float g, float b, float a) {
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();
        MeshPartBuilder builder = modelBuilder.part("line", 1, 3, new Material());
        builder.setColor(Color.RED);
        builder.line(x1, y1, z1, x2, y2, z2);
        Model lineModel = modelBuilder.end();
    //    lineInstance = new ModelInstance(lineModel);
    }

    // method for whole grid
    public static void grid(int width, int height) {
        for (int x = 0; x <= width; x+=100) {
            // draw vertical
            line(x, 0, 0,
                    x, 0, -height,
                    0, 1, 0, 0);
        }

        for (int y = 0; y <= height; y+=100) {
            // draw horizontal
            line(0, 0, -y,
                    width, 0, -y,
                    1, 10, 1, 0);
        }
    }
}
