package file;

import panel.GDrawingPanel;

import java.io.*;

public class GFile {

    private File file;
    private GDrawingPanel drawingPanel;

    public GFile() {
        this.file = null;
    }

    public Object readObject(File file) {
        try {
            this.file = file;
            FileInputStream fileInputStream = new FileInputStream(this.file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveObject(Object object, File file) {
        this.file = file;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Object writeObject(Object object, File file) {
        try {
            this.file = file;
            FileOutputStream fileOutputStream = new FileOutputStream(this.file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            return object;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
