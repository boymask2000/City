package com.boymask.city.job;

import com.boymask.city.MovingObject;

import java.util.ArrayList;
import java.util.List;

public class Job {
    @Override
    public String toString() {
        return "Job{" +
                "tasks=" + tasks +
                '}';
    }

    private boolean loop = false;
    private List<JobTask> tasks = new ArrayList<>();
    private int currenTask = 0;
    private MovingObject actor;


    private boolean jobCompleted;

   /* public Job(MovingObject actor, List<JobTask> tasks) {
        this.tasks = tasks;
        this.actor = actor;


    }*/

    public Job(MovingObject actor, List<JobTask> tasks, boolean loop) {
        this.tasks = tasks;
        this.actor = actor;
        this.loop = loop;
        for (JobTask jt : tasks)
            jt.setJob(this);
    }


    public void nextTask() {
        currenTask++;
    }

    public boolean execTask() {
        if (jobCompleted) return true;
        if (currenTask >= tasks.size()) {
            if (loop) currenTask = 0;
            else {
                jobCompleted = true;
                return true;
            }
        }

        JobTask task = tasks.get(currenTask);

        task.exec();
        return false;
        //    actor.notifyJob();

    }

    public void notifyTaskCompleted() {
        System.out.println("TaskCompleted");
        nextTask();
        execTask();
        System.out.println("JOB Completed: "+jobCompleted);
    }

    public boolean isJobCompleted() {
        return jobCompleted;
    }
}
