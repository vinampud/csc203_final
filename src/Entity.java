import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */

public abstract class Entity{

    public final static String BGND_KEY = "background";

    public final static String OBSTACLE_KEY = "obstacle";

    public final static String DUDE_KEY = "dude";

    public final static String HOUSE_KEY = "house";

    public final static String FAIRY_KEY = "fairy";

    public final static String STUMP_KEY = "stump";

    public final static String SAPLING_KEY = "sapling";

    public final static String TREE_KEY = "tree";

    public final static String WITCH_KEY = "witch";

    public final static String BURNING_KEY = "burning";

    public final static String DEAD_KEY = "dead";

    public final static String STRANGE_KEY = "strange";

    private final String id;
    private Point position;
    private final List<PImage> images;
    private int imageIndex;

    protected Entity(String id,
                     Point position,
                     List<PImage> images) {
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public List<PImage> getImages() {
        return images;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public PImage getCurrentImage() {
        return (this.images.get((this).imageIndex));
    }

    public int getNumFromRange(int max, int min) {
        Random rand = new Random();
        return min + rand.nextInt(
                max
                        - min);
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }
}

