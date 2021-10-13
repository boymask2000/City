package com.boymask.city.edifici;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.boymask.city.Carrier;
import com.boymask.city.City;
import com.boymask.city.MovingObject;
import com.boymask.city.infrastructure.MerceDisponibile;
import com.boymask.city.job.Job;
import com.boymask.city.job.JobTask;
import com.boymask.city.job.TaskOperation;
import com.boymask.city.merci.Merce;
import com.boymask.city.oggetti.TipoOggetto;

import java.util.ArrayList;
import java.util.List;

public class Boscaiolo extends Seminatore {
    private MovingObject mo;

    public Boscaiolo(City city, int x, int y, int z) {
        super(city);

        ModelBuilder modelBuilder = new ModelBuilder();
        Model box = modelBuilder.createBox(2f, 2f, 2f,
                new Material(ColorAttribute.createDiffuse(Color.BLUE)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        ModelInstance modelInstance = new ModelInstance(box, x, y, z);


    }


    public void produci() {
        Thread t = new Thread() {
            public void run() {
                while (true) {
                    try {

                        sleep(1000 * tempoSemina);
                        createJob();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    private Job createJob() {
return null;

    }





}