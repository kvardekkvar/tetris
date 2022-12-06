package tetris.figures;

public class PolyminoI extends Figure {

    public PolyminoI(int x, int y) {
        super(new int[] {x, x+1, x+2, x+3}, new int[] {y, y, y, y},"I");
    }
}
