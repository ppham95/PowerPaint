/*
 * TCSS 305 – Autumn 2016
 * Assignment 5 – PowerPaint
 */
package tool;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Represents a Rectangle drawing tool.
 * 
 * @author Phu-Lam Pham 
 * @version 22 November 2016
 */
public class RectangleTool extends AbstractTool {
    
    /** The  rectangle used for the tool. */
    private final Rectangle2D.Double myRectangle = new Rectangle2D.Double();
    
    /**
     * Construct the rectangle tool.
     */
    public RectangleTool() {
        super(DEFAULT_POINT, DEFAULT_POINT);
    }

    /**
     * Return the rectangle.
     * 
     * @return the rectangle shape.
     */
    @Override
    public Shape getShape() {
        myRectangle.setFrameFromDiagonal(getStartPoint(), getEndPoint()); 
        return (Shape) myRectangle.clone();
    }
}
