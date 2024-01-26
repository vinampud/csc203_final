import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import processing.core.PImage;

public class Strange extends Beings{
    public static final int STRANGE_NUM_PROPERTIES = 6;
    public static int STRANGE_ID = 1;
    public static final int STRANGE_COL = 2;
    public static final int STRANGE_ROW = 3;
    public static final int STRANGE_ACTION_PERIOD = 4;
    public static final int STRANGE_ANIMATION_PERIOD = 5;

    protected Strange(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod) {
        super(id, position, images, animationPeriod, actionPeriod);
    }

    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        Predicate<Point> canPassThrough = (p) -> (world.withinBounds(p) && !world.isOccupied(p));
        BiPredicate<Point, Point> withinReach = Point :: adjacent;

        PathingStrategy strat = new SingleStepPathingStrategy();
        List<Point> path = strat.computePath(this.getPosition(), destPos, canPassThrough, withinReach , PathingStrategy.CARDINAL_NEIGHBORS);

        if (path.size() == 0)
            return this.getPosition();
        return path.get(0);
    }

    @Override
    protected boolean _moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        world.removeEntity(target);
        scheduler.unscheduleAllEvents(target);
        return true;
    }

    @Override
    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> target =
                world.findNearest(this.getPosition(), new ArrayList<>(List.of(Witch.class)));

        if (target.isPresent())
            this.moveTo(world, target.get(), scheduler);


        scheduler.scheduleEvent(this, Factory.createActivityAction(this, world, imageStore), 500);

    }
}
