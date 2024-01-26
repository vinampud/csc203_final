import processing.core.PImage;

import java.util.List;

public abstract class Plant extends Movables{

    private int health;
    private final int healthLimit;

    protected Plant(String id, Point position, List<PImage> images, int animationPeriod,
                    int actionPeriod, int health, int healthLimit)
    {
        super(id, position, images, animationPeriod, actionPeriod);
        this.health = health;
        this.healthLimit = healthLimit;
    }

    int getHealth(){
        return this.health;
    }

    protected void setHealth(int health){
        this.health = health;
    }

    protected int getHealthLimit(){
        return this.healthLimit;
    }


    public boolean transformPlant(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {
        if (this.getHealth() <= 0) {
            DeadTree dead = Factory.createDeadTree("dead_" + this.getId(),
                    this.getPosition(),
                    imageStore.getImageList(DEAD_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(dead);

            return true;
        }

        return false;
    }
}
