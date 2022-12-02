package tetris.field;

public class Cell {
    private int x;
    private int y;
    private int color;

    private boolean isEmpty;

    public Cell(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.color = color;
        this.isEmpty = true;
    }
    public Cell(int x, int y, int color, boolean isEmpty){
        this.x = x;
        this.y = y;
        this.color = color;
        this.isEmpty = isEmpty;
    }
}
