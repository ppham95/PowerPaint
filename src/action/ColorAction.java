/*
 * TCSS 305 – Autumn 2016
 * Assignment 5 – PowerPaint
 */

package action;

import gui.PaintPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JColorChooser;
import javax.swing.JMenuItem;

/**
 * An action associated with colors.
 * 
 * @author Phu-Lam Pham 
 * @version 22 November 2016
 */
public class ColorAction extends AbstractAction {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = 7596548986627580686L;

    /** The color chooser message. */
    private static final String COLOR_CHOOSER_MESSAGE = "Choose a Color...";
    
    /** The default painting color. */
    private static final Color DEFAULT_COLOR = new Color(51, 0, 111);
    
    /** The default fill color. */
    private static final Color DEFAULT_FILL_COLOR = new Color(232, 211, 162);
    
    /** The main painting panel. */
    private final PaintPanel myPanel;
    
    /** The menu item associated with the action. */
    private final JMenuItem myMenu;
    
    /** If the menu item corresponds to fill option. */
    private final boolean myIsFilling;
    
    /**
     * Constructor initializing action that's associated with filling.
     * 
     * @param thePanel the main painting panel.
     * @param theMenu the menu item associated with the action.
     */
    public ColorAction(final PaintPanel thePanel, final JMenuItem theMenu) {
        super("Color");
        myMenu = theMenu;
        myPanel = thePanel;
        putValue(Action.MNEMONIC_KEY, KeyEvent.
                 getExtendedKeyCodeForChar(theMenu.getActionCommand().charAt(0)));
        
        myIsFilling = getValue(Action.MNEMONIC_KEY).equals(KeyEvent.VK_F);
        
        if (myIsFilling) {
            myMenu.setIcon(new ColorIcon(DEFAULT_FILL_COLOR));
            myPanel.setFillingColor(DEFAULT_FILL_COLOR);
        } else {
            myMenu.setIcon(new ColorIcon(DEFAULT_COLOR));
            myPanel.setColor(DEFAULT_COLOR);
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        final Color selectedColor;
        if (myIsFilling) {
            selectedColor = JColorChooser.showDialog(myPanel, COLOR_CHOOSER_MESSAGE, 
                                                     myPanel.getFillingColor());
        } else {
            selectedColor = JColorChooser.showDialog(myPanel, COLOR_CHOOSER_MESSAGE, 
                                                     myPanel.getColor());
        }

        if (selectedColor != null) {
            if (myIsFilling) {
                myPanel.setFillingColor(selectedColor);
            } else {
                myPanel.setColor(selectedColor);
            }
            putValue(Action.SMALL_ICON, new ColorIcon(selectedColor));
            myMenu.setIcon(new ColorIcon(selectedColor));
        }
    }
    
    /**
     * Inner class making color icons for this action.
     * 
     * @author phulampham95
     * @version 22 November 2016
     */
    public static class ColorIcon implements Icon {
        
        /** The default size for icon. */
        private static final int DEFAULT_SIZE = 15;
        
        /** The color of the icon. */
        private final Color myColor;
        
        /**
         * Constructor initializing icon's color.
         * 
         * @param theColor the color of the icon.
         */
        public ColorIcon(final Color theColor) {
            myColor = theColor;
        }
        
        /**
         * Paints the icon.
         */
        @Override
        public void paintIcon(final Component theComponent, 
                              final Graphics theGraphics, final int theX, final int theY) {
            theGraphics.setColor(myColor);
            theGraphics.fillRect(theX, theY, DEFAULT_SIZE, DEFAULT_SIZE);
            
            theGraphics.setColor(Color.BLACK);
            theGraphics.drawRect(theX, theY, DEFAULT_SIZE, DEFAULT_SIZE); 
        }
        
        /**
         * Gets the height of the icon.
         * 
         * @return the height of the icon.
         */
        @Override
        public int getIconHeight() {
            return DEFAULT_SIZE;
        }
        
        /**
         * Gets the width of the icon.
         * 
         * @return the width of the icon.
         */
        @Override
        public int getIconWidth() {
            return DEFAULT_SIZE;
        }
    }
}
