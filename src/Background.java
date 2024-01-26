import java.util.List;

import processing.core.PImage;

/**
 * Represents a background for the 2D world.
 */

public class Background
{
    private final String id;
    private final List<PImage> images;
    private int imageIndex;

    public static final int BGND_NUM_PROPERTIES = 4;
    public static final int BGND_ID = 1;
    public static final int BGND_COL = 2;
    public static final int BGND_ROW = 3;

    public Background(String id, List<PImage> images) {
        this.id = id;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public List<PImage> getImages() {
        return images;
    }

    public int getImageIndex() {
        return imageIndex;
    }


    public PImage getCurrentImage() {
        return (this.images.get(
                    this.imageIndex));
    }
}
