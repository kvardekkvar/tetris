package tetris.field;
import tetris.field.Cell;

import javax.swing.*;

import tetris.constants.Parameters;
import tetris.figures.Figure;

import java.awt.*;

import static tetris.constants.Parameters.SIZE_OF_CELL;

public class Field extends JPanel {

    private final int horizontalSize;
    private final int verticalSize;
    private final Cell[][] CellsArray;
    private StatusBar status;
    public final JFrame fieldGUI;

    public Field(){
        super();
        this.fieldGUI = new JFrame();
        this.horizontalSize = Parameters.SIZE_HORIZONTAL_PX;
        this.verticalSize = Parameters.SIZE_VERTICAL_PX;
        fieldGUI.setSize(horizontalSize,verticalSize);
        fieldGUI.add(this);
        fieldGUI.setVisible(true);

        int cellNumberX = Parameters.SIZE_HORIZONTAL_PX/ SIZE_OF_CELL;
        int cellNumberY = Parameters.SIZE_VERTICAL_PX/ SIZE_OF_CELL;
        this.CellsArray = new Cell[cellNumberX][cellNumberY];
        for (int i=0; i<cellNumberX;i++) {
          for(int j=0; j<cellNumberY; j++) {
              this.CellsArray[i][j] = new Cell(i* SIZE_OF_CELL, j* SIZE_OF_CELL, Color.WHITE,true);
          }
        }
        // rewrite -- is order correct?
        // do cells need to be initialized?



    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Cell[] temp: CellsArray) {
            for (Cell c : temp) {
                g.setColor(c.getColor());

                g.fillRect(c.getCellX(),c.getCellY(),SIZE_OF_CELL,SIZE_OF_CELL);
                g.setColor(java.awt.Color.black);
                g.drawRect(c.getCellX(),c.getCellY(),SIZE_OF_CELL,SIZE_OF_CELL);
            }
        }
        fieldGUI.setVisible(true);
    }

    public Cell getCellByCoordinates(int x, int y) {
        return CellsArray[x][y];
    }


    public boolean isReducible(){

        return false;
    }

    public void reduce(int row){

    }

    public void reduceAll() {
    }

    public void drawFigure(Figure figure) {
        for(int i : figure.getCellCoordinatesX()){
            for(int j : figure.getCellCoordinatesY()){
                CellsArray[i][j].setEmpty(false);
                CellsArray[i][j].setColor(figure.getColor());
            }
        }
        repaint();
    }
}
