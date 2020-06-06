package menu;

import file.GFile;
import global.GConstants;

import javax.swing.*;
import java.io.File;

public class GFileMenu extends GMenu {

    private static final long serialVersionUID = GConstants.serialVersionUID;

    //working variables
    private File directory, file;

    public GFileMenu(String name) {
        super(name);

        for (GConstants.EFileMenu eMenu : GConstants.EFileMenu.values()) {
            JMenuItem menuItem = new JMenuItem(eMenu.getTitle());
            menuItem.setActionCommand(eMenu.getActionCommand());
            menuItem.addActionListener(this.actionHandler);
            this.menuItems.add(menuItem);
            this.add(menuItem);
        }

        //default directory = current directory
        this.directory = new File("./data");
        this.file = null;
    }

    public void initialize() {
    }

    public void checkSave(){
        if(this.drawingPanel.isUpdated()) {
            int reply = JOptionPane.showConfirmDialog(this.drawingPanel,
                    GConstants.SAVE_TITLE,
                    null,
                    JOptionPane.YES_NO_CANCEL_OPTION);
            if(reply==JOptionPane.YES_OPTION) {
                this.save();
            }if(reply==JOptionPane.NO_OPTION){
                System.exit(0);
            }

        }
    }

    public void nnew() {
        this.checkSave();

        this.drawingPanel.clearShapeVector();
        this.file = null;
    }

    public void open() {
        this.checkSave();

        JFileChooser filechooser = new JFileChooser(this.directory);
        int returnVal = filechooser.showOpenDialog(this.drawingPanel);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            this.drawingPanel.clearShapeVector();
            this.directory = filechooser.getCurrentDirectory();
            this.file = filechooser.getSelectedFile();
            GFile gfile = new GFile();
            Object shapeVector = gfile.readObject(this.file);
            this.drawingPanel.setShapeVector(shapeVector);
        }
    }

    public void save() {
        if(this.file == null) {
            this.saveAs();
        }else{
            GFile gfile = new GFile();
            gfile.saveObject(drawingPanel.getShapeVector(), this.file);
            this.drawingPanel.setUpdated(false);
        }
    }

    public void saveAs() {
        JFileChooser filechooser = new JFileChooser(this.directory);
        int returnVal = filechooser.showSaveDialog(this.drawingPanel);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            this.directory = filechooser.getCurrentDirectory();
            this.file = filechooser.getSelectedFile();
            this.save();
        }
    }

    public void print() {
    }

    public void exit() {
        // int result = JOptionPane.showConfirmDialog(null, GConstants.EXIT_TITLE, null, JOptionPane.OK_CANCEL_OPTION);
        //  if(result==0) {
        this.checkSave();
        //System.exit(0);

    }



}





