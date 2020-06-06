package toolbar;

import global.GConstants;
import panel.GDrawingPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class GToolBar extends JToolBar {
    private static final long serialVersionUID = 1L;


    private ActionHandler actionHandler;
    private JButton rectangleButton;
    private JButton ellipseButton;
    private JButton lineButton;
    private JButton polygonButton;

    private Vector<JButton> buttons;

    private GDrawingPanel drawingPanel;


    public void setAssociation (GDrawingPanel drawingPanel){
        this.drawingPanel = drawingPanel;
    }

    public GToolBar() {
        super();
        ButtonGroup buttonGroup = new ButtonGroup();
        this.buttons = new Vector<JButton>();
        this.actionHandler = new ActionHandler();

        for (GConstants.EToolbar eToolBar : GConstants.EToolbar.values()) {
            JButton button = new JButton(eToolBar.getTitle());
            button.setActionCommand(eToolBar.name());
            button.addActionListener(actionHandler);
            this.buttons.add(button);
            buttonGroup.add(button);
            this.add(button);
        }
    }

    public void initialize() {

        this.buttons.get(0).doClick();
        //GConstants.EToolbar.eRectangle.ordinal()

    }


    class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            drawingPanel.setCurrentTool(GConstants.EToolbar.valueOf(e.getActionCommand()));
        }
    }
}

