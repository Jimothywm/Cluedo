package cluedo;

import com.sun.tools.javac.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The type Main app.
 */
public class MainApp {

    /**
     * Instantiates a new Main app.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public MainApp() throws FileNotFoundException{
        String[] tempsArray;
        String boardString =  "";
        String[] board;

        //Reads file containing gameboard in text format.
        //TO DO: self-contained filepath that will work universally.
        Scanner inFile1 = new Scanner(new File("Gameboard_Grid.txt")).useDelimiter(",\\s*");

        List<String> temps = new ArrayList<String>();
        // while loop
        while (inFile1.hasNext()) {
            // find next line
            String token1 = inFile1.next();
            //System.out.println(token1);
            temps.add(token1);
        }
        inFile1.close();
        tempsArray = temps.toArray(new String[0]);
        boardString = Arrays.toString(tempsArray);
        boardString = boardString.replaceAll("[\\r\\n\\[\\]]", "");
        board = boardString.split("");

        GameCycle gameCycle = new GameCycle(boardString);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws FileNotFoundException the file not found exception
     */
    public static void main(String[] args) throws FileNotFoundException{
        MainApp mainApp = new MainApp();
    }

}
