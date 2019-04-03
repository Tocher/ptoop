package drawTools;

import drawTool.DrawTool;
import shapes.base.Shape;

import java.awt.event.MouseEvent;

public class StaticDrawTool extends DrawTool {
    @Override
    public void mousePressed(MouseEvent e) {}

    public void draw(Shape shape) {
        createShape(shape);
    }
}
