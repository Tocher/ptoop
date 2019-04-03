package drawTools;

import drawTool.DrawTool;
import mouse.MouseListener;
import shapes.Circle;
import shapes.Point;
import shapesDrawer.ShapesDrawer;

import java.awt.event.MouseEvent;

public class CircleDrawTool extends DrawTool {
    public CircleDrawTool() {
        super();

        getButton().setText("Круг");
        getButton().addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                ShapesDrawer.getInstance().setDrawTool(CircleDrawTool.this);
            }
        });
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawingShape = new Circle(new Point(e), 1);
        createShape(drawingShape);
    }
}
