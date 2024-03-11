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

    // Test checks the specified ship coordinates in config.txt match the given coordinates
    @Test
    public void FileReaderValidTest2(){
        GalacticMap galacticMap = FileReader.readFromFile("config.txt");
        Spaceship spaceship = galacticMap.getSpaceshipAt(3,4);
        CargoShip cargoShip = (CargoShip) spaceship;
        assertEquals(3, cargoShip.getX());
        assertEquals(4, cargoShip.getY());
    }

    @Test
    public void toStringWithEmptyMap(){
        GalacticMap galacticMap = new GalacticMap(3);
        String expected = "[         ][         ][         ]\n[         ][         ][         ]\n[         ][         ][         ]\n";
        assertEquals(expected,galacticMap.toString());
    }

    @Test
    public void fighterShipMoveOutOfBounds(){
        GalacticMap galacticMap = new GalacticMap(3);
        FighterShip fighterShip = new FighterShip("f4534",2,2,15);
        galacticMap.placeSpaceship(fighterShip);
        fighterShip.move(galacticMap);
        assertTrue(fighterShip.getX() >= 0 && fighterShip.getX() < 3);
        assertTrue(fighterShip.getY() >= 0 && fighterShip.getY() < 3);
    }

    @Test
    public void fighterShipMoveInBounds(){
        GalacticMap galacticMap = new GalacticMap(5);
        FighterShip fighterShip = new FighterShip("f4734",1,2,15);
        galacticMap.placeSpaceship(fighterShip);
        fighterShip.move(galacticMap);
        assertTrue(fighterShip.getX() >= 0 && fighterShip.getX() < 5);
        assertTrue(fighterShip.getY() >= 0 && fighterShip.getY() < 5);
    }



    @Test
    public void interactWithExplorerShipInRange(){
        GalacticMap galacticMap = new GalacticMap(5);
        FighterShip fighterShip = new FighterShip("f4534",2,2,18);
        ExplorerShip explorerShip = new ExplorerShip("e7645",3,3,2);
        galacticMap.placeSpaceship(fighterShip);
        galacticMap.placeSpaceship(explorerShip);
        fighterShip.interact(galacticMap, explorerShip);
        assertNull(galacticMap.getSpaceshipAt(explorerShip.getX(),explorerShip.getY()));
    }

    @Test
    public void interactWithExplorerShipNotInRange(){
        GalacticMap galacticMap = new GalacticMap(5);
        FighterShip fighterShip = new FighterShip("f4534",2,3,1);
        ExplorerShip explorerShip = new ExplorerShip("e7645",4,0,2);
        galacticMap.placeSpaceship(fighterShip);
        galacticMap.placeSpaceship(explorerShip);
        fighterShip.interact(galacticMap, explorerShip);
        assertNotNull(galacticMap.getSpaceshipAt(explorerShip.getX(),explorerShip.getY()));
    }

    @Test
    public void exploreShipMoveWithObstacle(){
        GalacticMap galacticMap = new GalacticMap(5);
        ExplorerShip explorerShip = new ExplorerShip("e7645",2,2,2);
        galacticMap.placeSpaceship(explorerShip);
        FighterShip obstacle = new FighterShip("f9088",3,2,10);
        galacticMap.placeSpaceship(obstacle);
        explorerShip.move(galacticMap);
        assertNotEquals(3,explorerShip.getX());
        assertNotEquals(2,explorerShip.getY());
    }

    @Test
    public void exploreShipMoveInBounds(){
        GalacticMap galacticMap = new GalacticMap(6);
        ExplorerShip explorerShip = new ExplorerShip("e0005",5,1,4);
        galacticMap.placeSpaceship(explorerShip);
        explorerShip.move(galacticMap);
        assertTrue(explorerShip.getX() >= 0 && explorerShip.getX() < 6);
        assertTrue(explorerShip.getY() >= 0 && explorerShip.getY() < 6);
    }

//    @Test
//    public void interactWithFighterShipsInRange(){
//        GalacticMap galacticMap = new GalacticMap(5);
//        ExplorerShip explorerShip = new ExplorerShip("e9087", 2,2,3);
//        FighterShip fighterShip = new FighterShip("f2341",3,2,10);
//        FighterShip fighterShipOutOfRange = new FighterShip("f5644",5,5,10)
//        galacticMap.placeSpaceship(explorerShip);
//        galacticMap.placeSpaceship(fighterShip);
//        galacticMap.placeSpaceship(fighterShipOutOfRange);
//        explorerShip.interact(galacticMap, fighterShip);
//        assertTrue(explorerShip.calculateDistance(fighterShip) <= galacticMap);
//
//    }
    @Test
    public void cargoInteractWithExplorerShipInRange(){
        GalacticMap galacticMap = new GalacticMap(6);
        ExplorerShip explorerShip = new ExplorerShip("e0005",3,2,4);
        CargoShip cargoShip = new CargoShip("c3221",2,2,10,5,1,1);
        galacticMap.placeSpaceship(cargoShip);
        galacticMap.placeSpaceship(explorerShip);
        cargoShip.interact(galacticMap, explorerShip);
    }
}