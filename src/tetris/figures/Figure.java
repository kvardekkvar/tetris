package tetris.figures;

public abstract class Figure {

    private boolean isStopped = false;
    private int numberOfCells;
    private int[] cellCoordinatesX;
    private int[] cellCoordinatesY;
    private String type;
    private int center;
    //private int orientation;

    public Figure(int[] cellCoordinatesX, int[] cellCoordinatesY, String type) {
        this.numberOfCells = cellCoordinatesX.length;
        this.cellCoordinatesX = cellCoordinatesX;
        this.cellCoordinatesY = cellCoordinatesY;
        this.type = type;
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


}
