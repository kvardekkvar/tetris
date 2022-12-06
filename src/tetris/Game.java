package tetris;

import tetris.constants.Direction;
import tetris.figures.Figure;
import tetris.field.Field;
import tetris.figures.*;

import java.util.Random;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static tetris.constants.Parameters.*;

public class Game {

    private Figure activeFigure;
    private final Field field;

    private boolean isPaused;
    private final boolean isOver;




    public Game(){
        this.field = new Field();
        this.isPaused = false;
        this.isOver = false;
    }

    public void run()  {

        field.fieldGUI.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                processUserInput(e.getKeyCode());
            }
        });


        try {
            while (!isOver) {
                activeFigure = newActiveFigure();
                field.drawFigure(activeFigure);
                while (activeFigure.isMovable(Direction.DOWN)) {
                    if (!isPaused) {
                        //processUserInput();
                        activeFigure.moveDown();
                        field.setCellsArrayFromActiveFigure(activeFigure);
                        field.repaint();
                        if(field.isReducible()) {
                            field.reduceAll();
                        }
                        Thread.sleep(500);
                    } else {
                        System.out.println("Paused");
                        Thread.sleep(1000);
                        //processUserInput();
                    }
                }
            }
        } catch (InterruptedException e) {
         throw new RuntimeException(e);
        }
    }

    public Figure newActiveFigure(){
        Random rand = new Random();
        int type = isMock? 1 : rand.nextInt(4);

        int XCoordinateActiveFigure = NUMBER_OF_CELLS_X/2;
        int YCoordinateActiveFigure = 1;
        // rewrite

        switch (type)
        {
            case 0:
                return new PolyminoI(XCoordinateActiveFigure,YCoordinateActiveFigure);
            case 1:
                return new PolyminoO(XCoordinateActiveFigure,YCoordinateActiveFigure);
            case 2:
                return new PolyminoT(XCoordinateActiveFigure,YCoordinateActiveFigure);
            case 3:
                return new PolyminoZ(XCoordinateActiveFigure,YCoordinateActiveFigure);

        }
        return null;
    }

    public void processUserInput(int keyCode){
        switch (keyCode){
            case KeyEvent.VK_UP:
                System.out.println("Key pressed");
                break;
            case KeyEvent.VK_A:
                activeFigure.moveLeft();
                break;
            case KeyEvent.VK_D:
                activeFigure.moveRight();
                break;
            case KeyEvent.VK_S:
                activeFigure.moveDown();
                break;
            case KeyEvent.VK_LEFT:
                activeFigure.rotateLeft();
                break;
            case KeyEvent.VK_RIGHT:
                activeFigure.rotateRight();
                break;
            case KeyEvent.VK_P:
                pause();
                break;
            case KeyEvent.VK_SPACE:
                activeFigure.drop();
                break;
        }
        field.setCellsArrayFromActiveFigure(activeFigure);
        field.repaint();

    }

    public void pause(){
        isPaused=!isPaused;
    }

}
