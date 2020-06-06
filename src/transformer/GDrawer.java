package transformer;

import shape.GShape;

import java.awt.*;

public class GDrawer extends GTransformer {
    public GDrawer(GShape shape) {
        super(shape);
    }

    public void initTransforming(Graphics2D g2D, int x, int y){
        this.shape.setOrigin(x,y);
    }

    public void keepTransforming(Graphics2D g2D, int x, int y){
        g2D.setXORMode(g2D.getBackground());
        this.shape.draw(g2D);
        this.shape.setPoint(x,y);
        this.shape.draw(g2D);
    }

}
