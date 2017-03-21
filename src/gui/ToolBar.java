/*
 * TCSS 305 – Autumn 2016
 * Assignment 5 – PowerPaint
 */

package gui;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * The JToolBar for PowerPaint program.
 * 
 * @author Phu-Lam Pham 
 * @version 22 November 2016
 */
public class ToolBar extends JToolBar {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = -5968332867050615376L;
    
    /** A button group for the mutually exclusive tool bar buttons. */ 
    private final ButtonGroup myGroup;
    
    /**
     * Construct the toolBar.
     */
    public ToolBar() {
        super();
        myGroup = new ButtonGroup();
    }
    
    /**
     * Create a JToggleButton for the ToolBar.
     * 
     * @param theAction to associate with the created JToggleButton
     */
    public void createToolBarButton(final Action theAction) {
        final JToggleButton toggleButton = new JToggleButton(theAction);
        
        // Pre-select Line Tool.
        if (theAction.getValue(Action.NAME).toString().equals("Line")) {
            toggleButton.setSelected(true);
        }
        
        myGroup.add(toggleButton);
        add(toggleButton);
    }
}
