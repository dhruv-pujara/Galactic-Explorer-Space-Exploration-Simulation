/**
 * The CargoShip class represents a spaceship specialized in transporting cargo within the galactic space.
 * It inherits from the Spaceship class and defines specific movement, interaction, and cargo management behaviors.
 * @author Parisa Daeijavad
 */
public class CargoShip extends Spaceship {

    private double cargoCapacity; // Maximum cargo capacity of the CargoShip
    private double currentCargo;  // Current amount of cargo on the CargoShip

    private int targetX; // The x-coordinate of the cargo ship's destination.
    private int targetY; // The y-coordinate of the cargo ship's destination.

    private boolean reachedDestination; // Indicates if the cargo has reached its destination

    /**
     * Constructs a CargoShip object with the specified attributes.
     *
     * @param id            The unique identifier of the cargo ship.
     * @param x             The initial x-coordinate of the cargo ship.
     * @param y             The initial y-coordinate of the cargo ship.
     * @param cargoCapacity The maximum cargo capacity of the cargo ship.
     * @param currentCargo  The current amount of cargo on the cargo ship.
     * @param targetX       The x-coordinate of the cargo ship's destination.
     * @param targetY       The y-coordinate of the cargo ship's destination.
     */
    public CargoShip(String id, int x, int y, double cargoCapacity, double currentCargo, int targetX, int targetY) {
        // Initialize CargoShip attributes properly
        super(id, x, y, SpaceshipType.CARGOSHIP);
        this.reachedDestination = false;
        this.cargoCapacity = cargoCapacity;
        this.currentCargo = currentCargo;
        this.targetX = targetX;
        this.targetY = targetY;    }

    /**
     * Implements the movement behavior of the cargo ship within the galactic map.
     * The cargo ship moves towards its designated target coordinates.
     *
     * ....................
     */
    @Override
    public void move(GalacticMap galacticMap) {

        System.out.print("........Moving.......");
        System.out.println();
        if(isReachedDestination()){
            System.out.println("CargoShip: " + getID() + " is already in destination");
            return;
        }

        int x = getX();
        int y = getY();
        int size = galacticMap.toString().split("\n").length;

        if (x < targetX) {
            x++;
            galacticMap.moveSpaceshipTo(this, x, y);

        } else if (x > targetX) {
            x--;
            galacticMap.moveSpaceshipTo(this, x, y);

        }
        if (x == targetX) {
            if (y < targetY) {
                y++;
                galacticMap.moveSpaceshipTo(this, x, y);

            } else if (y > targetY) {
                y--;
                galacticMap.moveSpaceshipTo(this, x, y);

            }
            if (x < 0 || x >= size || y < 0 || y > size) {
                System.out.println("Moving Failed! out of bound x or y!");
            }
            if (galacticMap.getSpaceshipAt(x, y) != null) {
                System.out.println("Moving Failed! the position is filled with another spaceship!");
            }
                setX(x);
                setY(y);
                System.out.println("Move Configuration");
                System.out.println(galacticMap.toString());
        }
    }



    /**
     * Implements the interaction behavior of the cargo ship with another spaceship.
     * The cargo ship can exchange cargo with other cargo ships during interaction.
     *
     * ............
     */
    @Override
    public void interact(GalacticMap galacticMap, Spaceship other) {

        System.out.println(".........interacting...........with.... " + other.getName());
        if (other== this) {
            System.out.println("CargoShip cannot interact with itself");
        } else if (other instanceof FighterShip) {
            System.out.println("CargoShip cannot interact with FIGHTER");
        } else if (other instanceof ExplorerShip) {
            System.out.println("CargoShip cannot interact with EXPLORER");
        }else if(other instanceof CargoShip otherCargoShip) {
            double transferAmount = Math.abs(otherCargoShip.getCurrentCargo() - this.currentCargo) / 2.0;
            if (otherCargoShip.getCurrentCargo() > this.currentCargo) {
                otherCargoShip.unloadCargo(transferAmount);
                this.loadCargo(transferAmount);
            } else if (otherCargoShip.getCurrentCargo() < this.currentCargo) {
                otherCargoShip.loadCargo(transferAmount);
                this.unloadCargo(transferAmount);
            }
        }else{
            System.out.println("Cargoship cannot interact with " + other.getType());
        }
    }

    /**
     * Loads cargo onto the CargoShip up to its maximum capacity.
     *
     * ...........
     */
    public void loadCargo(double cargoAmount) {
        if(currentCargo + cargoAmount > cargoCapacity){
            System.out.println("Cargo capacity exceeded! Cannot load cargo onto C-" + getID());
        } else{
            currentCargo += cargoAmount;
        }

    }

    /**
     * Unloads cargo from the CargoShip.
     *
     * ..............
     */
    public void unloadCargo(double cargoAmount) {
        if (cargoAmount > currentCargo){
            System.out.println("Cannot unload more cargo that what's currently on board.");
        } else{
            currentCargo -= cargoAmount;
        }

    }

    /**
     * Retrieves the current amount of cargo on the CargoShip.
     *
     * @return The current cargo amount.
     */
    public double getCurrentCargo() {
        return currentCargo;
    }

    /**
     * Retrieves the x-coordinate of the CargoShip's destination.
     *
     * @return The x-coordinate of the destination.
     */
    public int getTargetX() {
        return targetX;
    }

    /**
     * Retrieves the y-coordinate of the CargoShip's destination.
     *
     * @return The y-coordinate of the destination.
     */
    public int getTargetY() {
        return targetY;
    }

    /**
     * Sets the status of whether the CargoShip has reached its destination.
     *
     * @param b True if the CargoShip has reached its destination, false otherwise.
     */
    public void setReachedDestination(boolean b) {
        reachedDestination = b;
    }

    /**
     * Checks if the CargoShip has reached its destination.
     *
     * @return True if the CargoShip has reached its destination, false otherwise.
     */
    public boolean isReachedDestination() {
        return reachedDestination;
    }
}

