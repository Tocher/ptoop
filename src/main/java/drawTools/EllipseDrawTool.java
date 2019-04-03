package drawTools;

import drawTool.DrawTool;
import mouse.MouseListener;
import shapes.Ellipse;
import shapes.Point;
import shapesDrawer.ShapesDrawer;

import java.awt.event.MouseEvent;

public class EllipseDrawTool extends DrawTool {
    public EllipseDrawTool() {
        super();
        getButton().setText("Эллипс");
        getButton().addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                ShapesDrawer.getInstance().setDrawTool(EllipseDrawTool.this);
            }
        });
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawingShape = new Ellipse(new Point(e), 2, 2);
        createShape(drawingShape);
    }
}
