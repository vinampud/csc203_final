import java.util.*;

import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */

public class House extends Entity
{

    public static final int HOUSE_NUM_PROPERTIES = 4;
    public static final int HOUSE_ID = 1;
    public static final int HOUSE_COL = 2;
    public static final int HOUSE_ROW = 3;

    public House(
            String id,
            Point position,
            List<PImage> images
            )
    {
        super(id, position, images);
    }

}
