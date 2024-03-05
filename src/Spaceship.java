/**
 * The abstract class Spaceship represents a generic spaceship entity in the galactic space.
 * It defines common attributes and behaviors for different types of spaceships.
 * @author Parisa Daeijavad
 */
public abstract class Spaceship {

    // attributes:
    private String id;
    private int x;
    private int y;
    private SpaceshipType type;


    // The unique identifier of the spaceship
    // The current coordinates of the spaceship
    // The type of the spaceship

    // methods:

    /**
     * Constructs a Spaceship object with the specified attributes.
     *
     * @param ....
     *
     */
    public Spaceship(String id, int x, int y, SpaceshipType type){
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * Abstract method to define the movement behavior of the spaceship.
     *
     */
    public abstract void move(GalacticMap galacticMap);

    /**
     * Abstract method to define the interaction behavior of the spaceship with another spaceship.
     *
     * @param other The other spaceship to interact with
     */
    public abstract void interact(GalacticMap galacticMap, Spaceship other);


    // Getters and setters

    /**
     * Retrieves the unique identifier of the spaceship.
     * @return The ID of the spaceship
     */
    // getID
    protected String getID(){
        return id;
    }


    /**
     * Retrieves the current x-coordinate of the spaceship.
     * @return The x-coordinate of the spaceship
     */
    // getX
    protected int getX(){
        return x;
    }

    /**
     * Retrieves the current y-coordinate of the spaceship.
     * @return The y-coordinate of the spaceship
     */
    // getY
    protected int getY() {
        return y;
    }

    /**
     * Retrieves the type of the spaceship.
     * <p>
     * @return The type of spaceship
     */
    // getType
    protected SpaceshipType getType() {
        return type;
    }

    /**
     * Sets the x-coordinate of the spaceship to the specified value.
     * @param newX The new x-coordinate to set
     */
    // setX
    public void setX(int newX){
        this.x = newX;

    }

    /**
     * Sets the y-coordinate of the spaceship to the specified value.
     * @param newY The new x-coordinate to set
     */
    // setY
    public void setY(int newY) {
        this.y = newY;

    }

    /**
     * Calculates the distance between this spaceship and another spaceship.
     *
     * @param other The other spaceship to calculate the distance to.
     * @return The distance between this spaceship and the other spaceship.
     */
    public int calculateDistance(Spaceship other) {
        int deltaX = Math.abs(this.getX() - other.getX());
        int deltaY = Math.abs(this.getY() - other.getY());
        return Math.max(deltaX, deltaY);
    }

    /**
     * Retrieves the name of the spaceship.
     *
     * @return The name of the spaceship, which includes its type and ID.
     */
    public String getName() {
        return this.getType() + " " + this.getID();
    }
}
