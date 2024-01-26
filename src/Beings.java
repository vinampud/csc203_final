import processing.core.PImage;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public abstract class Beings extends Movables{

    protected Beings(String id, Point position, List<PImage> images, int animationPeriod, int actionPeriod){
        super(id, position, images, animationPeriod, actionPeriod);
    }

    public abstract Point nextPosition(WorldModel world, Point destPos);

    public boolean moveTo(WorldModel world,
                          Entity target,
                          EventScheduler scheduler)
    {
        boolean result;
        if (this.getPosition().adjacent(target.getPosition())) {
            result = _moveTo(world, target, scheduler);

        }
        else {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
        return result;
    }

    protected abstract boolean _moveTo(WorldModel world,
                                       Entity target,
                                       EventScheduler scheduler);
}
