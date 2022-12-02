package tetris.field;
import tetris.field.Cell;

public class Field {

    private int horizontalSize;
    private int verticalSize;
    private Cell[][] CellsArray;
    private StatusBar status;

    public Field(){

    }
    public Cell getCellByCoordinates(int x, int y) {
        return CellsArray[x][y];
    }

    public void show(){

    }


    public boolean isReducible(){

        return false;
    }

    public void reduce(int row){

    }
}
