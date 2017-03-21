/*
 * TCSS 305 – Autumn 2016
 * Assignment 5 – PowerPaint
 */

package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import tool.DrawingTool;

/**
 * The painting panel for the PowerPaint program.
 * 
 * @author Phu-Lam Pham 
 * @version 22 November 2016
 */
public class PaintPanel extends JPanel {

    /** A generated serialization ID. */
    private static final long serialVersionUID = 774794372007338579L;

    /** The default width of the panel. */
    private static final int DEFAULT_WIDTH = 600;   
   
    /** The default width of the panel. */
    private static final int DEFAULT_HEIGHT = 400;    
    
     /** List of previously drawn shapes. */
    private final List<DrawnShape> myDrawnShapes;

    /** The current selected drawing tool for the panel. */
    private DrawingTool myCurrentTool;
    
    /** The current drawing color of the panel. */
    private Color myColor;
    
    /** The current drawing stroke size of the panel. */
    private int myStrokeSize;
    
    /** The current shape being drawn in the panel. */
    private Shape myCurrentShape;

    /** If the shape is still being drawn. */
    private boolean myIsDrawing;
    
    /** If the panel is supposed to fill the current drawn shape. */
    private boolean myIsFilling;
    
    /** The current filling color of the panel. */
    private Color myFillingColor;

    /**
     * Constructor initializing the drawing panel.
     */
    public PaintPanel() {
        super();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        myColor = Color.BLACK;
        myStrokeSize = 1;
        myDrawnShapes = new ArrayList<DrawnShape>();
        final PaintPanelListener mouseAdapter = new PaintPanelListener();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        myIsDrawing = false;
    }
    
    /**
     * Clear all drawings from the panel.
     */
    public void clearDrawings() {
        myDrawnShapes.clear();
        repaint();
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        for (final DrawnShape d : myDrawnShapes) {
            if (d.isFilling()) {
                g2d.setPaint(d.getFillingColor());
                g2d.fill(d.getShape());
            }
            g2d.setColor(d.getColor());
            g2d.setStroke(new BasicStroke(d.getStrokeSize()));
            g2d.draw(d.getShape());
        }
        
        if (myIsDrawing) {
            if (myIsFilling && isNotLineOrPencil()) {
                g2d.setPaint(myFillingColor);
                g2d.fill(myCurrentShape);
            }
            g2d.setColor(myColor);
            g2d.setStroke(new BasicStroke(myStrokeSize));
            g2d.draw(myCurrentShape);
        }
    }
    
    /**
     * Set new drawing tool for the panel.
     * 
     * @param theTool the new drawing tool.
     */
    public void setTool(final DrawingTool theTool) {
        myCurrentTool = theTool;
        firePropertyChange("tool", null, theTool);
    }
    
    /**
     * Set new drawing color for the panel.
     * 
     * @param theColor the new drawing color.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    /**
     * Get the current drawing color of the panel.
     * 
     * @return the current drawing color.
     */
    public Color getColor() { 
        return myColor;
    }

    /**
     * Set a new stroke size for the panel.
     * 
     * @param theStrokeSize the new stroke size.
     */
    public void setStrokeSize(final int theStrokeSize) {
        myStrokeSize = theStrokeSize;
    }
    
    /**
     * Set a new filling color of the panel.
     * 
     * @param theColor the new filling color.
     */
    public void setFillingColor(final Color theColor) {
        myFillingColor = theColor;
    }
    
    /**
     * Set if the panel is filling.
     * 
     * @param theFilling if the panel is filling.
     */
    public void setFilling(final boolean theFilling) {
        myIsFilling = theFilling;
    }

    /**
     * Get the panel's current filling color.
     * 
     * @return the current filling color.
     */
    public Color getFillingColor() {
        return myFillingColor;
    }
    
    /**
     * Helper method to make sure that filling option is never implemented when 
     * the tool is line or pencil.
     * 
     * @return if tool is not a line or pencil.
     */
    private boolean isNotLineOrPencil() {
        return !(myCurrentTool.getClass().getSimpleName().startsWith("Line") 
                        || myCurrentTool.getClass().getSimpleName().startsWith("Pencil"));
    }
    
    /**
     * Inner class as the mouse listener for PaintPanel.
     * 
     * @author phulampham95
     * @version 22 November 2016
     */
    class PaintPanelListener extends MouseAdapter {
        
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myCurrentTool.setStartPoint(theEvent.getPoint());
            myCurrentTool.setEndPoint(theEvent.getPoint());
            myCurrentShape = myCurrentTool.getShape();
            myIsDrawing = true;
            
        }
        
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            myCurrentTool.setEndPoint(theEvent.getPoint()); 
            myCurrentShape = myCurrentTool.getShape();
            repaint();
        }
        
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            myCurrentTool.setEndPoint(theEvent.getPoint());
            
            final DrawnShape newShape = new DrawnShape(myCurrentTool.getShape(), 
                                                       myColor, myStrokeSize, 
                                                       myIsFilling && isNotLineOrPencil(), 
                                                       myFillingColor);
            myIsDrawing = false;
            myDrawnShapes.add(newShape);
            firePropertyChange("hasDrawn", null, myDrawnShapes.size());
            repaint();
        }    
    }  
}