import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Factory {

    public static Action createAnimationAction(Animated entity, int repeatCount) {

        return new AnimationAction(entity, null,
                repeatCount);
    }

    public static House createHouse(
            String id, Point position, List<PImage> images)
    {
        return new House(id, position, images);
    }

    public static Action createActivityAction(
            Movables entity, WorldModel world, ImageStore imageStore)
    {
        return new ActivityAction(entity, world, imageStore);
    }


    public static Obstacle createObstacle(
            String id, Point position, int animationPeriod, List<PImage> images)
    {
        return new Obstacle(id, position, images, animationPeriod);
    }

    public static Tree createTree(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int health,
            List<PImage> images)
    {
        return new Tree(id, position, images, actionPeriod, animationPeriod, health, 0);
    }

    public static BurningTree createBurningTree(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            int health,
            List<PImage> images)
    {
        return new BurningTree(id, position, images, actionPeriod, animationPeriod, health, 0);
    }

    public static DeadTree createDeadTree(
            String id,
            Point position,
            List<PImage> images)
    {
        return new DeadTree(id, position, images);
    }

    // health starts at 0 and builds up until ready to convert to Tree
    public static Sapling createSapling(
            String id,
            Point position,
            List<PImage> images)
    {
        return new Sapling(id, position, images,
                Sapling.SAPLING_ACTION_ANIMATION_PERIOD, Sapling.SAPLING_ACTION_ANIMATION_PERIOD, 0, Sapling.SAPLING_HEALTH_LIMIT);
    }

    public static Witch createWitch(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
        return new Witch(id, position, images, actionPeriod, animationPeriod);
    }

    public static Strange createStrange(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
        return new Strange(id, position, images, actionPeriod, animationPeriod);
    }
}
