package tetris.field;
import tetris.field.Cell;

import javax.swing.*;

import tetris.constants.Parameters;
import tetris.figures.Figure;

import java.awt.*;

import static tetris.constants.Parameters.*;

public class Field extends JPanel {

    private static Cell[][] CellsArray;

    private StatusBar status;
    public final JFrame fieldGUI;

    public Field(){
        super();
        this.fieldGUI = new JFrame();
        fieldGUI.setSize(Parameters.SIZE_HORIZONTAL_PX+50,Parameters.SIZE_VERTICAL_PX+50);
        fieldGUI.add(this);
        fieldGUI.setVisible(true);

        int cellNumberX = NUMBER_OF_CELLS_X;
        int cellNumberY = NUMBER_OF_CELLS_Y;
        CellsArray = new Cell[cellNumberX][cellNumberY];
        for (int i=0; i<cellNumberX;i++) {
          for(int j=0; j<cellNumberY; j++) {
              CellsArray[i][j] = new Cell(i* SIZE_OF_CELL, j* SIZE_OF_CELL, Color.WHITE,true);
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

    public static Cell getCellByCoordinates(int x, int y) {
        return CellsArray[x][y];
    }

    public void setCellsArrayFromActiveFigure(Figure activeFigure) {
        for(int i=0; i<NUMBER_OF_CELLS_X; i++){
            for(int j=0; j<NUMBER_OF_CELLS_Y; j++) {
                if(!CellsArray[i][j].isEmpty() && !CellsArray[i][j].isCalcified()) {
                    CellsArray[i][j].setEmpty(true);
                    CellsArray[i][j].setColor(Color.WHITE);
                }
            }
        }

        for (int i=0; i< activeFigure.getNumberOfCells(); i++)
        {
            int x = activeFigure.getCellCoordinatesX()[i];
            int y = activeFigure.getCellCoordinatesY()[i];
            CellsArray[x][y].setEmpty(false);
            CellsArray[x][y].setColor(activeFigure.getColor());
        }
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
