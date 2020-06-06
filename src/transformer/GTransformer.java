package transformer;

import shape.GShape;

import java.awt.*;

public class GTransformer {

    protected GShape shape;
    public GTransformer(GShape shape){
        this.shape = shape;
    }

    public void initTransforming(Graphics2D g2D, int x, int y){
        this.shape.initTransforming(x,y);
    }

    public void keepTransforming(Graphics2D g2D, int x, int y){
        g2D.setXORMode(g2D.getBackground());
        this.shape.draw(g2D);
        this.shape.keepTransforming(x,y);
        this.shape.draw(g2D);
    }

    public void finishTransforming(Graphics2D g2D, int x, int y){
        this.shape.finishTransforming(x,y);
    }

    public void continueTransforming(Graphics2D g2D, int x, int y){
        this.shape.addPoint(x,y);
    }
}
