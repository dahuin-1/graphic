package menu;
import global.GConstants;

import javax.swing.*;
import java.util.Vector;

public class GEditMenu extends GMenu {

    private static final long serialVersionUID = GConstants.serialVersionUID;
    // private Vector<JMenuItem> menuItems;

    public GEditMenu(String name) {
        super(name);
        this.menuItems = new Vector<JMenuItem>();

        for(GConstants.EEditMenu eMenu: GConstants.EEditMenu.values()) {
            JMenuItem menuItem = new JMenuItem(eMenu.getTitle());
            this.menuItems.add(menuItem);
            this.add(menuItem);
        }
    }


    public void initialize() {
    }

}
