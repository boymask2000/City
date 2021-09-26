package com.boymask.city.job;

import com.boymask.city.MovingObject;

import java.util.ArrayList;
import java.util.List;

public class Job {
    private boolean loop = false;
    private List<JobTask> tasks = new ArrayList<>();
    private int currenTask = -1;
    private MovingObject actor;

    public Job(MovingObject actor, List<JobTask> tasks) {
        this.tasks = tasks;
        this.actor = actor;
    }

    public Job(MovingObject actor, List<JobTask> tasks, boolean loop) {
        this.tasks = tasks;
        this.actor = actor;
        this.loop = loop;
    }

    public void next() {
        currenTask++;
    }

    public void execTask() {

        if (currenTask >= tasks.size()) {
            if (loop) currenTask = 0;
            else return;
        }
        JobTask task = tasks.get(currenTask);

        task.exec(actor);

        //    actor.notifyJob();

    }
}
