package drawTool;

import shapes.Point;
import shapes.base.Shape;
import shapesDrawer.ShapesDrawer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class DrawTool extends MouseAdapter {
    private ShapesDrawer shapesDrawer = ShapesDrawer.getInstance();
    private JButton button = new JButton();
    protected Shape drawingShape;

    public DrawTool() {}

    public abstract void mousePressed(MouseEvent e);

    protected void createShape(Shape shape) {
        ShapesDrawer.getInstance().addShape(shape);
        ShapesDrawer.getInstance().draw();
    }

    public JButton getButton() {
        return button;
    }

    public void finishDrawing() {
    }

    public void mouseDragged(MouseEvent e) {
        drawingShape.refreshShape(new Point(e));
        ShapesDrawer.getInstance().draw();
    }
}
