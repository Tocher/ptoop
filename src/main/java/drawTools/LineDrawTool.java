package drawTools;

import drawTool.DrawTool;
import mouse.MouseListener;
import shapes.Line;
import shapes.Point;
import shapesDrawer.ShapesDrawer;

import java.awt.event.MouseEvent;


public class LineDrawTool extends DrawTool {
    public LineDrawTool() {
        super();
        getButton().setText("Линия");
        getButton().addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                ShapesDrawer.getInstance().setDrawTool(LineDrawTool.this);
            }
        });
    }

    public void mousePressed(MouseEvent e) {
        drawingShape = new Line(new Point(e.getX(), e.getY()));
        createShape(drawingShape);
    }
}

