import processing.core.PImage;

import java.util.List;

public abstract class Animated extends Entity{

    private final int animationPeriod;

    protected Animated(String id, Point position, List<PImage> images, int animationPeriod) {
        super(id, position, images);
        this.animationPeriod = animationPeriod;
    }

    public int getAnimationPeriod() {
        return this.animationPeriod;
    }

    public void nextImage() {
        this.setImageIndex((this.getImageIndex() + 1) % this.getImages().size());
    }


}
