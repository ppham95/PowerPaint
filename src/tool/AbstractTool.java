/*
 * TCSS 305 – Autumn 2016
 * Assignment 5 – PowerPaint
 */

package tool;

import java.awt.Point;

/**
 * Abstract class representing a drawing tool for the paint panel.
 * 
 * @author Phu-Lam Pham 
 * @version 22 November 2016
 */
public abstract class AbstractTool implements DrawingTool {
    
    /** Coordinate number that is out of bound of the panel.*/
    private static final int OUT_OF_BOUND = -999;
    
    /** The default drawing tool. */
    protected static final Point DEFAULT_POINT = new Point(OUT_OF_BOUND, OUT_OF_BOUND);
    
    /** The tool's start point. */
    private Point myStartPoint;
    
    /** The tool's end point. */
    private Point myEndPoint;
    
    /**
     * Construct the tool.
     * 
     * @param theStartPoint the start point.
     * @param theEndPoint the end point.
     */
    public AbstractTool(final Point theStartPoint, final Point theEndPoint) {
        myStartPoint = (Point) theStartPoint.clone();
        myEndPoint = (Point) theEndPoint.clone();
    }

    /**
     * Set the start point for the tool.
     * 
     * @param theStartPoint the start point.
     */
    @Override
    public void setStartPoint(final Point theStartPoint) {
        myStartPoint = (Point) theStartPoint.clone();
    }
    
    /**
     * Set the end point for the tool.
     * 
     * @param theEndPoint the end point.
     */
    @Override
    public void setEndPoint(final Point theEndPoint) {
        myEndPoint = (Point) theEndPoint.clone();
        
    }
    
    /**
     * Get the start point of the tool.
     * 
     * @return the end point.
     */
    @Override
    public Point getStartPoint() {
        return (Point) myStartPoint.clone();
    }

    /**
     * Get the end point of the tool.
     * 
     * @return the end point.
     */
    @Override
    public Point getEndPoint() {
        return (Point) myEndPoint.clone();
    }
}
