import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Witch extends Beings{
    public static final int WITCH_NUM_PROPERTIES = 7;
    public static int WITCH_ID = 1;
    public static final int WITCH_COL = 2;
    public static final int WITCH_ROW = 3;
    public static final int WITCH_ACTION_PERIOD = 1500;
    public static final int WITCH_ANIMATION_PERIOD = 1000;

    public Witch(
            String id,
            Point position,
            List<PImage> images,
            int actionPeriod,
            int animationPeriod)
    {
        super(id, position, images, actionPeriod, animationPeriod);
    }

    @Override
    protected void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> target =
                world.findNearest(this.getPosition(), new ArrayList<>(List.of(Tree.class)));


       if (target.isPresent()) {
            Point tgtPos = target.get().getPosition();

            if (this.moveTo(world, target.get(), scheduler)) {
                BurningTree burningTree = Factory.createBurningTree("tree_" + this.getId(),
                        tgtPos,
                        getNumFromRange(BurningTree.BURN_ACTION_MAX, BurningTree.BURN_ACTION_MIN),
                        getNumFromRange(BurningTree.BURN_ANIMATION_MAX, BurningTree.BURN_ANIMATION_MIN),
                        getNumFromRange(BurningTree.BURN_HEALTH_MAX, BurningTree.BURN_HEALTH_MIN),
                        imageStore.getImageList(BURNING_KEY));

                world.addEntity(burningTree);
                burningTree.scheduleActions(scheduler, world, imageStore);
            }
        }
       scheduler.scheduleEvent(this, Factory.createActivityAction(this, world, imageStore), WITCH_ACTION_PERIOD);
    }

    @Override
    public Point nextPosition(WorldModel world, Point destPos)
    {

        Predicate<Point> canPassThrough = (p) -> (world.withinBounds(p) && !(( world.isOccupied(p) && (world.getOccupancyCell(p).getClass() != DeadTree.class))));
        BiPredicate<Point, Point> withinReach = Point :: adjacent;

        PathingStrategy strat = new SingleStepPathingStrategy();
        List<Point> path = strat.computePath(this.getPosition(), destPos, canPassThrough, withinReach , PathingStrategy.CARDINAL_NEIGHBORS);

        if (path.size() == 0)
            return this.getPosition();
        return path.get(0);

//        int horiz = Integer.signum(destPos.getX() - this.getPosition().getX());
//        Point newPos = new Point(this.getPosition().getX() + horiz, this.getPosition().getY());
//
//        if (horiz == 0 || world.isOccupied(newPos)) {
//            int vert = Integer.signum(destPos.getY() - this.getPosition().getY());
//            newPos = new Point(this.getPosition().getX(), this.getPosition().getY() + vert);
//
//            if (vert == 0 || world.isOccupied(newPos)) {
//                newPos = this.getPosition();
//            }
//        }
//        return newPos;
    }

    @Override
    protected boolean _moveTo(WorldModel world,
                              Entity target,
                              EventScheduler scheduler)
    {
        world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            return true;

    }
}
