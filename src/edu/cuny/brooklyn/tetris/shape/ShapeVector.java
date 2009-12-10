package edu.cuny.brooklyn.tetris.shape;

import edu.cuny.brooklyn.tetris.grid.Point;


/**
 * Contains constant shape vectors.
 *
 * @author Ramin Rakhamimov
 * @author Jonathan Weinblatt
 * @see Shape
 */

public interface ShapeVector {

    Point[] LINE = { 
        new Point(0,0),
        new Point(1,0),
        new Point(2,0),
        new Point(3,0) 
    };

    Point[] T = { 
        new Point(1,0),
        new Point(0,1),
        new Point(1,1),
        new Point(2,1) 
    };

    Point[] SQUARE = { 
        new Point(0,0),
        new Point(0,1),
        new Point(1,0),
        new Point(1,1) 
    };

    Point[] L = { 
        new Point(0,0),
        new Point(0,1),
        new Point(1,1),
        new Point(2,1) 
    };

    Point[] REVERSED_L = {
        new Point(0,0),
        new Point(0,1),
        new Point(0,2),
        new Point(1,2)
    };

    Point[] ZIG_ZAG = { 
        new Point(0,0),
        new Point(1,0),
        new Point(1,1),
        new Point(2,1) 
    };

    Point[] REVERSED_ZIG_ZAG = {
        new Point(0,1),
        new Point(1,1),
        new Point(1,0),
        new Point(2,0)
    };

    /**
     * An array of all the shape vectors in this class.
     */
    Point[][] VECTORS = {T, L, LINE, ZIG_ZAG, SQUARE}; //, REVERSED_L, REVERSED_ZIG_ZAG};
}
