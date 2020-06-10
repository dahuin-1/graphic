package shape;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class GRectangle extends GShape implements Cloneable{

    private static final long serialVersionUID = 1L;

    private java.awt.Rectangle rectangle;

    public GRectangle() {
        this.eDrawingStyle = EDrawingStyle.e2Points;
        this.shape = new java.awt.Rectangle();
        this.rectangle = (java.awt.Rectangle) this.shape;
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
      //   this.rectangle.setBounds(x, y, 0, 0);
        Rectangle rectangle = (Rectangle)this.shape;
        rectangle.setLocation(x,y);
        rectangle.setSize(0,0);
    }


    @Override
    public void setPoint(int x, int y) {
        Rectangle rectangle = (Rectangle)this.shape;
        int w = x - rectangle.x ;
        int h = y - rectangle.y ;
        rectangle.setSize(w,h);
    }


    //no use
    @Override
    public void addPoint(int x, int y) {
    }


}
