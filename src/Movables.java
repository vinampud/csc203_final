import processing.core.PImage;

import java.util.List;

public abstract class Movables extends Animated {

    private final int actionPeriod;

    protected int getActionPeriod(){
        return this.actionPeriod;
    }
    protected Movables(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod) {
        super(id, position, images, animationPeriod);
        this.actionPeriod = actionPeriod;
    }

    protected abstract void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler);

    public void scheduleActions(EventScheduler scheduler,
                                WorldModel world,
                                ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                this.getActionPeriod());
        scheduler.scheduleEvent(this,
                Factory.createAnimationAction(this, 0),
                this.getAnimationPeriod());
    }



}
