/*
 * TCSS 305 – Autumn 2016
 * Assignment 5 – PowerPaint
 */

package gui;

import java.awt.Color;
import java.awt.Shape;

/**
 * Represents a shape and its properties that has been drawn in a paint panel.
 * 
 * @author Phu-Lam Pham 
 * @version 22 November 2016
 */
public class DrawnShape {
    
    /** The shape's color. */
    private final Color myColor;

    /** The shape's stroke size. */
    private final int myStrokeSize;
    
    /** The drawn shape. */
    private final Shape myShape;
    
    /** If the shape is filling. */
    private final boolean myFilling;
    
    /** The shape's filling color. */
    private final Color myFillingColor;
    
    /**
     * Constructor initializing the shape and its properties.
     * 
     * @param theShape the shape.
     * @param theColor the shape's color.
     * @param theStrokeSize the shape's stroke size.
     * @param theFilling if the shape is filling.
     * @param theFillingColor the shape's filling color.
     */
    public DrawnShape(final Shape theShape, final Color theColor, final int theStrokeSize, 
                       final boolean theFilling, final Color theFillingColor) {
        myShape = theShape;
        myColor = theColor;
        myStrokeSize = theStrokeSize;
        myFilling = theFilling;
        myFillingColor = theFillingColor;
    }

    /**
     * Return the shape.
     * 
     * @return the shape.
     */
    public Shape getShape() {
        return myShape;
    }
    
    /**
     * Return the shape's color.
     * 
     * @return the color.
     */
    public Color getColor() { 
        return myColor;
    }
    
    /**
     * Return the shape's stroke size.
     * 
     * @return the stroke size.
     */
    public int getStrokeSize() {
        return myStrokeSize;
    }
    
    /**
     * Return if the shape is filling.
     * 
     * @return if the shape is filling.
     */
    public boolean isFilling() {
        return myFilling;
    }
    
    /**
     * Return the shape's filling color.
     * 
     * @return the filling color.
     */
    public Color getFillingColor() {
        return myFillingColor;
    }
}