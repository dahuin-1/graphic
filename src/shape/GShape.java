package shape;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.PolicyNode;

public abstract class GShape implements Serializable {

    private static final long serialVersionUID = 1L;
    protected GAnchors.EAnchors eSelectedAnchor;

    private int tMoveX, tMoveY;

    protected Graphics2D graphics2D;
    protected Shape shape;
    protected GAnchors anchors;
    protected Color lineColor, fillColor;
   // private GAnchors.EAnchors eSelectedAnchor;


    public enum EDrawingStyle{
        e2Points, eNPoints
    }
    protected boolean bSelected;
    protected EDrawingStyle eDrawingStyle;

    public GShape() {
        this.bSelected = false;
        this.anchors = new GAnchors();
        this.lineColor = null;
        this.fillColor = null;
    }


    public EDrawingStyle getEDrawingStyle() {
        return this.eDrawingStyle;
    }

    public void setSelected(boolean bSelected) {
        this.bSelected = bSelected;
        if(this.bSelected){
            this.anchors.setBounds(this.shape.getBounds());
        }
    }
    public GAnchors.EAnchors getESelectedAnchor(){
        return this.eSelectedAnchor;
    }

    public void draw(Graphics g) {
        Graphics2D graphics2D = (Graphics2D)g;
        if (this.fillColor != null) {
            graphics2D.setColor(this.fillColor);
            graphics2D.fill(this.shape);
        }
        if (this.lineColor != null) {
            graphics2D.setColor(this.lineColor);
            graphics2D.draw(this.shape);
        }
        if(this.bSelected) {
            this.anchors.draw(graphics2D);
        }
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public GShape clone() {
        try{
            return this.getClass().getDeclaredConstructor().newInstance();

        }catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e){
            e.printStackTrace();
        }
        return null;
    }

    //  abstract public GShape newInstance();


    public boolean contains (int x, int y){
        boolean bContains = false;
        this.eSelectedAnchor = null;
        if(this.bSelected) {
            this.eSelectedAnchor = this.anchors.contains(x,y);
        }
        if(this.eSelectedAnchor == null){
            if(this.shape.contains(new Point(x,y))){
                this.eSelectedAnchor = GAnchors.EAnchors.MM;
                bContains = true;
            }
        } else {
            bContains = true;
        }
        return bContains;
    }

    public Rectangle getBounds() {
        return this.shape.getBounds();
    }

    public void initTransforming(int x, int y) {
        // Graphics2D graphics2D = Graphics2D;
        this.tMoveX = x;
        this.tMoveY = y;
//        if(this.bSelected) {
//            this.anchors.setBounds(this.shape.getBounds());
//            this.anchors.draw(graphics2D);
//        }

    }

    public void keepTransforming(int x, int y) {
        this.move(x-this.tMoveX, y-this.tMoveY);
        this.tMoveX = x;
        this.tMoveY = y;
    }

    public void finishTransforming(int x, int y) {
        this.tMoveX = x;
        this.tMoveY = y;
    }
    public void move(int dx, int dy) {
        AffineTransform at = new AffineTransform();
        at.translate(dx,dy);
        this.shape = at.createTransformedShape(this.shape);
    }

    public boolean isSelected() {return bSelected;}
    public Shape getShape() {return shape;}
    public void setShape(Shape shape) {this.shape = shape;}
   // public abstract void move(int dx, int dy);

    public abstract void setOrigin(int x, int y);
    public abstract void setPoint(int x, int y);
    public abstract void addPoint(int x, int y);

}

