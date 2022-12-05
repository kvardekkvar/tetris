package tetris.figures;

public class PolyminoI extends Figure {

    public PolyminoI(int x, int y) {
        super(new int[] {x, x+1, x+2, x+3}, new int[] {y, y, y, y}, new double[] {x + 1.5, y},"I");
    }
}
