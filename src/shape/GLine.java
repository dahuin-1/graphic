package shape;

import java.awt.geom.Line2D;

public class GLine extends GShape implements Cloneable{

    private static final long serialVersionUID = 1L;

    private Line2D.Float line;

    public GLine() {
        this.eDrawingStyle = EDrawingStyle.e2Points;
        this.line = new Line2D.Float();
        this.shape = this.line;
    }

    @Override
    public boolean contains(int x, int y) {
        Line2D line = (Line2D) this.shape;
        //Line2D.Float line = (Line2D.Float)this.shape;
        boolean bContains = false;
        this.eSelectedAnchor = null;
        if(this.bSelected){
            this.eSelectedAnchor = this.anchors.contains(x,y);
        }
        if(this.eSelectedAnchor == null) {
          //  Line2D line = (Line2D) this.shape;
            if (line.ptSegDist(x, y) < 5) {
                this.eSelectedAnchor = GAnchors.EAnchors.MM;
                bContains = true;
            }
        } else {
            bContains = true;
        }
        return bContains;
    }

    @Override
    public void setOrigin(int x, int y) {
       // Line2D line = (Line2D)this.shape;
        this.line.setLine(x, y, x, y);
    }

    @Override
    public void setPoint(int x, int y) {
       // Line2D line = (Line2D)this.shape;
        this.line.setLine(line.getX1(), line.getY1(), x, y);
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