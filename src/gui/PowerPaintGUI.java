/*
 * TCSS 305 – Autumn 2016
 * Assignment 5 – PowerPaint
 */

package gui;

import action.ColorAction;
import action.ToolAction;

import java.awt.BorderLayout;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tool.EllipseTool;
import tool.PencilTool;
import tool.RectangleTool;

/**
 * The Graphical User Interface for PowerPaint program.
 * 
 * @author Phu-Lam Pham 
 * @version 22 November 2016
 */
public class PowerPaintGUI extends JFrame implements PropertyChangeListener {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = 1036293668209098485L;
    
    /** The slider's major spacing size. */
    private static final int SLIDER_MAJOR_SPACING = 5;
    
    /** The slider's minor spacing size. */
    private static final int SLIDER_MINOR_SPACING = 1;
    
    /** The slider's max value. */
    private static final int SLIDER_MAX_VALUE = 20;
    
    /** The program's default image path folder. */
    private static final String DEFAULT_IMAGE_FOLDER = "images/";
    
    /** The program's default window icon. */
    private static final String WINDOW_ICON_PATH = "images/paint.png";
    
    /** A list of tool actions. */
    private final List<ToolAction> myActions;
    
    /** The main painting panel. */
    private final PaintPanel myPanel;
    
    /** The clear menu. */
    private final JMenuItem myClear;

    /**
     * Constructor initializing the graphical user interface.
     */
    public PowerPaintGUI() {
        super("TCSS 305 - PowerPaint");
        myPanel = new PaintPanel();
        myPanel.addPropertyChangeListener(this);
        myClear = new JMenuItem("Clear", KeyEvent.VK_C);
        add(myPanel);
        myActions = new ArrayList<ToolAction>();
        setIconImage(new ImageIcon(WINDOW_ICON_PATH).getImage());
        setup();      
    }
    
    /**
     * Set up the GUi's different components.
     */
    private void setup() {
        final JMenuBar menuBar = new JMenuBar();
        setupToolsActions();

        
        menuBar.add(createFileMenu());
        menuBar.add(createOptionsMenu());
        setupTools(menuBar);
        menuBar.add(createHelpMenu());
        
        setJMenuBar(menuBar);
        pack();
    }
    
    /**
     * Create the help menu.
     * 
     * @return the help menu.
     */
    private JMenu createHelpMenu() {
        final JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        
        final JMenuItem about = new JMenuItem("About...");
        about.setMnemonic(KeyEvent.VK_A);
        
        help.add(about);
        
        about.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                ImageIcon imageIcon = new ImageIcon(WINDOW_ICON_PATH);
                final Image image = imageIcon.getImage();
                final Image newImage = image.getScaledInstance(100, 100, 
                                               java.awt.Image.SCALE_SMOOTH);                 
                imageIcon = new ImageIcon(newImage);
                    
                    JOptionPane.showMessageDialog(null, 
                                      "TCSS 305 PowerPaint \nAutumn 2016 \nPhu-Lam Pham", 
                                      "About", JOptionPane.INFORMATION_MESSAGE, imageIcon);
            }
        });
        
        return help;
    }
    
    /**
     * Set up tool actions.
     */
    private void setupToolsActions() {
        myActions.add(new ToolAction("Line", 
                                          new ImageIcon("images/line_bw.gif"), 
                                          myPanel));
        myActions.add(new ToolAction("Pencil", 
                                          new ImageIcon("images/pencil_bw.gif"), myPanel,
                                          new PencilTool()));
        myActions.add(new ToolAction("Rectangle", 
                                          new ImageIcon("images/rectangle_bw.gif"), myPanel,
                                          new RectangleTool()));
        myActions.add(new ToolAction("Ellipse", 
                                          new ImageIcon("images/ellipse_bw.gif"), myPanel,
                                          new EllipseTool()));
    }
    
    /**
     * Set up the tool options.
     * 
     * @param theMenuBar the GUI's menu bar.
     */
    private void setupTools(final JMenuBar theMenuBar) {
        final ToolBar toolBar = new ToolBar();
        final ToolsMenu toolsMenu = new ToolsMenu();
        
        for (final Action action : myActions) {
            toolBar.createToolBarButton(action);
            toolsMenu.createMenuButton(action);
        }
        
        add(toolBar, BorderLayout.SOUTH);
        theMenuBar.add(toolsMenu);
    }
    
    /**
     * Create the file menu.
     * @return the file menu.
     */
    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);    
        
        // will need to do some kind of property listener to enable quit button
        myClear.setEnabled(false);
        
        myClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myPanel.clearDrawings();
                myClear.setEnabled(false);
            }
        });
            
        
        fileMenu.add(myClear);
        
        fileMenu.addSeparator();
        final JMenuItem quit = new JMenuItem("Quit", KeyEvent.VK_Q);
        quit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                dispose();
                
            }
        });
        
        fileMenu.add(quit);
        
        return fileMenu;
    }
    
    /**
     * Create option menu.
     * 
     * @return the option menu.
     */
    private JMenu createOptionsMenu() {
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O); 
        
        // Create a slider responding to stroke size
        final JMenu thicknessMenu = new JMenu("Thickness");
        thicknessMenu.setMnemonic(KeyEvent.VK_T);
        
        final JSlider slider = new JSlider(JSlider.HORIZONTAL,
                                              0, SLIDER_MAX_VALUE, SLIDER_MAJOR_SPACING);
        slider.setMajorTickSpacing(SLIDER_MAJOR_SPACING);
        slider.setMinorTickSpacing(SLIDER_MINOR_SPACING);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        thicknessMenu.add(slider);
        slider.setValue(1);
        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                myPanel.setStrokeSize(slider.getValue());
            }
            
        });
        
        
        optionsMenu.add(thicknessMenu);
        optionsMenu.addSeparator();
        
        // Create coloring options
        final JMenuItem drawColor = new JMenuItem("Draw Color...", KeyEvent.VK_D);
        drawColor.addActionListener(new ColorAction(myPanel, drawColor));
        final JMenuItem fillColor = new JMenuItem("Fill Color...", KeyEvent.VK_F);
        fillColor.addActionListener(new ColorAction(myPanel, fillColor));
        
        optionsMenu.add(drawColor);
        optionsMenu.add(fillColor);
        
        optionsMenu.addSeparator();
        
        final JCheckBoxMenuItem fill = new JCheckBoxMenuItem("Fill");
        fill.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myPanel.setFilling(fill.isSelected());
            }
            
        });
        optionsMenu.add(fill);
        fill.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, 
                                                   java.awt.Event.CTRL_MASK));
        return optionsMenu;
    }
    
    
    /**
     * Make this GUI frame visible.
     */
    public void start() {
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("hasDrawn".equals(theEvent.getPropertyName())) {
            if (theEvent.getNewValue() != null && (int) theEvent.getNewValue() > 0) {
                myClear.setEnabled(true);
            }
        } else if ("tool".equals(theEvent.getPropertyName())) {
            for (final ToolAction action : myActions) {
                if (theEvent.getNewValue().getClass().getSimpleName().
                                startsWith(action.getName())) {
                    action.putValue(Action.SMALL_ICON, 
                                    new ImageIcon(DEFAULT_IMAGE_FOLDER 
                                                  + action.getName().toLowerCase() 
                                                  + ".gif"));
                   
                } else { 
                    action.putValue(Action.SMALL_ICON, 
                                    new ImageIcon(DEFAULT_IMAGE_FOLDER 
                                                  + action.getName().toLowerCase() 
                                                  + "_bw.gif"));
                }
            }
        }
    }
}
