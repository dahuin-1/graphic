package transformer;

import shape.GShape;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class GRotator extends GTransformer {

    private int centerX, centerY, startX, startY, endX, endY;
    private AffineTransform affineTransform;

    public GRotator(GShape shape) {
        super(shape);
        this.affineTransform = new AffineTransform();
    }

    public void initTransforming(Graphics2D g2D, int x, int y){
        this.centerX = (int)this.shape.getBounds().getCenterX();
        this.centerY = (int)this.shape.getBounds().getCenterY();
        this.startX = x;
        this.startY = y;
    }

    public void keepTransforming(Graphics2D g2D, int x, int y){
        g2D.setXORMode(g2D.getBackground());
        this.shape.draw(g2D);
        this.endX = x;
        this.endY = y;

        double startAngle = Math.toDegrees(Math.atan2(centerX-startX, centerY-startY));
        double endAngle = Math.toDegrees(Math.atan2(centerX-endX, centerY-endY));
        double rotationAngle = startAngle-endAngle;
        if (rotationAngle < 0) {
            rotationAngle += 360;
        }
        this.affineTransform.setToRotation(Math.toRadians(rotationAngle), centerX, centerY);
        this.shape.setShape(this.affineTransform.createTransformedShape(this.shape.getShape()));

        this.startX = this.endX;
        this.startY = this.endY;
        this.shape.draw(g2D);
    }

    public void finishTransforming(Graphics2D g2D, int x, int y){

    }

}

