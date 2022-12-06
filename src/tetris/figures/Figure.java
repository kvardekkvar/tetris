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
    private final Color color;
    //private int orientation;

    public Figure(int[] cellCoordinatesX, int[] cellCoordinatesY, String type ) {
        this.numberOfCells = cellCoordinatesX.length;
        this.cellCoordinatesX = cellCoordinatesX;
        this.cellCoordinatesY = cellCoordinatesY;
        this.type = type;
        this.color = Util.randomColor();
    }

    public void rotate(String direction){
        double[] center = Util.calculateCenter(cellCoordinatesX, cellCoordinatesY);

        int sign;
        if (direction.equals(Direction.LEFT)) {
            sign = 1;
        } else {sign = -1;}

        int[] newCellCoordinatesX = new int[numberOfCells];
        int[] newCellCoordinatesY = new int[numberOfCells];

        // rewrite below -- looks ugly
        for (int i = 0; i < numberOfCells; i++) {
            int temp = cellCoordinatesX[i];

            double addendumX = center[0] - sign * center[1];
            double addendumY = sign * center[0] + center[1];
            int addendumXInt = direction.equals(Direction.LEFT) ? (int) Math.floor(addendumX) : (int) Math.ceil(addendumX);
            int addendumYInt = direction.equals(Direction.LEFT) ? (int) Math.floor(addendumY) : (int) Math.floor(addendumY);
            newCellCoordinatesX[i] = sign * cellCoordinatesY[i] + addendumXInt;
            newCellCoordinatesY[i] = -sign * temp + addendumYInt;
        }
        setCellCoordinates(newCellCoordinatesX, newCellCoordinatesY);
    }

    public void rotateLeft() {
            rotate(Direction.LEFT);
    }
    public void rotateRight() {
            rotate(Direction.RIGHT);
    }

    public boolean isMovable(String direction) {
        //rewrite -- looks ugly
        //rewrite -- ideologically wrong (should be inside setter)

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

                    Cell newCell = Field.getCellByCoordinates(cellCoordinatesX[i], cellCoordinatesY[i] + 1);
                        if (newCell.isCalcified()) {
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

    public void drop(){
        while(isMovable(Direction.DOWN)) {
            move(Direction.DOWN);
        }
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

    public int[] getCellCoordinatesY() {
        return cellCoordinatesY;
    }

    public void setCellCoordinates(int[] cellCoordinatesX, int[] cellCoordinatesY){

        boolean flag = true;
        if (!Util.isInsideFieldX(cellCoordinatesX) || !Util.isInsideFieldY(cellCoordinatesY)) {
        flag = false; }

        for (int i=0;i<cellCoordinatesX.length;i++) {
            if(Field.getCellByCoordinates(cellCoordinatesX[i],cellCoordinatesY[i]).isCalcified()) {
                flag=false;
            }
        }
            if(flag){

            this.cellCoordinatesX = cellCoordinatesX;
            this.cellCoordinatesY = cellCoordinatesY;
        }
    }


    public String getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
