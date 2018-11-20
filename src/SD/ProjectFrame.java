package SD;

import javax.swing.*;
import java.awt.*;

class ProjectFrame extends JFrame {

    ProjectFrame() throws HeadlessException {
        super("Своет департатеф");
        setIconImage(new ImageIcon("res/icon.png").getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(new MainPanel());
        setVisible(true);
        setSize(new Dimension(609 + getInsets().left + getInsets().right,
                678 + getInsets().top + getInsets().bottom));
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
