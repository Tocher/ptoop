package drawTools;

import drawTool.DrawTool;
import mouse.MouseListener;
import shapes.Point;
import shapes.Square;
import shapesDrawer.ShapesDrawer;

import java.awt.event.MouseEvent;

public class SquareDrawTool extends DrawTool {
    public SquareDrawTool(){
        super();
        getButton().setText("Квадрат");
        getButton().addMouseListener(new MouseListener(){
            public void mousePressed(MouseEvent e){
                ShapesDrawer.getInstance().setDrawTool(SquareDrawTool.this);
            }
        });
    }

    @Override
    public void mousePressed(MouseEvent e){
        drawingShape = new Square(new Point(e.getX(), e.getY()), 1);
        createShape(drawingShape);
    }
}
