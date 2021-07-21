package menu;

import global.GConstants;
import panel.GDrawingPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

public abstract class GMenu extends JMenu {

    private static final long serialVersionUID = 1L;

    protected GDrawingPanel drawingPanel;
    protected Vector<JMenuItem> menuItems;
    protected ActionHandler actionHandler;

    public GMenu(String name){
        super(name);

        this.menuItems = new Vector<JMenuItem>();
        this.actionHandler = new ActionHandler();
    }

    public abstract void initialize();

    public void setAssociation(GDrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    private void invokeMethod(String methodName) {
        try {
            this.getClass().getMethod(methodName).invoke(this);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            e.printStackTrace();
        }
    }

  protected class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String methodName = event.getActionCommand();
            invokeMethod(methodName);
        }
    }

}
