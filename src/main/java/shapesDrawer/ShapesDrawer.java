package shapesDrawer;

import button.ButtonListener;
import drawTool.DrawTool;
import shapes.base.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class ShapesDrawer extends JPanel {
    private static ShapesDrawer instance;

    private List<Shape> shapesList = new ArrayList<>();
    private static JPanel shapesPanel = new JPanel();
    private DrawTool drawTool;
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList shapesListJ;

    public static synchronized ShapesDrawer getInstance() {
        if (instance == null) {
            instance = new ShapesDrawer();
        }
        return instance;
    }

    public ShapesDrawer() {
        setBackground(Color.WHITE);
    }

    private JButton undoButton = new JButton("Отмена");

    {
        undoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                removeLast();
            }
        });
    }

    public JButton getUndoButton() {
        return undoButton;
    }

    public void draw() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapesList) {
            shape.draw(g);
        }
    }

    private void removeListeners() {
        removeMouseListener(drawTool);
        removeMouseMotionListener(drawTool);
    }

    public void setDrawTool(DrawTool drawTool) {
        if (this.drawTool != null) {
            this.drawTool.finishDrawing();
        }
        removeListeners();
        this.drawTool = drawTool;
        addMouseListener(drawTool);
        addMouseMotionListener(drawTool);
    }

    private void removeLast() {
        if (shapesList.size() > 0) {
            shapesList.remove(shapesList.size() - 1);
        }

        this.updateModel();
    }

    public void addShape(Shape f) {
        shapesList.add(f);

        this.updateModel();
    }

    public void updateModel() {
        model.removeAllElements();

        for ( int i = 0; i < shapesList.size(); i++ ){
            model.addElement( shapesList.get(i).getName() );
        }

        draw();
    }

    public List<Shape> getShapesList() {
        return this.shapesList;
    }

    public List<Shape> getSelectedShapes() {
        List<Shape> selectedShapesList = new ArrayList<>();
        int[] indexes = shapesListJ.getSelectedIndices();

        for ( int index : indexes){
            selectedShapesList.add( shapesList.get(index) );
        }

        return selectedShapesList;
    }

    private JScrollPane getShapesListScrollPane() {
        for ( int i = 0; i < shapesList.size(); i++ ){
            model.addElement( shapesList.get(i).getName() );
        }

        shapesListJ = new JList<>(model);

        JScrollPane scrollPane = new JScrollPane(shapesListJ);
        scrollPane.setPreferredSize(new Dimension(150,400));

        return scrollPane;
    }

    public JPanel getShapesPanel() {
        shapesPanel.setLayout(new BoxLayout(shapesPanel, BoxLayout.Y_AXIS));

        shapesPanel.add(this.getShapesListScrollPane());

        JButton removeButton = new JButton("Удалить");
        removeButton.addActionListener(new ButtonListener() {
            public void actionPerformed(ActionEvent e) {
                int[] indexes = shapesListJ.getSelectedIndices();

                onRemoveClick(indexes);
            }
        });

        shapesPanel.add(removeButton);

        return shapesPanel;
    }

    private void onRemoveClick(int[] indexes) {
        for (int index = indexes.length - 1; index >= 0; index--) {
            shapesList.remove(indexes[index]);
        }

        updateModel();
    }
}
