import java.sql.SQLOutput;
import java.util.Random;
/**
 * The FighterShip class represents a spaceship specialized in combat within the galactic space.
 * It inherits from the Spaceship class and defines specific movement and interaction behaviors.
 * @author Parisa Daeijavad
 */
public class FighterShip extends Spaceship {
    private int damage;// The damage inflicted by the fighter ship during combat

    /**
     * Constructs a FighterShip object with the specified attributes.
     *
     * @param id     The unique identifier of the fighter ship.
     * @param x      The initial x-coordinate of the fighter ship.
     * @param y      The initial y-coordinate of the fighter ship.
     * @param damage The damage inflicted by the fighter ship during combat.
     */
    public FighterShip(String id, int x, int y, int damage) {
        // Initialize FighterShip attributes
        super(id, x, y, SpaceshipType.FIGHTER);
        this.damage = damage;
    }

    /**
     * Generates a random direction for the fighter ship to move.
     *
     * @return A random integer representing the direction (0 to 7).
     */
    private int getRandomDirection() {
        Random random = new Random();
        return random.nextInt(8); // Generates a random integer between 0 and 7
    }

    /**
     * Implements the movement behavior of the fighter ship within the galactic map.
     * The movement of the fighter ship is random, as it changes direction randomly.\
     *
     * ..............
     *
     */
    @Override
    public void move(GalacticMap galacticMap) {
        System.out.print("........Moving.......");
        int x = getX();
        int y = getY();

        int direction = getRandomDirection();

        switch (direction) {
            case 0:
                x--;
                break;
            case 1:
                x++;
                break;
            case 2:
                y--;
                break;
            case 3:
                y++;
                break;
            case 4:
                x++;
                y++;
                break;
            case 5:
                x++;
                y--;
                break;
            case 6:
                x--;
                y++;
                break;
            case 7:
                x--;
                y--;
                break;
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
     * Implements the interaction behavior of the fighter ship with another spaceship.
     * The fighter ship engages in combat with other spaceships during interaction.
     *
     * .........
     *
     */
    @Override
    public void interact(GalacticMap galacticMap, Spaceship other) {
        System.out.println(".........interacting...........with.... " + other.getName());

        if(other instanceof FighterShip){
            System.out.println("fighters do not fight with fighters");
        }
        int distance = calculateDistance(other);
        if(damage < distance){
            System.out.println("damage is less than distance!");
        }
        System.out.println("FIGHTER " + getID() + " destroyed spaceship: " + other.getName() + " " + other.getID());
        galacticMap.removeSpaceshipAt(other.getX(), other.getY());
        System.out.println("Interaction Configuration");
        System.out.println(galacticMap.toString());
    }
}

