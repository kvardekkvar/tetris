package tetris.figures;

public class PolyminoO extends Figure {

    public PolyminoO(int x, int y){
        super(new int[] {x, x, x+1, x+1}, new int[]{y, y-1,y,y-1}, new double[] {x+0.5,y-0.5}, "O");
    }
}
