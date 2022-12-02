package tetris.figures;

public class PolyminoT extends Figure {

    public PolyminoT(int x, int y){
            super(new int[] {x, x+1, x+1, x+2}, new int[] {y,y,y-1,y} , "T");
    }
}
