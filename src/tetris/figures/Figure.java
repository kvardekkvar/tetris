package tetris.figures;

import tetris.utility.Util;

import java.awt.*;

public abstract class Figure {

    private boolean isStopped = false;
    private int[] cellCoordinatesX;
    private int[] cellCoordinatesY;
    private final int numberOfCells;
    private final String type;

    private int center;
    private Color color;
    //private int orientation;

    public Figure(int[] cellCoordinatesX, int[] cellCoordinatesY, String type) {
        this.numberOfCells = cellCoordinatesX.length;
        this.cellCoordinatesX = cellCoordinatesX;
        this.cellCoordinatesY = cellCoordinatesY;
        this.type = type;
        this.color = Util.randomColor();
    }


    public void rotateLeft() {

    }
    public void rotateRight() {

    }

    public boolean isMovable(String direction){

        return true;
    }

    public void moveDown(){

    }

    public void calcify(){

    }

    public void moveRight(){

   }
    public void moveLeft(){

    }


    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
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
