package drawTools;

import drawTool.DrawTool;
import shapes.Point;
import shapes.Polyline;
import shapesDrawer.ShapesDrawer;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public abstract class MultidotShapeDrawTool extends DrawTool {
    private Polyline drawingMultidotShape = null;

    protected abstract Polyline createMultidotShape(Point point);

    public void mousePressed(MouseEvent e) {
        Point pressedPoint = new Point(e);
        if (drawingShape == null) {
            drawingShape = createMultidotShape(pressedPoint);
            drawingMultidotShape = (Polyline) drawingShape;
            createShape(drawingShape);
        } else {
            if ((e.getModifiers() & InputEvent.CTRL_MASK) != 0) {
                drawingShape = null;
            } else {
                drawingShape.refreshShape(pressedPoint);
                ShapesDrawer.getInstance().draw();
            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void finishDrawing(){
        drawingMultidotShape.removeLastPoint();
        ShapesDrawer.getInstance().draw();
        drawingShape = null;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (drawingShape != null) {
            drawingMultidotShape.setFinishPoint(new Point(e));
            ShapesDrawer.getInstance().draw();
        }

    }
}
