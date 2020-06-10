package panel;



import java.util.Vector;

import shape.GShape;

public class GBoard {
    private Vector<GShape> shapes;

    public GBoard() {
        this.shapes = new Vector<GShape>();
    }

    //멀티플 셀렉션이 올수도 있기 때문에
    public void setContents(Vector<GShape> shapes) {
        this.shapes.clear();
        this.shapes.addAll(shapes);
    }

    public Vector<GShape> getContents() {
        Vector<GShape> clonedShapes = new Vector<GShape>();
        for(GShape shape : this.shapes) {
            GShape clonedShape = shape.clone();
            clonedShapes.add(clonedShape);
        }
        return clonedShapes;
    }
}