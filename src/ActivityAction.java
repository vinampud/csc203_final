public final class ActivityAction implements Action {

    /**
     * An action that can be taken by an entity*/

    private final Movables entity;
    private final WorldModel world;
    private final ImageStore imageStore;

    public ActivityAction(
            Movables entity,
            WorldModel world,
            ImageStore imageStore) {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
    }

    public Movables getEntity() {
        return entity;
    }

    public WorldModel getWorld() {
        return world;
    }

    public ImageStore getImageStore() {
        return imageStore;
    }

    public void executeAction(EventScheduler scheduler) {

        this.entity.executeActivity(this.world,
                this.imageStore, scheduler);
    }

}
