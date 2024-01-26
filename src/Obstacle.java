import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */

public class Obstacle extends Animated
{
    public static final int OBSTACLE_NUM_PROPERTIES = 5;
    public static final int OBSTACLE_ID = 1;
    public static final int OBSTACLE_COL = 2;
    public static final int OBSTACLE_ROW = 3;
    public static final int OBSTACLE_ANIMATION_PERIOD = 4;

    public Obstacle(
            String id,
            Point position,
            List<PImage> images,
            int animationPeriod)
    {
        super(id, position, images, animationPeriod);
    }

    // schedule actions is different for obstacle
    public void scheduleActions(EventScheduler scheduler,
                                WorldModel world,
                                ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
            Factory.createAnimationAction(this, 0),
            this.getAnimationPeriod());


    }
}
