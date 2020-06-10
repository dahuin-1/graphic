package transformer;

import shape.GShape;

import java.awt.*;

public class GMover extends GTransformer {
    public GMover(GShape shape) {
        super(shape);
        previousP = new Point();
    }
    
    public void init(Point p){
        previousP = p;
    }

    public void initTransforming(Graphics2D g2D, int x, int y) {
        this.shape.initTransforming(x, y);
    }

    public void keepTransforming(Graphics2D g2D, int x, int y) {
        g2D.setXORMode(g2D.getBackground());
        this.shape.draw(g2D);
        this.shape.keepTransforming(x, y);
        this.shape.draw(g2D);
    }

    public void finishTransforming(Graphics2D g2D, int x, int y) {
        this.shape.finishTransforming(x, y);
    }
}

