import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    public static GalacticMap readFromFile(String fileName) {
        int size = 1;
        GalacticMap map = new GalacticMap(size);
        java.io.FileReader fr = null;
        try {
            fr = new java.io.FileReader(new File(fileName));
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        // Your code goes here ....

        // hint: you need to call placeSpaceship method....
        return map;
    }
}
