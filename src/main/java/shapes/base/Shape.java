package shapes.base;

import java.awt.*;
import java.io.Serializable;

public abstract class Shape implements Serializable {
    private Color borderColor;

    public Shape(){
        borderColor = Color.BLACK;
    }

    public Color getBorderColor(){
        return borderColor;
    }

    public void setBorderColor(Color borderColor){
        this.borderColor = borderColor;
    }

    public abstract void refreshShape(shapes.Point point);

    public abstract void draw(Graphics g);

    public abstract String getName();
}