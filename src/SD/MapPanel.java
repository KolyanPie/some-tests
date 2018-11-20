package SD;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private int width;
    private int height;
    private int houses;
    private int x1;
    private int y1;
    private int squareSize;

    MapPanel() {
        width = 10;
        height = 10;
        squareSize = Math.min(WIDTH / width, HEIGHT / height);
        x1 = (WIDTH - width * squareSize) / 2;
        y1 = (HEIGHT - height * squareSize) / 2;
        addMouseListener(new MapMouseListener(this, width, height, x1, y1, squareSize));
    }

    void generateMap(int width, int height, int houses) {
        this.width = width;
        this.height = height;
        this.houses = houses;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawField(g);
    }

    private void drawField(Graphics g) {
        byte[][] arr = new byte[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                arr[i][j] = (byte) (Math.random() * 2);
            }
        }
        squareSize = Math.min(WIDTH / width, HEIGHT / height);
        x1 = (WIDTH - width * squareSize) / 2;
        y1 = (HEIGHT - height * squareSize) / 2;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                switch (arr[i][j]) {
                    case 0:
                        g.fillRect(x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize);
                        break;
                    case 1:
                        g.drawRect(x1 + i * squareSize, y1 + j * squareSize, squareSize, squareSize);
                        break;
                }
            }
        }
    }
}
