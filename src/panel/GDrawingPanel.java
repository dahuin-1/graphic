package panel;

import global.GConstants;
import shape.GAnchors;
import shape.GShape;
import transformer.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

public class GDrawingPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private enum EDrawingState {
        eIdle, eDrawing, eTransforming
    }
    private EDrawingState eDrawingState;
    private Color lineColor, fillColor;
    private boolean Updated;
    private boolean bDrawing;

    //components
    private MouseHandler mouseHandler;
    private Vector<GShape> shapeVector;
    private GTransformer transformer;
  //private DeepClone deepClone;

    //associations components
    private GShape currentShape;
    private GShape currentTool;

    //working variables
    private GShape selectedShape;
   // private GShape copyshape;

    //constructors and initializers
    public GDrawingPanel() {
        //attributes
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        this.eDrawingState = EDrawingState.eIdle;

        this.lineColor = null;
        this.fillColor = null;
        this.Updated = false;
        this.bDrawing = true;
        this.transformer = null;

        //components
        this.mouseHandler = new MouseHandler();
        this.addMouseMotionListener(this.mouseHandler); //버튼이벤트
        this.addMouseListener(this.mouseHandler); //마우스의 움직임을 인지하는 이벤트

        this.shapeVector = new Vector<GShape>();

        //working variables
        this.currentTool = null;
        this.currentShape = null;
    }

    public void initialize() {
        this.lineColor = this.getForeground();
        this.fillColor = this.getBackground();
    }

    //setter & getter

    public Vector<GShape> getShapeVector() {
        return this.shapeVector;
    }

    public void setShapeVector(Object shapeVector) {
        this.shapeVector = (Vector<GShape>) shapeVector;
        this.repaint();
    }

    public void clearShapeVector() {
        this.shapeVector.clear();
        this.repaint();
    }

    public void setCurrentTool(GConstants.EToolbar eToolBar) {
        this.currentTool = eToolBar.getActionCommand();
    }

    public boolean isUpdated() {
        return this.Updated;
    }

    public void setUpdated(boolean Updated) {
        this.Updated = Updated;
    }

    //setter & getter
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setCurrentShape(GShape currentShape) {
        this.currentShape = currentShape;
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (GShape shape : this.shapeVector) {
            shape.draw(g);
        }
    }

    private void checkCursor(int x, int y) {
        GShape selectedShape = this.onShape(x, y);
        if (selectedShape == null) {
            this.setCursor(GConstants.ECursor.eDefault.getCursor());
        } else {
            GAnchors.EAnchors eSelectedAnchor = selectedShape.getESelectedAnchor();
            switch (eSelectedAnchor) {
                case NW:
                    this.setCursor(GConstants.ECursor.eNW.getCursor());
                    break;
                case NN:
                    this.setCursor(GConstants.ECursor.eNN.getCursor());
                    break;
                case NE:
                    this.setCursor(GConstants.ECursor.eNE.getCursor());
                    break;
                case EE:
                    this.setCursor(GConstants.ECursor.eEE.getCursor());
                    break;
                case SE:
                    this.setCursor(GConstants.ECursor.eSE.getCursor());
                    break;
                case SS:
                    this.setCursor(GConstants.ECursor.eSS.getCursor());
                    break;
                case SW:
                    this.setCursor(GConstants.ECursor.eSW.getCursor());
                    break;
                case WW:
                    this.setCursor(GConstants.ECursor.eWW.getCursor());
                    break;
                case RR:
                    this.setCursor(GConstants.ECursor.eRotate.getCursor());
                    break;
                case MM:
                    this.setCursor(GConstants.ECursor.eMove.getCursor());
                    break;
                default:
                    break;
            }
        }
    }

    private GShape onShape(int x, int y) {
        for (GShape shape : this.shapeVector) {
            if (shape.contains(x, y)) {
                return shape;
            }
        }
        return null;
    }

    private void setSelected(GShape selectedShape) {
        for (GShape shape : this.shapeVector) {
            shape.setSelected(false);
        }
        selectedShape.setSelected(true);
        this.repaint();
    }

    private void initTransforming(GShape shape, int x, int y) {
        if (shape == null) { //그림이 없다면
            this.bDrawing = true;
            //drawing
            this.currentShape = this.currentTool.clone();
            this.currentShape.setLineColor(this.lineColor);
            this.currentShape.setFillColor(this.fillColor);
            this.transformer = new GDrawer(this.currentShape);
        } else {
            this.bDrawing = false;
            //moving, resizing, ratating
            this.currentShape = shape;
            //transformation
            switch (shape.getESelectedAnchor()) {
                case MM:
                    this.transformer = new GMover(this.currentShape);
                    break;
                case RR:
                    this.transformer = new GRotator(this.currentShape);
                    break;
                default:
                    this.transformer = new GResizer(this.currentShape);
                    break;
            }
        }
        Graphics2D g2D = (Graphics2D)this.getGraphics();
        this.transformer.initTransforming(g2D, x, y);
    }

    private void keepTransforming(int x, int y) {
        Graphics2D g2D = (Graphics2D)this.getGraphics();
        this.transformer.keepTransforming(g2D, x, y);
    }

    private void finishTransforming(int x, int y) {
        Graphics2D g2D = (Graphics2D)this.getGraphics();
        this.transformer.finishTransforming(g2D, x, y);
        this.Updated = true;
        this.setSelected(this.currentShape);
        if (this.bDrawing) {
            this.shapeVector.add(this.currentShape);
        }
    }


    private void continueTransforming(int x, int y) {
        Graphics2D g2D = (Graphics2D)this.getGraphics();
        this.transformer.continueTransforming(g2D,x,y);
    }



    // inner class
    class MouseHandler implements MouseMotionListener, MouseListener {
        @Override
        public void mouseClicked(MouseEvent event) {
            if (event.getClickCount() == 1) {
                this.mouse1Clicked(event);
            } else if (event.getClickCount() == 2) {
                this.mouse2Clicked(event);
            }
        }

        // n point drawing
        private void mouse1Clicked(MouseEvent event) {
            int x = event.getX();
            int y = event.getY();
            GShape shape = onShape(x,y);
            if(shape == null) {
                if (currentTool.getEDrawingStyle() == GShape.EDrawingStyle.eNPoints && eDrawingState == EDrawingState.eIdle) {
                    initTransforming(null, x, y);
                    eDrawingState = EDrawingState.eDrawing;
                }
            }else{
                setSelected(shape);
            }
            if (currentTool.getEDrawingStyle() == GShape.EDrawingStyle.eNPoints && eDrawingState == EDrawingState.eDrawing) {
                continueTransforming(x, y);
            }
        }

        private void mouse2Clicked(MouseEvent event) {
            if (currentTool.getEDrawingStyle() == GShape.EDrawingStyle.eNPoints && eDrawingState == EDrawingState.eDrawing) {
                int x = event.getX();
                int y = event.getY();
                finishTransforming(x, y);
                eDrawingState = EDrawingState.eIdle;
            }
        }

        @Override
        public void mouseMoved(MouseEvent event) {
            int x = event.getX();
            int y = event.getY();
            if (currentTool.getEDrawingStyle() == GShape.EDrawingStyle.eNPoints && eDrawingState == EDrawingState.eDrawing) {
                keepTransforming(x, y);
            }
            checkCursor(x,y);
        }

        // 2 point drawing
        @Override
        public void mousePressed(MouseEvent event) {
            int x = event.getX();
            int y = event.getY();
            if (eDrawingState == EDrawingState.eIdle){
                GShape shape = onShape(x,y);
                if(shape == null) {
                    if (currentTool.getEDrawingStyle() == GShape.EDrawingStyle.e2Points) {
                        initTransforming(null, x, y);
                        eDrawingState = EDrawingState.eDrawing;
                    }
                }else{
                    initTransforming(shape, x, y);
                    eDrawingState = EDrawingState.eTransforming;
                }
            }
        }


        @Override
        public void mouseDragged(MouseEvent event) {
            int x = event.getX();
            int y = event.getY();

            if (eDrawingState == EDrawingState.eTransforming) {
                keepTransforming(x, y);
            }else if(eDrawingState == EDrawingState.eDrawing) {
                if (currentTool.getEDrawingStyle() == GShape.EDrawingStyle.e2Points) {
                    keepTransforming(x, y);
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            int x = event.getX();
            int y = event.getY();

            if (eDrawingState == EDrawingState.eTransforming) {
                finishTransforming(x, y);
                eDrawingState = EDrawingState.eIdle;
            }else if (eDrawingState == EDrawingState.eDrawing) {
                if(currentTool.getEDrawingStyle() == GShape.EDrawingStyle.e2Points) {
                    finishTransforming(x, y);
                    eDrawingState = EDrawingState.eIdle;
                }
            }
        }


        @Override
        public void mouseEntered(MouseEvent event) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent event) {
            // TODO Auto-generated method stub

        }

    }
}
