/*
 * TCSS 305 – Autumn 2016
 * Assignment 5 – PowerPaint
 */

package gui;

import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 * The JMenu for PowerPaint program.
 * 
 * @author Phu-Lam Pham 
 * @version 22 November 2016
 */
public class ToolsMenu extends JMenu {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = -6767056395840407028L;
    
    /** A button group for the mutually exclusive tool bar buttons. */ 
    private final ButtonGroup myGroup;
    
    /**
     * Construct the menu.
     */
    public ToolsMenu() {
        super("Tools");
        setMnemonic(KeyEvent.VK_T);
        myGroup = new ButtonGroup();
    }
    
    /**
     * Create a JRadioButtonMenuItem for the menu.
     * 
     * @param theAction to associate with the created button.
     */
    public void createMenuButton(final Action theAction) {
        final JRadioButtonMenuItem tool = new JRadioButtonMenuItem(theAction);
        
        // Pre=select Line tool.
        if (theAction.getValue(Action.NAME).toString().equals("Line")) {
            tool.setSelected(true);
        }
        
        myGroup.add(tool);
        add(tool);
    }
}
