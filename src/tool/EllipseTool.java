/*
 * TCSS 305 – Autumn 2016
 * Assignment 5 – PowerPaint
 */

package tool;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Represents an Ellipse drawing tool.
 * 
 * @author Phu-Lam Pham 
 * @version 22 November 2016
 */
public class EllipseTool extends AbstractTool {
    
    /** The ellipse used for the tool. */
    private final Ellipse2D.Double myEllipse  = new Ellipse2D.Double();

    /**
     * Construct the ellipse tool.
     */
    public EllipseTool() {
        super(DEFAULT_POINT, DEFAULT_POINT);
    }

    /**
     * Return the ellipse.
     * 
     * @return the ellipse shape.
     */
    @Override
    public Shape getShape() {
        myEllipse.setFrameFromDiagonal(getStartPoint(), getEndPoint());
        return (Shape) myEllipse.clone();
    }
}
