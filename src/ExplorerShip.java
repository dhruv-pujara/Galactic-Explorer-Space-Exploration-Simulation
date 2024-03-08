/**
 * The ExplorerShip class represents a spaceship specialized in exploration within the galactic space.
 * It inherits from the Spaceship class and defines specific movement and interaction behaviors.
 * @author Parisa Daeijavad
 */
public class ExplorerShip extends Spaceship {
    private int scanRange; // The range within which the explorer ship can scan for nearby spaceships
    private boolean moveHorizontally = true; // Flag to track horizontal movement

    /**
     * Constructs an ExplorerShip object with the specified attributes.
     *
     * @param id        The unique identifier of the explorer ship.
     * @param x         The initial x-coordinate of the explorer ship.
     * @param y         The initial y-coordinate of the explorer ship.
     * @param scanRange The range within which the explorer ship can scan for nearby spaceships.
     */
    public ExplorerShip(String id, int x, int y, int scanRange) {
        super(id, x, y, SpaceshipType.EXPLORER);
        this.scanRange = scanRange;
    }

    /**
     * Implements the movement behavior of the explorer ship within the galactic map.
     * The explorer ship moves in a zigzag pattern, alternating between horizontal and vertical movements.
     * <p>
     * .........
     */
    @Override
    public void move(GalacticMap galacticMap) {
        System.out.print("........Moving.......");
        int x = getX();
        int y = getY();

        if (moveHorizontally) {
            y++;
        } else {
            x++;
        }
        if (x < 0 || x >= galacticMap.getSize()  || y < 0 || y >= galacticMap.getSize()) {
            System.out.println("Moving Failed! out of bounds x or y!");
        }
        if (galacticMap.getSpaceshipAt(x, y) != null) {
            System.out.println("Moving Failed! the position is filled with another spaceship!");
        } else {
            setX(x);
            setY(y);
            System.out.println("Move Configuration");
            System.out.println(galacticMap.toString());
        }
    }

    /**
     * Implements the interaction behavior of the explorer ship with another spaceship.
     * The explorer ship reports nearby spaceships found within its scan range during interaction.
     * <p>
     * ......
     */
    @Override
    public void interact(GalacticMap galacticMap, Spaceship other) {
        System.out.println(".........interacting...........with.... " + other.getName());

        if(other instanceof ExplorerShip){
            System.out.println("the spaceship cannot interact with itself");
        }
        int distance = calculateDistance(other);
        if(distance <= scanRange){
            System.out.println("Found" + other.getName() + " at distance: " + distance);
        } else{
            System.out.println("Spaceship: " + other.getName() + " is not in the scan-range");
        }

    }
}
