import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */

public class Sapling extends Plant
{

    public static final int SAPLING_HEALTH_LIMIT = 5;
    public static final int SAPLING_ACTION_ANIMATION_PERIOD = 1000; // have to be in sync since grows and gains health at same time
    public static final int SAPLING_NUM_PROPERTIES = 5;
    public static final int SAPLING_ID = 1;
    public static final int SAPLING_COL = 2;
    public static final int SAPLING_ROW = 3;
    public static final int SAPLING_HEALTH = 4;

    public static final int TREE_ANIMATION_MAX = 600;
    public static final int TREE_ANIMATION_MIN = 50;
    public static final int TREE_ACTION_MAX = 1400;
    public static final int TREE_ACTION_MIN = 1000;
    public static final int TREE_HEALTH_MAX = 3;
    public static final int TREE_HEALTH_MIN = 1;


    public Sapling(
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
        boolean result = super.transformPlant(world, scheduler, imageStore);

        if (this.getHealth() >= this.getHealthLimit())
        {
            Tree tree = Factory.createTree("tree_" + this.getId(),
                    this.getPosition(),
                    getNumFromRange(TREE_ACTION_MAX, TREE_ACTION_MIN),
                    getNumFromRange(TREE_ANIMATION_MAX, TREE_ANIMATION_MIN),
                    getNumFromRange(TREE_HEALTH_MAX, TREE_HEALTH_MIN),
                    imageStore.getImageList(TREE_KEY));

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(tree);
            ((Movables)tree).scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return result;

    }

    public void executeActivity(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        this.setHealth(getHealth()+1);
        if (!transformPlant(world, scheduler, imageStore))
        {
            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore),
                    this.getActionPeriod());
        }
    }



}
