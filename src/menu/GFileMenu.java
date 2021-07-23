package menu;

import file.GFile;
import global.GConstants;
import panel.GDrawingPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class GFileMenu extends GMenu {

    private static final long serialVersionUID = GConstants.serialVersionUID;

    //working variables
    private File directory, file;

    public GFileMenu(String name) {
        super(name);

        this.file = null;
        this.directory = new File("data");

        for (GConstants.EFileMenu eMenu : GConstants.EFileMenu.values()) {
            JMenuItem menuItem = new JMenuItem(eMenu.getTitle());
            menuItem.setActionCommand(eMenu.getActionCommand());
            menuItem.addActionListener(this.actionHandler);
            this.menuItems.add(menuItem);
            this.add(menuItem);
        }
    }

    public void initialize() {
    }
    private void readObject() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream(this.file)));
            this.drawingPanel.restoreShapeVector(objectInputStream.readObject());
            objectInputStream.close();
            this.drawingPanel.setUpdated(false);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void nnew() {
        this.save();
        this.drawingPanel.restoreShapeVector(null);
        this.drawingPanel.setUpdated(true);
    }

    //현재파일인지를 확인한다.
    public void open() {
        this.save();
        JFileChooser chooser = new JFileChooser(this.directory);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Data", "dh");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this.drawingPanel);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            this.directory = chooser.getCurrentDirectory();
            this.file = chooser.getSelectedFile();
            this.readObject();
        }
    }

    private void writeObject() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(this.file)));
            objectOutputStream.writeObject(this.drawingPanel.getShapeVector());
            objectOutputStream.close();
            this.drawingPanel.setUpdated(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Serialization
    //new를 누르고 그림판을 할 때는 save_as로 빠져서 저장된다.
    public void save() {
        if(this.drawingPanel.isUpdated()) {
            if(this.file == null) {
                this.saveAs();
            } else {
                this.writeObject();
            }
        }
    }

    public void saveAs() {
        JFileChooser chooser = new JFileChooser(this.directory);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Data", "god");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this.drawingPanel);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            this.directory = chooser.getCurrentDirectory();
            this.file = chooser.getSelectedFile();
            this.writeObject();
        }
    }

    public void exit() {
        this.checkSave();
        System.exit(0);
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


    private void invokeMethod(String actionCommand) {
        try {
            this.getClass().getMethod(actionCommand).invoke(this);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            e.printStackTrace();
        }
    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            invokeMethod(actionEvent.getActionCommand());
        }
    }
}

