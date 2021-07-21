package frame;

import global.GConstants;
import menu.GMenuBar;
import panel.GDrawingPanel;
import toolbar.GToolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GMainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private GMenuBar menuBar;
    private GToolBar toolBar;
    private GDrawingPanel drawingPanel;

    public GMainFrame() {
        super();

        //this.setLocation(GConstants.EMainFrame.eWidth, GConstants.EMainFrame.eHeight);
        this.setSize(GConstants.EMainFrame.eWidth.getValue(),
                GConstants.EMainFrame.eHeight.getValue());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        WindowActionHandler windowActionHandler = new WindowActionHandler();
        this.addWindowListener(windowActionHandler);


        this.menuBar = new GMenuBar();
        this.setJMenuBar(this.menuBar);

        this.toolBar = new GToolBar();
        this.add(this.toolBar, BorderLayout.NORTH);

        this.drawingPanel = new GDrawingPanel();
        this.add(this.drawingPanel, BorderLayout.CENTER);
    }

    public void initialize() {
        //set associations
        this.toolBar.setAssociation(this.drawingPanel);
        this.menuBar.setAssociation(this.drawingPanel);

        //initialize associative attributes
        this.menuBar.initialize();
        this.toolBar.initialize();
        this.drawingPanel.initialize();
    }

   public class WindowActionHandler extends WindowAdapter {
        public void windowClosing(WindowEvent e){
           // System.out.println("windowClosing");
            menuBar.getFileMenu().checkSave();
        }

    }

}
