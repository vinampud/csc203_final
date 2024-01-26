import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */

public class Tree extends Plant
{

    public static final int TREE_ANIMATION_MAX = 600;
    public static final int TREE_ANIMATION_MIN = 50;
    public static final int TREE_ACTION_MAX = 1400;
    public static final int TREE_ACTION_MIN = 1000;
    public static final int TREE_HEALTH_MAX = 3;
    public static final int TREE_HEALTH_MIN = 1;
    public static final int TREE_NUM_PROPERTIES = 7;
    public static final int TREE_ID = 1;
    public static final int TREE_COL = 2;
    public static final int TREE_ROW = 3;
    public static final int TREE_ANIMATION_PERIOD = 4;
    public static final int TREE_ACTION_PERIOD = 5;
    public static final int TREE_HEALTH = 6;

    public Tree(
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


    public void executeActivity(WorldModel world,
                                    ImageStore imageStore,
                                    EventScheduler scheduler)
    {

        if (!transformPlant(world, scheduler, imageStore)) {

            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore),
                    this.getActionPeriod());
        }
    }
}
