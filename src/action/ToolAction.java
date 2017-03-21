/*
 * TCSS 305 – Autumn 2016
 * Assignment 5 – PowerPaint
 */

package action;

import gui.PaintPanel;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import tool.DrawingTool;
import tool.LineTool;

/**
 * An action associated with drawing tool.
 * 
 * @author Phu-Lam Pham 
 * @version 22 November 2016
 */
public class ToolAction extends AbstractAction {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = 2413929039519550493L;
    
    /** Coordinate number that is out of bound of the panel.*/
    private static final int OUT_OF_BOUND = -999;

    /** The default drawing tool of the paint program. */
    private static final DrawingTool DEFAULT_TOOL = new LineTool(new Point(OUT_OF_BOUND, 
                                                                           OUT_OF_BOUND), 
                                                                 new Point(OUT_OF_BOUND, 
                                                                           OUT_OF_BOUND));

    /** The main painting panel. */
    private PaintPanel myPanel;
    
    /** The drawing tool associated with this action. */
    private DrawingTool myTool = DEFAULT_TOOL;
    
    /** The name of this tool. */
    private String myName;
    
    /**
     * Constructor initializing this tool action with a given tool.
     * 
     * @param theName the name of the tool.
     * @param theIcon the icon of the tool.
     * @param thePanel the panel for the tool to draw on.
     * @param theTool the tool associated with this action.
     */
    public ToolAction(final String theName, 
                      final ImageIcon theIcon, final PaintPanel thePanel, 
                      final DrawingTool theTool) {
        super(theName, theIcon);
        myPanel = thePanel;
        myTool = theTool;
        putValue(Action.MNEMONIC_KEY,
                 KeyEvent.getExtendedKeyCodeForChar(theName.codePointAt(0)));
        if (theTool.equals(DEFAULT_TOOL)) {
            myPanel.setTool(theTool);
        }  
        myName = theName;
    }
    
    /**
     * Constructor initializing this tool action with the default tool.
     * 
     * @param theName the name of the tool.
     * @param theIcon the icon of the tool.
     * @param thePanel the panel for the tool to draw on.
     */
    public ToolAction(final String theName, final ImageIcon theIcon, 
                      final PaintPanel thePanel) {
        this(theName, theIcon, thePanel, DEFAULT_TOOL);
    }
    
    /**
     * Get the name of this tool action.
     * 
     * @return the name of the tool action.
     */
    public String getName() {
        return myName;
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        putValue(Action.SELECTED_KEY, true);
        myPanel.setTool(myTool);
    }
}
