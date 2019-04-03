import binSerialization.BinSerialization;
import drawTools.*;
import pluginLoader.Plugin;
import shapes.Circle;
import shapes.Point;
import shapesDrawer.ShapesDrawer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends JPanel {

    private static ShapesDrawer shapesDrawer = ShapesDrawer.getInstance();

    private Main() {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        shapesDrawer.setPreferredSize(new Dimension(600,400));
        add(shapesDrawer, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setPreferredSize(new Dimension(150,400));
        initButtons(buttonsPanel);
        add(buttonsPanel, BorderLayout.WEST);

        // EditorPane
        add(shapesDrawer.getShapesPanel(), BorderLayout.WEST);

        loadPlugins(buttonsPanel);

        drawStaticShapes();
    }

    private void initButtons(JPanel panel) {
        panel.add(new LineDrawTool().getButton());
        panel.add(new RectangleDrawTool().getButton());
        panel.add(new SquareDrawTool().getButton());
        panel.add(new EllipseDrawTool().getButton());
        panel.add(new CircleDrawTool().getButton());
        panel.add(new PolygonDrawTool().getButton());
        panel.add(new PolylineDrawTool().getButton());

        panel.add(shapesDrawer.getUndoButton());
    }

    // Lab1 + Lab2
    private void drawStaticShapes() {
        Circle circle = new Circle(new Point(50, 20), 10);

        StaticDrawTool tool = new StaticDrawTool();
        tool.draw(circle);

        BinSerialization BS = new BinSerialization();

        try {
            BS.saveObject(circle, "circle.bin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("PTOOP");
        mainFrame.setMinimumSize(new Dimension(900, 400));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setContentPane(new Main());
        mainFrame.setVisible(true);
        mainFrame.pack();
    }

    private static void loadPlugins(JPanel panel) {
        Path path = Paths.get("target/classes/plugins");

        String modulePath = path.toString();

        File dir = new File(modulePath);
        String[] modules = dir.list();

        for (String module: modules) {
            try {
                URL url = dir.toURI().toURL();
                URL[] urls = new URL[]{url};
                ClassLoader cl = new URLClassLoader(urls);

                String moduleName = module.split(".class")[0];

                boolean isPluginLoader = moduleName.contains("Plugin");
                boolean isTempClass = moduleName.contains("$");

                if (isPluginLoader && !isTempClass) {
                    Class<?> cls = cl.loadClass("plugins." + moduleName);

                    Plugin execute = (Plugin) cls.newInstance();

                    execute.load(panel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
