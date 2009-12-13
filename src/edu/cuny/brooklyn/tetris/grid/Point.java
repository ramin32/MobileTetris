package edu.cuny.brooklyn.tetris.grid;

/**
 * Same as java.awt.Point
 *
 * @author Ramin Rakhamimov
 * @see Shape
 */
public class Point {

    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

}
