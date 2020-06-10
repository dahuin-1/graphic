package shape;

import global.GConstants;

import java.awt.*;

public class GPolygon extends GShape implements Cloneable {

    private static final long serialVersionUID = 1L;

    public final  static  int nMaxPoints = GConstants.MAXPOINTS;


    public GPolygon() {
        this.eDrawingStyle = EDrawingStyle.eNPoints;
        this.shape = new Polygon();
    }

    @Override
    public void setOrigin(int x, int y) {
        Polygon polygon = (Polygon)this.shape;
        polygon.addPoint(x,y);
        polygon.addPoint(x,y);
    }

    @Override
    public void setPoint(int x, int y) {
        Polygon polygon = (Polygon)this.shape;
        polygon.xpoints[polygon.npoints - 1] = x;
        polygon.ypoints[polygon.npoints - 1] = y;
    }

    @Override
    public void addPoint(int x, int y) {
        Polygon polygon = (Polygon)this.shape;
        polygon.addPoint(x,y);
    }

//    @Override
//    public void move(int dx, int dy) {
//        Polygon polygon = (Polygon)this.shape;
//        polygon.translate(dx,dy);
//    }


}