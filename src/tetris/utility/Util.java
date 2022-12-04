package tetris.utility;

import java.awt.*;
import java.util.Random;

public class Util {

    public static Color randomColor(){
        Random rand = new Random();
        int n = rand.nextInt(3);
        switch(n){
            case 0:
                return Color.GREEN;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.RED;

        }
        return null;
    }
}
