public class AnimationAction implements Action {

    /**
     * An action that can be taken by an entity*/

    private final Animated entity;
    private final ImageStore imageStore;
    private final int repeatCount;

    public AnimationAction(
            Animated entity,
            ImageStore imageStore,
            int repeatCount) {
        this.entity = entity;
        this.imageStore = imageStore;
        this.repeatCount = repeatCount;
    }

    public Animated getEntity() {
        return entity;
    }


    public ImageStore getImageStore() {
        return imageStore;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void executeAction(EventScheduler scheduler) {
        this.entity.nextImage();

        if (this.repeatCount != 1) {
            scheduler.scheduleEvent(this.entity,
                    Factory.createAnimationAction(this.entity,
                            Math.max(this.repeatCount - 1,
                                    0)),
                    this.entity.getAnimationPeriod());
        }
    }

}
