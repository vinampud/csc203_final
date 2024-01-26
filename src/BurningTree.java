import processing.core.PImage;

import java.util.List;

public class BurningTree extends Plant{

    public static final int BURN_ANIMATION_MAX = 2000;
    public static final int BURN_ANIMATION_MIN = 1500;
    public static final int BURN_ACTION_MAX = 800;
    public static final int BURN_ACTION_MIN = 400;
    public static final int BURN_HEALTH_MAX = 3;
    public static final int BURN_HEALTH_MIN = 0;
    public static final int BURN_NUM_PROPERTIES = 7;
    public static final int BURN_ID = 1;
    public static final int BURN_COL = 2;
    public static final int BURN_ROW = 3;
    public static final int BURN_ANIMATION_PERIOD = 4;
    public static final int BURN_ACTION_PERIOD = 5;
    public static final int BURN_HEALTH = 6;

    public BurningTree(
            String id,
            Point position,
            List<PImage> images,
            int actionPeriod,
            int animationPeriod,
            int health,
            int healthLimit)
    {
        super(id, position, images, actionPeriod, animationPeriod, health, healthLimit);
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

    //slowly decrease in health
    @Override
    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        setHealth(getHealth() - 1);
        if (!transformPlant(world, scheduler, imageStore)) {

                scheduler.scheduleEvent(this,
                        Factory.createActivityAction(this, world, imageStore),
                        this.getActionPeriod());
            }

        }

}
