package tetris.utility;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Util {

    public static Color randomColor(){
        Random rand = new Random();
        int n = rand.nextInt(4);
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
    public static int MaxInt(int[] array) {
        int max = Arrays.stream(array).max().getAsInt();
        return max;
    }
    public static int MinInt(int[] array) {
        int min = Arrays.stream(array).min().getAsInt();
        return min;
    }

    public static double[] calculateCenter(int[] coordinatesX, int[] coordinatesY){
        double[] center = new double[2];
        center[0]=0;
        center[0]=0;
        for (int i =0; i< coordinatesX.length; i++) {
            center[0] += coordinatesX[i];
            center[1] += coordinatesY[i];
        }
        center[0] = center[0]/coordinatesX.length;
        center[1] = center[1]/coordinatesY.length;
        return center;
    }
}
