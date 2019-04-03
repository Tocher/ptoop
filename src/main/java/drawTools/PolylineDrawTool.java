package drawTools;

import mouse.MouseListener;
import shapes.Point;
import shapes.Polyline;
import shapesDrawer.ShapesDrawer;

import java.awt.event.MouseEvent;

public class PolylineDrawTool extends MultidotShapeDrawTool {
    public PolylineDrawTool() {
        super();
        getButton().setText("Ломаная");
        getButton().addMouseListener(new MouseListener(){
            public void mousePressed(MouseEvent e){
                ShapesDrawer.getInstance().setDrawTool(PolylineDrawTool.this);
            }
        });
    }

    protected Polyline createMultidotShape(Point point){
        return new Polyline(point);
    }
}
