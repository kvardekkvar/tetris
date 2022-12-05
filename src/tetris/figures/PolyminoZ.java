package tetris.figures;

public class PolyminoZ extends Figure {
    public PolyminoZ(int x, int y) {
        super(new int[] {x,x+1,x+1,x+2}, new int[] {y,y,y-1,y-1}, new double[] {x+1, y-0.5}, "Z");
    }
}
