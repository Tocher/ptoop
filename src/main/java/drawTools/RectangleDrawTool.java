package drawTools;

import drawTool.DrawTool;
import mouse.MouseListener;
import shapes.Point;
import shapes.Rectangle;
import shapesDrawer.ShapesDrawer;

import java.awt.event.MouseEvent;

public class RectangleDrawTool extends DrawTool {
    public RectangleDrawTool(){
        super();
        getButton().setText("Прямоугольник");
        getButton().addMouseListener(new MouseListener(){
            public void mousePressed(MouseEvent e){
                ShapesDrawer.getInstance().setDrawTool(RectangleDrawTool.this);
            }
        });
    }

    public void mousePressed(MouseEvent e){
        drawingShape = new Rectangle(new Point(e.getX(), e.getY()), 1, 1);
        createShape(drawingShape);
    }
}
