package menu;

import global.GConstants;

import javax.swing.*;
import java.awt.*;

public class GColorMenu extends GMenu {

    private static final long serialVersionUID = GConstants.serialVersionUID;

    public GColorMenu(String name) {
        super(name);

        for(GConstants.EColorMenu eMenu: GConstants.EColorMenu.values()) {
            JMenuItem menuItem = new JMenuItem(eMenu.getTitle());
            menuItem.addActionListener(this.actionHandler);
            menuItem.setActionCommand(eMenu.getActionCommand());
            this.menuItems.add(menuItem);
            this.add(menuItem);
        }
    }

    public void initialize() {
    }

    public void linecolor() {
        Color selectedColor = JColorChooser.showDialog (null,
                GConstants.LINECOLOR_TITLE , this.drawingPanel.getForeground());
        this.drawingPanel.setLineColor(selectedColor);
    }
    public void fillcolor() {
        Color selectedColor = JColorChooser.showDialog (null,
                GConstants.FILLCOLOR_TITLE , this.drawingPanel.getForeground());
        drawingPanel.setFillColor(selectedColor);
    }



}
