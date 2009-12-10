package edu.cuny.brooklyn.tetris;

import edu.cuny.brooklyn.tetris.grid.ColoredGrid;
import edu.cuny.brooklyn.tetris.shape.Shape;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * Main class that runs the Game extends the Midlet class, as required
 * by J2ME
 *
 * @author Ramin Rakhamimov
 * @author Jonathan Weinblatt
 */
public class TetrisMIDlet extends MIDlet implements CommandListener, Runnable
{
    private static final int ANIMATION_RATE = 300;
    private static final int X_CELLS = 15;
    private static final int Y_CELLS = 20;
    public final int LEFT_KEY = -3;
    public final int RIGHT_KEY = -4;
    public final int UP_KEY = -1;
    public final int DOWN_KEY = -2;

    private final Command exitCommand;
    private final Command resetCommand;
    private final Command pauseCommand;

    private final Display display;

    // private final Timer timer_;
    private int xPosition_;
    private int yPosition_;
    private final int velocity_ = 1;
    private Shape currentShape_;
    private final ColoredGrid cellGrid_;
    private boolean paused;

    public TetrisMIDlet() {
        display = Display.getDisplay(this);
        exitCommand = new Command("Exit", Command.EXIT, 0);
        pauseCommand = new Command("Pause", Command.SCREEN, 0);
        resetCommand = new Command("Reset", Command.SCREEN, 0);

        cellGrid_ = new ColoredGrid(this, X_CELLS, Y_CELLS);

        cellGrid_.addCommand(exitCommand);
        cellGrid_.addCommand(pauseCommand);
        cellGrid_.addCommand(resetCommand);
        cellGrid_.setCommandListener(this);
    }

    /**
     * Resets all states of the game.
     */
    final public void resetGame() {
        cellGrid_.clearAll();
        refreshState();
        paused = false;
    }

    final private void refreshState() {
        currentShape_ = Shape.randomShape();
        yPosition_ = 0;
        xPosition_ = X_CELLS / 2 - currentShape_.getWidth() / 2;
    }

    /**
     * Updates game state on each timer iteration.
     */
    public void run() {
        if (!paused) {
            yPosition_ += velocity_;

            Shape movedShape = currentShape_.move(xPosition_, yPosition_);

            if (cellGrid_.collidesWith(movedShape, ColoredGrid.BOTTOM_CELL)) {
                if (yPosition_ < currentShape_.getHeight()) {
                    cellGrid_.setMessage("Nice Try!");
                    cellGrid_.repaint();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    resetGame();
                    return;
                } else {
                    cellGrid_.addShape(movedShape);
                    refreshState();
                }
            } else {
                cellGrid_.setTemporaryShape(movedShape);
            }

            cellGrid_.repaint();
        }
    }

    /**
     * Handles user key presses, shifts and rotations.
     * @param e KeyEvent type
     */
    public void keyPressed(int keyEvent) {

        if (keyEvent == LEFT_KEY) {
            Shape movedShape = currentShape_.move(xPosition_, yPosition_);
            if (!cellGrid_.collidesWith(movedShape, ColoredGrid.LEFT_CELL)) {
                xPosition_--;
            }
        } else if (keyEvent == RIGHT_KEY) {
            Shape movedShape = currentShape_.move(xPosition_, yPosition_);
            if (!cellGrid_.collidesWith(movedShape, ColoredGrid.RIGHT_CELL)) {
                xPosition_++;
            }
        } else if (keyEvent == UP_KEY) {
            currentShape_.rotate();
        } else if (keyEvent == DOWN_KEY) {
            run();
        }
    }

    public void startApp() {
        display.setCurrent(cellGrid_);
        resetGame();
        final TetrisMIDlet game = this;
        new Thread(new Runnable() {

            public void run() {
                game.run();
                try {
                    Thread.sleep(ANIMATION_RATE);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                run();
            }
        }).start();
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable s) {
        if (c == exitCommand) {
            destroyApp(false);
            notifyDestroyed();
        } else if (c == pauseCommand) {
            paused = !paused;
            if(paused)
            {
                cellGrid_.setMessage("Paused!");
                cellGrid_.repaint();
            }
        }
        else if (c == resetCommand)
        {
            resetGame();
        }
        
    }
}
