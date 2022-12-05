package tetris.field;

import java.awt.*;

import static tetris.constants.Parameters.SIZE_OF_CELL;

public class Cell extends Rectangle {
    private Color color;

    private boolean isEmpty;
    private boolean isCalcified;

    private final int cellX;
    private final int cellY;
    private final int cellW;
    private final int cellH;




    public Cell(int x, int y, Color color, boolean isEmpty){
        super(x,y,SIZE_OF_CELL,SIZE_OF_CELL);
        this.color = color;
        this.isEmpty = isEmpty;
        this.cellX = x ;
        this.cellY = y ;
        this.cellW = SIZE_OF_CELL ;
        this.cellH = SIZE_OF_CELL;
        this.isCalcified = false;
    }
    public Cell(int x, int y, Color color){
        this(x,y,color,true);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public int getCellX() {
        return cellX;
    }

    public int getCellY() {
        return cellY;
    }

    public boolean isCalcified() {
        return isCalcified;
    }

    public void setCalcified(boolean calcified) {
        isCalcified = calcified;
    }
}
