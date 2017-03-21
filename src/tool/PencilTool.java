/*
 * TCSS 305 – Autumn 2016
 * Assignment 5 – PowerPaint
 */
package tool;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 * Represents a Pencil drawing tool.
 * 
 * @author Phu-Lam Pham 
 * @version 22 November 2016
 */
public class PencilTool extends AbstractTool {
    
    /** The path used for the tool. */
    private Path2D.Double myPath;
    
    /**
     * Constructor initializing the default coordinates of the pencil path.
     */
    public PencilTool() {
        super(DEFAULT_POINT, DEFAULT_POINT);
        myPath = new Path2D.Double();
    }

    /**
     * Return the pencil.
     * 
     * @return the pencil path.
     */
    @Override
    public Shape getShape() {
        myPath.lineTo(getEndPoint().getX(), getEndPoint().getY());
        return (Path2D) myPath.clone();
    }
    
    /**
     * Set the start point of the path.
     * 
     * @param theStartPoint the start point.
     */
    @Override
    public void setStartPoint(final Point theStartPoint) {
        super.setStartPoint(theStartPoint);
        myPath = new Path2D.Double();
        myPath.moveTo(getStartPoint().getX(), getStartPoint().getY());
    }
}
