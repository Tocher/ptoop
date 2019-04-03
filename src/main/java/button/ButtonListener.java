package button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ButtonListener implements ActionListener {
    protected ButtonListener() {}

    public abstract void actionPerformed(ActionEvent e);
}