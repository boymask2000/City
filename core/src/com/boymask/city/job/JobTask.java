package com.boymask.city.job;

import com.badlogic.gdx.math.Vector3;
import com.boymask.city.MovingObject;
import com.boymask.city.core.Mover;
import com.boymask.city.edifici.Edificio;
import com.boymask.city.merci.TipoMerce;
import com.boymask.city.oggetti.TipoOggetto;

public abstract class JobTask {

    private Mover mover;
    private Edificio target = null;
    private TipoOggetto tipoOggetto = null;


    private Job job;



    public abstract void exec() ;

    public void setJob(Job job) {
        this.job = job;
    }
}
