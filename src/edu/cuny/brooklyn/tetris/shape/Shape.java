package edu.cuny.brooklyn.tetris.shape;

import edu.cuny.brooklyn.tetris.grid.Point;
import java.util.Random;


/**
 * Data structure that contains a vector of cells to form a shape.
 *
 * @author Ramin Rakhamimov
 */

public class Shape 
{
    public static final int SHAPE_GRID_SIZE = 4;
    private static final Random random_ = new Random(System.currentTimeMillis());

    private Point[] points_;
    private int color_;

    /**
     * Constructs a shape object given a points vector and a color.
     * @param points points vector
     * @param color color for each point
     */
    public Shape(Point[] points, int color)
    {
        points_ = points;
        color_ = color;
    }

    /**
     * Generates a random shape.
     */
    public static Shape randomShape() 
    {
        return new Shape(ShapeVector.VECTORS[random_.nextInt(ShapeVector.VECTORS.length)],
                         randomColor());
    }

    /**
     * moves the shape to point (x,y)
     * @param x 
     * @param y
     */
    public Shape move(int x, int y)
    {
        Point[] newPoints = new Point[points_.length];
        for(int i = 0; i < points_.length; i++)
            newPoints[i] = new Point(points_[i].x + x, 
                                     points_[i].y + y);
        return new Shape(newPoints, color_);
    }

    /**
     * Generates a random color.
     */
    private static int randomColor()
    {
        return random_.nextInt();
    }
    
    /**
     * Return width.
     */
    public int getWidth()
    {
        int width = 0;
        for(int i = 0; i < points_.length; i++){
            Point p = points_[i];
            width = Math.max(width,p.x);
        }
        return width + 1;
    }

    /**
     * Return width.
     */
    public int getHeight()
    {
        int height = 0;
        for(int i = 0; i < points_.length; i++){
            Point p = points_[i];
            height = Math.max(height,p.y);
        }
        return  height + 1;
    }

    /**
     * Return the points vector.
     */
    public Point[] getPoints()
    {
        return points_;
    }

    /**
     * Return the color of the shape.
     */
    public int getColor()
    {
        return color_;
    }

    /** 
     * Rotate the shape in a clockwise direction by 90 degrees.
     */
    public void rotate()
    {
        int minX = SHAPE_GRID_SIZE;
        int minY = SHAPE_GRID_SIZE;
        for(int i = 0; i < points_.length; i++){
            Point p = points_[i];
            p.move(p.y, SHAPE_GRID_SIZE - 1 - p.x);
            minX = Math.min(minX,p.x);
            minY = Math.min(minY,p.y);
        }

        // Normalize
        for(int i = 0; i < points_.length; i++){
                Point p = points_[i];
                p.x = p.x-minX;
                p.y = p.y-minY;
        }
    }
}
