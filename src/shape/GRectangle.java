package shape;

import java.awt.*;

public class GRectangle extends GShape implements Cloneable{

    private static final long serialVersionUID = 1L;


    public GRectangle() {
        this.eDrawingStyle = EDrawingStyle.e2Points;
        this.shape = new Rectangle();
    }

    @Override
    public void setOrigin(int x, int y) {
        // this.rectangle.setBounds(x, y, 0, 0);
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

    @Override
    public void addPoint(int x, int y) {
    }


    @Override
    public void move(int dx, int dy) {
        Rectangle rectangle = (Rectangle)this.shape;
        rectangle.translate(dx, dy);
    }



}
