package shape;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class GLine extends GShape implements Cloneable{

    private static final long serialVersionUID = 1L;

    //private Line2D.Float line;
    private java.awt.geom.Line2D line2D;

    public GLine() {
        this.eDrawingStyle = EDrawingStyle.e2Points;
       // this.line = new Line2D.Float();
        this.shape = new java.awt.geom.Line2D.Double(0, 0, 0, 0);
        this.line2D = (java.awt.geom.Line2D) this.shape;
    }

    @Override
    public boolean contains(int x, int y) {
        //Line2D line = (Line2D) this.shape;
        //Line2D.Float line = (Line2D.Float)this.shape;
        boolean bContains = false;
        this.eSelectedAnchor = null;
        if(this.bSelected){
            this.eSelectedAnchor = this.anchors.contains(x,y);
        }
        if (this.line2D.getX1() < x && x < this.line2D.getX2()) {
            if (this.line2D.getY1() < y && y < this.line2D.getY2()) {
                this.eSelectedAnchor = GAnchors.EAnchors.MM;
                bContains = true;
                this.setSelected(true);
            }
        } else {
            bContains = false;
            this.setSelected(false);
        }
        return bContains;
    }
//        this.eSelectedAnchor = null;
//        if(this.bSelected){
//            this.eSelectedAnchor = this.anchors.contains(x,y);
//        }
//        if(this.eSelectedAnchor == null) {
  //          Line2D line = (Line2D) this.shape;
//            if (this.line2D.ptSegDist(x, y) < 5) {
//                this.eSelectedAnchor = GAnchors.EAnchors.MM;
//                bContains = true;
//                this.setSelected(true);
//            }
//        } else {
//            bContains = false;
//            this.setSelected(false);
//        }
//        return bContains;
//    }

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
       // Line2D line = (Line2D)this.shape;
        this.line2D.setLine(x, y, x, y);
    }

    @Override
    public void setPoint(int x, int y) {
       // Line2D line = (Line2D)this.shape;
        this.line2D.setLine(this.line2D.getX1(), this.line2D.getY1(), x, y);
    }

    @Override
    public void addPoint(int x, int y) {
    }



//    @Override
//    public void move(int dx, int dy) {
//        Line2D line = (Line2D)this.shape;
//        line.setLine(dx+line.getX1(),dy+line.getY1(),line.getX2()+dx,line.getY2()+dy);
//
//    }


}