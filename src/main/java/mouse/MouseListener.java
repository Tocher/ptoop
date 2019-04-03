package mouse;

import shapesDrawer.ShapesDrawer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class MouseListener extends MouseAdapter {
    protected MouseListener() {}

    protected ShapesDrawer getShapesDrawer(){
        return ShapesDrawer.getInstance();
    }

    public abstract void mousePressed(MouseEvent e);
}
