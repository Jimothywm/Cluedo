package cluedo;

import java.io.*;
import java.util.Date;
import java.util.Properties;

public class GetPropertyValues {
    String[] result = new String[8];
    InputStream inputStream;
    String numofplayers;
    String gametheme;
    String player1col;
    String player2col;
    String player3col;
    String player4col;
    String player5col;
    String player6col;


    public String[] getPropValues() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "resources/config.prp";

            //inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            inputStream = new FileInputStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            //Date time = new Date(System.currentTimeMillis());

            // get the property value and print it out
            String numofplayers = prop.getProperty("numofplayers");
            String gametheme = prop.getProperty("gametheme");
            String player1col = prop.getProperty("player1col");
            String player2col = prop.getProperty("player2col");
            String player3col = prop.getProperty("player3col");
            String player4col = prop.getProperty("player4col");
            String player5col = prop.getProperty("player5col");
            String player6col = prop.getProperty("player6col");
            result = new String[]{numofplayers, gametheme, player1col, player2col, player3col, player4col, player5col, player6col};

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        //result = new String[]{numofplayers, gametheme, player1col, player2col, player3col, player4col, player5col, player6col};
        result = new String[]{numofplayers, gametheme, player1col, player2col, player3col, player4col, player5col, player6col};
        System.out.println(numofplayers);
        return result;
    }
}
