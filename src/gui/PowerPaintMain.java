/*
 * TCSS 305 – Autumn 2016
 * Assignment 5 – PowerPaint
 */

package gui;

import java.awt.EventQueue;

/**
 * Runs PowerPaint, instantiating and starting the PowerPaintGUI.
 * 
 * @author Phu-Lam Pham 
 * @version 22 November 2016
 */
public final class PowerPaintMain {

    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private PowerPaintMain() {
        throw new IllegalStateException();
    }

    /**
     * The main method, invokes the PowerPaint GUI. Command line arguments are
     * ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PowerPaintGUI().start();
            }
        });
    }
}
