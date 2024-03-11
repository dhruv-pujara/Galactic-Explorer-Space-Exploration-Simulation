import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    // Tests to check if the specified ship's ID matches with the co-ordinates from the text file
    @Test
    public void FileReaderValidTest1() {
        GalacticMap galacticMap = FileReader.readFromFile("config.txt");
        Spaceship ship = galacticMap.getSpaceshipAt(3, 3);
        if (ship instanceof ExplorerShip explorerShip) {
            assertEquals("e8263", explorerShip.getID());
        }

    }

    // Tests to check whether an error is detected if the file is not found
    @Test
    public void FileReaderInvalidFile(){
        boolean error = false;

        GalacticMap galacticMap;

        try {
            galacticMap = FileReader.readFromFile("config12.txt");
        } catch (RuntimeException e ) {
            error = true;
        }
        assertTrue(error);
    }


    // Tests if the toString() method returns the correct string representation for an empty map
    @Test
    public void toStringWithEmptyMap() {
        GalacticMap galacticMap = new GalacticMap(3);
        String expected = "[         ][         ][         ]\n[         ][         ][         ]\n[         ][         ][         ]\n";
        assertEquals(expected, galacticMap.toString());
    }

    // Tests if the toString() method returns the correct string representation for an empty map
    @Test
    public void toStringWithData() {
        // Create a galactic map with no spaceships
        GalacticMap galacticMap = new GalacticMap(3);
        Spaceship ship = new FighterShip("f1332",0,0,12);
        Spaceship ship1 = new ExplorerShip("e2232",2,2,4);
        galacticMap.placeSpaceship(ship);
        galacticMap.placeSpaceship(ship1);

        String expected = "[ F-f1332 ][         ][         ]\n" +
                "[         ][         ][         ]\n" +
                "[         ][         ][ E-e2232 ]\n";

        assertEquals(expected, galacticMap.toString());
    }

    // Test to verify that a FighterShip moves correctly within the bounds of the GalacticMap
    @Test
    public void fighterShipMoveOutOfBounds() {
        GalacticMap galacticMap = new GalacticMap(3);
        FighterShip fighterShip = new FighterShip("f4534", 2, 2, 15);
        galacticMap.placeSpaceship(fighterShip);
        fighterShip.move(galacticMap);
        assertTrue(fighterShip.getX() >= 0 && fighterShip.getX() < 3);
        assertTrue(fighterShip.getY() >= 0 && fighterShip.getY() < 3);
    }

    // Test to verify that a FighterShip moves correctly within the bounds of the GalacticMap
    @Test
    public void fighterShipMoveInBounds() {
        GalacticMap galacticMap = new GalacticMap(5);
        FighterShip fighterShip = new FighterShip("f4734", 1, 2, 15);
        galacticMap.placeSpaceship(fighterShip);
        fighterShip.move(galacticMap);
        assertTrue(fighterShip.getX() >= 0 && fighterShip.getX() < 5);
        assertTrue(fighterShip.getY() >= 0 && fighterShip.getY() < 5);
    }


    // Test to check interaction between a FighterShip and an ExplorerShip within range
    @Test
    public void interactWithExplorerShipInRange() {
        GalacticMap galacticMap = new GalacticMap(5);
        FighterShip fighterShip = new FighterShip("f4534", 2, 2, 18);
        ExplorerShip explorerShip = new ExplorerShip("e7645", 3, 3, 2);
        galacticMap.placeSpaceship(fighterShip);
        galacticMap.placeSpaceship(explorerShip);
        fighterShip.interact(galacticMap, explorerShip);
        assertNull(galacticMap.getSpaceshipAt(explorerShip.getX(), explorerShip.getY()));
    }

    // Test to check interaction between a FighterShip and an ExplorerShip out of range
    @Test
    public void interactWithExplorerShipNotInRange() {
        GalacticMap galacticMap = new GalacticMap(5);
        FighterShip fighterShip = new FighterShip("f4534", 2, 3, 1);
        ExplorerShip explorerShip = new ExplorerShip("e7645", 4, 0, 2);
        galacticMap.placeSpaceship(fighterShip);
        galacticMap.placeSpaceship(explorerShip);
        fighterShip.interact(galacticMap, explorerShip);
        assertNotNull(galacticMap.getSpaceshipAt(explorerShip.getX(), explorerShip.getY()));
    }

    // Test to check if an ExplorerShip moves correctly with an obstacle in its path
    @Test
    public void exploreShipMoveWithObstacle() {
        GalacticMap galacticMap = new GalacticMap(5);
        ExplorerShip explorerShip = new ExplorerShip("e7645", 2, 2, 2);
        galacticMap.placeSpaceship(explorerShip);
        FighterShip obstacle = new FighterShip("f9088", 3, 2, 10);
        galacticMap.placeSpaceship(obstacle);
        explorerShip.move(galacticMap);
        assertNotEquals(3, explorerShip.getX());
        assertNotEquals(2, explorerShip.getY());
    }

    // Test to verify that an ExplorerShip moves correctly within the bounds of the GalacticMap
    @Test
    public void exploreShipMoveInBounds() {
        GalacticMap galacticMap = new GalacticMap(6);
        ExplorerShip explorerShip = new ExplorerShip("e0005", 5, 1, 4);
        galacticMap.placeSpaceship(explorerShip);
        explorerShip.move(galacticMap);
        assertTrue(explorerShip.getX() >= 0 && explorerShip.getX() < 6);
        assertTrue(explorerShip.getY() >= 0 && explorerShip.getY() < 6);
    }

    // Test to check interaction of an ExplorerShip with itself
    @Test
    public void testInteractWithItself() {
        GalacticMap galacticMap = new GalacticMap(3);
        ExplorerShip explorerShip = new ExplorerShip("e2342", 1, 0, 3);
        galacticMap.placeSpaceship(explorerShip);

        explorerShip.interact(galacticMap, explorerShip);

    }

    // Test to verify that a generic Spaceship moves correctly within the bounds of the GalacticMap
    @Test
    public void testMoveWithinBounds() {
        // Create a galactic map with the spaceship at the center
        GalacticMap galacticMap = new GalacticMap(5);
        Spaceship spaceship = new FighterShip("f3242", 2,1,13);
        int oldX = spaceship.getX();
        int oldy = spaceship.getY();

        spaceship.move(galacticMap);

        int newX = spaceship.getX();
        int newY = spaceship.getY();
        boolean xCheck = (oldX==newX), yCheck = (oldy==newY);
        assertFalse(xCheck ||yCheck);
}

    // Test to verify interaction of a CargoShip with an ExplorerShip within range
    @Test
    public void cargoInteractWithExplorerShipInRange() {
        GalacticMap galacticMap = new GalacticMap(6);
        ExplorerShip explorerShip = new ExplorerShip("e0005", 3, 2, 4);
        CargoShip cargoShip = new CargoShip("c3221", 2, 2, 10, 5, 1, 1);
        galacticMap.placeSpaceship(cargoShip);
        galacticMap.placeSpaceship(explorerShip);
        cargoShip.interact(galacticMap, explorerShip);
    }

    // Test to verify interaction of a CargoShip with another CargoShip
    @Test
    public void cargoInteractWithAnotherCargo() {
        GalacticMap galacticMap = new GalacticMap(6);
        CargoShip cargoship1 = new CargoShip("c0005", 2, 2, 10, 8, 1, 1);
        CargoShip cargoShip2 = new CargoShip("c3221", 3, 2, 10, 1, 1, 1);
        galacticMap.placeSpaceship(cargoship1);
        galacticMap.placeSpaceship(cargoShip2);
        cargoship1.interact(galacticMap,cargoShip2);
        assertEquals(4.5, cargoship1.getCurrentCargo());
        assertEquals(4.5, cargoShip2.getCurrentCargo());
    }

    // Test to verify that a Spaceship does not move outside the bounds of the GalacticMap
    @Test
    public void spaceshipMoveOutsideBounds(){
        GalacticMap galacticMap = new GalacticMap(3);
        Spaceship spaceship = new Spaceship("f4444", 2, 2, SpaceshipType.FIGHTER) {
            @Override
            public void move(GalacticMap galacticMap) {

            }
            @Override
            public void interact(GalacticMap galacticMap, Spaceship other) {

            }
        };
        galacticMap.placeSpaceship(spaceship);
        spaceship.move(galacticMap);
        assertEquals(2,spaceship.getX());
        assertEquals(2,spaceship.getY());

    }
}