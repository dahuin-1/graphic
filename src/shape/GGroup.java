package shape;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

public class GGroup extends GRectangle {
    private static final long serialVersionUID = 1L;
   // private Vector<GShape> containedShapes;
    private Vector<GShape> containedShapes;
    public GGroup() {
        this.containedShapes = new Vector<GShape>();
    }

    public void contains(Vector<GShape> shapeVector) {
        for(GShape shape : shapeVector) {
            if(this.getShape().contains(shape.getShape().getBounds())) {
                this.containedShapes.add(shape);
                shape.setSelected(true);
            }
        }
    }

   // @Override
    public GShape newInstance() {
        return new GGroup();
    }
}


   /* private GAnchors.EAnchors eSelectedAnchor;

    public GGroup(){
        this.shapeVector  = new Vector<GShape>();
    }

    public void draw(Graphics g) {
        Rectangle unionRectangle = new Rectangle();
        for(GShape shape:this.shapeVector){
            shape.draw(g);
            unionRectangle.union(shape.getBounds());
        }
        if(this.bSelected) {
            Graphics2D graphics2D = (Graphics2D)g;
            this.anchors.draw(graphics2D);
        }
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

    public void setLineColor(Color lineColor) {
        for (GShape shape : this.shapeVector) {
            shape.setLineColor(lineColor);
        }
    }

    public void setFillColor(Color fillColor) {
        for (GShape shape : this.shapeVector) {
            shape.setFillColor(fillColor);
        }
    }

  /*  public void contains(Vector<GShape> shapeVector) {
        for(GShape shape : shapeVector) {
            if(this.getShape().contains(shape.getShape().getBounds())) {
                this.containedShapes.add(shape);
                shape.setSelected(true);
            }
        }
    }*/

  /*public boolean contains(int x, int y){
        boolean bContains = false;
        this.eSelectedAnchor = null;
        if(this.bSelected) {
            this.eSelectedAnchor = this.anchors.contains(x,y);
        }
        if(this.eSelectedAnchor == null) {
            for (GShape shape : this.shapeVector) {
                if (shape.contains(x, y)) {
                    this.eSelectedAnchor = GAnchors.EAnchors.MM;
                    bContains = true;
                    break;
                }
            }
        } else {
            bContains = true;
        }
        return bContains;
    }*/

    /*public Rectangle getBounds() {
        Rectangle unionRectangle = new Rectangle();
        for(GShape shape : this.shapeVector){
            unionRectangle.union(shape.getBounds());
        }
        this.shape = unionRectangle;
        return this.shape.getBounds();
    }*/

