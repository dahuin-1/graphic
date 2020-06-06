package menu;
import global.GConstants;
import panel.GDrawingPanel;

import javax.swing.*;
import java.util.Vector;

public class GMenuBar extends JMenuBar {
    private static final long serialVersionUID = GConstants.serialVersionUID;

    private Vector<GMenu> menus;

    public GMenuBar() {
        super();

        this.menus = new Vector<GMenu>();

        for (GConstants.EMenubar eMenu : GConstants.EMenubar.values()) {
            GMenu menu = eMenu.getMenu();
            this.menus.add(menu);
            this.add(menu);
        }
    }

    public void initialize() {
        for(GMenu menu : this.menus) {
            menu.initialize();
        }
    }

    public void setAssociation(GDrawingPanel drawingPanel) {
        for (GMenu menu : this.menus) {
            menu.setAssociation(drawingPanel);
        }
    }

    public GFileMenu getFileMenu(){
        return (GFileMenu) this.menus.get(GConstants.EMenubar.eFile.ordinal());
    }
}
