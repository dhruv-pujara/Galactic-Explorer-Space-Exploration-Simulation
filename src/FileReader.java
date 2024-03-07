import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * The FileReader class provides static methods for reading data from a file and constructing a GalacticMap object.
 * It reads a text file containing information about spaceships and their attributes, and initializes a GalacticMap
 * based on the data read from the file.
 *
 * @author Parisa Daeijavad
 *
 */

public class FileReader {

    /**
     * Reads data from a specified file and constructs a GalacticMap object based on the information read.
     *
     * @param fileName the name of the file to read from
     * @return a GalacticMap object initialized with data read from the file
     * @throws RuntimeException if the file specified by fileName is not found or if an error occurs while reading the file
     *
     */

    public static GalacticMap readFromFile(String fileName){
        GalacticMap galacticMap = null;
        Set<String> encounteredIds = new HashSet<>();
        Set<String> encounteredPositions = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(fileName))){
            String firstLine = br.readLine();
            if (firstLine == null || firstLine.trim().isEmpty()){
                throw new IllegalArgumentException("Invalid file format: Missing map size.");
            }
            int mapSize;
            try{
                mapSize = Integer.parseInt(firstLine.trim());
            }catch (NumberFormatException e){
                throw new IllegalArgumentException("Invalid file format: Missing map size.");
            }
            galacticMap = new GalacticMap(mapSize);

            String line;
            while ((line = br.readLine())!=null){
                if(line.isEmpty()){
                    continue;
                }
                String[] parts = line.split(" ");
                if (parts.length < 5){
                    System.out.println(Arrays.toString(parts));
                    throw new IllegalArgumentException("Invalid data format: Missing spaceship attributes.");
                }
                String type = parts[0];
                if(!type.equals("FIGHTER") && !type.equals("EXPLORER") && !type.equals("CARGOSHIP")){
                    throw new IllegalArgumentException("Invalid spaceship type: " + type);
                }
                String id = parts[1];
                if(id.length() != 5){
                    throw new IllegalArgumentException("Invalid ID length: " + id);
                }
                if (encounteredIds.contains(id)){
                    throw new IllegalArgumentException("Non-unique ID:" +id);
                }
                encounteredIds.add(id);

                int x, y;
                try {
                     x = Integer.parseInt(parts[2]);
                     y = Integer.parseInt(parts[3]);
                     if(x < 0 || x >= mapSize || y < 0 || y >= mapSize){
                         throw new ArrayIndexOutOfBoundsException("Wrong input file! position is outside of the map!");
                     }
                } catch (NumberFormatException e){
                    System.err.println("Invalid data format: Unable to parse numeric value");
                    continue;
                }
                String position = x + "," + y;
                if (encounteredPositions.contains(position)){
                    throw new IllegalArgumentException("Wrong input file! the position is filled with with another item!");
                }
                encounteredIds.add(position);

                switch (type){
                    case "FIGHTER":
                        int damage;
                        try {
                            damage = Integer.parseInt(parts[4]);
                        } catch (NumberFormatException e){
                            System.err.println("Invalid data format: Unable to parse numeric value");
                            continue;
                        }
                        galacticMap.placeSpaceship(new FighterShip(id, x, y, damage));
                        break;

                    case "EXPLORER":
                        int scanRange;
                        try {
                            scanRange = Integer.parseInt(parts[4]);
                        } catch (NumberFormatException e){
                            System.err.println("Invalid data format: Unable to parse numeric value");
                            continue;
                        }
                        galacticMap.placeSpaceship(new ExplorerShip(id, x, y, scanRange));
                        break;

                    case "CARGOSHIP":
                        if (parts.length < 8){
                            throw new IllegalArgumentException("Invalid data format: Missing cargo ship attributes.");
                        }
                        double cargoCapacity, currentCargo;
                        int targetX, targetY;
                        try {
                            cargoCapacity = Double.parseDouble(parts[4]);
                            currentCargo = Double.parseDouble(parts[5]);
                            targetX = Integer.parseInt(parts[6]);
                            targetY = Integer.parseInt(parts[7]);
                        } catch (NumberFormatException e){
                            System.err.println("Invalid data format: Unable to parse numeric value");
                            continue;
                        }
                        galacticMap.placeSpaceship(new CargoShip(id, x, y, cargoCapacity, currentCargo, targetX, targetY));
                        break;

                }

            }


        } catch (IOException e) {
            throw new UncheckedIOException("File not found: " + fileName, e);
        }


        return galacticMap;
    }
}
