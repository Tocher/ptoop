package drawTools;

import mouse.MouseListener;
import shapes.Point;
import shapes.Polygon;
import shapesDrawer.ShapesDrawer;

import java.awt.event.MouseEvent;

public class PolygonDrawTool extends MultidotShapeDrawTool {
    public PolygonDrawTool() {
        super();
        getButton().setText("Многоугольник");
        getButton().addMouseListener(new MouseListener(){
            public void mousePressed(MouseEvent e){
                ShapesDrawer.getInstance().setDrawTool(PolygonDrawTool.this);
            }
        });
    }

    protected Polygon createMultidotShape(Point point){
        return new Polygon(point);
    }
}