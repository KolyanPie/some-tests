package src;

import javax.swing.*;
import java.awt.*;

public class ProjectFrame extends JFrame {

    private JPanel panel = new JPanel();

    public ProjectFrame() throws HeadlessException {
        super("Своет департатеф");
        setIconImage(new ImageIcon("res/icon.png").getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(new MainPanel());
        setVisible(true);
        setSize(new Dimension(609 + getInsets().left + getInsets().right, 678 + getInsets().top + getInsets().bottom));
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
