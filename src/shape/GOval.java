package shape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class GOval extends GShape implements Cloneable {

    private static final long serialVersionUID = 1L;

    public GOval() {
        this.eDrawingStyle = EDrawingStyle.e2Points;
        this.shape = new Ellipse2D.Float();

    }

    @Override
    public GShape deepCopy() {
        AffineTransform affineTransform = new AffineTransform();
        Shape newShape = affineTransform.createTransformedShape(shape);
        GOval shape = new GOval();
        shape.setShape(newShape);
        shape.setGraphicsAttributes(this);
        return shape;
    }

    @Override
    public void setOrigin(int x, int y) {
        Ellipse2D ellipse = (Ellipse2D)this.shape;
        ellipse.setFrame(x,y,0,0);
    }

    @Override
    public void setPoint(int x, int y) {
        Ellipse2D ellipse = (Ellipse2D)this.shape;
        int newWidth = (int)(x - ellipse.getX());
        int newHeight = (int) (y - ellipse.getY());
        ellipse.setFrame(ellipse.getX(),ellipse.getY(),newWidth, newHeight);
    }

    @Override
    public void addPoint(int x, int y) {

    }

//    @Override
//    public void move(int dx, int dy) {
//        Ellipse2D ellipse = (Ellipse2D)this.shape;
//        ellipse.setFrame(ellipse.getX()+dx, ellipse.getY()+dy,
//                ellipse.getWidth(),ellipse.getHeight());
//
//    }






}