/*
 * TCSS 305 – Autumn 2016
 * Assignment 5 – PowerPaint
 */

package tool;

import java.awt.Point;
import java.awt.Shape;

/**
 * An interface for objects that can be painted through a JPanel.
 * 
 * @author Phu-Lam Pham 
 * @version 22 November 2016
 */
public interface DrawingTool {
    
    /**
     * Set the start point for the object.
     * 
     * @param theStartPoint the start point.
     */
    void setStartPoint(final Point theStartPoint);
    
    /**
     * Set the end point for the object.
     * 
     * @param theEndPoint the end point.
     */
    void setEndPoint(final Point theEndPoint);
    
    /**
     * Get the start point of the object.
     * 
     * @return the start point.
     */
    Point getStartPoint();
    
    /**
     * Get the end point of the object.
     * 
     * @return the end point.
     */
    Point getEndPoint();
    
    /**
     * Get the shape that the object represents.
     * 
     * @return the shape of the object.
     */
    Shape getShape();
}
