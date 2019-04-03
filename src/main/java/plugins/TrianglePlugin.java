package plugins;

import pluginLoader.Plugin;

import javax.swing.*;

public class TrianglePlugin implements Plugin {

    public void load(JPanel panel) {
        panel.add(new TriangleDrawTool().getButton());
    }
}
