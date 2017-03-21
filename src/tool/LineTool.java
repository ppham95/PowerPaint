/*
 * TCSS 305 – Autumn 2016
 * Assignment 5 – PowerPaint
 */
package tool;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * Represents a Line drawing tool.
 * 
 * @author Phu-Lam Pham 
 * @version 22 November 2016
 */
public class LineTool extends AbstractTool {
    
    /** The line used for the tool. */
    private final Line2D.Double myLine = new Line2D.Double();
    
    /**
     * Constructor initializing the coordinates of the line.
     * 
     * @param theStartPoint the start point.
     * @param theEndPoint the end point.
     */
    public LineTool(final Point theStartPoint, final Point theEndPoint) {
        super(theStartPoint, theEndPoint);
    }
    
    /**
     * Return the line.
     * 
     * @return the line shape.
     */
    @Override
    public Shape getShape() {
        
        myLine.setLine(getStartPoint(), getEndPoint());
        return (Shape) myLine.clone();
    }
}
