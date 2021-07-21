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

    public void close() {
        this.save();
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


 /*   public void nnew() {
        this.checkSave();
        this.drawingPanel.clearShapeVector();
        this.file = null;
    }

    public void nnew() {
       // this.save();
       // this.drawingPanel.clearShapeVector();
       // this.drawingPanel.setUpdated(true);
    }
*/

 /*   public void open() {
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

/*public class GFileMenu extends JMenu {
    private static final long serialVersionUID = 1L;

    private File directory, file;

    //association
    private GDrawingPanel drawingPanel;
    public void associate(GDrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    public GFileMenu(String text) {
        super(text);

        this.file = null;
        this.directory = new File("data");

        ActionHandler actionHandler = new ActionHandler();

        for (GConstants.EFileMenu eMenu : GConstants.EFileMenu.values()) {
            JMenuItem menuItem = new JMenuItem(eMenu.getTitle());
            menuItem.setActionCommand(eMenu.getActionCommand());
            menuItem.addActionListener(actionHandler);
            this.menuItems.add(menuItem);
            this.add(menuItem);
        }

        for(GConstants.EFileMenu eMenuItem : GConstants.EFileMenu.values()) {
            JMenuItem menuItem = new JMenuItem(eMenuItem.getTitle());
            menuItem.setActionCommand(eMenuItem.getActionCommand());
            menuItem.addActionListener(actionHandler);
            this.add(menuItem);
        }
    }

    public void initialize() {

    }

    public void nnew() {
        this.save();
        this.drawingPanel.restoreShapeVector(null);
        this.drawingPanel.setUpdated(true);
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

    public void close() {
        this.save();
        System.exit(0);
    }

    public void print() {

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
}*/





