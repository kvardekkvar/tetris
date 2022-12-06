package tetris.field;
import tetris.field.Cell;

import javax.swing.*;

import tetris.constants.Parameters;
import tetris.figures.Figure;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        CellsArray = new Cell[NUMBER_OF_CELLS_X][NUMBER_OF_CELLS_Y];
        for (int i = 0; i< NUMBER_OF_CELLS_X; i++) {
          for(int j = 0; j< NUMBER_OF_CELLS_Y; j++) {
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
        boolean flag = false;
        for (int j = 0 ; j< NUMBER_OF_CELLS_Y; j++){
            flag = true;
            for (int i = 0; i< NUMBER_OF_CELLS_X; i++) {
                if (!CellsArray[i][j].isCalcified()) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    private void reduce(int rowIndex){
        Cell[][] transposedArray = transpose();
            for (Cell c : transposedArray[rowIndex]) {
                c.setCalcified(false);
                c.setColor(Color.white);
            }
            for(int i = rowIndex; i>=0; i--) {
                for (int j=0; j<NUMBER_OF_CELLS_X; j++) {
                    cellSwap(j, i);
                }
            }

    }

    private void cellSwap(int row, int column) {

        Cell c1 = getCellByCoordinates(row, column);
        Cell c2 = getCellByCoordinates(row-1, column);
        CellsArray[column][row] = c2;
        CellsArray[column][row-1] = c1;

    }

    public void reduceAll() {
        Cell[][] transposedArray = transpose();
        while (isReducible()) {
            List<Integer> rows = new ArrayList<>();
            for (int i = NUMBER_OF_CELLS_Y-1; i >= 0; i--) {
                Cell[] row = transposedArray[i];
                if (Arrays.stream(row).allMatch(c -> c.isCalcified())) {
                    rows.add(i);
                    reduce(i);
                }

            }
            System.out.println(rows);
        }
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


    private Cell[][] transpose(){
        Cell[][] transposed = new Cell[NUMBER_OF_CELLS_Y][NUMBER_OF_CELLS_X];
        for(int i=0; i<NUMBER_OF_CELLS_X; i++){
            for(int j=0; j<NUMBER_OF_CELLS_Y; j++) {
                transposed[j][i] = CellsArray[i][j];
            }
        }
        return transposed;
    }
}
