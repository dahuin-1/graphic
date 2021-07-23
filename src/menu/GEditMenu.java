package menu;
import global.GConstants;

import shape.GShape;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class GEditMenu extends GMenu {

    private static final long serialVersionUID = GConstants.serialVersionUID;
    // private Vector<JMenuItem> menuItems;
    private ArrayList<GShape> copyList;
    public GEditMenu(String name) {
        super(name);
        //this.menuItems = new Vector<JMenuItem>();

        for(GConstants.EEditMenu eMenu: GConstants.EEditMenu.values()) {
            JMenuItem menuItem = new JMenuItem(eMenu.getTitle());
            menuItem.setActionCommand(eMenu.getActionCommand());
            menuItem.addActionListener(this.actionHandler);
            this.menuItems.add(menuItem);
            this.add(menuItem);
        }
        copyList = new ArrayList<GShape>();
    }



    public void initialize() {
    }

    public void undo() {

    }

    public void redo() {

    }

    public void cut() {
        copyList.clear();
        copyList.addAll(drawingPanel.cut());
       // this.drawingPanel.cut();
    }

    public void copy() {
        copyList.clear();
        copyList.addAll(drawingPanel.copy());
       // this.drawingPanel.copy();
    }

    public void paste() {
        this.drawingPanel.paste(copyList);
    }





}
