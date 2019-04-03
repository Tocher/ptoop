package plugins;

import drawTool.DrawTool;
import mouse.MouseListener;
import pluginLoader.Plugin;
import shapes.Point;
import shapesDrawer.ShapesDrawer;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class TriangleDrawTool extends DrawTool implements Plugin {
    public TriangleDrawTool() {
        super();
        getButton().setText("Треугольник");
        getButton().addMouseListener(new MouseListener(){
            public void mousePressed(MouseEvent e) {
                ShapesDrawer.getInstance().setDrawTool(TriangleDrawTool.this);
            }
        });
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point pressedPoint = new Point(e.getX(), e.getY());
        drawingShape = new Triangle(pressedPoint, pressedPoint);
        createShape(drawingShape);
    }

    public void load(JPanel panel) {

    }
}
