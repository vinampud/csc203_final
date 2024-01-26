import java.util.Scanner;

public class ParseEntity {

    private static final int PROPERTY_KEY = 0;


    public ParseEntity(){

    }

    public static void load(Scanner in, WorldModel world, ImageStore imageStore)
    {
        int lineNumber = 0;
        while (in.hasNextLine()) {
            try {
                if (!processLine(in.nextLine(), world, imageStore)) {
                    System.err.println(String.format("invalid entry on line %d",
                            lineNumber));
                }
            }
            catch (NumberFormatException e) {
                System.err.println(
                        String.format("invalid entry on line %d", lineNumber));
            }
            catch (IllegalArgumentException e) {
                System.err.println(
                        String.format("issue on line %d: %s", lineNumber,
                                e.getMessage()));
            }

            lineNumber++;
        }
    }

    public static boolean processLine(String line, WorldModel world, ImageStore imageStore)
    {
        String[] properties = line.split("\\s");
        if (properties.length > 0) {
            switch (properties[PROPERTY_KEY]) {
                case Entity.BGND_KEY:
                    return parseBackground(properties, world, imageStore);
                case Entity.OBSTACLE_KEY:
                    return parseObstacle(properties, world, imageStore);
                case Entity.WITCH_KEY:
                    return parseWitch(properties, world, imageStore);
                case Entity.STRANGE_KEY:
                    return parseStrange(properties, world, imageStore);
                case Entity.BURNING_KEY:
                    return parseBurningTree(properties, world, imageStore);
                case Entity.HOUSE_KEY:
                    return parseHouse(properties, world, imageStore);
                case Entity.TREE_KEY:
                    return parseTree(properties, world, imageStore);
                case Entity.SAPLING_KEY:
                    return parseSapling(properties, world, imageStore);
            }
        }

        return false;
    }

    public static boolean parseHouse(String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == House.HOUSE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[House.HOUSE_COL]),
                    Integer.parseInt(properties[House.HOUSE_ROW]));
            House entity = Factory.createHouse(properties[House.HOUSE_ID], pt,
                    imageStore.getImageList(
                            Entity.HOUSE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == House.HOUSE_NUM_PROPERTIES;
    }

    public static boolean parseObstacle(String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == Obstacle.OBSTACLE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Obstacle.OBSTACLE_COL]),
                    Integer.parseInt(properties[Obstacle.OBSTACLE_ROW]));
            Obstacle entity = Factory.createObstacle(properties[Obstacle.OBSTACLE_ID], pt,
                    Integer.parseInt(properties[Obstacle.OBSTACLE_ANIMATION_PERIOD]),
                    imageStore.getImageList(
                            Entity.OBSTACLE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == Obstacle.OBSTACLE_NUM_PROPERTIES;
    }

    public static boolean parseTree(String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == Tree.TREE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Tree.TREE_COL]),
                    Integer.parseInt(properties[Tree.TREE_ROW]));
            Tree entity = Factory.createTree(properties[Tree.TREE_ID],
                    pt,
                    Integer.parseInt(properties[Tree.TREE_ACTION_PERIOD]),
                    Integer.parseInt(properties[Tree.TREE_ANIMATION_PERIOD]),
                    Integer.parseInt(properties[Tree.TREE_HEALTH]),
                    imageStore.getImageList(Entity.TREE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == Tree.TREE_NUM_PROPERTIES;
    }

    public static boolean parseBurningTree(String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == Tree.TREE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Tree.TREE_COL]),
                    Integer.parseInt(properties[Tree.TREE_ROW]));
            Tree entity = Factory.createTree(properties[Tree.TREE_ID],
                    pt,
                    Integer.parseInt(properties[Tree.TREE_ACTION_PERIOD]),
                    Integer.parseInt(properties[Tree.TREE_ANIMATION_PERIOD]),
                    Integer.parseInt(properties[Tree.TREE_HEALTH]),
                    imageStore.getImageList(Entity.TREE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == Tree.TREE_NUM_PROPERTIES;
    }

    public static boolean parseWitch(String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == Witch.WITCH_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Witch.WITCH_COL]),
                    Integer.parseInt(properties[Witch.WITCH_ROW]));
            Witch entity = Factory.createWitch(properties[Witch.WITCH_ID],
                    pt,
                    Integer.parseInt(properties[Witch.WITCH_ACTION_PERIOD]),
                    Integer.parseInt(properties[Witch.WITCH_ANIMATION_PERIOD]),
                    imageStore.getImageList(Entity.WITCH_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == Witch.WITCH_NUM_PROPERTIES;
    }

    public static boolean parseStrange(String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == Strange.STRANGE_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Strange.STRANGE_COL]),
                    Integer.parseInt(properties[Strange.STRANGE_ROW]));
            Strange entity = Factory.createStrange(properties[Strange.STRANGE_ID],
                    pt,
                    Integer.parseInt(properties[Strange.STRANGE_ACTION_PERIOD]),
                    Integer.parseInt(properties[Strange.STRANGE_ANIMATION_PERIOD]),
                    imageStore.getImageList(Entity.STRANGE_KEY));
            world.tryAddEntity(entity);
        }

        return properties.length == Strange.STRANGE_NUM_PROPERTIES;
    }


    public static boolean parseSapling(String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == Sapling.SAPLING_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Sapling.SAPLING_COL]),
                    Integer.parseInt(properties[Sapling.SAPLING_ROW]));
            String id = properties[Sapling.SAPLING_ID];
            int health = Integer.parseInt(properties[Sapling.SAPLING_HEALTH]);
            Entity entity = new Sapling(id, pt, imageStore.getImageList(Entity.BURNING_KEY),
                    Sapling.SAPLING_ACTION_ANIMATION_PERIOD, Sapling.SAPLING_ACTION_ANIMATION_PERIOD, health, Sapling.SAPLING_HEALTH_LIMIT);
            world.tryAddEntity(entity);
        }

        return properties.length == Sapling.SAPLING_NUM_PROPERTIES;
    }

    public static boolean parseBackground(String[] properties, WorldModel world, ImageStore imageStore)
    {
        if (properties.length == Background.BGND_NUM_PROPERTIES) {
            Point pt = new Point(Integer.parseInt(properties[Background.BGND_COL]),
                    Integer.parseInt(properties[Background.BGND_ROW]));
            String id = properties[Background.BGND_ID];
            world.setBackground(pt,
                    new Background(id, imageStore.getImageList(id)));
        }

        return properties.length == Background.BGND_NUM_PROPERTIES;
    }
}
