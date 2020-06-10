package shape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Vector;

public class GAnchors implements Serializable {

    private final int ANCHORS_RRHEIGHT = 40;
    private final int ANCHORS_W = 10;
    private final int ANCHORS_H = 10;

    public enum EAnchors { NW, NN, NE, EE, SE, SS, SW, WW, RR, MM };
    private Vector<Ellipse2D> anchors;

    public GAnchors() {
        this.anchors = new Vector<Ellipse2D>();
        for(int i=0;i<EAnchors.values().length-1;i++) {
            Ellipse2D anchor = new Ellipse2D.Double();
            this.anchors.add(anchor);
        }
    }

    private void setCoordinate(EAnchors eAnchor, Ellipse2D anchors, Rectangle bounds){
        int x = 0;
        int y = 0;
        int w = 5;
        int h = 5;

        switch (eAnchor) {
            case NW:
                x = bounds.x;
                y = bounds.y;
                break;
            case NN:
                x = bounds.x + bounds.width / 2;
                y = bounds.y;
                break;
            case NE:
                x = bounds.x + bounds.width;
                y = bounds.y;
                break;
            case EE:
                x = bounds.x + bounds.width;
                y = bounds.y + bounds.height / 2;
                break;
            case SE:
                x = bounds.x + bounds.width;
                y = bounds.y + bounds.height;
                break;
            case SS:
                x = bounds.x + bounds.width / 2;
                y = bounds.y + bounds.height;
                break;
            case SW:
                x = bounds.x;
                y = bounds.y + bounds.height;
                break;
            case WW:
                x = bounds.x;
                y = bounds.y + bounds.height / 2;
                break;
            case RR:
                x = bounds.x + bounds.width / 2;
                y = bounds.y - ANCHORS_RRHEIGHT;
                break;
            default:
                break;
        }
        x = x - w/2;
        y = y - h/2;
        anchors.setFrame(x, y, w, h);
    }

    public void setBounds(Rectangle bounds) {
        for(int i=0;i<EAnchors.values().length-1; i++) {
            Ellipse2D anchor = this.anchors.get(i);
            this.setCoordinate(EAnchors.values()[i],anchor,bounds);
        }
    }

    public void draw(Graphics2D graphics2D) {
        for(Ellipse2D anchor : this.anchors) {
            Color penColor = graphics2D.getColor();
            graphics2D.setColor(graphics2D.getBackground());
            graphics2D.fill(anchor);
            graphics2D.setColor(penColor);
            graphics2D.draw(anchor);
        }
    }

    //나중에 선택된 앵커를 저장해놔야 한다.
    public EAnchors contains(int x, int y) {
        for(int i=0; i<EAnchors.values().length-1; i++){
            if(this.anchors.get(i).contains(x,y)) {
                return EAnchors.values()[i];
            }
        }
        return null;
    }




}
