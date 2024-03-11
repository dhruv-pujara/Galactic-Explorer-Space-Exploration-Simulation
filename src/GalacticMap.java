import java.util.HashSet;
import java.util.Random;

/**
 * The GalacticMap class represents the grid-based map of the galactic space.
 * It stores information about the positions of spaceships and provides methods
 * for managing the entities within the map.
 *
 * <p>This class contains functionality to add fighters, retrieve a list of reported spaceships,
 * and check three game winning conditions for the galactic space.</p>
 *
 * @author Parisa Daeijavad
 */
public class GalacticMap {
    private Spaceship[][] grid; // 2D array representing the grid of the galactic map
    private int fighterNumber = 0; // Counter for the number of fighters in the map
    private HashSet<Spaceship> reportList = new HashSet<Spaceship>(); // Set to store reported fighter spaceships

    /**
     * Constructs a GalacticMap object with the specified size.
     *
     * @param size The size of the grid for the galactic map.
     */
    public GalacticMap(int size) {
        this.grid = new Spaceship[size][size];
    }

    /**
     * Adds a fighter spaceship to the report list.
     *
     * @param fighter The fighter spaceship to add to the report list.
     */
    public void AddReportedFighter(FighterShip fighter) {
        reportList.add(fighter);
    }

    /**
     * Adds one to the count of fighters in the GalacticMap.
     */
    public void addOneFighter() {
        fighterNumber++;
    }

    /**
     * Retrieves the spaceship at the specified coordinates in the GalacticMap.
     *
     * @param x The x-coordinate of the position to retrieve the spaceship.
     * @param y The y-coordinate of the position to retrieve the spaceship.
     * @return The spaceship at the specified coordinates.
     */
    public Spaceship getSpaceshipAt(int x, int y) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
            return this.grid[x][y];
        } else {
            return null;
        }
    }

    /**
     * Retrieves a random spaceship from the GalacticMap.
     *
     * @return A random spaceship from the GalacticMap.
     */
    public Spaceship getRandomSpaceship() {
        Random random = new Random();
        Spaceship randomSpaceship = null;

        // Keep generating random coordinates until a non-null grid cell is found
        while (randomSpaceship == null) {
            int randomX = random.nextInt(grid.length);
            int randomY = random.nextInt(grid[0].length);
            randomSpaceship = grid[randomX][randomY];
        }

        return randomSpaceship;
    }

    /**
     * Returns a string representation of the GalacticMap.
     * <p>
     * ...............
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Spaceship currentShip = grid[i][j];
                if (grid[i][j] == null) {
                    result.append("[         ]");
                } else {
                    String type = null;
                    if (grid[i][j] instanceof ExplorerShip) {
                        type = "E-";
                    } else if (grid[i][j] instanceof FighterShip) {
                        type = "F-";
                    } else if (grid[i][j] instanceof CargoShip) {
                        type = "C-";
                    }
                    result.append("[ ").append(type).append(((Spaceship) currentShip).getID()).append(" ]");
                }
            }
            result.append("\n");
        }

        return result.toString();
    }

    /**
     * Removes the spaceship at the specified coordinates in the GalacticMap.
     *
     * @param x The x-coordinate of the position to remove the spaceship.
     * @param y The y-coordinate of the position to remove the spaceship.
     */
    public void removeSpaceshipAt(int x, int y) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
            this.grid[x][y] = null;
        }
    }

    /**
     * Moves the specified spaceship to the new coordinates in the GalacticMap.
     * <p>
     * ........
     */
    public void moveSpaceshipTo(Spaceship spaceship, int newX, int newY) {
        if (!isValidMove(newX, newY)) {
            System.out.println("Moving failed! out of bound x or y!");
            return;
        }
        if (isCollision(newX, newY)) {
            System.out.println("Moving Failed! the position is filled with another spaceship!");
            return;
        }
        grid[spaceship.getX()][spaceship.getY()] = null;
        spaceship.setX(newX);
        spaceship.setY(newY);
        grid[newX][newY] = spaceship;
        System.out.println(toString());
    }

    /**
     * Checks if the specified coordinates represent a valid move within the GalacticMap grid.
     * <p>
     * ............
     */
    private boolean isValidMove(int newX, int newY) {
        return newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length;
    }

    /**
     * Checks if the specified coordinates represent a collision with another spaceship.
     * <p>
     * .......
     */
    private boolean isCollision(int newX, int newY) {
        return grid[newX][newY] != null;
    }

    /**
     * Places the specified spaceship, that is read from the file, in the GalacticMap.
     * <p>
     * .............
     */
    public void placeSpaceship(Spaceship spaceship) {
        int x = spaceship.getX();
        int y = spaceship.getY();

        if (!isValidMove(x, y)) {
            throw new ArrayIndexOutOfBoundsException("Wrong input file! position is outside of the map!");
        }
        if (isCollision(x, y)) {
            throw new IllegalArgumentException("Wrong input file! the position is filled with another item!");
        }
        grid[x][y] = spaceship;

    }

    /**
     * Checks if all cargoes have reached their destinations.
     * <p>
     * ..............
     */
    public boolean allCargoesReachedDestination() {
        boolean anyCargo = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Spaceship spaceship = grid[i][j];
                if (spaceship != null && spaceship.getType() == SpaceshipType.CARGOSHIP) {
                    anyCargo = true;
                    CargoShip cargoShip = (CargoShip) spaceship;
                    if (!cargoShip.isReachedDestination()) {
                        return false;
                    }
                }
            }
        }
        return anyCargo;
    }

    /**
     * Checks if all explorers and cargoes have been removed by fighters.
     * <p>
     * ............
     */
    public boolean allExplorersAndCargoesRemoved() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Spaceship spaceship = grid[i][j];
                if (spaceship != null && (spaceship.getType() == SpaceshipType.EXPLORER || spaceship.getType() == SpaceshipType.CARGOSHIP)) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Checks if all fighters have been reported by explorers.
     * <p>
     * ................
     */
    public boolean allFightersReported() {
        for (Spaceship[] spaceships : grid) {
            for (Spaceship spaceship : spaceships) {
                if (spaceship instanceof FighterShip && !reportList.contains(spaceship)) {
                    return false;
                }
            }

        }
        return true;
    }
}