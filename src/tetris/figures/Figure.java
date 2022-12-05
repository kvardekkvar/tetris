package tetris.figures;

import tetris.constants.Direction;
import tetris.field.Cell;
import tetris.field.Field;
import tetris.utility.Util;

import java.awt.*;

import static tetris.constants.Parameters.NUMBER_OF_CELLS_X;
import static tetris.constants.Parameters.NUMBER_OF_CELLS_Y;

public abstract class Figure {

    private int[] cellCoordinatesX;
    private int[] cellCoordinatesY;
    private final int numberOfCells;
    private final String type;

    private double[] center;
    private final Color color;
    //private int orientation;

    public Figure(int[] cellCoordinatesX, int[] cellCoordinatesY, double[] center, String type ) {
        this.numberOfCells = cellCoordinatesX.length;
        this.cellCoordinatesX = cellCoordinatesX;
        this.cellCoordinatesY = cellCoordinatesY;
        this.type = type;
        this.color = Util.randomColor();
        this.center = center;
    }


    public void rotate(String direction){
        this.center = Util.calculateCenter(cellCoordinatesX, cellCoordinatesY);

        int sign;
        if (direction.equals(Direction.LEFT)) {
            sign = 1;
        } else {sign = -1;}
        for (int i =0; i<numberOfCells;i++) {
            int temp = cellCoordinatesX[i];
            cellCoordinatesX[i] = sign * cellCoordinatesY[i] + (int) (center[0] - sign * center[1]);
            cellCoordinatesY[i] = -sign * temp + (int) (sign * center[0] + center[1]);
        }


    }

    public void rotateLeft() {
            rotate(Direction.LEFT);
    }
    public void rotateRight() {
            rotate(Direction.RIGHT);
    }

    public boolean isMovable(String direction) {
        //rewrite -- looks ugly
        // bug -- только самые нижние клетки учитываются при движении вниз

        switch (direction) {
            case Direction.LEFT: {
                int targetX = (Util.MinInt(cellCoordinatesX)) - 1;

                if (targetX < 0) {
                    return false;
                } else {
                    for(int i = 0; i<cellCoordinatesY.length; i++) {
                        Cell newCell = Field.getCellByCoordinates(targetX, cellCoordinatesY[i]);
                        int currentCoordinate = cellCoordinatesX[i];
                        if(!newCell.isEmpty() && currentCoordinate - 1 == targetX) {
                            return false;
                        }
                    }
                }
                return true;
            }

            case Direction.RIGHT: {
                int targetX = (Util.MaxInt(cellCoordinatesX)) + 1;
                if (targetX >= NUMBER_OF_CELLS_X) {
                    return false;
                } else {
                    for (int i = 0; i < cellCoordinatesY.length; i++) {
                        Cell newCell = Field.getCellByCoordinates(targetX, cellCoordinatesY[i]);
                        int currentCoordinate = cellCoordinatesX[i];
                        if (!newCell.isEmpty() && currentCoordinate + 1 == targetX) {
                            return false;
                        }
                    }
                }
                return true;
            }
            case Direction.DOWN: {
                int targetY = Util.MaxInt(cellCoordinatesY) + 1;
                if(targetY>=NUMBER_OF_CELLS_Y) {calcify(); return false;}

                for(int i = 0; i<cellCoordinatesX.length; i++) {
                    Cell newCell = Field.getCellByCoordinates(cellCoordinatesX[i],targetY);
                    int currentCoordinate = cellCoordinatesY[i];
                    if(!newCell.isEmpty() && currentCoordinate + 1 == targetY) {
                        calcify();
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    private void move(String direction){
        if(isMovable(direction)) {
            switch (direction) {
                case Direction.DOWN:
                    for (int i = 0; i < cellCoordinatesY.length; i++) {
                        cellCoordinatesY[i] += 1;

                }
                    break;
                case Direction.RIGHT:
                    for (int i = 0; i < cellCoordinatesX.length; i++) {
                        cellCoordinatesX[i] += 1;

                    }
                    break;
                case Direction.LEFT:
                    for (int i = 0; i < cellCoordinatesX.length; i++) {
                        cellCoordinatesX[i] -= 1;

                    }
                    break;
            }
        }

    }
    public void moveDown(){
        if (isMovable(Direction.DOWN)) {
            move(Direction.DOWN);
        } else {
            calcify();
        }
    }
    public void moveRight(){
        move(Direction.RIGHT);
    }
    public void moveLeft(){
        move(Direction.LEFT);
    }
    public void calcify(){
        for(int i=0; i<numberOfCells; i++) {
            Field.getCellByCoordinates(cellCoordinatesX[i], cellCoordinatesY[i]).setCalcified(true);
        }
    }

    public int getNumberOfCells() {
        return numberOfCells;
    }


    public int[] getCellCoordinatesX() {
        return cellCoordinatesX;
    }

    public void setCellCoordinatesX(int[] cellCoordinatesX) {
        this.cellCoordinatesX = cellCoordinatesX;
    }

    public int[] getCellCoordinatesY() {
        return cellCoordinatesY;
    }

    public void setCellCoordinatesY(int[] cellCoordinatesY) {
        this.cellCoordinatesY = cellCoordinatesY;
    }

    public String getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
